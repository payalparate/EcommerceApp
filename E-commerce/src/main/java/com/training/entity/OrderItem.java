package com.training.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
/**
 * @author payal.parate
 *
 */
@Entity
@Getter
@Setter
public class OrderItem{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderItemId;
	//private int orderId;
	private int productId;
	private double price;
	private int quantity;
	
}
