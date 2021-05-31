package com.training.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.entity.Category;
import com.training.entity.Product;
import com.training.exception.CategoryNotFoundException;
import com.training.exception.ProductNotFoundException;
import com.training.exception.handler.MessageResponse;
import com.training.service.CategoryService;
import com.training.service.ProductService;

/**
 * @author payal.parate
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	
	static Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	/**
	 * @param productRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 * @throws CategoryNotFoundException 
	 */
	@PostMapping()
	public ResponseEntity<?> getProductsByProductNameAndCategoryName(@Valid @RequestBody(required=false) ProductRequestDTO productRequestDTO) throws ProductNotFoundException,MethodArgumentNotValidException, CategoryNotFoundException
	{
		ProductResponseDTO dto=productService.getProductsByProductNameAndCategoryName(productRequestDTO);
		if(dto!=null)
		{
			logger.info("Product details fetched");
			return new ResponseEntity<ProductResponseDTO>(dto, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid details"));
		
	}
	
	/**
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 */
	@GetMapping()
	public ResponseEntity<?> getProductsByProductName(@RequestParam String productName) throws ProductNotFoundException, MethodArgumentNotValidException
	{
		List<Product> product=productService.getProductsByProductName(productName);	
		if(product !=null)
		{
			List<Product> productList = new ArrayList<>();
			for (Product product2 : product) {
				productList.add(product2);
			}

			return new ResponseEntity<>(productList, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid product name")); 
	}
	
	/**
	 * @param categoryName
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 * @throws CategoryNotFoundException 
	 */
	@GetMapping("/categories")
	public ResponseEntity<?> getProductsByCategoryName(@RequestParam String categoryName) throws ProductNotFoundException, MethodArgumentNotValidException, CategoryNotFoundException
	{
		List<Category> categoryList=categoryService.findCategoryByCategoryName(categoryName);
		if(categoryList !=null)
		{
			List<Category> categoryList2 = new ArrayList<>();
			for (Category category : categoryList) {
				categoryList2.add(category);
			}

			return new ResponseEntity<>(categoryList2, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please enter valid category name")); 
	}

}
