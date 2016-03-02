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

import com.liferay.portal.reports.service.SourceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SourceLocalServiceClpInvoker {
	public SourceLocalServiceClpInvoker() {
		_methodName0 = "addSource";

		_methodParameterTypes0 = new String[] { "com.liferay.portal.reports.model.Source" };

		_methodName1 = "createSource";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSource";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSource";

		_methodParameterTypes3 = new String[] { "com.liferay.portal.reports.model.Source" };

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

		_methodName10 = "fetchSource";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchSourceByUuidAndGroupId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "getSource";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "getActionableDynamicQuery";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes14 = new String[] {  };

		_methodName16 = "getExportActionableDynamicQuery";

		_methodParameterTypes16 = new String[] {
				"com.liferay.exportimport.kernel.lar.PortletDataContext"
			};

		_methodName17 = "deletePersistedModel";

		_methodParameterTypes17 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName18 = "getPersistedModel";

		_methodParameterTypes18 = new String[] { "java.io.Serializable" };

		_methodName19 = "getSourcesByUuidAndCompanyId";

		_methodParameterTypes19 = new String[] { "java.lang.String", "long" };

		_methodName20 = "getSourcesByUuidAndCompanyId";

		_methodParameterTypes20 = new String[] {
				"java.lang.String", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName21 = "getSourceByUuidAndGroupId";

		_methodParameterTypes21 = new String[] { "java.lang.String", "long" };

		_methodName22 = "getSources";

		_methodParameterTypes22 = new String[] { "int", "int" };

		_methodName23 = "getSourcesCount";

		_methodParameterTypes23 = new String[] {  };

		_methodName24 = "updateSource";

		_methodParameterTypes24 = new String[] {
				"com.liferay.portal.reports.model.Source"
			};

		_methodName51 = "getOSGiServiceIdentifier";

		_methodParameterTypes51 = new String[] {  };

		_methodName56 = "addSource";

		_methodParameterTypes56 = new String[] {
				"long", "long", "java.util.Map", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName57 = "deleteSource";

		_methodParameterTypes57 = new String[] { "long" };

		_methodName58 = "deleteSource";

		_methodParameterTypes58 = new String[] {
				"com.liferay.portal.reports.model.Source"
			};

		_methodName59 = "deleteSources";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "getSource";

		_methodParameterTypes60 = new String[] { "long" };

		_methodName61 = "getSources";

		_methodParameterTypes61 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName62 = "getSourcesCount";

		_methodParameterTypes62 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean"
			};

		_methodName63 = "updateSource";

		_methodParameterTypes63 = new String[] {
				"long", "java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.kernel.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SourceLocalServiceUtil.addSource((com.liferay.portal.reports.model.Source)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SourceLocalServiceUtil.createSource(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SourceLocalServiceUtil.deleteSource(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SourceLocalServiceUtil.deleteSource((com.liferay.portal.reports.model.Source)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SourceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SourceLocalServiceUtil.fetchSource(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SourceLocalServiceUtil.fetchSourceByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SourceLocalServiceUtil.getSource(((Long)arguments[0]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SourceLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SourceLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return SourceLocalServiceUtil.getExportActionableDynamicQuery((com.liferay.exportimport.kernel.lar.PortletDataContext)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return SourceLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return SourceLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return SourceLocalServiceUtil.getSourcesByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			return SourceLocalServiceUtil.getSourcesByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.reports.model.Source>)arguments[4]);
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			return SourceLocalServiceUtil.getSourceByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			return SourceLocalServiceUtil.getSources(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			return SourceLocalServiceUtil.getSourcesCount();
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return SourceLocalServiceUtil.updateSource((com.liferay.portal.reports.model.Source)arguments[0]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return SourceLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return SourceLocalServiceUtil.addSource(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return SourceLocalServiceUtil.deleteSource(((Long)arguments[0]).longValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return SourceLocalServiceUtil.deleteSource((com.liferay.portal.reports.model.Source)arguments[0]);
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			SourceLocalServiceUtil.deleteSources(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return SourceLocalServiceUtil.getSource(((Long)arguments[0]).longValue());
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return SourceLocalServiceUtil.getSources(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SourceLocalServiceUtil.getSourcesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return SourceLocalServiceUtil.updateSource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[6]);
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
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName51;
	private String[] _methodParameterTypes51;
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
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
}