package com.abhi.backEndShoppingApp.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abhi.backEndShoppingApp.dao.CategoryDAO;
import com.abhi.backEndShoppingApp.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	
	private static List<Category> categories = new ArrayList<>();
	 
	static{
		Category category = new Category();
		//adding first category
		
		category.setId(1);
		category.setName("Television");
		category.setDescription("Discription for Televisions is here");
		category.setImageURL("CAT_1.png");
		
		categories.add(category);
		
		category = new Category();
		//adding first category
		
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Discription for Mobile is here");
		category.setImageURL("CAT_2.png");
		
		categories.add(category);

		category = new Category();
		//adding first category
		
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Discription for Laptop is here");
		category.setImageURL("CAT_3.png");
		
		categories.add(category);
		
	
		
		
		
		
		
		
		
	}
	
	
	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}


	@Override
	public Category get(int id) {
		
		//enhanced for loop
		for(Category category : categories){
			if(category.getId()==id) return category;
		}
		
		return null;
	}

}
