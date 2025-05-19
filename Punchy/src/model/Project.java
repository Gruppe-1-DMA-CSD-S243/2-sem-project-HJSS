package model;

/** Purpose of this class is present a project in the system. <br>
 * This class contains all the relevant information of a project. <br>
 * project number, project name, time budget and description.
 * 
 * @author Henrik Holmberg Kringel
 * 
 */

public class Project {

	private String projectNumber;
	private String projectName;
	private int timeBudget;
	private String description;
	
	/**
	 * 
	 * Constructs an object of a project with the parameters.
	 * 
	 * @param projectNumber
	 * @param projectName
	 * @param timeBudget
	 * @param description
	 */
	
	public Project(String projectNumber, String projectName, int timeBudget, String description) {
		
		this.projectNumber = projectNumber;
		this.projectName = projectName;
		this.timeBudget = timeBudget;
		this.description = description;
		
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
