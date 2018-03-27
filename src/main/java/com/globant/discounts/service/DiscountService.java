package com.globant.discounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.discounts.dao.DiscountDAO;
import com.globant.discounts.persistence.Discount;
import com.globant.discounts.util.DiscountException;

@Service
public class DiscountService {

	@Autowired
	private DiscountDAO discountDAO;
	
	public List<Discount> getDiscounts(int companyId) {
		return discountDAO.retrieveDiscounts(companyId); 
	}
	
	public void addDiscount(Discount discount) throws DiscountException {
		discountDAO.storeDiscount(discount);
	}
	
	public void deleteDiscount(Discount discount) {
		discountDAO.deleteDiscount(discount);
	}
	
	public void editDiscount(Discount src, Discount dest) throws DiscountException {
		discountDAO.updateDiscount(src, dest);
	}

	public void setDiscountDAO(DiscountDAO discountDAO) {
		this.discountDAO = discountDAO;
	}
}
