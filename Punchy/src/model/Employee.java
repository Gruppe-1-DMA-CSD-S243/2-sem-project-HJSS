package model;

public class Employee {
	private String employeeNumber;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String zipCode;
	private String email;
	private String jobTitle;
	private String userName;
	private String password;
	private boolean isAdministrator;
	
	public Employee(String employeeNumber, String phoneNumber, String firstName, String lastName, String address, String zipCode,
			String email, String jobTitle, String userName, String password, boolean isAdministrator) {
		
		this.employeeNumber = employeeNumber;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.zipCode = zipCode;
		this.email = email;
		this.jobTitle = jobTitle;
		this.userName = userName;
		this.password = password;
		this.isAdministrator = isAdministrator;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public String getEmail() {
		return email;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdministrator() {
		return isAdministrator;
	}
	
	

}
