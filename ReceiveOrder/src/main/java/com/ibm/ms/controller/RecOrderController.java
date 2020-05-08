package com.ibm.ms.controller;

import java.util.HashMap;
import java.util.List;

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

import com.ibm.ms.model.Product;
import com.ibm.ms.service.RecOrderService;

@RestController
@RequestMapping(path = "/placeOrder")
public class RecOrderController {
	Logger logger = LoggerFactory.getLogger(RecOrderController.class);
	
	@Autowired
	RecOrderService service;
	
	@RequestMapping(path ="/product/{id}")
	public Product findProduct(@PathVariable int id) {
		return service.getProduct(id);
	}
	
	@RequestMapping(path="/",method = RequestMethod.POST )
	public ResponseEntity<HashMap<String, String>> receiveOrder(@RequestBody List<String> products){
		logger.info(products.toString());
		return service.parseOrder(products);
	}

}
