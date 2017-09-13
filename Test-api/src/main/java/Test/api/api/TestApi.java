package Test.api.api;

import java.util.List;
import java.util.Map;


public interface TestApi {
	public List<String> getUsers();
	
	public List<Project> getProjects();
	
	public List<String> getProjectsNames();
	
	public Map<String, List<String>> getCommits(List<String> string,List<Project> projects);

}