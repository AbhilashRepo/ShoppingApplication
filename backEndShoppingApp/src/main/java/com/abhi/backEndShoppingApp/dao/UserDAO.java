package com.abhi.backEndShoppingApp.dao;

import java.util.List;

import com.abhi.backEndShoppingApp.dto.Address;
import com.abhi.backEndShoppingApp.dto.Cart;
import com.abhi.backEndShoppingApp.dto.User;

public interface UserDAO {

	// user related operation	
	//	User get(int id);
	
	// add a user
	boolean addUser(User user);
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> listShippingAddresses(User user);
	
	//update a cart
	boolean updateCart(Cart cart);
	
	/*// adding and updating a new address
	Address getAddress(int addressId);
	

	*/

	
}
