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

package com.liferay.portlet.asset.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;

/**
 * Provides the HTTP utility for the
 * {@link com.liferay.portlet.asset.service.AssetCategoryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link com.liferay.portal.security.auth.HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetCategoryServiceSoap
 * @see com.liferay.portal.security.auth.HttpPrincipal
 * @see com.liferay.portlet.asset.service.AssetCategoryServiceUtil
 * @generated
 */
@ProviderType
public class AssetCategoryServiceHttp {
	public static com.liferay.portlet.asset.model.AssetCategory addCategory(
		HttpPrincipal httpPrincipal, long parentCategoryId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long vocabularyId, java.lang.String[] categoryProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"addCategory", _addCategoryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCategoryId, titleMap, descriptionMap, vocabularyId,
					categoryProperties, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategory addCategory(
		HttpPrincipal httpPrincipal, java.lang.String title, long vocabularyId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"addCategory", _addCategoryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, title,
					vocabularyId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCategories(HttpPrincipal httpPrincipal,
		long[] categoryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"deleteCategories", _deleteCategoriesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryIds);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> deleteCategories(
		HttpPrincipal httpPrincipal, long[] categoryIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"deleteCategories", _deleteCategoriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryIds, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCategory(HttpPrincipal httpPrincipal,
		long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"deleteCategory", _deleteCategoryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategory fetchCategory(
		HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"fetchCategory", _fetchCategoryParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getCategories(
		HttpPrincipal httpPrincipal, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getCategories", _getCategoriesParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategory getCategory(
		HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getCategory", _getCategoryParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.lang.String getCategoryPath(
		HttpPrincipal httpPrincipal, long categoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getCategoryPath", _getCategoryPathParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.lang.String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getChildCategories(
		HttpPrincipal httpPrincipal, long parentCategoryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getChildCategories", _getChildCategoriesParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCategoryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getChildCategories(
		HttpPrincipal httpPrincipal, long parentCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getChildCategories", _getChildCategoriesParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCategoryId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getJSONSearch(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getJSONSearch", _getJSONSearchParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, vocabularyIds, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getJSONVocabularyCategories(
		HttpPrincipal httpPrincipal, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getJSONVocabularyCategories",
					_getJSONVocabularyCategoriesParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONObject getJSONVocabularyCategories(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getJSONVocabularyCategories",
					_getJSONVocabularyCategoriesParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONObject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyCategories(
		HttpPrincipal httpPrincipal, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategories",
					_getVocabularyCategoriesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyCategories(
		HttpPrincipal httpPrincipal, long parentCategoryId, long vocabularyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategories",
					_getVocabularyCategoriesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentCategoryId, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyCategories(
		HttpPrincipal httpPrincipal, long groupId, long parentCategoryId,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategories",
					_getVocabularyCategoriesParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentCategoryId, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyCategories(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategories",
					_getVocabularyCategoriesParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long vocabularyId) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategoriesCount",
					_getVocabularyCategoriesCountParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long parentCategory,
		long vocabularyId) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategoriesCount",
					_getVocabularyCategoriesCountParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentCategory, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getVocabularyCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		long vocabularyId) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategoriesCount",
					_getVocabularyCategoriesCountParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay getVocabularyCategoriesDisplay(
		HttpPrincipal httpPrincipal, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategoriesDisplay",
					_getVocabularyCategoriesDisplayParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay getVocabularyCategoriesDisplay(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyCategoriesDisplay",
					_getVocabularyCategoriesDisplayParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyRootCategories(
		HttpPrincipal httpPrincipal, long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyRootCategories",
					_getVocabularyRootCategoriesParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> getVocabularyRootCategories(
		HttpPrincipal httpPrincipal, long groupId, long vocabularyId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyRootCategories",
					_getVocabularyRootCategoriesParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getVocabularyRootCategoriesCount(
		HttpPrincipal httpPrincipal, long groupId, long vocabularyId) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"getVocabularyRootCategoriesCount",
					_getVocabularyRootCategoriesCountParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					vocabularyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategory moveCategory(
		HttpPrincipal httpPrincipal, long categoryId, long parentCategoryId,
		long vocabularyId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"moveCategory", _moveCategoryParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId, parentCategoryId, vocabularyId, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetCategory> search(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String keywords,
		long vocabularyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portlet.asset.model.AssetCategory> obc) {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"search", _searchParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					keywords, vocabularyId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portlet.asset.model.AssetCategory>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String name,
		java.lang.String[] categoryProperties, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"search", _searchParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					name, categoryProperties, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray search(
		HttpPrincipal httpPrincipal, long[] groupIds, java.lang.String name,
		long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"search", _searchParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					groupIds, name, vocabularyIds, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay searchCategoriesDisplay(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String title,
		long vocabularyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"searchCategoriesDisplay",
					_searchCategoriesDisplayParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					title, vocabularyId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay searchCategoriesDisplay(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String title,
		long parentCategoryId, long vocabularyId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"searchCategoriesDisplay",
					_searchCategoriesDisplayParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					title, parentCategoryId, vocabularyId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay searchCategoriesDisplay(
		HttpPrincipal httpPrincipal, long[] groupIds, java.lang.String title,
		long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"searchCategoriesDisplay",
					_searchCategoriesDisplayParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					groupIds, title, vocabularyIds, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategoryDisplay searchCategoriesDisplay(
		HttpPrincipal httpPrincipal, long[] groupIds, java.lang.String title,
		long[] parentCategoryIds, long[] vocabularyIds, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"searchCategoriesDisplay",
					_searchCategoriesDisplayParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					groupIds, title, parentCategoryIds, vocabularyIds, start,
					end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategoryDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portlet.asset.model.AssetCategory updateCategory(
		HttpPrincipal httpPrincipal, long categoryId, long parentCategoryId,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long vocabularyId, java.lang.String[] categoryProperties,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AssetCategoryServiceUtil.class,
					"updateCategory", _updateCategoryParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					categoryId, parentCategoryId, titleMap, descriptionMap,
					vocabularyId, categoryProperties, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portlet.asset.model.AssetCategory)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetCategoryServiceHttp.class);
	private static final Class<?>[] _addCategoryParameterTypes0 = new Class[] {
			long.class, java.util.Map.class, java.util.Map.class, long.class,
			java.lang.String[].class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _addCategoryParameterTypes1 = new Class[] {
			java.lang.String.class, long.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCategoriesParameterTypes2 = new Class[] {
			long[].class
		};
	private static final Class<?>[] _deleteCategoriesParameterTypes3 = new Class[] {
			long[].class, com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCategoryParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCategoryParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCategoriesParameterTypes6 = new Class[] {
			java.lang.String.class, long.class
		};
	private static final Class<?>[] _getCategoryParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCategoryPathParameterTypes8 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getChildCategoriesParameterTypes9 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getChildCategoriesParameterTypes10 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getJSONSearchParameterTypes11 = new Class[] {
			long.class, java.lang.String.class, long[].class, int.class,
			int.class
		};
	private static final Class<?>[] _getJSONVocabularyCategoriesParameterTypes12 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getJSONVocabularyCategoriesParameterTypes13 =
		new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes14 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes15 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes16 = new Class[] {
			long.class, long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesParameterTypes17 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesCountParameterTypes18 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _getVocabularyCategoriesCountParameterTypes19 =
		new Class[] { long.class, long.class, long.class };
	private static final Class<?>[] _getVocabularyCategoriesCountParameterTypes20 =
		new Class[] { long.class, java.lang.String.class, long.class };
	private static final Class<?>[] _getVocabularyCategoriesDisplayParameterTypes21 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyCategoriesDisplayParameterTypes22 =
		new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyRootCategoriesParameterTypes23 =
		new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyRootCategoriesParameterTypes24 =
		new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getVocabularyRootCategoriesCountParameterTypes25 =
		new Class[] { long.class, long.class };
	private static final Class<?>[] _moveCategoryParameterTypes26 = new Class[] {
			long.class, long.class, long.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _searchParameterTypes27 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes28 = new Class[] {
			long.class, java.lang.String.class, java.lang.String[].class,
			int.class, int.class
		};
	private static final Class<?>[] _searchParameterTypes29 = new Class[] {
			long[].class, java.lang.String.class, long[].class, int.class,
			int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes30 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes31 = new Class[] {
			long.class, java.lang.String.class, long.class, long.class,
			int.class, int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes32 = new Class[] {
			long[].class, java.lang.String.class, long[].class, int.class,
			int.class
		};
	private static final Class<?>[] _searchCategoriesDisplayParameterTypes33 = new Class[] {
			long[].class, java.lang.String.class, long[].class, long[].class,
			int.class, int.class
		};
	private static final Class<?>[] _updateCategoryParameterTypes34 = new Class[] {
			long.class, long.class, java.util.Map.class, java.util.Map.class,
			long.class, java.lang.String[].class,
			com.liferay.portal.service.ServiceContext.class
		};
}