package com.ibm.ms.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CurrencyId.class)
public class Currency {

	
	@Id
	private String countryCode;
	
	private BigDecimal conversionFactor;

	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Currency(String countryCode, BigDecimal factor) {
		this.countryCode = countryCode;
		this.conversionFactor = factor;
	}

	

	

	

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal conversionFactor) {
		this.conversionFactor = conversionFactor;
	}

	@Override
	public String toString() {
		return "Currency [countryCode=" + countryCode + ", conversionFactor=" + conversionFactor + "]";
	}

	
	
	
}
