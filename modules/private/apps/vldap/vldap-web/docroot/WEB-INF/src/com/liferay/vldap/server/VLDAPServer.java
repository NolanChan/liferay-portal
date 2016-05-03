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

package com.liferay.vldap.server;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.vldap.server.handler.util.LdapSslContextFactory;
import com.liferay.vldap.util.PortletPropsValues;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.directory.api.ldap.codec.protocol.mina.LdapProtocolCodecFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class VLDAPServer {

	public void destroy() {
		destroyIoAcceptor();
	}

	public void init() throws IOException {
		initIoAcceptor();
	}

	protected void destroyIoAcceptor() {
		if (_nioSocketAcceptor != null) {
			Map<Long, IoSession> managedSessions =
				_nioSocketAcceptor.getManagedSessions();

			for (IoSession ioSession : managedSessions.values()) {
				ioSession.close(true);
			}

			_nioSocketAcceptor.unbind();
			_nioSocketAcceptor.dispose();
		}
	}

	protected void initCodec() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_nioSocketAcceptor.getFilterChain();

		ProtocolCodecFactory protocolCodecFactory =
			new LdapProtocolCodecFactory();

		IoFilterAdapter ioFilterAdapter = new ProtocolCodecFilter(
			protocolCodecFactory);

		defaultIoFilterChainBuilder.addLast("codec", ioFilterAdapter);
	}

	protected void initIoAcceptor() throws IOException {
		_nioSocketAcceptor = new NioSocketAcceptor();

		_nioSocketAcceptor.setReuseAddress(true);

		if (PortletPropsValues.LDAPS_SSL_REQUIRED) {
			initSslFilter();
		}

		initIoHandler();
		initCodec();
		initLogging();

		SocketAddress socketAddress = new InetSocketAddress(
			PortletPropsValues.BIND_PORT);

		_nioSocketAcceptor.bind(socketAddress);
	}

	protected void initIoHandler() {
		DispatchIoHandler dispatchIoHandler = new DispatchIoHandler();

		_nioSocketAcceptor.setHandler(dispatchIoHandler);
	}

	protected void initLogging() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_nioSocketAcceptor.getFilterChain();

		if (_log.isDebugEnabled()) {
			defaultIoFilterChainBuilder.addLast("logger", new LoggingFilter());
		}
	}

	protected void initSslFilter() {
		DefaultIoFilterChainBuilder defaultIoFilterChainBuilder =
			_nioSocketAcceptor.getFilterChain();

		SSLContext sslContext = LdapSslContextFactory.getSSLContext(true);

		SslFilter sslFilter = new SslFilter(sslContext);

		defaultIoFilterChainBuilder.addFirst("sslFilter", sslFilter);
	}

	private static final Log _log = LogFactoryUtil.getLog(VLDAPServer.class);

	private NioSocketAcceptor _nioSocketAcceptor;

}