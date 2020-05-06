package com.ibm.ms.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.ms.model.Currency;


@Component
public class CurrencyCoversionMngrFallback implements CurrencyFactorServiceProxy {

	@Override
	public Currency getConversionFaction(String countryCode) {
		System.out.println("Fallback call");
		Currency currency=new Currency();
		currency.setConversionFactor(new BigDecimal(1.00));
		return currency;
	}

}
