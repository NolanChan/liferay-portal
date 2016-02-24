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

package com.liferay.portal.reports.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.portal.reports.model.DefinitionClp;
import com.liferay.portal.reports.model.EntryClp;
import com.liferay.portal.reports.model.SourceClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"reports-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"reports-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "reports-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(DefinitionClp.class.getName())) {
			return translateInputDefinition(oldModel);
		}

		if (oldModelClassName.equals(EntryClp.class.getName())) {
			return translateInputEntry(oldModel);
		}

		if (oldModelClassName.equals(SourceClp.class.getName())) {
			return translateInputSource(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputDefinition(BaseModel<?> oldModel) {
		DefinitionClp oldClpModel = (DefinitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDefinitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputEntry(BaseModel<?> oldModel) {
		EntryClp oldClpModel = (EntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSource(BaseModel<?> oldModel) {
		SourceClp oldClpModel = (SourceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSourceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.portal.reports.model.impl.DefinitionImpl")) {
			return translateOutputDefinition(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals("com.liferay.portal.reports.model.impl.EntryImpl")) {
			return translateOutputEntry(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.portal.reports.model.impl.SourceImpl")) {
			return translateOutputSource(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(
					"com.liferay.portal.reports.exception.DefinitionFileException")) {
			return new com.liferay.portal.reports.exception.DefinitionFileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.DefinitionNameException")) {
			return new com.liferay.portal.reports.exception.DefinitionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.EntryEmailDeliveryException")) {
			return new com.liferay.portal.reports.exception.EntryEmailDeliveryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.EntryEmailNotificationsException")) {
			return new com.liferay.portal.reports.exception.EntryEmailNotificationsException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceCharsetException")) {
			return new com.liferay.portal.reports.exception.SourceCharsetException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceColumnNamesException")) {
			return new com.liferay.portal.reports.exception.SourceColumnNamesException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceDriverClassNameException")) {
			return new com.liferay.portal.reports.exception.SourceDriverClassNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceFileException")) {
			return new com.liferay.portal.reports.exception.SourceFileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceJDBCConnectionException")) {
			return new com.liferay.portal.reports.exception.SourceJDBCConnectionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.SourceTypeException")) {
			return new com.liferay.portal.reports.exception.SourceTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.NoSuchDefinitionException")) {
			return new com.liferay.portal.reports.exception.NoSuchDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.NoSuchEntryException")) {
			return new com.liferay.portal.reports.exception.NoSuchEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.portal.reports.exception.NoSuchSourceException")) {
			return new com.liferay.portal.reports.exception.NoSuchSourceException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputDefinition(BaseModel<?> oldModel) {
		DefinitionClp newModel = new DefinitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDefinitionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputEntry(BaseModel<?> oldModel) {
		EntryClp newModel = new EntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSource(BaseModel<?> oldModel) {
		SourceClp newModel = new SourceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSourceRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}