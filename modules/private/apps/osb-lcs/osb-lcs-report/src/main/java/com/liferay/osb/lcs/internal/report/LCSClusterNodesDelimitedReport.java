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

package com.liferay.osb.lcs.internal.report;

import com.liferay.osb.lcs.report.ReportContext;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import java.util.Date;
import java.util.List;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 * @author Marko Cikos
 */
public class LCSClusterNodesDelimitedReport extends BaseReport {

	@Override
	public ByteArrayOutputStream process(ReportContext reportContext)
		throws Exception {

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		BufferedWriter bufferedWriter = new BufferedWriter(
			new OutputStreamWriter(byteArrayOutputStream));

		List<Object[]> lcsClusterNodeObjectArrays =
			reportContext.getLCSClusterNodeObjectArrays();

		for (Object[] lcsClusterNodeObjectArray : lcsClusterNodeObjectArrays) {
			bufferedWriter.write((String)lcsClusterNodeObjectArray[1]);
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write((String)lcsClusterNodeObjectArray[3]);
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write((String)lcsClusterNodeObjectArray[5]);
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(String.valueOf(lcsClusterNodeObjectArray[6]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[7]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[8]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[13]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write((String)lcsClusterNodeObjectArray[4]);
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[10]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(
				sanitize(new Date((Long)lcsClusterNodeObjectArray[9])));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[11]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[12]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(sanitize(lcsClusterNodeObjectArray[14]));
			bufferedWriter.write(StringPool.SEMICOLON);
			bufferedWriter.write(reportContext.getLineSeparator());

			bufferedWriter.flush();
		}

		return byteArrayOutputStream;
	}

}