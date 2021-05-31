package com.training.service;

import java.util.List;

import com.training.entity.Category;
import com.training.exception.CategoryNotFoundException;
import com.training.exception.ProductNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface CategoryService {
	
	/**
	 * @param categoryName
	 * @return
	 * @throws ProductNotFoundException
	 * @throws CategoryNotFoundException 
	 */
	List<Category> findCategoryByCategoryName(String categoryName) throws ProductNotFoundException, CategoryNotFoundException;

}
