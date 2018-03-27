package com.globant.discounts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.util.DiscountException;
import com.globant.discounts.util.DiscountHandler;
import com.globant.discounts.util.FileHandler;
import com.globant.discounts.util.FileUtil;

@Repository
public class DiscountDAO {

	private FileHandler util;
	
	@Autowired
	public void configureFileUtil() {
		this.util = new DiscountHandler(new FileSystemResource("discounts.txt"));
	}
	
	public List<Discount> retrieveDiscounts(int companyId) {
		return new ArrayList<>();
	}
	
	public void storeDiscount(Discount discount) throws DiscountException {
		List<Discount> discounts = new ArrayList<>();
		discounts.add(discount);
		util.write(discounts);
	}
	
	public void deleteDiscount(Discount discount) {
		
	}
	
	public void updateDiscount(Discount src, Discount dest) throws DiscountException {
		util.overwrite(src, dest);
	}
}
