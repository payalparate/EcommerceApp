package com.training.service;

import java.util.List;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.entity.Product;
import com.training.exception.CategoryNotFoundException;
import com.training.exception.ProductNotFoundException;

/**
 * @author payal.parate
 *
 */
public interface ProductService {
	/**
	 * @param productRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws CategoryNotFoundException 
	 */
	ProductResponseDTO getProductsByProductNameAndCategoryName(ProductRequestDTO productRequestDTO) throws ProductNotFoundException, CategoryNotFoundException;
	/**
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 */
	List<Product> getProductsByProductName(String productName) throws ProductNotFoundException, MethodArgumentNotValidException;
    /**
     * @param productId
     * @return
     * @throws ProductNotFoundException
     */
    Product getProductByProductId(int productId) throws ProductNotFoundException;
    /**
     * @param product
     * @throws ProductNotFoundException
     */
    public void updateProductDetails(Product product) throws ProductNotFoundException;
}
