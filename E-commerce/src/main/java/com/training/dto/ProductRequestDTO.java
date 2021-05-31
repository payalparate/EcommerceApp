package com.training.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class ProductRequestDTO {
	
	@NotEmpty(message="productName is required")
	private String productName;
	@NotEmpty(message="categoryName is required")
	private String categoryName;
}
