
package com.training.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class OrderItemRequestDTO {

	@NotEmpty(message="productId is mandatory for placing order")
	private int productId;
	@Size(min=1)
	private int quantity;

}
