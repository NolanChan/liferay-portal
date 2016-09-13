package com.liferay.osb.lcs.supportadmin.portlet;

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
		"javax.portlet.name=" + PortletKeys.SUPPORT_ADMIN,
		"javax.portlet.display-name=Support Admin",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/supportadmin/",
		"javax.portlet.init-param.view-template=/supportadmin/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Support Admin",
		"javax.portlet.info.short-title=Support Admin",
		"javax.portlet.info.keywords=Support Admin",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-support-admin",
		"com.liferay.portlet.display-category=category.lcs"
	},
	service = Portlet.class
)
public class SupportAdminPortlet extends MVCPortlet{
}
