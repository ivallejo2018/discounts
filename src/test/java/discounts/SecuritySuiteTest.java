package discounts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.globant.discounts.persistence.Employee;
import com.globant.discounts.service.SecurityService;
import com.globant.discounts.util.DiscountException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = discounts.Configuration.class)
public class SecuritySuiteTest {

	@Autowired
	private SecurityService securityService;
	
	@Test
	public void testIsUserValid() {
		boolean isValid = securityService.validateUser(getEmployee());
		Assert.isTrue(isValid, "User is not valid");
	}
	
	@Test
	public void testAddUser() {
		boolean userAdded = false;
		
		try {
			securityService.addUser(newEmployee());
			userAdded = true;
		} catch (DiscountException e) {
			e.printStackTrace();
		}
		
		Assert.isTrue(userAdded, "User insertion error!");
	}
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmail("isai@gmail.com");
		employee.setPassword("6789");
		
		return employee;
	}
	
	private Employee newEmployee() {
		Employee employee = new Employee();
		employee.setName("Udemy");
		employee.setLastName("Prueba");
		employee.setEmail("udemy@globant.com");
		employee.setPassword("abcde");
		
		return employee;
	}
}
