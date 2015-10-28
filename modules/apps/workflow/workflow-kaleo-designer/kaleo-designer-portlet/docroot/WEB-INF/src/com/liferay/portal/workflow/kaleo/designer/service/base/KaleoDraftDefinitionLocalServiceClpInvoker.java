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

package com.liferay.portal.workflow.kaleo.designer.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
@ProviderType
public class KaleoDraftDefinitionLocalServiceClpInvoker {
	public KaleoDraftDefinitionLocalServiceClpInvoker() {
		_methodName0 = "addKaleoDraftDefinition";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
			};

		_methodName1 = "createKaleoDraftDefinition";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoDraftDefinition";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoDraftDefinition";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchKaleoDraftDefinition";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getKaleoDraftDefinition";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName14 = "deletePersistedModel";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.model.PersistedModel"
			};

		_methodName15 = "getPersistedModel";

		_methodParameterTypes15 = new String[] { "java.io.Serializable" };

		_methodName16 = "getKaleoDraftDefinitions";

		_methodParameterTypes16 = new String[] { "int", "int" };

		_methodName17 = "getKaleoDraftDefinitionsCount";

		_methodParameterTypes17 = new String[] {  };

		_methodName18 = "updateKaleoDraftDefinition";

		_methodParameterTypes18 = new String[] {
				"com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
			};

		_methodName43 = "getBeanIdentifier";

		_methodParameterTypes43 = new String[] {  };

		_methodName44 = "setBeanIdentifier";

		_methodParameterTypes44 = new String[] { "java.lang.String" };

		_methodName49 = "addKaleoDraftDefinition";

		_methodParameterTypes49 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map",
				"java.lang.String", "int", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName50 = "deleteKaleoDraftDefinition";

		_methodParameterTypes50 = new String[] {
				"com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
			};

		_methodName51 = "deleteKaleoDraftDefinition";

		_methodParameterTypes51 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName52 = "deleteKaleoDraftDefinitions";

		_methodParameterTypes52 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName53 = "getKaleoDraftDefinition";

		_methodParameterTypes53 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName54 = "getKaleoDraftDefinitions";

		_methodParameterTypes54 = new String[] {
				"java.lang.String", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName55 = "getKaleoDraftDefinitionsCount";

		_methodParameterTypes55 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName56 = "getLatestKaleoDraftDefinition";

		_methodParameterTypes56 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName57 = "getLatestKaleoDraftDefinitions";

		_methodParameterTypes57 = new String[] {
				"long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName58 = "getLatestKaleoDraftDefinitionsCount";

		_methodParameterTypes58 = new String[] { "long", "int" };

		_methodName59 = "incrementKaleoDraftDefinitionDraftVersion";

		_methodParameterTypes59 = new String[] {
				"long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName60 = "publishKaleoDraftDefinition";

		_methodParameterTypes60 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName61 = "updateKaleoDraftDefinition";

		_methodParameterTypes61 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"int", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.addKaleoDraftDefinition((com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.createKaleoDraftDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinition((com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.fetchKaleoDraftDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.deletePersistedModel((com.liferay.portal.model.PersistedModel)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinitions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinitionsCount();
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.updateKaleoDraftDefinition((com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition)arguments[0]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			KaleoDraftDefinitionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.addKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinition((com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition)arguments[0]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			KaleoDraftDefinitionLocalServiceUtil.deleteKaleoDraftDefinitions((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinitions((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinitionsCount((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinitions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinitionsCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.incrementKaleoDraftDefinitionDraftVersion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.publishKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return KaleoDraftDefinitionLocalServiceUtil.updateKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
}