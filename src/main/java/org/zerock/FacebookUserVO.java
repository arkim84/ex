package org.zerock;

public class FacebookUserVO {
	
	private String id;
	private String name;
	private String firstName;
	private String lastName;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public FacebookUserVO(
			String id, 
			String name, 
			String firstName, 
			String lastName) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
}
