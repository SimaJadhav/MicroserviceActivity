package com.ibm.ms;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ibm.ms.model.UserCredential;
import com.ibm.ms.repo.AuthenticationRepository;

@SpringBootApplication
@EnableEurekaClient
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}
	
	@Autowired
	AuthenticationRepository repo;
	
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
	private void seedItUp() {
		
		UserCredential user = new UserCredential();
		user =new UserCredential("Sima","12345678");
		repo.save(user);
		
		user = new UserCredential("test","12345");
		repo.save(user);
		
		user = new UserCredential("testuser","56789");
		repo.save(user);
		
	}


	


}


