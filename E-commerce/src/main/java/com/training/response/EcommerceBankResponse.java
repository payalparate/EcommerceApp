package com.training.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author payal.parate
 *
 */
@Getter
@Setter
public class EcommerceBankResponse {
	
	private List<BankResponse> bankList = new ArrayList<>();

}
