package model;

public class Project {

	private String projectNumber;
	private String projectName;
	private int timeBudget;
	private String description;
	
	public Project(String projectNumber, String projectName, int timeBudget, String description) {
		
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.timeBudget = timeBudget;
		this.description = description;
		
	}
	
	//Constructor til tests
    public Project(String projectName) {
        this.projectName = projectName;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public int getTimeBudget() {
		return timeBudget;
	}
	
	
	
}
