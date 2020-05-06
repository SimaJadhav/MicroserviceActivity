package com.ibm.ms.model;

import java.math.BigDecimal;

public class Currency {
	
	String countryCode;
	BigDecimal conversionFactor;
	public Currency() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Currency(String countryCode, BigDecimal conversionFactor) {
		super();
		this.countryCode = countryCode;
		this.conversionFactor = conversionFactor;
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
