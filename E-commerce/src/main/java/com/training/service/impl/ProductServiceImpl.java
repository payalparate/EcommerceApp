package com.training.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.training.dto.CategoryDTO;
import com.training.dto.ProductDTO;
import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.entity.Category;
import com.training.entity.Product;
import com.training.exception.CategoryNotFoundException;
import com.training.exception.ProductNotFoundException;
import com.training.repository.CategoryRepository;
import com.training.repository.ProductRepository;
import com.training.service.ProductService;

/**
 * @author payal.parate
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	
	/**
	 * @param productRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws CategoryNotFoundException 
	 * @throws MethodArgumentNotValidException
	 */
	@Override
	public  ProductResponseDTO getProductsByProductNameAndCategoryName(ProductRequestDTO productRequestDTO) throws ProductNotFoundException, CategoryNotFoundException {
		ProductResponseDTO productResponseDTO= new ProductResponseDTO();
		List<Product> productList =productRepository.findByProductNameContains(productRequestDTO.getProductName());
		if(ObjectUtils.isEmpty(productList))
		{
			logger.error("Product with name : {} ",productRequestDTO.getProductName(), " is not avaiable at this moment ");
			throw new ProductNotFoundException("Product is not avaiable at this moment");
		}
		Category category= categoryRepository.findCategoryByCategoryNameContains(productRequestDTO.getCategoryName());
		
		if((ObjectUtils.isEmpty(category)))
		{
			logger.error("product not found for specified category");
			throw new CategoryNotFoundException("Product is not avaiable at this moment");
		}
		
		 productList=productRepository.findByCategoryId(category.getCategoryId());
		ProductDTO productDTO = new ProductDTO();
	
		List<ProductDTO> productDTOList = new ArrayList<>();
		for (Product product : productList) {
			BeanUtils.copyProperties(product, productDTO);
			productDTOList.add(productDTO);
			logger.info("Product with id : {} ",product.getProductId());
		}
		productResponseDTO.setProductDTOList(productDTOList);
		return productResponseDTO;
	}

	/**
	 * @param productName
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 */
	@Override
	public List<Product> getProductsByProductName(String productName) throws ProductNotFoundException, MethodArgumentNotValidException {
		List<Product> productList=productRepository.findByProductNameContains(productName);
		if(ObjectUtils.isEmpty(productList))
		{
			logger.error("Product with name : {} " ,productName, " is not avaiable at this moment ");
			throw new ProductNotFoundException("Product is not deliverable at this point of time");
		}
		return productList;
	}

	/**
	 * 
	 * @return
	 * @throws ProductNotFoundException
	 */
	@Override
	public Product getProductByProductId(int productId) throws ProductNotFoundException {
		Optional<Product> product=productRepository.findById(productId);
		if(product.isEmpty())
		{
			logger.error("Product with id : {} " ,productId, " is not avaiable at this moment ");
			throw new ProductNotFoundException("Product not found");
		}
		logger.info("Retrieving product with id : {} ", productId);
		return product.get();
	}

	/**
	 * @return
	 * @throws ProductNotFoundException
	 */
	@Override
	public void updateProductDetails(Product product) throws ProductNotFoundException {
		Optional<Product> product2=productRepository.findById(product.getProductId());
		if(product2.isEmpty())
		{
			logger.error("Exception while updating product details");
			throw new ProductNotFoundException("Product not found");
		}
		product.setCategoryId(product2.get().getCategoryId());
		product.setPrice(product2.get().getPrice());
		product.setProductName(product2.get().getProductName());
		product.setQuantity(product2.get().getQuantity());
		productRepository.save(product);
		
	}

}
