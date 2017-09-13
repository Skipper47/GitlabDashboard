package com.testservice;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import Test.api.api.Project;
import Test.api.api.TestApi;
import Test.api.api.User;


@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = TestApi.class
)
public class TestService implements TestApi {
	
	HttpClient client = new HttpClient();
	

	public List<Project> getProjects() {
		String urlProjects = "http://gitlab.open.gr/api/v4/projects?private_token=usJo8krA1-pRTEoFL4u8&per_page=100";
		GetMethod methodProjects = new GetMethod(urlProjects);

		methodProjects.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(methodProjects);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + methodProjects.getStatusLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Project> projects = new ArrayList();

		try {
			String responseBody = methodProjects.getResponseBodyAsString();

			JSONArray allProjects = JSONFactoryUtil.createJSONArray(responseBody);
			for (int i = 0; i < allProjects.length(); i++) {
				JSONObject project = allProjects.getJSONObject(i);
				int id = project.getInt("id");
				String name = project.getString("name_with_namespace");
				Project p = new Project(id, 0, name, null, null);
				projects.add(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}
	

	public List[] getProjectsNames2() {
		String urlProjects = "http://gitlab.open.gr/api/v4/projects?private_token=usJo8krA1-pRTEoFL4u8&per_page=100";
		GetMethod methodProjects = new GetMethod(urlProjects);

		methodProjects.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(methodProjects);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + methodProjects.getStatusLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List[] projects = null;

		int counter=0;
		try {
			String responseBody = methodProjects.getResponseBodyAsString();

			JSONArray allProjects = JSONFactoryUtil.createJSONArray(responseBody);
			for (int i = 0; i < allProjects.length(); i++) {
				JSONObject project = allProjects.getJSONObject(i);
				String name = project.getString("name_with_namespace");
				projects[counter].add(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}
	
	public List<String> getProjectsNames() {
		String urlProjects = "http://gitlab.open.gr/api/v4/projects?private_token=usJo8krA1-pRTEoFL4u8&per_page=100";
		GetMethod methodProjects = new GetMethod(urlProjects);

		methodProjects.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(methodProjects);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + methodProjects.getStatusLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> projects = new ArrayList();

		try {
			String responseBody = methodProjects.getResponseBodyAsString();

			JSONArray allProjects = JSONFactoryUtil.createJSONArray(responseBody);
			for (int i = 0; i < allProjects.length(); i++) {
				JSONObject project = allProjects.getJSONObject(i);
				String name = project.getString("name_with_namespace");
				projects.add(name);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return projects;
	}
	
	
	
	public List<String> getUsers() {
		String urlUsers = "http://gitlab.open.gr/api/v4/users?private_token=ndtMo_91JR1rcDyn8xzK&per_page=100";
		GetMethod methodUser = new GetMethod(urlUsers);

		methodUser.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));
		try {
			int statusCode = client.executeMethod(methodUser);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + methodUser.getStatusLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> users = new ArrayList();

		try {
			String responseBody = methodUser.getResponseBodyAsString();

			JSONArray resultUser = JSONFactoryUtil.createJSONArray(responseBody);
			for (int totalUsersCounter = 0; totalUsersCounter < resultUser.length(); totalUsersCounter++) {
				JSONObject user = resultUser.getJSONObject(totalUsersCounter);
				String userEmail = user.getString("email");
				users.add(userEmail);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return users;
	}
	

	public Map<String, List<String>> getCommits(List<String> users,List<Project> projects) {
		Map<String, List<String>> mapWithProjectsAndUsers = new HashMap<String, List<String>>();
		mapWithProjectsAndUsers.put("", users);
		List<String> row = null;

		for (int projectNumber = 0; projectNumber < projects.size(); projectNumber++) {
			List<String> commits = new ArrayList();
			List<String> committerEmail = new ArrayList<String>();
			List<User> usersPerProject = new ArrayList<User>();

			String urlCommits = "http://gitlab.open.gr/api/v4/projects/" + projects.get(projectNumber).getId()
					+ "/repository/commits?private_token=usJo8krA1-pRTEoFL4u8&per_page=100";
			GetMethod methodCommits = new GetMethod(urlCommits);
			methodCommits.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			try {
				int statusCode = client.executeMethod(methodCommits);
				if (statusCode != HttpStatus.SC_OK) {
					System.err.println("Method failed: " + methodCommits.getStatusLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String responseBodyCommits = methodCommits.getResponseBodyAsString();
				JSONArray result = JSONFactoryUtil.createJSONArray(responseBodyCommits);

				for (int i = 0; i < result.length(); i++) {
					JSONObject commit = result.getJSONObject(i);
					commits.add(commit.toJSONString());
					committerEmail.add(commit.getString("committer_email"));
				}
				Collections.sort(commits);
				Collections.reverse(commits);

				Set<String> unique = new HashSet<String>(committerEmail);
				for (String key : unique) {
					User user = new User(key, Collections.frequency(committerEmail, key), 0);
					usersPerProject.add(user);
				}
				projects.get(projectNumber).setCommits(commits);
				projects.get(projectNumber).setUsers(usersPerProject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
			
			row = new ArrayList<String>();
			for (int totalUsersCounter = 0; totalUsersCounter < users.size(); totalUsersCounter++) {
				boolean found = false;
				for (int usersPerProjectCounter = 0; usersPerProjectCounter < usersPerProject.size(); usersPerProjectCounter++) {
					if (usersPerProject.get(usersPerProjectCounter).getUsername().equals(users.get(totalUsersCounter))) {
						found = true;
						row.add(usersPerProject.get(usersPerProjectCounter).getCommits() + " commits");
					}
				}
				if (!found) {
					row.add("");
				}
			}
			mapWithProjectsAndUsers.put(projects.get(projectNumber).toString(), row);
		}
		return mapWithProjectsAndUsers;
		
	}



}
