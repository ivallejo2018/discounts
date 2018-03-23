package discounts;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import com.globant.discounts.dao.DiscountDAO;
import com.globant.discounts.persistence.Discount;
import com.globant.discounts.persistence.Type;
import com.globant.discounts.service.DiscountService;

public class DiscountSuiteTest {
	
	@Mock
	private DiscountDAO discountDAO;
	
	private DiscountService discountService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		discountService = new DiscountService();
		discountService.setDiscountDAO(discountDAO);
	}
		
	@Test
	public void testMessage() {
		System.out.println("Testing...");
	}
	
	@Test
	public void testAccessDiscounts() {
		discountService.getDiscounts();
		Mockito.verify(discountDAO).retrieveDiscounts();
	}
	
	@Test
	public void testRetrieveDiscounts() {
		Mockito.when(discountDAO.retrieveDiscounts()).thenReturn(getDiscounts());
		List<Discount> discounts = discountService.getDiscounts();
		Assert.notEmpty(discounts, "Discounts retrieved from DB");
		Mockito.verify(discountDAO).retrieveDiscounts();
	}
	
	private List<Discount> getDiscounts() {
		List<Discount> discounts = new ArrayList<>();
		Discount d1 = new Discount(1, 5.0, Type.FURNITURE);
		Discount d2 = new Discount(2, 10.0, Type.CLOTHES);
		Discount d3 = new Discount(3, 15.0, Type.BOOKS);
		
		discounts.add(d1);
		discounts.add(d2);
		discounts.add(d3);
		
		return discounts;
	}
}
