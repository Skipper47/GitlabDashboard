package test.portlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.servlet.ServletContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;



@Component(
		immediate = true,
		property = {
			"javax.portlet.name=dimosProject",
		},
		service = ConfigurationAction.class
	)
	public class FilesystemAccessPortletInstanceConfigurationAction extends DefaultConfigurationAction {

	@Override
	public void processAction(
		PortletConfig portletConfig, ActionRequest actionRequest,
		ActionResponse actionResponse)
		throws Exception {
			        
		PortletPreferences portletPreferences = actionRequest.getPreferences();
		String[] projectsChosen = actionRequest.getParameterValues("projects");
		TestPortlet tp = new TestPortlet();
		tp.setProjectsSelected(projectsChosen);
		portletPreferences.store();
		
	}

	
		@Override
		@Reference(
			target = "(osgi.web.symbolicname=test)", unbind = "-")
		public void setServletContext(ServletContext servletContext) {
			super.setServletContext(servletContext);
		}
	}