package test.portlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import Test.api.api.*;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Validator;



@Component(
		immediate = true,
		property = {
		"com.liferay.portlet.display-category=category.sample",
		"javax.portlet.name=dimosProject",
		"com.liferay.portlet.instanceable=true", 
		"javax.portlet.display-name=Test Portlet",
		"javax.portlet.init-param.template-path=/", 
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.preferences-owned-by-group=true"
		},
		service = Portlet.class)

public class TestPortlet extends MVCPortlet {
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY)
	private TestApi testApi;
	boolean checkFirstLoadViewJSP=false;
	private static final Log _log = LogFactoryUtil.getLog(TestPortlet.class);
	private static String[] projectsSelected=null;
	
	public String[] getProjectsSelected() {
		return projectsSelected;
	}

	public void setProjectsSelected(String[] projectsSelected) {
		this.projectsSelected = projectsSelected;
	}

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)throws IOException, PortletException {
		 List<String> usersSelected = new ArrayList();
		 usersSelected.add("kitsios@open.gr");
			
		 List<Project> projectsChosenList = new ArrayList<Project>();
		 
		 try {
			projectsSelected=getProjectsSelected();
			
			List<String> projectsNamesChosenList = Arrays.asList(projectsSelected);
			List<Project> allProjects = testApi.getProjects();
			

			for(Project p: allProjects) {
				for(int i=0; i<projectsNamesChosenList.size(); i++) {
					if(projectsNamesChosenList.get(i).equals(p.getName())) {
						projectsChosenList.add(p);
					}
				}
			}
				
			 System.err.println(projectsSelected.length);
		 }catch(NullPointerException e) {
			 System.err.println("error");
		 }		
			
			
		 try {
			 Map<String, List<String>> mapWithProjectsAndUsers = testApi.getCommits(usersSelected, projectsChosenList);
			 renderRequest.setAttribute("map", mapWithProjectsAndUsers);
		 }catch(NullPointerException e) {
			 System.err.println("error2");
		 }
		 
		
		super.doView(renderRequest, renderResponse);
	}

	 
	
}