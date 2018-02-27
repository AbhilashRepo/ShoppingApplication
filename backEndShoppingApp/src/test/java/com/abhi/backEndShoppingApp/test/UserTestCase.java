package com.abhi.backEndShoppingApp.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.abhi.backEndShoppingApp.dao.UserDAO;
import com.abhi.backEndShoppingApp.dto.Address;
import com.abhi.backEndShoppingApp.dto.Cart;
import com.abhi.backEndShoppingApp.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.abhi.backEndShoppingApp");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	

/*	@Test
	public void testAdd() {
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		//user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));	
		
		
		address = new Address();
		address.setAddressLineOne("101 Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		
		
		// linked the address with the user
		address.setUserId(user.getId());
	
		// add the address
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));

		if(user.getRole().equals("USER")){
			//Create cart for this user
			cart = new Cart();
			cart.setUser(user);
			
			//add the cart
			assertEquals("Failed to add cart !", true, userDAO.addCart(cart));
			
			
		}
		
		// linked the cart with the user
		cart.setUser(user);
		
		// link the user with the cart
		user.setCart(cart);
		
		
				
		// add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		
		//link it with the user
		address.setUserId(user.getId());
		
		//add the shipping address
		
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}*/
	
	

	// working for uni-directional
/*
	@Test
	public void testAddAddress() {
		user = userDAO.get(1);
		
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
				
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
	}
	
	@Test
	public void testUpdateCart() {
		user = userDAO.get(1);
		cart = user.getCart();
		cart.setGrandTotal(10000);
		cart.setCartLines(1);
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));			
	} 

*/
	
/*	@Test
	public void testAddAddress(){
		
		//we need to add an user
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		//user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));	
		
		//we are going to add the address
		

		address = new Address();
		address.setAddressLineOne("101 Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attach the user to the address
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	

		
		
		// we are also going to add the shipping address
		address = new Address();
		address.setAddressLineOne("201/B Jadoo Society, Kishan Kanhaiya Nagar");
		address.setAddressLineTwo("Near Kudrat Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		//set shipping to true
		address.setShipping(true);
		
		//Attach the user to the address
		address.setUser(user);
				
		//add the shipping address
				
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		
	}
	
*/	
	/*@Test
	public void testAddAddress(){
		
		
		//we need to add an user
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		//user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));	

		
		
		user = userDAO.getByEmail("hr@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("101 Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
	
		//attach the user to the address
		address.setUser(user);
		// add the address
		assertEquals("Failed to add the address!", true, userDAO.addAddress(address));	
	}*/

	@Test
	public void testGetAddresses(){
		
		user = userDAO.getByEmail("hr@gmail.com");
		
		assertEquals("Failed to fetch the list of address and size does not match", 2 ,
				userDAO.listShippingAddresses(user).size());
		
		assertEquals("Failed to fetch the list of billing address and size does not match","Mumbai",
				userDAO.getBillingAddress(user).getCity());
		
		
	}
	
}









