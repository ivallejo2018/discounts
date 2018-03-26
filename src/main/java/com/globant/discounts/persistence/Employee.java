package com.globant.discounts.persistence;

import com.globant.discounts.util.FileUtil;

public class Employee {

	private int id;
	private String email;
	private String name;
	private String lastName;
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.lastName);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.email);
		sb.append(FileUtil.SEPARATOR);
		sb.append(this.password);
		
		return sb.toString();
	}
	
}
