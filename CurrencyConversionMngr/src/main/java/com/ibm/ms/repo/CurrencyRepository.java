package com.ibm.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ms.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
	
	Currency findByCountryCode(String country_code);

}
