package com.ibm.ms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.ms.controller.RecOrderController;
import com.ibm.ms.model.Product;
import com.ibm.ms.repository.ProductRepository;


@Service
public class RecOrderService {
	
	Logger logger = LoggerFactory.getLogger(RecOrderService.class);

	@Autowired
	private ProductRepository repo;

	public Product getProduct(Integer id) {
		Optional<Product> oProduct = repo.findById(id);
		if (oProduct.isPresent())
			return oProduct.get();
		else
			return null;

	}

	public ResponseEntity<HashMap<String, String>> parseOrder(List<String> products) {
		// TODO Auto-generated method stub
		//ResponseEntity<String> response= new ResponseEntity<>(HttpStatus.ACCEPTED) ;
		System.out.println("----products:"+products);
		List<Product> prodList = repo.findByNameIn(products);
		System.out.println(prodList);
		
		HashMap<String,String> prod = new HashMap<String,String>();
		
		
		boolean validList = false;
		
		for(String item :products) {
			
			Product prodItem = repo.findByName(item);
			if(prodItem!=null) {
				prod.put(item, String.valueOf(prodItem.getMrp()));
				validList = true;
			}else {
				prod.put(item, "NOT FOUND");
			}
		}
		
		if(validList == false) {
			ResponseEntity<HashMap<String, String>> response = new ResponseEntity<HashMap<String, String>>(prod, HttpStatus.NOT_ACCEPTABLE);
			logger.info(response.toString());
			return response;
			
		}else {
			ResponseEntity<HashMap<String, String>> response = new ResponseEntity<HashMap<String, String>>(prod, HttpStatus.ACCEPTED);
			logger.info(response.toString());
			return response;
		}
		
		//return prodList.toString();
		}
}
