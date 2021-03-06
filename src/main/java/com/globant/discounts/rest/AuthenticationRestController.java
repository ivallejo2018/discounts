package com.globant.discounts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.discounts.persistence.Employee;
import com.globant.discounts.service.SecurityService;
import com.globant.discounts.util.DiscountException;

/**
 * The service is responsible for the authentication
 * of the employees of a company who has access to 
 * the discount service
 * 
 * @author isaac.vallejo
 *
 */
@RestController
public class AuthenticationRestController {

	@Autowired
	private SecurityService securityService;
	
	/**
	 * Determines if the employee is registered as a user
	 * for the discount benefit of a company and validates
	 * the access to the discount service based on the email 
	 * and password validation
	 * 
	 * @param employee	The employee who will be sign in to the discount service
	 */
	@RequestMapping(method=RequestMethod.POST, value="/discounts/login")
	public void login(@RequestBody Employee employee) {
		securityService.validateUser(employee);	
	}
	
	/**
	 * Add an employee of a company to enable the discount benefit
	 * for that employee
	 * 
	 * @param employee	The employee who will have the discount benefit
	 */
	@RequestMapping(method=RequestMethod.POST, value="/discounts/users")
	public void addUsers(@RequestBody List<Employee> employees) {
		try {
			for(Employee employee : employees)
				securityService.addUser(employee);
		} catch (DiscountException e) {
			e.printStackTrace();
		}
	}
}
