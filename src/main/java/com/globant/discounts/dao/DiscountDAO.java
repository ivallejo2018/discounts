package com.globant.discounts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.util.DiscountException;
import com.globant.discounts.util.DiscountHandler;
import com.globant.discounts.util.FileHandler;

@Repository
public class DiscountDAO {

	private FileHandler util;
	
	private List<Discount> discounts = new ArrayList<>();
		
	@Autowired
	public void configureFileUtil() {
		this.util = new DiscountHandler(new FileSystemResource("discounts.txt"));
	}
	
	public List<Discount> retrieveDiscounts(int companyId) throws DiscountException {
		return util.read(companyId);
	}
	
	public void storeDiscount(Discount discount) throws DiscountException {
		List<Discount> discounts = new ArrayList<>();
		discounts.add(discount);
		util.write(discounts);
	}
	
	public void deleteDiscount(int companyId, int discountId) throws DiscountException {
		util.erase(companyId, discountId);
	}
	
	public void updateDiscount(int companyId, int discountId, Discount discount) throws DiscountException {
		util.overwrite(companyId, discountId, discount);
	}
}
