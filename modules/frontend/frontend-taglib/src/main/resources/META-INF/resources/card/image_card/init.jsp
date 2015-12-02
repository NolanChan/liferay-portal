<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String cssClass = (String)request.getAttribute("liferay-frontend:image-card:cssClass");
Map<String, Object> data = (Map<String, Object>)request.getAttribute("liferay-frontend:image-card:data");
String imageCSSClass = (String)request.getAttribute("liferay-frontend:image-card:imageCSSClass");
String imageUrl = (String)request.getAttribute("liferay-frontend:image-card:imageUrl");
%>