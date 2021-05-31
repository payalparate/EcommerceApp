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
@Getter
@Setter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String categoryName;
	private String description;
}
