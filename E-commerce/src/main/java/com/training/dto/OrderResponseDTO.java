package com.training.dto;

import java.time.LocalDateTime;
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
public class OrderResponseDTO {
	private int userId;
	@NotEmpty()
	private String accountNumber;
	private List<OrderItemRequestDTO> orderItemRequestDTOList;
	private LocalDateTime localDateTime;
}
