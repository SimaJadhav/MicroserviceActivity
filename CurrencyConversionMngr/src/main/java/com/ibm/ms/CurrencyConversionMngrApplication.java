package com.ibm.ms;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.ms.model.Currency;
import com.ibm.ms.repo.CurrencyRepository;
import com.ibm.ms.service.CurrencyMngrService;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class CurrencyConversionMngrApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(CurrencyConversionMngrApplication.class, args);
		
	}
	
	@Autowired
	CurrencyRepository   repo;
	
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
	public  void seedItUp() {
			
		repo.saveAll(Arrays.asList(new Currency("INR", new BigDecimal(1.00)),
				new Currency("USD", new BigDecimal(75.87)),
				new Currency("EUR", new BigDecimal(72.10)),
				new Currency("GBP", new BigDecimal(90.00))));
		
		
	}

}
