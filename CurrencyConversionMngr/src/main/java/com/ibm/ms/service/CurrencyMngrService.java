package com.ibm.ms.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ms.model.Currency;
import com.ibm.ms.repo.CurrencyRepository;

@Service
public class CurrencyMngrService {

	@Autowired
	CurrencyRepository repo;

	public Currency addConversionFactor(String countryCode, BigDecimal factor) {

		try {
			Currency curre = new Currency(countryCode,factor);
			Currency response = repo.save(curre);
			return response;
		}catch(IllegalArgumentException e) {
			return null;
		}
	}
	
	
	public Currency getConversionFactor(String countryCode) {		
		 
			Currency currency = repo.findByCountryCode(countryCode);
			if(currency ==null)
				return null;
			else
				return currency;
	}

}
