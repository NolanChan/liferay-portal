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

package com.liferay.portal.kernel.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Spasic
 */
public class MethodParameter {

	public MethodParameter(String name, String signatures, Class<?> type) {
		_name = name;
		_signatures = signatures;
		_type = type;
	}

	public Class<?>[] getGenericTypes() throws ClassNotFoundException {
		if (_initialized) {
			return _genericTypes;
		}

		String[] genericSignatures = _extractTopLevelGenericSignatures(
			_signatures);

		if (genericSignatures == null) {
			_genericTypes = null;
		}
		else {
			_genericTypes = _loadGenericTypes(genericSignatures);
		}

		_initialized = true;

		return _genericTypes;
	}

	public String getName() {
		return _name;
	}

	public String getSignature() {
		return _signatures;
	}

	public Class<?> getType() {
		return _type;
	}

	private static String[] _extractTopLevelGenericSignatures(
		String signature) {

		if (signature == null) {
			return null;
		}

		int leftBracketIndex = signature.indexOf(CharPool.LESS_THAN);

		if (leftBracketIndex == -1) {
			return null;
		}

		int rightBracketIndex = signature.lastIndexOf(CharPool.GREATER_THAN);

		if (rightBracketIndex == -1) {
			return null;
		}

		String generics = signature.substring(
			leftBracketIndex + 1, rightBracketIndex);

		StringBundler sb = new StringBundler(generics.length());

		List<String> list = new ArrayList<String>();

		int level = 0;

		for (char c : generics.toCharArray()) {
			if (c == CharPool.LESS_THAN) {
				level++;
			}
			else if (c == CharPool.GREATER_THAN) {
				level--;
			}
			else if (level == 0) {
				sb.append(c);

				if (c == CharPool.SEMICOLON) {
					String extractedTopLevelGenericName = _getGenericName(
						sb.toString());

					list.add(extractedTopLevelGenericName);

					sb.setIndex(0);
				}
			}
		}

		if (sb.length() > 0) {
			String extractedTopLevelGenericName = _getGenericName(
				sb.toString());

			list.add(extractedTopLevelGenericName);
		}

		int nullCount = 0;

		for (String genericType : list) {
			if (genericType != null) {
				break;
			}

			nullCount++;
		}

		if (nullCount == list.size()) {
			return null;
		}

		return list.toArray(new String[list.size()]);
	}

	private static String _getClassName(String signature) {
		String className = signature;

		char c = signature.charAt(0);

		if ((c == 'B') || (c == 'C') || (c == 'D') || (c == 'F') ||
			(c == 'I') || (c == 'J') || (c == 'S') || (c == 'V') ||
			(c == 'Z')) {

			if (signature.length() != 1) {
				throw new IllegalArgumentException("Invalid: " + signature);
			}
		}
		else if (c == 'L') {
			className = className.substring(1, className.length() - 1);
			className = className.replace('/', '.');
		}
		else if (c == '[') {
			className = className.replace('/', '.');
		}
		else if (signature.equals(StringPool.STAR)) {
			className = Object.class.getName();
		}
		else {
			throw new IllegalArgumentException(
				"Invalid signature " + signature);
		}

		return className;
	}

	private static String _getGenericName(String typeName) {
		if (typeName.equals(StringPool.STAR)) {
			return null;
		}

		if (typeName.startsWith(StringPool.MINUS) ||
			typeName.startsWith(StringPool.PLUS)) {

			typeName = typeName.substring(1);
		}

		return typeName;
	}

	private static Class<?>[] _loadGenericTypes(String[] signatures)
		throws ClassNotFoundException {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		Class<?>[] types = new Class<?>[signatures.length];

		for (int i = 0; i < signatures.length; i++) {
			if (signatures[i] == null) {
				continue;
			}

			String className = _getClassName(signatures[i]);

			if (className.startsWith(StringPool.OPEN_BRACKET)) {
				try {
					types[i] = Class.forName(
						className, true, contextClassLoader);

					continue;
				}
				catch (ClassNotFoundException cnfe) {
				}
			}

			types[i] = contextClassLoader.loadClass(className);
		}

		return types;
	}

	private Class<?>[] _genericTypes;
	private boolean _initialized;
	private String _name;
	private String _signatures;
	private Class<?> _type;

}