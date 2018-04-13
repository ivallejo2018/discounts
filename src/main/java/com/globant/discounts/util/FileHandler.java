package com.globant.discounts.util;

import java.util.List;

import com.globant.discounts.persistence.Discount;

public interface FileHandler {

	public static final String SEPARATOR = "|";
	
	public static final String NEW_LINE = "\n";
	
	public static final String DEL_CHAR = "D";
	
	public static final String CREATE_CHAR = "C";
	
	public static final String UPDATE_CHAR = "U";
	
	public List<Discount> read(Integer companyId) throws DiscountException;
	
	public void write(List<Discount> discounts) throws DiscountException;
	
	public void erase(Integer companyId, Integer discountId) throws DiscountException;
	
	public Discount overwrite(Integer companyId, Integer discountId, Discount discount) throws DiscountException;
}
