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

package com.liferay.portlet.ratings.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;
import com.liferay.portlet.ratings.model.RatingsStats;
import com.liferay.portlet.ratings.model.RatingsStatsModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the RatingsStats service. Represents a row in the &quot;RatingsStats&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link RatingsStatsModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RatingsStatsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RatingsStatsImpl
 * @see RatingsStats
 * @see RatingsStatsModel
 * @generated
 */
@ProviderType
public class RatingsStatsModelImpl extends BaseModelImpl<RatingsStats>
	implements RatingsStatsModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a ratings stats model instance should use the {@link RatingsStats} interface instead.
	 */
	public static final String TABLE_NAME = "RatingsStats";
	public static final Object[][] TABLE_COLUMNS = {
			{ "statsId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "totalEntries", Types.INTEGER },
			{ "totalScore", Types.DOUBLE },
			{ "averageScore", Types.DOUBLE }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("statsId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("totalEntries", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("totalScore", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("averageScore", Types.DOUBLE);
	}

	public static final String TABLE_SQL_CREATE = "create table RatingsStats (statsId LONG not null primary key,companyId LONG,classNameId LONG,classPK LONG,totalEntries INTEGER,totalScore DOUBLE,averageScore DOUBLE)";
	public static final String TABLE_SQL_DROP = "drop table RatingsStats";
	public static final String ORDER_BY_JPQL = " ORDER BY ratingsStats.statsId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY RatingsStats.statsId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.ratings.model.RatingsStats"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.ratings.model.RatingsStats"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.ratings.model.RatingsStats"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long CLASSPK_COLUMN_BITMASK = 2L;
	public static final long STATSID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.ratings.model.RatingsStats"));

	public RatingsStatsModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _statsId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setStatsId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _statsId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RatingsStats.class;
	}

	@Override
	public String getModelClassName() {
		return RatingsStats.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("statsId", getStatsId());
		attributes.put("companyId", getCompanyId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("totalEntries", getTotalEntries());
		attributes.put("totalScore", getTotalScore());
		attributes.put("averageScore", getAverageScore());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long statsId = (Long)attributes.get("statsId");

		if (statsId != null) {
			setStatsId(statsId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer totalEntries = (Integer)attributes.get("totalEntries");

		if (totalEntries != null) {
			setTotalEntries(totalEntries);
		}

		Double totalScore = (Double)attributes.get("totalScore");

		if (totalScore != null) {
			setTotalScore(totalScore);
		}

		Double averageScore = (Double)attributes.get("averageScore");

		if (averageScore != null) {
			setAverageScore(averageScore);
		}
	}

	@Override
	public long getStatsId() {
		return _statsId;
	}

	@Override
	public void setStatsId(long statsId) {
		_statsId = statsId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public int getTotalEntries() {
		return _totalEntries;
	}

	@Override
	public void setTotalEntries(int totalEntries) {
		_totalEntries = totalEntries;
	}

	@Override
	public double getTotalScore() {
		return _totalScore;
	}

	@Override
	public void setTotalScore(double totalScore) {
		_totalScore = totalScore;
	}

	@Override
	public double getAverageScore() {
		return _averageScore;
	}

	@Override
	public void setAverageScore(double averageScore) {
		_averageScore = averageScore;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			RatingsStats.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RatingsStats toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (RatingsStats)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		RatingsStatsImpl ratingsStatsImpl = new RatingsStatsImpl();

		ratingsStatsImpl.setStatsId(getStatsId());
		ratingsStatsImpl.setCompanyId(getCompanyId());
		ratingsStatsImpl.setClassNameId(getClassNameId());
		ratingsStatsImpl.setClassPK(getClassPK());
		ratingsStatsImpl.setTotalEntries(getTotalEntries());
		ratingsStatsImpl.setTotalScore(getTotalScore());
		ratingsStatsImpl.setAverageScore(getAverageScore());

		ratingsStatsImpl.resetOriginalValues();

		return ratingsStatsImpl;
	}

	@Override
	public int compareTo(RatingsStats ratingsStats) {
		long primaryKey = ratingsStats.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RatingsStats)) {
			return false;
		}

		RatingsStats ratingsStats = (RatingsStats)obj;

		long primaryKey = ratingsStats.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		RatingsStatsModelImpl ratingsStatsModelImpl = this;

		ratingsStatsModelImpl._originalClassNameId = ratingsStatsModelImpl._classNameId;

		ratingsStatsModelImpl._setOriginalClassNameId = false;

		ratingsStatsModelImpl._originalClassPK = ratingsStatsModelImpl._classPK;

		ratingsStatsModelImpl._setOriginalClassPK = false;

		ratingsStatsModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<RatingsStats> toCacheModel() {
		RatingsStatsCacheModel ratingsStatsCacheModel = new RatingsStatsCacheModel();

		ratingsStatsCacheModel.statsId = getStatsId();

		ratingsStatsCacheModel.companyId = getCompanyId();

		ratingsStatsCacheModel.classNameId = getClassNameId();

		ratingsStatsCacheModel.classPK = getClassPK();

		ratingsStatsCacheModel.totalEntries = getTotalEntries();

		ratingsStatsCacheModel.totalScore = getTotalScore();

		ratingsStatsCacheModel.averageScore = getAverageScore();

		return ratingsStatsCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{statsId=");
		sb.append(getStatsId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", totalEntries=");
		sb.append(getTotalEntries());
		sb.append(", totalScore=");
		sb.append(getTotalScore());
		sb.append(", averageScore=");
		sb.append(getAverageScore());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.ratings.model.RatingsStats");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>statsId</column-name><column-value><![CDATA[");
		sb.append(getStatsId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalEntries</column-name><column-value><![CDATA[");
		sb.append(getTotalEntries());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>totalScore</column-name><column-value><![CDATA[");
		sb.append(getTotalScore());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>averageScore</column-name><column-value><![CDATA[");
		sb.append(getAverageScore());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = RatingsStats.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			RatingsStats.class
		};
	private long _statsId;
	private long _companyId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private int _totalEntries;
	private double _totalScore;
	private double _averageScore;
	private long _columnBitmask;
	private RatingsStats _escapedModel;
}