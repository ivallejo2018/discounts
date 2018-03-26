package com.globant.discounts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.discounts.dao.SecurityDAO;
import com.globant.discounts.persistence.Employee;
import com.globant.discounts.util.DiscountException;

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
	 * @return 			A boolean value that determines if a user is valid or not
	 */
	public boolean validateUser(Employee employee) {
		return securityDAO.isUserValid(employee.getEmail(), employee.getPassword());
	}
	
	/**
	 * Register a new user that will be enabled with the discount benefits
	 * 
	 * @param employee	The employee that will be registered in the discount application		
	 */
	public void addUser(Employee employee) throws DiscountException {
		securityDAO.addUser(employee);
	}

	public void setSecurityDAO(SecurityDAO securityDAO) {
		this.securityDAO = securityDAO;
	}	

}
