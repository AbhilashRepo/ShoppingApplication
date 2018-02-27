package com.abhi.frontEndShoppingApp.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.abhi.backEndShoppingApp.dao.UserDAO;
import com.abhi.backEndShoppingApp.dto.Address;
import com.abhi.backEndShoppingApp.dto.Cart;
import com.abhi.backEndShoppingApp.dto.User;
import com.abhi.frontEndShoppingApp.Model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		 registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	//checking password matching confirm password
	public String validateUser(User user, MessageContext error){
		String transitionValue = "success";
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password doesn't match the Confirm Password !")
					.build()					
					);
			transitionValue = "failure";
		}
		
		//check the uniqueness of the email id
		if(userDAO.getByEmail(user.getEmail())!=null){
			
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("Email address alreay exists !")
					.build()					
					);
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
	
	
	
	
	
	
	public String saveAll(RegisterModel model){
		String transitionValue = "success";
		
		//fetch the user
		User user = model.getUser();
		
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//save the user
		userDAO.addUser(user);
		
		//get the address
		Address billing = model.getBilling();
		
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		//save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
	
}







