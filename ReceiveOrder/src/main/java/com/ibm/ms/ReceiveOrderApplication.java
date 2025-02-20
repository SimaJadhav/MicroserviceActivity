package com.ibm.ms;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.ms.model.Product;
import com.ibm.ms.model.ProductCategory;
import com.ibm.ms.model.ProductTag;
import com.ibm.ms.repository.ProductRepository;

@SpringBootApplication
@EnableEurekaClient
public class ReceiveOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiveOrderApplication.class, args);
	}
	
	@Autowired
	private ProductRepository prepo;

	public Map<Integer, Product> productSeeds = new HashMap<Integer, Product>();

	@Bean
	Map<Integer, Product> productSeeds() {
		return productSeeds;
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			int beanCount = ctx.getBeanDefinitionCount();
			String[] beans = ctx.getBeanDefinitionNames();
			System.out.println("Bean Count = " + beanCount);
			for (int i = 0; i < beanCount; i++) {
				System.out.println(beans[i]);
			}
			seedItUp();

		};
	}

	@Transactional
	public void seedItUp() {
		Product p = new Product(1, "Kitchen Chimney", "6x4. Non-exhaust", ProductCategory.KITCHENELECTRONIC, 200.87);
		p.getTags().add(new ProductTag(1, "kitchen"));
		productSeeds.put(1, p);
		prepo.save(p);
		p = new Product(2, "Persian Carpet", "9x9. Handwoven", ProductCategory.FURNISHING, 1000.45);
		p.getTags().add(new ProductTag(2, "wool"));
		productSeeds.put(2, p);
		prepo.save(p);
		p = new Product(3, "Space Craft Lego", "580 pieces", ProductCategory.TOY, 776.00);
		p.getTags().add(new ProductTag(3, "plastic"));
		productSeeds.put(3, p);
		prepo.save(p);

		
	}
	
}
