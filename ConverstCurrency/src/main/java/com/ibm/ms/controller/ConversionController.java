package com.ibm.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ms.service.CurrConversionService;

@RestController
@RequestMapping(path = "/convert")
public class ConversionController {
	
	@Autowired
	CurrConversionService service;
	
	@RequestMapping(path = "/")
	public Double Test() {
		return 1.0;
	}
	
	@RequestMapping(path = "/proxy/{countryCode}/{amount}")
	public Double convertAmountProxy(@PathVariable String countryCode, @PathVariable Double amount) {
		
		Double convertedAmount = service.ConvertCurrencyWithProxy(countryCode, amount);		
		return convertedAmount;
		
	}
	
	@RequestMapping(path = "/discli/{countryCode}/{amount}")
	public Double convertAmountDiscClient(@PathVariable String countryCode, @PathVariable Double amount) {
		
		Double convertedAmount = service.ConvertCurrencyWithDiscoveryCleint(countryCode, amount);		
		return convertedAmount;
		
	}
	
	
	@RequestMapping(path = "/loadbal/{countryCode}/{amount}")
	public Double convertCurrencyWithLoadBalClient(@PathVariable String countryCode, @PathVariable Double amount) {
		
		Double convertedAmount = service.convertCurrencyWithLoadBalClient(countryCode, amount);		
		return convertedAmount;
		
	}
	
	
	@RequestMapping(path = "/lbrest/{countryCode}/{amount}")
	public Double covertCurrencyWithLbrestTemplate(@PathVariable String countryCode, @PathVariable Double amount) {
		
		Double convertedAmount = service.covertCurrencyWithLbrestTemplate(countryCode, amount);		
		return convertedAmount;
		
	}
}
