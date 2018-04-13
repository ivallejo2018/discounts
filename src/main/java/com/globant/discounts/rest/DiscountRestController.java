package com.globant.discounts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.service.DiscountService;
import com.globant.discounts.util.DiscountException;

/**
 * The service is responsible for the discount services operations like food,
 * furniture, clothes among others which are available for a company.
 * 
 * @author isaac.vallejo
 *
 */
@RestController
@RequestMapping("/companies")
public class DiscountRestController {

	@Autowired
	private DiscountService discountService;
	
	/**
	 * Returns the discount list available for a company 
	 * 
	 * @param companyId	The company identifier
	 * @return			The discount list that belongs to a company
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{companyId}/discounts")
	public List<Discount> getDiscounts(@PathVariable int companyId) {
		try {
			return discountService.getDiscounts(companyId);
		} catch (DiscountException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Add a new discount with the percentage and the discount type
	 * that a company requires
	 * 
	 * @param discount	The discount to add for a company
	 */
	@RequestMapping(method=RequestMethod.POST, value="/{companyId}/discounts")
	public void addDiscount(@PathVariable int companyId, @RequestBody Discount discount) {
		try {
			discountService.addDiscount(discount);
		} catch (DiscountException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Delete a discount available for a company
	 * 
	 * @param discount	The discount of a company to delete
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/{companyId}/discounts/{discountId}")
	public void deleteDiscount(@PathVariable int companyId, @PathVariable int discountId) {
		try {
			discountService.deleteDiscount(companyId, discountId);
		} catch (DiscountException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update a discount available for a company
	 * The information that can be updated are the percentage and the discount type 
	 * 
	 * @param discounts	The discount to find is in the first index of the list
	 * 					The discount with the new data is in the second index of the list
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/{companyId}/discounts/{discountId}")
	public void editDiscount(@PathVariable int companyId, @PathVariable int discountId, @RequestBody Discount discount) {
		try {
			discountService.editDiscount(companyId, discountId, discount);
		} catch (DiscountException e) {
			e.printStackTrace();
		}
	}

}
