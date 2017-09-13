package Test.api.api;

import java.util.List;



public class Project {
	
	private int id;
	private int open_issues_count;
	private String name;
	private List<String> commits;
	private List<User> users;
	

	public Project(int id, int open_issues_count, String name, List<String> commits,List<User> users) {
		super();
		this.id = id;
		this.open_issues_count = open_issues_count;
		this.name = name;
		this.commits = commits;
		this.users = users;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOpen_issues_count() {
		return open_issues_count;
	}
	
	public void setOpen_issues_count(int open_issues_count) {
		this.open_issues_count = open_issues_count;
	}

	public List<String> getCommits() {
		return commits;
	}

	public void setCommits(List<String> commits) {
		this.commits = commits;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() { 
	    return this.name;
	} 
	
}
