package com.training.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Entity
@Getter
@Setter
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private String accountNumber;
	private int userId;	
	private double totalPrice;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime localDateTime;
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name="orderId")
	private List<OrderItem> orderItem;
	

}
