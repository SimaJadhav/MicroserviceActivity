package com.ibm.ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.ibm.ms.model.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> findByNameIn(List<String> name);
	
	Product findByName(String name);

}
