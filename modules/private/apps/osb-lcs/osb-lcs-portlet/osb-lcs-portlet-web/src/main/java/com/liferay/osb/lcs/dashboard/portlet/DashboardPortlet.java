package com.liferay.osb.lcs.dashboard.portlet;

import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-dashboard" + PortletKeys.DASHBOARD,
		"javax.portlet.display-name=Dashboard",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Dashboard",
		"javax.portlet.info.short-title=Dashboard",
		"javax.portlet.info.title=Dashboard",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/dashboard/",
		"javax.portlet.init-param.view-template=/dashboard/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class DashboardPortlet extends MVCPortlet {
}