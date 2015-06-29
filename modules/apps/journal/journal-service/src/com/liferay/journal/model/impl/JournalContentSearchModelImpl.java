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

package com.liferay.journal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.journal.model.JournalContentSearch;
import com.liferay.journal.model.JournalContentSearchModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the JournalContentSearch service. Represents a row in the &quot;JournalContentSearch&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link JournalContentSearchModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link JournalContentSearchImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JournalContentSearchImpl
 * @see JournalContentSearch
 * @see JournalContentSearchModel
 * @generated
 */
@ProviderType
public class JournalContentSearchModelImpl extends BaseModelImpl<JournalContentSearch>
	implements JournalContentSearchModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a journal content search model instance should use the {@link JournalContentSearch} interface instead.
	 */
	public static final String TABLE_NAME = "JournalContentSearch";
	public static final Object[][] TABLE_COLUMNS = {
			{ "contentSearchId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "privateLayout", Types.BOOLEAN },
			{ "layoutId", Types.BIGINT },
			{ "portletId", Types.VARCHAR },
			{ "articleId", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table JournalContentSearch (contentSearchId LONG not null primary key,groupId LONG,companyId LONG,privateLayout BOOLEAN,layoutId LONG,portletId VARCHAR(200) null,articleId VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table JournalContentSearch";
	public static final String ORDER_BY_JPQL = " ORDER BY journalContentSearch.contentSearchId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY JournalContentSearch.contentSearchId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.journal.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.journal.model.JournalContentSearch"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.journal.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.journal.model.JournalContentSearch"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.journal.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.journal.model.JournalContentSearch"),
			true);
	public static final long ARTICLEID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long LAYOUTID_COLUMN_BITMASK = 4L;
	public static final long PORTLETID_COLUMN_BITMASK = 8L;
	public static final long PRIVATELAYOUT_COLUMN_BITMASK = 16L;
	public static final long CONTENTSEARCHID_COLUMN_BITMASK = 32L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.journal.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.journal.model.JournalContentSearch"));

	public JournalContentSearchModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _contentSearchId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setContentSearchId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _contentSearchId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return JournalContentSearch.class;
	}

	@Override
	public String getModelClassName() {
		return JournalContentSearch.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("contentSearchId", getContentSearchId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("privateLayout", getPrivateLayout());
		attributes.put("layoutId", getLayoutId());
		attributes.put("portletId", getPortletId());
		attributes.put("articleId", getArticleId());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long contentSearchId = (Long)attributes.get("contentSearchId");

		if (contentSearchId != null) {
			setContentSearchId(contentSearchId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Boolean privateLayout = (Boolean)attributes.get("privateLayout");

		if (privateLayout != null) {
			setPrivateLayout(privateLayout);
		}

		Long layoutId = (Long)attributes.get("layoutId");

		if (layoutId != null) {
			setLayoutId(layoutId);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		String articleId = (String)attributes.get("articleId");

		if (articleId != null) {
			setArticleId(articleId);
		}
	}

	@Override
	public long getContentSearchId() {
		return _contentSearchId;
	}

	@Override
	public void setContentSearchId(long contentSearchId) {
		_contentSearchId = contentSearchId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
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
	public boolean getPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		_columnBitmask |= PRIVATELAYOUT_COLUMN_BITMASK;

		if (!_setOriginalPrivateLayout) {
			_setOriginalPrivateLayout = true;

			_originalPrivateLayout = _privateLayout;
		}

		_privateLayout = privateLayout;
	}

	public boolean getOriginalPrivateLayout() {
		return _originalPrivateLayout;
	}

	@Override
	public long getLayoutId() {
		return _layoutId;
	}

	@Override
	public void setLayoutId(long layoutId) {
		_columnBitmask |= LAYOUTID_COLUMN_BITMASK;

		if (!_setOriginalLayoutId) {
			_setOriginalLayoutId = true;

			_originalLayoutId = _layoutId;
		}

		_layoutId = layoutId;
	}

	public long getOriginalLayoutId() {
		return _originalLayoutId;
	}

	@Override
	public String getPortletId() {
		if (_portletId == null) {
			return StringPool.BLANK;
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		_columnBitmask |= PORTLETID_COLUMN_BITMASK;

		if (_originalPortletId == null) {
			_originalPortletId = _portletId;
		}

		_portletId = portletId;
	}

	public String getOriginalPortletId() {
		return GetterUtil.getString(_originalPortletId);
	}

	@Override
	public String getArticleId() {
		if (_articleId == null) {
			return StringPool.BLANK;
		}
		else {
			return _articleId;
		}
	}

	@Override
	public void setArticleId(String articleId) {
		_columnBitmask |= ARTICLEID_COLUMN_BITMASK;

		if (_originalArticleId == null) {
			_originalArticleId = _articleId;
		}

		_articleId = articleId;
	}

	public String getOriginalArticleId() {
		return GetterUtil.getString(_originalArticleId);
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			JournalContentSearch.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public JournalContentSearch toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (JournalContentSearch)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		JournalContentSearchImpl journalContentSearchImpl = new JournalContentSearchImpl();

		journalContentSearchImpl.setContentSearchId(getContentSearchId());
		journalContentSearchImpl.setGroupId(getGroupId());
		journalContentSearchImpl.setCompanyId(getCompanyId());
		journalContentSearchImpl.setPrivateLayout(getPrivateLayout());
		journalContentSearchImpl.setLayoutId(getLayoutId());
		journalContentSearchImpl.setPortletId(getPortletId());
		journalContentSearchImpl.setArticleId(getArticleId());

		journalContentSearchImpl.resetOriginalValues();

		return journalContentSearchImpl;
	}

	@Override
	public int compareTo(JournalContentSearch journalContentSearch) {
		long primaryKey = journalContentSearch.getPrimaryKey();

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

		if (!(obj instanceof JournalContentSearch)) {
			return false;
		}

		JournalContentSearch journalContentSearch = (JournalContentSearch)obj;

		long primaryKey = journalContentSearch.getPrimaryKey();

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
		JournalContentSearchModelImpl journalContentSearchModelImpl = this;

		journalContentSearchModelImpl._originalGroupId = journalContentSearchModelImpl._groupId;

		journalContentSearchModelImpl._setOriginalGroupId = false;

		journalContentSearchModelImpl._originalPrivateLayout = journalContentSearchModelImpl._privateLayout;

		journalContentSearchModelImpl._setOriginalPrivateLayout = false;

		journalContentSearchModelImpl._originalLayoutId = journalContentSearchModelImpl._layoutId;

		journalContentSearchModelImpl._setOriginalLayoutId = false;

		journalContentSearchModelImpl._originalPortletId = journalContentSearchModelImpl._portletId;

		journalContentSearchModelImpl._originalArticleId = journalContentSearchModelImpl._articleId;

		journalContentSearchModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<JournalContentSearch> toCacheModel() {
		JournalContentSearchCacheModel journalContentSearchCacheModel = new JournalContentSearchCacheModel();

		journalContentSearchCacheModel.contentSearchId = getContentSearchId();

		journalContentSearchCacheModel.groupId = getGroupId();

		journalContentSearchCacheModel.companyId = getCompanyId();

		journalContentSearchCacheModel.privateLayout = getPrivateLayout();

		journalContentSearchCacheModel.layoutId = getLayoutId();

		journalContentSearchCacheModel.portletId = getPortletId();

		String portletId = journalContentSearchCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			journalContentSearchCacheModel.portletId = null;
		}

		journalContentSearchCacheModel.articleId = getArticleId();

		String articleId = journalContentSearchCacheModel.articleId;

		if ((articleId != null) && (articleId.length() == 0)) {
			journalContentSearchCacheModel.articleId = null;
		}

		return journalContentSearchCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{contentSearchId=");
		sb.append(getContentSearchId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", privateLayout=");
		sb.append(getPrivateLayout());
		sb.append(", layoutId=");
		sb.append(getLayoutId());
		sb.append(", portletId=");
		sb.append(getPortletId());
		sb.append(", articleId=");
		sb.append(getArticleId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(25);

		sb.append("<model><model-name>");
		sb.append("com.liferay.journal.model.JournalContentSearch");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>contentSearchId</column-name><column-value><![CDATA[");
		sb.append(getContentSearchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>privateLayout</column-name><column-value><![CDATA[");
		sb.append(getPrivateLayout());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>layoutId</column-name><column-value><![CDATA[");
		sb.append(getLayoutId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletId</column-name><column-value><![CDATA[");
		sb.append(getPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>articleId</column-name><column-value><![CDATA[");
		sb.append(getArticleId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = JournalContentSearch.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			JournalContentSearch.class
		};
	private long _contentSearchId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private boolean _privateLayout;
	private boolean _originalPrivateLayout;
	private boolean _setOriginalPrivateLayout;
	private long _layoutId;
	private long _originalLayoutId;
	private boolean _setOriginalLayoutId;
	private String _portletId;
	private String _originalPortletId;
	private String _articleId;
	private String _originalArticleId;
	private long _columnBitmask;
	private JournalContentSearch _escapedModel;
}