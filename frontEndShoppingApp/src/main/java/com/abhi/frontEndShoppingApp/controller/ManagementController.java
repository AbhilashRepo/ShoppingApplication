package com.abhi.frontEndShoppingApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abhi.backEndShoppingApp.dao.CategoryDAO;
import com.abhi.backEndShoppingApp.dao.ProductDAO;
import com.abhi.backEndShoppingApp.dto.Category;
import com.abhi.backEndShoppingApp.dto.Product;
import com.abhi.frontEndShoppingApp.util.FileUploadUtility;
import com.abhi.frontEndShoppingApp.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();

		// set few fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product Submitted Successfully !");
			}else if(operation.equals("category")){
				mv.addObject("message", "Category created Successfully !");
			}
		}

		return mv;

	}
	
	
	//Update the existing product
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		//fetching the product from the database
		Product nProduct = productDAO.get(id);
		//set the product fetched from database
		mv.addObject("product", nProduct);

		return mv;

	}
	
	
	
	
	

	// Handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {

		if(mProduct.getId()==0){
			new ProductValidator().validate(mProduct, results);
		}else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		
		// Check if there are any errors
		if (results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission !");

			return "page";
		}

		logger.info(mProduct.toString());
		
		// create a new product record
		if(mProduct.getId()==0){
			productDAO.add(mProduct);
		}else{
			//update the product if id not 0
			productDAO.update(mProduct);
		}

		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		//fetching the data from the database
		Product product = productDAO.get(id);
		
		boolean isActive= product.isActive();
		//here we modify the state of the product ()active or deactive
		product.setActive(!product.isActive());
		
		productDAO.update(product);
		
		return (isActive)?
				"You have succesfully deactivated the product with id " + product.getId()
		:"You have succesfully activated the product with id "+product.getId();
		
	}

	//To handle category submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		//Add new category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	
	
	// Returning categories for all the requests
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}
	
	
	
}





