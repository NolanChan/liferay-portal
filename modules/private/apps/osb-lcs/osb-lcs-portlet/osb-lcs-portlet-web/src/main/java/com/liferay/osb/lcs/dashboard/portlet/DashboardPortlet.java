package com.liferay.osb.lcs.dashboard.portlet;

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
		"javax.portlet.name=" + PortletKeys.DASHBOARD,
		"javax.portlet.display-name=Dashboard",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/dashboard/",
		"javax.portlet.init-param.view-template=/dashboard/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Dashboard",
		"javax.portlet.info.short-title=Dashboard",
		"javax.portlet.info.keywords=Dashboard",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-dashboard",
	},
	service = Portlet.class
)
public class DashboardPortlet extends MVCPortlet{
}
