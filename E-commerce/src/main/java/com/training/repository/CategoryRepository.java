package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.entity.Category;

/**
 * @author payal.parate
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	/**
	 * @param categoryName
	 * @return
	 */
	Category findCategoryByCategoryNameContains(String categoryName);
	/**
	 * @param categoryName
	 * @return
	 */
	List<Category> findByCategoryNameContains(String categoryName);

}
