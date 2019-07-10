package com.reactive.model;

public class SSNDetails {
	private long socialSecurityNo;
	private String firstName;
	private String lastName;
	private String email;
	private Address address;

	public SSNDetails() {

	}
	public SSNDetails(long id, String firstName, String lastName, String email, Address address) {
		this.socialSecurityNo = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
	}
	public long getSocialSecurityNo() {
		return socialSecurityNo;
	}
	public void setSocialSecurityNo(long socialSecurityNo) {
		this.socialSecurityNo = socialSecurityNo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
