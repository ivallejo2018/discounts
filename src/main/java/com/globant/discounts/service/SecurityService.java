package com.globant.discounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.discounts.dao.SecurityDAO;
import com.globant.discounts.persistence.Employee;

/**
 * Security service layer that provides all the security issues 
 * for the discount service point. Provides the operations
 * for authenticating the user and storing new user registration. 
 * 
 * @author isaac.vallejo
 *
 */
@Service
public class SecurityService {

	@Autowired
	private SecurityDAO securityDAO;
	
	/**
	 * Determines if the user is valid or not based on
	 * the user key, in this case the email, and password.
	 * 
	 * @param employee	The employee information that will be validated as a user
	 */
	public void validateUser(Employee employee) {
		securityDAO.isUserValid(employee.getEmail(), employee.getPassword());
	}
	
	/**
	 * Register a new user that will be enabled with the discount benefits
	 * 
	 * @param employee	The employee that will be registered in the discount application
	 * @return			
	 */
	public String addUser(Employee employee) {
		securityDAO.addUser(employee);
		return "User successfully added";
	}	

}
