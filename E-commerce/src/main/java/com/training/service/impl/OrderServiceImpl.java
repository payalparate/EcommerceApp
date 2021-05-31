package com.training.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.training.dto.OrderItemRequestDTO;
import com.training.dto.OrderRequestDTO;
import com.training.dto.OrderResponseDTO;
import com.training.entity.OrderItem;
import com.training.entity.Orders;
import com.training.entity.Product;
import com.training.exception.AccountNotFoundException;
import com.training.exception.InsufficientBalanceException;
import com.training.exception.InsufficientQuantityException;
import com.training.exception.ProductNotFoundException;
import com.training.exception.TransactionFailedException;
import com.training.exception.UserNotFoundException;
import com.training.feignClient.BankClient;
import com.training.repository.OrderRepository;
import com.training.response.BankResponse;
import com.training.service.OrderService;
import com.training.service.ProductService;
import com.training.service.UserService;

import feign.FeignException;

/**
 * @author payal.parate
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	BankClient bankClient;

	static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	/**
	 * @param orderRequestDTO
	 * @return
	 * @throws ProductNotFoundException
	 * @throws MethodArgumentNotValidException
	 * @throws BeansException
	 * @throws UserNotFoundException
	 * @throws InsufficientQuantityException
	 * @throws TransactionFailedException
	 * @throws AccountNotFoundException
	 * @throws InsufficientBalanceException 
	 */
	@Override
	public OrderResponseDTO saveOrder(OrderRequestDTO orderRequestDTO) throws ProductNotFoundException, BeansException,AccountNotFoundException,
			UserNotFoundException, InsufficientQuantityException, TransactionFailedException, InsufficientBalanceException {
		Orders order = new Orders();
		if (ObjectUtils.isEmpty(userService.getUserByUserId(orderRequestDTO.getUserId()))) {
			logger.error("User not found for id " + orderRequestDTO.getUserId());
			throw new UserNotFoundException("User not found");
		}

		OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
		List<OrderItem> orderItemList = new ArrayList<>();
		List<OrderItemRequestDTO> orderItemRequestDTOList = orderRequestDTO.getOrderItemRequestDTOList();
		Double price = 0.0;
		for (OrderItemRequestDTO orderItemDTO2 : orderItemRequestDTOList) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(orderItemDTO2, orderItem);
			
			Product product = productService.getProductByProductId(orderItemDTO2.getProductId());
			if (ObjectUtils.isEmpty(product)) {
				logger.error("Product not found for id : {}", orderItemDTO2.getProductId());
				throw new ProductNotFoundException("Unable to find product");
			}
			orderItem.setProductId(product.getProductId());
			orderItem.setPrice(product.getPrice());
			if (orderItemDTO2.getQuantity() > product.getQuantity()) {
				logger.error("Product you are requesting for , is not avaiable at this moment");
				throw new InsufficientQuantityException("Insufficient quantity");
			}
			orderItem.setQuantity(orderItemDTO2.getQuantity());
			product.setQuantity(product.getQuantity() - orderItem.getQuantity());
			productService.updateProductDetails(product);
			logger.info("Avaiable quanitity updated in database after purchase");
			price = price + (orderItem.getPrice() * orderItem.getQuantity());
			orderItemList.add(orderItem);
		}
		order.setTotalPrice(price);
		order.setUserId(orderRequestDTO.getUserId());
		order.setAccountNumber(orderRequestDTO.getAccountNumber());
		order.setOrderItem(orderItemList);
		order.setLocalDateTime(LocalDateTime.now());

		BankResponse bankResponse = new BankResponse();
		bankResponse.setAccountNumber(orderRequestDTO.getAccountNumber());
		bankResponse.setUserId(orderRequestDTO.getUserId());
		bankResponse.setAmount(price);
		BankResponse response = new BankResponse();
		try
		{
		   response = bankClient.saveTransaction(bankResponse);
		logger.info("Transaction for userId : {} ", order.getUserId(), " is success ");
		Orders savedOrder = orderRepository.save(order);
		logger.info("Your order is placed");
		BeanUtils.copyProperties(savedOrder, orderResponseDTO);
		orderResponseDTO.setOrderItemRequestDTOList(orderItemRequestDTOList);
		}
		catch(FeignException e)
		{			
			logger.error("Bank server not responding at this point of time");
			if(e.getMessage().contains("AccountNotFoundException"))
			{
				throw new AccountNotFoundException("Account not found");
			}
			if(e.getMessage().contains("InsufficientBalanceException"))
			{
				throw new InsufficientBalanceException("Insufficient balance");
			}		
			
		}
		return orderResponseDTO;
		
	}

	/**
	 * @param userId
	 * @return
	 * @throws UserNotFoundException
	 * @throws MethodArgumentNotValidException
	 */
	@Override
	public List<OrderResponseDTO> getOrderByUserId(int userId) throws UserNotFoundException {
		List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
		List<Orders> orderList = orderRepository.findOrdersByUserId(userId);
		if (ObjectUtils.isEmpty(orderList)) {
			logger.error("User not found for id : {} ", userId);
			throw new UserNotFoundException("Unable to locate user");
		}

		for (Orders orders : orderList) {
			List<OrderItem> orderItemList = new ArrayList<>();
			orderItemList = orders.getOrderItem();
			 logger.info("Fetching details for order id : {} ", orders.getOrderId());
			List<OrderItemRequestDTO> orderItemRequestDTOList = new ArrayList<>();
			OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

			for (OrderItem orderItem : orderItemList) {

				OrderItemRequestDTO orderItemRequestDTO = new OrderItemRequestDTO();
				BeanUtils.copyProperties(orderItem, orderItemRequestDTO);

				orderItemRequestDTOList.add(orderItemRequestDTO);
			}

			BeanUtils.copyProperties(orders, orderResponseDTO);
			orderResponseDTO.setOrderItemRequestDTOList(orderItemRequestDTOList);
			orderResponseDTOList.add(orderResponseDTO);

		}
       
		return orderResponseDTOList;
	}

}
