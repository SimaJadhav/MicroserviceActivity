package com.ibm.ms.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;

public class CurrencyId implements Serializable{
	
	
	private String countryCode;
	public CurrencyId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrencyId(String countryCode) {
		super();
		this.countryCode = countryCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyId other = (CurrencyId) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		return true;
	}
	

	
}
