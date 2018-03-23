package com.globant.discounts.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.globant.discounts.dao.CompanyDAO;
import com.globant.discounts.persistence.Employee;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * Service layer responsible for adding new employees of a company 
 * that will have the discount benefits. This service communicates
 * with the security service using the registration operation. 
 * 
 * 
 * @author isaac.vallejo
 *
 */
@Service
public class CompanyService {

	private final RestTemplate restTemplate;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public CompanyService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Add a list of employees of a company to the discount application.
	 * This operation communicates with the addUser operation
	 * from the security service. In case of failure or unavailable
	 * service operation it will be redirected to another operation that 
	 * store the list to cache and will be doing a retry after a fix time lapse.
	 * 
	 * @param employees	The list of employees of a company
	 */
	@HystrixCommand(fallbackMethod = "addEmployeesToCache")
	public void addEmployees(List<Employee> employees) {
		URI uri = URI.create("http://localhost:8080/user");
		
		HttpEntity<List<Employee>> request = new HttpEntity<List<Employee>>(employees);
		ResponseEntity<List> result = this.restTemplate.postForEntity(uri, request, List.class);
	}
	
	/**
	 * Add a list of employees to cache in case of failure or service unavailable.
	 * 
	 * @param employees The list of employees of a company
	 * @return			A message that warns of a later processing
	 */
	public String addEmployeesToCache(List<Employee> employees) {
		return "Users added to later processing";
	}
}
