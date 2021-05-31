package com.training.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.training.entity.Category;
import com.training.exception.CategoryNotFoundException;
import com.training.exception.ProductNotFoundException;
import com.training.repository.CategoryRepository;
import com.training.service.CategoryService;

/**
 * @author payal.parate
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;

	static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	/**
	 * @throws CategoryNotFoundException 
	 *
	 */
	@Override
	public List<Category> findCategoryByCategoryName(String categoryName) throws CategoryNotFoundException {
		logger.info("Searching in repository for category name : "+categoryName);
		List<Category> categoryList= categoryRepository.findByCategoryNameContains(categoryName);
		if(ObjectUtils.isEmpty(categoryList))
		{
			logger.info("No category found for requested data");
			throw new CategoryNotFoundException("Required Category is not avaiable at this point of time");
		}
		return categoryList;
	}

}
