package com.globant.discounts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.discounts.persistence.Employee;
import com.globant.discounts.service.CompanyService;

/**
 * The service is responsible for the operations on the discounts applied 
 * for the employees of a company
 * 
 * @author isaac.vallejo
 *
 */
@EnableCircuitBreaker
@RestController
public class CompanyRestController {

	@Autowired
	private CompanyService companyService;
	
	/**
	 * Add the employees of a company which has the discount benefit
	 * 
	 * @param employees	The list of employees of a company that will 
	 * 					have the benefit of discounts
	 */
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public void addEmployees(@RequestBody List<Employee> employees) {
		companyService.addEmployees(employees);
	}
}
