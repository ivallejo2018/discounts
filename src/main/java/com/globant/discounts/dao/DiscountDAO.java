package com.globant.discounts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.globant.discounts.persistence.Discount;

@Repository
public class DiscountDAO {

	public List<Discount> retrieveDiscounts() {
		return new ArrayList<>();
	}
	
	public void storeDiscount(Discount discount) {
		
	}
	
	public void deleteDiscount(Discount discount) {
		
	}
	
	public void updateDiscount(Discount discount) {
		
	}
}
