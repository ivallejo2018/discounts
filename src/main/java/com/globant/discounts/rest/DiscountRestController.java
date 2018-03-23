package com.globant.discounts.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globant.discounts.persistence.Discount;
import com.globant.discounts.persistence.Employee;
import com.globant.discounts.service.DiscountService;

@RestController
public class DiscountRestController {

	@Autowired
	private DiscountService discountService;
	
	@RequestMapping("/discounts")
	public List<Discount> getDiscounts() {
		System.out.println("Obteniendo descuentos...");
		
		return discountService.getDiscounts();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/discounts")
	public void addDiscount(@RequestBody Discount discount) {
		discountService.addDiscount(discount);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/discounts")
	public void deleteDiscount(@RequestBody Discount discount) {
		discountService.deleteDiscount(discount);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/discounts")
	public void editDiscount(@RequestBody Discount discount) {
		discountService.editDiscount(discount);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employee")
	public void allowEmployeeDiscount(@RequestBody Employee employee) {
		
	}
}
