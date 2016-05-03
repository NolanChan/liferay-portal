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

package com.liferay.saml.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.saml.model.SamlIdpSpConnectionClp;
import com.liferay.saml.model.SamlIdpSpSessionClp;
import com.liferay.saml.model.SamlIdpSsoSessionClp;
import com.liferay.saml.model.SamlSpAuthRequestClp;
import com.liferay.saml.model.SamlSpIdpConnectionClp;
import com.liferay.saml.model.SamlSpMessageClp;
import com.liferay.saml.model.SamlSpSessionClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mika Koivisto
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
						"saml-portlet-deployment-context");

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
							"saml-portlet-deployment-context");

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
				_servletContextName = "saml-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(SamlIdpSpConnectionClp.class.getName())) {
			return translateInputSamlIdpSpConnection(oldModel);
		}

		if (oldModelClassName.equals(SamlIdpSpSessionClp.class.getName())) {
			return translateInputSamlIdpSpSession(oldModel);
		}

		if (oldModelClassName.equals(SamlIdpSsoSessionClp.class.getName())) {
			return translateInputSamlIdpSsoSession(oldModel);
		}

		if (oldModelClassName.equals(SamlSpAuthRequestClp.class.getName())) {
			return translateInputSamlSpAuthRequest(oldModel);
		}

		if (oldModelClassName.equals(SamlSpIdpConnectionClp.class.getName())) {
			return translateInputSamlSpIdpConnection(oldModel);
		}

		if (oldModelClassName.equals(SamlSpMessageClp.class.getName())) {
			return translateInputSamlSpMessage(oldModel);
		}

		if (oldModelClassName.equals(SamlSpSessionClp.class.getName())) {
			return translateInputSamlSpSession(oldModel);
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

	public static Object translateInputSamlIdpSpConnection(
		BaseModel<?> oldModel) {
		SamlIdpSpConnectionClp oldClpModel = (SamlIdpSpConnectionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlIdpSpConnectionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlIdpSpSession(BaseModel<?> oldModel) {
		SamlIdpSpSessionClp oldClpModel = (SamlIdpSpSessionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlIdpSpSessionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlIdpSsoSession(BaseModel<?> oldModel) {
		SamlIdpSsoSessionClp oldClpModel = (SamlIdpSsoSessionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlIdpSsoSessionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlSpAuthRequest(BaseModel<?> oldModel) {
		SamlSpAuthRequestClp oldClpModel = (SamlSpAuthRequestClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlSpAuthRequestRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlSpIdpConnection(
		BaseModel<?> oldModel) {
		SamlSpIdpConnectionClp oldClpModel = (SamlSpIdpConnectionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlSpIdpConnectionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlSpMessage(BaseModel<?> oldModel) {
		SamlSpMessageClp oldClpModel = (SamlSpMessageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlSpMessageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSamlSpSession(BaseModel<?> oldModel) {
		SamlSpSessionClp oldClpModel = (SamlSpSessionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSamlSpSessionRemoteModel();

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
					"com.liferay.saml.model.impl.SamlIdpSpConnectionImpl")) {
			return translateOutputSamlIdpSpConnection(oldModel);
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
					"com.liferay.saml.model.impl.SamlIdpSpSessionImpl")) {
			return translateOutputSamlIdpSpSession(oldModel);
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
					"com.liferay.saml.model.impl.SamlIdpSsoSessionImpl")) {
			return translateOutputSamlIdpSsoSession(oldModel);
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
					"com.liferay.saml.model.impl.SamlSpAuthRequestImpl")) {
			return translateOutputSamlSpAuthRequest(oldModel);
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
					"com.liferay.saml.model.impl.SamlSpIdpConnectionImpl")) {
			return translateOutputSamlSpIdpConnection(oldModel);
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
					"com.liferay.saml.model.impl.SamlSpMessageImpl")) {
			return translateOutputSamlSpMessage(oldModel);
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
					"com.liferay.saml.model.impl.SamlSpSessionImpl")) {
			return translateOutputSamlSpSession(oldModel);
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

		if (className.equals("com.liferay.saml.exception.AssertionException")) {
			return new com.liferay.saml.exception.AssertionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.AudienceException")) {
			return new com.liferay.saml.exception.AudienceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.CertificateKeyPasswordException")) {
			return new com.liferay.saml.exception.CertificateKeyPasswordException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.CredentialException")) {
			return new com.liferay.saml.exception.CredentialException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.DestinationException")) {
			return new com.liferay.saml.exception.DestinationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.DuplicateSamlIdpSpConnectionSamlSpEntityIdException")) {
			return new com.liferay.saml.exception.DuplicateSamlIdpSpConnectionSamlSpEntityIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.DuplicateSamlIdpSpSessionException")) {
			return new com.liferay.saml.exception.DuplicateSamlIdpSpSessionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.DuplicateSamlIdpSsoSessionException")) {
			return new com.liferay.saml.exception.DuplicateSamlIdpSsoSessionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.DuplicateSamlSpIdpConnectionSamlIdpEntityIdException")) {
			return new com.liferay.saml.exception.DuplicateSamlSpIdpConnectionSamlIdpEntityIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.EntityIdException")) {
			return new com.liferay.saml.exception.EntityIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.ExpiredException")) {
			return new com.liferay.saml.exception.ExpiredException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.InResponseToException")) {
			return new com.liferay.saml.exception.InResponseToException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.IssuerException")) {
			return new com.liferay.saml.exception.IssuerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.ReplayException")) {
			return new com.liferay.saml.exception.ReplayException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlIdpSpConnectionMetadataUrlException")) {
			return new com.liferay.saml.exception.SamlIdpSpConnectionMetadataUrlException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlIdpSpConnectionMetadataXmlException")) {
			return new com.liferay.saml.exception.SamlIdpSpConnectionMetadataXmlException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlIdpSpConnectionNameException")) {
			return new com.liferay.saml.exception.SamlIdpSpConnectionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlIdpSpConnectionSamlSpEntityIdException")) {
			return new com.liferay.saml.exception.SamlIdpSpConnectionSamlSpEntityIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlSpIdpConnectionMetadataUrlException")) {
			return new com.liferay.saml.exception.SamlSpIdpConnectionMetadataUrlException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlSpIdpConnectionMetadataXmlException")) {
			return new com.liferay.saml.exception.SamlSpIdpConnectionMetadataXmlException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlSpIdpConnectionNameException")) {
			return new com.liferay.saml.exception.SamlSpIdpConnectionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.SamlSpIdpConnectionSamlIdpEntityIdException")) {
			return new com.liferay.saml.exception.SamlSpIdpConnectionSamlIdpEntityIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.SignatureException")) {
			return new com.liferay.saml.exception.SignatureException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.StatusException")) {
			return new com.liferay.saml.exception.StatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.saml.exception.SubjectException")) {
			return new com.liferay.saml.exception.SubjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.UnsolicitedLogoutResponseException")) {
			return new com.liferay.saml.exception.UnsolicitedLogoutResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.UnsupportedBindingException")) {
			return new com.liferay.saml.exception.UnsupportedBindingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchIdpSpConnectionException")) {
			return new com.liferay.saml.exception.NoSuchIdpSpConnectionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchIdpSpSessionException")) {
			return new com.liferay.saml.exception.NoSuchIdpSpSessionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchIdpSsoSessionException")) {
			return new com.liferay.saml.exception.NoSuchIdpSsoSessionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchSpAuthRequestException")) {
			return new com.liferay.saml.exception.NoSuchSpAuthRequestException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchSpIdpConnectionException")) {
			return new com.liferay.saml.exception.NoSuchSpIdpConnectionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchSpMessageException")) {
			return new com.liferay.saml.exception.NoSuchSpMessageException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.saml.exception.NoSuchSpSessionException")) {
			return new com.liferay.saml.exception.NoSuchSpSessionException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputSamlIdpSpConnection(
		BaseModel<?> oldModel) {
		SamlIdpSpConnectionClp newModel = new SamlIdpSpConnectionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlIdpSpConnectionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlIdpSpSession(BaseModel<?> oldModel) {
		SamlIdpSpSessionClp newModel = new SamlIdpSpSessionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlIdpSpSessionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlIdpSsoSession(BaseModel<?> oldModel) {
		SamlIdpSsoSessionClp newModel = new SamlIdpSsoSessionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlIdpSsoSessionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlSpAuthRequest(BaseModel<?> oldModel) {
		SamlSpAuthRequestClp newModel = new SamlSpAuthRequestClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlSpAuthRequestRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlSpIdpConnection(
		BaseModel<?> oldModel) {
		SamlSpIdpConnectionClp newModel = new SamlSpIdpConnectionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlSpIdpConnectionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlSpMessage(BaseModel<?> oldModel) {
		SamlSpMessageClp newModel = new SamlSpMessageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlSpMessageRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSamlSpSession(BaseModel<?> oldModel) {
		SamlSpSessionClp newModel = new SamlSpSessionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSamlSpSessionRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}