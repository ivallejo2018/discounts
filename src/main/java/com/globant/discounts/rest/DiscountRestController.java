package com.globant.discounts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.service.DiscountService;

/**
 * The service is responsible for the discount services operations like food,
 * furniture, clothes among others which are available for a company.
 * 
 * @author isaac.vallejo
 *
 */
@RestController
public class DiscountRestController {

	@Autowired
	private DiscountService discountService;
	
	/**
	 * Returns the discount list available for a company 
	 * 
	 * @param companyId	The company identifier
	 * @return			The discount list that belongs to a company
	 */
	@RequestMapping("/discounts/{companyId}")
	public List<Discount> getDiscounts(@RequestParam int companyId) {
		return discountService.getDiscounts(companyId);
	}
	
	/**
	 * Add a new discount with the percentage and the discount type
	 * that a company requires
	 * 
	 * @param discount	The discount to add for a company
	 */
	@RequestMapping(method=RequestMethod.POST, value="/discount")
	public void addDiscount(@RequestBody Discount discount) {
		discountService.addDiscount(discount);
	}
	
	/**
	 * Delete a discount available for a company
	 * 
	 * @param discount	The discount of a company to delete
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/discount")
	public void deleteDiscount(@RequestBody Discount discount) {
		discountService.deleteDiscount(discount);
	}
	
	/**
	 * Update a discount available for a company
	 * The information that can be updated are the percentage and the discount type 
	 * 
	 * @param discount	The discount of a company to update
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/discount")
	public void editDiscount(@RequestBody Discount discount) {
		discountService.editDiscount(discount);
	}

}
