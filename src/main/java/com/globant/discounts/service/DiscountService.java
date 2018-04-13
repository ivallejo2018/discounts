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
	
	public List<Discount> getDiscounts(int companyId) throws DiscountException {
		return discountDAO.retrieveDiscounts(companyId); 
	}
	
	public void addDiscount(Discount discount) throws DiscountException {
		discountDAO.storeDiscount(discount);
	}
	
	public void deleteDiscount(int companyId, int discountId) throws DiscountException {
		discountDAO.deleteDiscount(companyId, discountId);
	}
	
	public void editDiscount(int companyId, int discountId, Discount discount) throws DiscountException {
		discountDAO.updateDiscount(companyId, discountId, discount);
	}

	public void setDiscountDAO(DiscountDAO discountDAO) {
		this.discountDAO = discountDAO;
	}
}
