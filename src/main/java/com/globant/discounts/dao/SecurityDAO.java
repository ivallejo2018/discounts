package com.globant.discounts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import com.globant.discounts.persistence.Employee;
import com.globant.discounts.util.DiscountException;
import com.globant.discounts.util.FileUtil;

@Repository
public class SecurityDAO {

	private FileUtil util;
	
	@Autowired
	public void configureFileUtil() {
		this.util = new FileUtil(new FileSystemResource("authentication.txt"));
	}
	
	public boolean isUserValid(String user, String password) {
		
		try {
			List<List<Object>> result = util.read(2, user);
			if(!result.isEmpty()) {
				String pwd = String.valueOf(result.get(0).get(3));
				if(password.equals(pwd)) {
					return true;
				}
			}			
		} catch(DiscountException e) {
			return false;
		}

		return false;
	}
	
	public void addUser(Employee employee) throws DiscountException {
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		
		util.write(employees);
	}
}
