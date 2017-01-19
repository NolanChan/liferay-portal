<%@ page import="com.liferay.portal.kernel.util.ReleaseInfo" %>
<%@ taglib prefix="example" uri="http://liferay.com/tld/example" %>


<%= "Welcome to the new and improved ".concat(ReleaseInfo.getReleaseInfo()).concat(".") %>

<example:test/>
