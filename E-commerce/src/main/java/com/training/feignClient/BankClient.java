package com.training.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.exception.AccountNotFoundException;
import com.training.response.BankResponse;

/**
 * @author payal.parate
 *
 */
@FeignClient(name="http://BANK-SERVICE/Bank/transactions")
public interface BankClient {
	
	/**
	 * @param bankResponse
	 * @return
	 * @throws AccountNotFoundException
	 */
	@PostMapping()
	public BankResponse saveTransaction(@RequestBody BankResponse bankResponse)throws AccountNotFoundException;

}
