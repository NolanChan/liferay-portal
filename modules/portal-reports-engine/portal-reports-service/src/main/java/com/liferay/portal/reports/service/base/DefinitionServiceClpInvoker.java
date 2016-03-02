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

package com.liferay.portal.reports.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.reports.service.DefinitionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class DefinitionServiceClpInvoker {
	public DefinitionServiceClpInvoker() {
		_methodName36 = "getOSGiServiceIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName41 = "addDefinition";

		_methodParameterTypes41 = new String[] {
				"long", "java.util.Map", "java.util.Map", "long",
				"java.lang.String", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName42 = "deleteDefinition";

		_methodParameterTypes42 = new String[] { "long" };

		_methodName43 = "getDefinition";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "getDefinitions";

		_methodParameterTypes44 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName45 = "getDefinitionsCount";

		_methodParameterTypes45 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName46 = "updateDefinition";

		_methodParameterTypes46 = new String[] {
				"long", "java.util.Map", "java.util.Map", "long",
				"java.lang.String", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.kernel.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return DefinitionServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return DefinitionServiceUtil.addDefinition(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return DefinitionServiceUtil.deleteDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return DefinitionServiceUtil.getDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return DefinitionServiceUtil.getDefinitions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return DefinitionServiceUtil.getDefinitionsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return DefinitionServiceUtil.updateDefinition(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
}