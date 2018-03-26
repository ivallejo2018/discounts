package com.globant.discounts.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.globant.discounts.persistence.Employee;
import com.globant.discounts.util.FileUtil;

@Repository
public class SecurityDAO {

	private FileUtil util;
	
	public void isUserValid(String user, String password) {
		List<List<Object>> result = util.read(2, user);
		if(!result.isEmpty()) {
			
		}
	}
	
	public void addUser(Employee employee) {
		
	}
}
