package com.globant.discounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.discounts.dao.DiscountDAO;
import com.globant.discounts.persistence.Discount;

@Service
public class DiscountService {

	@Autowired
	private DiscountDAO discountDAO;
	
	public List<Discount> getDiscounts(int companyId) {
		return discountDAO.retrieveDiscounts(companyId); 
	}
	
	public void addDiscount(Discount discount) {
		discountDAO.storeDiscount(discount);
	}
	
	public void deleteDiscount(Discount discount) {
		discountDAO.deleteDiscount(discount);
	}
	
	public void editDiscount(Discount discount) {
		discountDAO.updateDiscount(discount);
	}

	public void setDiscountDAO(DiscountDAO discountDAO) {
		this.discountDAO = discountDAO;
	}
}
