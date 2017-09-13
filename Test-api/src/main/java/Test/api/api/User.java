package Test.api.api;


public class User {
	private String username;
	private int commits;
	private int comments;

	
	public User(String username, int commits, int comments) {
		super();
		this.username = username;
		this.commits = commits;
		this.comments = comments;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCommits() {
		return commits;
	}

	public void setCommits(int commits) {
		this.commits = commits;
	}

	@Override
	public String toString() { 
	    return "username: " + this.username;
	} 

	
}
