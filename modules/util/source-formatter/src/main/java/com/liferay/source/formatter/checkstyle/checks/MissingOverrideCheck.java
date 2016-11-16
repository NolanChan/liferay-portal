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

package com.liferay.source.formatter.checkstyle.checks;

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.source.formatter.util.ThreadSafeClassLibrary;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.FullIdent;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.AbstractBaseJavaEntity;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.DefaultDocletTagFactory;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;
import com.thoughtworks.qdox.model.annotation.AnnotationValue;
import com.thoughtworks.qdox.parser.ParseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Hugo Huijser
 */
public class MissingOverrideCheck extends AbstractCheck {

	public static final String MSG_MISSING_OVERRIDE = "override.missing";

	@Override
	public int[] getDefaultTokens() {
		return new int[] {TokenTypes.PACKAGE_DEF};
	}

	@Override
	public void visitToken(DetailAST detailAST) {
		String content = _getContent();

		JavaDocBuilder javaDocBuilder = new JavaDocBuilder(
			new DefaultDocletTagFactory(), new ThreadSafeClassLibrary());

		try {
			javaDocBuilder.addSource(new UnsyncStringReader(content));
		}
		catch (ParseException pe) {
			return;
		}

		FileContents fileContents = getFileContents();

		String fileName = StringUtil.replace(
			fileContents.getFileName(), '\\', '/');

		String className = _getClassName(fileName);

		JavaClass javaClass = javaDocBuilder.getClassByName(
			_getPackagePath(detailAST) + "." + className);

		List<Tuple> ancestorJavaClassTuples = new ArrayList<>();

		ancestorJavaClassTuples = _addAncestorJavaClassTuples(
			javaClass, ancestorJavaClassTuples);

		for (JavaMethod javaMethod : javaClass.getMethods()) {
			if (!_hasAnnotation(javaMethod, "Override")) {
				if (_isOverrideMethod(
						javaClass, javaMethod, ancestorJavaClassTuples)) {

					log(javaMethod.getLineNumber(), MSG_MISSING_OVERRIDE);
				}
			}
		}
	}

	private List<Tuple> _addAncestorJavaClassTuples(
		JavaClass javaClass, List<Tuple> ancestorJavaClassTuples) {

		JavaClass superJavaClass = javaClass.getSuperJavaClass();

		if (superJavaClass != null) {
			ancestorJavaClassTuples.add(new Tuple(superJavaClass));

			ancestorJavaClassTuples = _addAncestorJavaClassTuples(
				superJavaClass, ancestorJavaClassTuples);
		}

		Type[] implementz = javaClass.getImplements();

		for (Type implement : implementz) {
			Type[] actualTypeArguments = implement.getActualTypeArguments();
			JavaClass implementedInterface = implement.getJavaClass();

			if (actualTypeArguments == null) {
				ancestorJavaClassTuples.add(new Tuple(implementedInterface));
			}
			else {
				ancestorJavaClassTuples.add(
					new Tuple(implementedInterface, actualTypeArguments));
			}

			ancestorJavaClassTuples = _addAncestorJavaClassTuples(
				implementedInterface, ancestorJavaClassTuples);
		}

		return ancestorJavaClassTuples;
	}

	private String _getClassName(String fileName) {
		int pos = fileName.lastIndexOf('/');

		return fileName.substring(pos + 1, fileName.length() - 5);
	}

	private String _getContent() {
		FileContents fileContents = getFileContents();

		FileText fileText = fileContents.getText();

		return (String)fileText.getFullText();
	}

	private String _getPackagePath(DetailAST packageDefAST) {
		DetailAST dotAST = packageDefAST.findFirstToken(TokenTypes.DOT);

		FullIdent fullIdent = FullIdent.createFullIdent(dotAST);

		return fullIdent.getText();
	}

	private boolean _hasAnnotation(
		AbstractBaseJavaEntity abstractBaseJavaEntity, String annotationName) {

		Annotation[] annotations = abstractBaseJavaEntity.getAnnotations();

		if (annotations == null) {
			return false;
		}

		for (int i = 0; i < annotations.length; i++) {
			Type type = annotations[i].getType();

			JavaClass javaClass = type.getJavaClass();

			if (annotationName.equals(javaClass.getName())) {
				return true;
			}
		}

		return false;
	}

	private boolean _isOverrideMethod(
		JavaClass javaClass, JavaMethod javaMethod,
		Collection<Tuple> ancestorJavaClassTuples) {

		if (javaMethod.isConstructor() || javaMethod.isPrivate() ||
			javaMethod.isStatic() ||
			_overridesHigherJavaAPIVersion(javaMethod)) {

			return false;
		}

		String methodName = javaMethod.getName();

		JavaParameter[] javaParameters = javaMethod.getParameters();

		Type[] types = new Type[javaParameters.length];

		for (int i = 0; i < javaParameters.length; i++) {
			types[i] = javaParameters[i].getType();
		}

		// Check for matching method in each ancestor

		for (Tuple ancestorJavaClassTuple : ancestorJavaClassTuples) {
			JavaClass ancestorJavaClass =
				(JavaClass)ancestorJavaClassTuple.getObject(0);

			JavaMethod ancestorJavaMethod = null;

			String ancestorJavaClassName =
				ancestorJavaClass.getFullyQualifiedName();

			if ((ancestorJavaClassTuple.getSize() == 1) ||
				(ancestorJavaClassName.equals("java.util.Map") &&
				 methodName.equals("get"))) {

				ancestorJavaMethod = ancestorJavaClass.getMethodBySignature(
					methodName, types);
			}
			else {

				// LPS-35613

				Type[] ancestorActualTypeArguments =
					(Type[])ancestorJavaClassTuple.getObject(1);

				Type[] genericTypes = new Type[types.length];

				for (int i = 0; i < types.length; i++) {
					Type type = types[i];

					String typeValue = type.getValue();

					boolean useGenericType = false;

					for (int j = 0; j < ancestorActualTypeArguments.length;
						j++) {

						if (typeValue.equals(
								ancestorActualTypeArguments[j].getValue())) {

							useGenericType = true;

							break;
						}
					}

					if (useGenericType) {
						genericTypes[i] = new Type("java.lang.Object");
					}
					else {
						genericTypes[i] = type;
					}
				}

				ancestorJavaMethod = ancestorJavaClass.getMethodBySignature(
					methodName, genericTypes);
			}

			if (ancestorJavaMethod == null) {
				continue;
			}

			boolean samePackage = false;

			JavaPackage ancestorJavaPackage = ancestorJavaClass.getPackage();

			if (ancestorJavaPackage != null) {
				samePackage = ancestorJavaPackage.equals(
					javaClass.getPackage());
			}

			// Check if the method is in scope

			if (samePackage) {
				return !ancestorJavaMethod.isPrivate();
			}

			if (ancestorJavaMethod.isProtected() ||
				ancestorJavaMethod.isPublic()) {

				return true;
			}
			else {
				return false;
			}
		}

		return false;
	}

	private boolean _overridesHigherJavaAPIVersion(JavaMethod javaMethod) {
		Annotation[] annotations = javaMethod.getAnnotations();

		if (annotations == null) {
			return false;
		}

		for (Annotation annotation : annotations) {
			Type type = annotation.getType();

			JavaClass javaClass = type.getJavaClass();

			String javaClassName = javaClass.getFullyQualifiedName();

			if (javaClassName.equals(SinceJava.class.getName())) {
				AnnotationValue annotationValue = annotation.getProperty(
					"value");

				double sinceJava = GetterUtil.getDouble(
					annotationValue.getParameterValue());

				if (sinceJava > _LOWEST_SUPPORTED_JAVA_VERSION) {
					return true;
				}
			}
		}

		return false;
	}

	private static final double _LOWEST_SUPPORTED_JAVA_VERSION = 1.7;

}