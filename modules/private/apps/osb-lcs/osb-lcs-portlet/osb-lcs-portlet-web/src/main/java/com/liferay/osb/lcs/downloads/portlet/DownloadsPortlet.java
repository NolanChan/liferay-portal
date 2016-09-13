package com.liferay.osb.lcs.downloads.portlet;

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
		"javax.portlet.name=" + PortletKeys.DOWNLOADS,
		"javax.portlet.display-name=Downloads",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/downloads/",
		"javax.portlet.init-param.view-template=/downloads/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Downloads",
		"javax.portlet.info.short-title=Downloads",
		"javax.portlet.info.keywords=Downloads",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-downloads",
		"com.liferay.portlet.display-category=category.lcs"
	},
	service = Portlet.class
)
public class DownloadsPortlet extends MVCPortlet{
}
