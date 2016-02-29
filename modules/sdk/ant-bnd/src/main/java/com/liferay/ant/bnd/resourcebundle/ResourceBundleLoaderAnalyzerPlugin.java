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

package com.liferay.ant.bnd.resourcebundle;

import aQute.bnd.header.Attrs;
import aQute.bnd.header.Parameters;
import aQute.bnd.osgi.Analyzer;
import aQute.bnd.osgi.Constants;
import aQute.bnd.osgi.Jar;
import aQute.bnd.service.AnalyzerPlugin;

/**
 * @author Carlos Sierra Andrés
 */
public class ResourceBundleLoaderAnalyzerPlugin implements AnalyzerPlugin {

	@Override
	public boolean analyzeJar(Analyzer analyzer) throws Exception {
		Jar jar = analyzer.getJar();

		if (jar.exists("content/Language.properties")) {
			Parameters headerParameters = new Parameters(
				analyzer.getProperty(Constants.PROVIDE_CAPABILITY));

			Parameters capabilities = new Parameters();

			Attrs attrs = new Attrs();

			attrs.put("baseName", "content.Language");

			capabilities.add("liferay.resource.bundle", attrs);

			headerParameters.mergeWith(capabilities, false);

			analyzer.setProperty(
				Constants.PROVIDE_CAPABILITY, headerParameters.toString());

			return true;
		}

		return false;
	}

}