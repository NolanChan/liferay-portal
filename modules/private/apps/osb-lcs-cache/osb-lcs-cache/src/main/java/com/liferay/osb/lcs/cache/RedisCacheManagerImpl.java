/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.lcs.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author Ivica Cardic
 */
public class RedisCacheManagerImpl<T> extends AbstractCacheManagerImpl<T> {

	@Override
	public void addMessageListener(MessageListener messageListener) {
		_messageListeners.add(messageListener);
	}

	public void afterPropertiesSet() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

		jedisPoolConfig.setMaxTotal(20);

		_masterJedisPool = new JedisPool(
			jedisPoolConfig, _masterHostName, _masterHostPort);

		if ((_slaveHostName != null) && !_slaveHostName.equals("")) {
			_slaveJedisPool = new JedisPool(
				jedisPoolConfig, _slaveHostName, _slaveHostPort);
		}

		_jedisPubSub = _getJedisPubSub();
	}

	public void destroy() {
		if (_jedisPubSub != null) {
			_jedisPubSub.unsubscribe();
		}

		_masterJedisPool.destroy();

		if (_slaveJedisPool != null) {
			_slaveJedisPool.destroy();
		}
	}

	@Override
	public T get(String key) {
		Jedis jedis = getJedis();

		try {
			byte[] keyBytes = toByteArray(key);

			byte[] valueBytes = jedis.get(keyBytes);

			if (valueBytes == null) {
				return null;
			}

			T value = (T)fromByteArray(valueBytes);

			return value;
		}
		catch (JedisConnectionException jce) {
			if (jedis != null) {
				jedis.close();
			}

			throw new RuntimeException(jce);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public Set<String> getKeys() {
		return getKeys("*");
	}

	@Override
	public Set<String> getKeys(String prefix) {
		Jedis jedis = getJedis();

		try {
			Set<String> keys = new HashSet<>();

			for (String key : jedis.keys("*" + prefix + "*")) {
				key = key.substring(7, key.length());

				keys.add(key);
			}

			return keys;
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void onMessage(String channelName, String message) {
	}

	@Override
	public void put(String key, T value) {
		Jedis jedis = _masterJedisPool.getResource();

		try {
			byte[] keyBytes = toByteArray(key);

			byte[] valueBytes = toByteArray(value);

			jedis.set(keyBytes, valueBytes);

			jedis.publish("remove", key);
		}
		catch (JedisConnectionException jce) {
			if (jedis != null) {
				jedis.close();
			}

			throw new RuntimeException(jce);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void remove(String key) {
		Jedis jedis = _masterJedisPool.getResource();

		try {
			byte[] keyBytes = toByteArray(key);

			jedis.del(keyBytes);

			jedis.publish("remove", key);
		}
		catch (JedisConnectionException jce) {
			if (jedis != null) {
				jedis.close();
			}

			throw new RuntimeException(jce);
		}
		finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void setMasterHostName(String masterHostName) {
		_masterHostName = masterHostName;
	}

	@Override
	public void setMasterHostPort(int masterHostPort) {
		_masterHostPort = masterHostPort;
	}

	@Override
	public void setSlaveHostName(String slaveHostName) {
		_slaveHostName = slaveHostName;
	}

	@Override
	public void setSlaveHostPort(int slaveHostPort) {
		_slaveHostPort = slaveHostPort;
	}

	protected Object fromByteArray(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			bytes);

		ObjectInput objectInput = null;

		try {
			objectInput = new ObjectInputStream(byteArrayInputStream);

			return objectInput.readObject();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				byteArrayInputStream.close();

				if (objectInput != null) {
					objectInput.close();
				}
			}
			catch (IOException ioe) {
			}
		}
	}

	protected Jedis getJedis() {
		Jedis jedis = null;

		try {
			jedis = _masterJedisPool.getResource();
		}
		catch (JedisConnectionException jce) {
			if (_slaveJedisPool != null) {
				_logger.error(jce.getMessage());

				jedis = _slaveJedisPool.getResource();
			}
			else {
				throw jce;
			}
		}

		return jedis;
	}

	protected byte[] toByteArray(Object object) {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		ObjectOutput objectOutput = null;

		try {
			objectOutput = new ObjectOutputStream(byteArrayOutputStream);

			objectOutput.writeObject(object);

			return byteArrayOutputStream.toByteArray();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		finally {
			try {
				byteArrayOutputStream.close();

				if (objectOutput != null) {
					objectOutput.close();
				}
			}
			catch (IOException ioe) {
			}
		}
	}

	private JedisPubSub _getJedisPubSub() {
		final JedisPubSub jedisPubSub = new JedisPubSub() {

			@Override
			public void onMessage(String channel, String message) {
				for (MessageListener messageListener : _messageListeners) {
					messageListener.onMessage(channel, message);
				}
			}

			@Override
			public void onPMessage(
				String pattern, String channel, String message) {
			}

			@Override
			public void onPSubscribe(String pattern, int subscribedChannels) {
			}

			@Override
			public void onPUnsubscribe(String pattern, int subscribedChannels) {
			}

			@Override
			public void onSubscribe(String channel, int subscribedChannels) {
			}

			@Override
			public void onUnsubscribe(String channel, int subscribedChannels) {
			}

		};

		new Thread(
			new Runnable() {

				@Override
				public void run() {
					Jedis jedis = _masterJedisPool.getResource();

					try {
						jedis.subscribe(jedisPubSub, "remove");
					}
					catch (Exception e) {
						if (jedis != null) {
							jedis.close();
						}
					}
					finally {
						if (jedis != null) {
							try {
								jedis.close();
							}
							catch (Exception e) {
							}
						}
					}
				}

			}).start();

		return jedisPubSub;
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		RedisCacheManagerImpl.class);

	private JedisPubSub _jedisPubSub;
	private String _masterHostName;
	private int _masterHostPort;
	private JedisPool _masterJedisPool;
	private final List<MessageListener> _messageListeners = new ArrayList<>();
	private String _slaveHostName;
	private int _slaveHostPort;
	private JedisPool _slaveJedisPool;

}