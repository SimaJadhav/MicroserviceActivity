package com.ibm.ms.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.ms.model.Currency;

@FeignClient(name = "CurrencyConversionMngr", fallback = CurrencyCoversionMngrFallback.class)
public interface CurrencyFactorServiceProxy {
	
	@RequestMapping(value ="/currency/getFactor",method = RequestMethod.POST)
	public Currency getConversionFaction(String countryCode);

}
