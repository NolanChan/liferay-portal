package com.liferay.osb.lcs.info.portlet;

import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

/**
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.INFO,
		"javax.portlet.display-name=Info",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/info/",
		"javax.portlet.init-param.view-template=/info/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Info",
		"javax.portlet.info.short-title=Info",
		"javax.portlet.info.keywords=Info",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-info",
	},
	service = Portlet.class
)
public class InfoPortlet extends MVCPortlet{
}
