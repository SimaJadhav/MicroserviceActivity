package com.ibm.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ms.model.Product;
import com.ibm.ms.service.RecOrderService;

@RestController
@RequestMapping(path = "/placeOrder")
public class RecOrderController {
	
	@Autowired
	RecOrderService service;
	
	@RequestMapping(path ="/product/{id}")
	public Product findProduct(@PathVariable int id) {
		return service.getProduct(id);
	}
	
	@RequestMapping(path="/",method = RequestMethod.POST )
	public String receiveOrder(@RequestBody List<String> products){
		
		return service.parseOrder(products);
	}

}
