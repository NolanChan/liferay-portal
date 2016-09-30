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

package com.liferay.osb.ldn.generator.layout;

/**
 * @author Ryan Park
 */
public interface LayoutGenerator {

	public void generate(long plid) throws Exception;

	public String getLayoutDescription();

	public String getLayoutFriendlyURL();

	public boolean getLayoutHidden();

	public String getLayoutName();

	public String getLayoutTitle();

	public String getLayoutType();

}