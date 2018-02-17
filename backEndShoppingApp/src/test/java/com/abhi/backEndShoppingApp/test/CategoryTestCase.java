package com.abhi.backEndShoppingApp.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.abhi.backEndShoppingApp.dao.CategoryDAO;
import com.abhi.backEndShoppingApp.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.abhi.backEndShoppingApp");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Rocket");
		category.setDescription("This is disciption about Rocket");
		category.setImageURL("CAT_3.png");

		assertEquals("NOT Successfully added a category inside the table", true, categoryDAO.add(category));
	}
	
	/*@Test
	public void testGetCategory(){
		category = categoryDAO.get(2);
		
		assertEquals("NOT Successfully fetched a single category from the table.!","bikes",category.getName());
		
	}*/
	
/*	@Test
	public void testUpdateCategory(){
		category = categoryDAO.get(2);
		category.setName("new Bike");
		
		assertEquals("NOT Successfully updated a single category in the table.!",true,categoryDAO.update(category));
		
	}*/
	
	/*@Test
	public void testDeleteCategory(){
		category = categoryDAO.get(2);
				
		assertEquals("NOT Successfully disabled a single category in the table.!",true,categoryDAO.delete(category));
		
	}*/
	
/*	@Test
	public void testListCategory(){
						
		assertEquals("NOT Successfully fetched the list of categories from table.!",1,categoryDAO.list().size());
		
	}*/
	
	
}
