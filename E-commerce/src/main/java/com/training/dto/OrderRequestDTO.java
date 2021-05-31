package com.training.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class OrderRequestDTO {

	private int userId;
	@NotEmpty()
	private String accountNumber;
	@NotEmpty
	private List<OrderItemRequestDTO> orderItemRequestDTOList;
}
