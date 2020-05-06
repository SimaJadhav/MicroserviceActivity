package com.ibm.ms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.ms.model.Product;
import com.ibm.ms.repository.ProductRepository;


@Service
public class RecOrderService {

	@Autowired
	private ProductRepository repo;

	public Product getProduct(Integer id) {
		Optional<Product> oProduct = repo.findById(id);
		if (oProduct.isPresent())
			return oProduct.get();
		else
			return null;

	}

	public String parseOrder(List<String> products) {
		// TODO Auto-generated method stub
		//ResponseEntity<String> response= new ResponseEntity<>(HttpStatus.ACCEPTED) ;
		System.out.println("----products:"+products);
		List<Product> prodList = repo.findByNameIn(products);
		System.out.println(prodList);
		
		return prodList.toString();
		}
}
