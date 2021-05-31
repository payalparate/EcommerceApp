package com.training.dto;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class ProductDTO {

	private int productId;
	private String productName;
	private double price;
	@Size(min=1)
	private int quantity;
}
