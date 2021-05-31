package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.entity.Product;

/**
 * @author payal.parate
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	/**
	 * @param productName
	 * @return
	 */
	List<Product> findByProductNameContains(String productName);
	/**
	 * @param categoryId
	 * @return
	 */
	List<Product> findByCategoryId(int categoryId);
	

}
