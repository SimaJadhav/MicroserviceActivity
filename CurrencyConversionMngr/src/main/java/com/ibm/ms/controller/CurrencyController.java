package com.ibm.ms.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ibm.ms.model.Currency;
import com.ibm.ms.service.CurrencyMngrService;

@RestController
@RequestMapping(path = "/currency")
public class CurrencyController {
	
	private static Logger log = LoggerFactory.getLogger(CurrencyController.class);
	
	@Autowired	
	CurrencyMngrService currencyService;
	
	@RequestMapping(path= "/addUpdateCovFactor/{countryCode}/{conversionFactor}")
	public ResponseEntity<Currency> addCurrencyFactor(@PathVariable String countryCode, @PathVariable BigDecimal conversionFactor) {
		log.info(countryCode+":"+conversionFactor);
		Currency currency = currencyService.addConversionFactor(countryCode, conversionFactor);
		
		if(currency == null) {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(HttpStatus.NOT_FOUND);
			return response;
		}else {
			ResponseEntity<Currency> response = new ResponseEntity<Currency>(currency,HttpStatus.OK);
			return response;
		}
		
	}
	
	
	
	@RequestMapping(path = "/getFactor/{countryCode}")
	public Double getConversionFaction1(@PathVariable String countryCode){
		
		Currency currency = currencyService.getConversionFactor(countryCode);
		if(currency ==null ) {
			
			return null;
		}else {
			return currency.getConversionFactor().doubleValue();
		}
	}
	
	@RequestMapping(value = "/getFactor",method = RequestMethod.POST)
	public Currency getConversionFaction(@RequestBody String countryCode){
		
		Currency currency = currencyService.getConversionFactor(countryCode);
		if(currency ==null ) {
			
			return null;
		}else {
			return currency;
		}
	}
}
