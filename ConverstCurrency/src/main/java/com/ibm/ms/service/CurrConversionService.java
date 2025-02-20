package com.ibm.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.ms.model.Currency;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@Component
@RibbonClient(name = "CurrencyConversionMngr")
public class CurrConversionService {
	
	@Autowired
	private CurrencyFactorServiceProxy proxy;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	LoadBalancerClient loadbalancedClient;
	
	@Bean
	@LoadBalanced
	RestTemplate createRestTemplate() {
		RestTemplateBuilder template = new RestTemplateBuilder();
		return template.build();	
	}
	
	@Autowired
	@Lazy
	RestTemplate lbrestTemplate;
	
	public Double conertCurrency(String countryCode, double amount) {
		
		//Currency conversionFactor = proxy.getConversionFaction(countryCode);
		//System.out.println("conversionFactor:"+conversionFactor.getConversionFactor());
		Double convertedCurrency = amount * ConvertCurrencyWithDiscoveryCleint(countryCode, amount);
		System.out.println("convertedCurrency:"+convertedCurrency);
		return convertedCurrency;
	}
	
  public Double ConvertCurrencyWithProxy(String countryCode, double amount) {
		
		Currency currency = proxy.getConversionFaction(countryCode);
		System.out.println("conversionFactor:"+currency);
	    double val = amount * (currency.getConversionFactor().doubleValue());
	    System.out.println(val);
		return val;
	}
	
	@HystrixCommand( fallbackMethod = "getDefaultConversionFactor")
	public Double ConvertCurrencyWithDiscoveryCleint(String countryCode, double amount) {
		ServiceInstance instance=null;
		
		List<ServiceInstance> instances =  discoveryClient.getInstances("CurrencyConversionMngr");
		System.out.println("No of instances:"+instances.size());
		if(!instances.isEmpty()) {
			instance = instances.get(0);
		} else {
			System.out.println("there are no instances....");
		}
		
		String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/currency/getFactor";
		System.out.println("Calling URL :" + url);

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> countryCodetHttpEntity = new HttpEntity<String>(countryCode);
		ResponseEntity<Currency> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
				countryCodetHttpEntity, Currency.class);

		Currency response = responseEntity.getBody();
		//return ceateProductResponseDTO(dResponse, p);
		System.out.println(response);
		return amount * response.getConversionFactor().doubleValue();
	}
	
	
	
	public Double convertCurrencyWithLoadBalClient(String countryCode, double amount) {
		
		ServiceInstance instance = loadbalancedClient.choose("CurrencyConversionMngr");
		String url = "http://"+instance.getHost()+":"+instance.getPort()+"/currency/getFactor";
		System.out.println("calling URL:"+url);
	
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> countryCodetHttpEntity = new HttpEntity<String>(countryCode);
		ResponseEntity<Currency> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
				countryCodetHttpEntity, Currency.class);

		Currency response = responseEntity.getBody();
		//return ceateProductResponseDTO(dResponse, p);
		System.out.println(response);
		return amount * response.getConversionFactor().doubleValue();
	}
	
	
	public Double covertCurrencyWithLbrestTemplate(String countryCode, double amount) {
		
		HttpEntity<String> countryCodetHttpEntity = new HttpEntity<String>(countryCode);
		ResponseEntity<Currency> responseEntity = lbrestTemplate.exchange("http://CurrencyConversionMngr/currency/getFactor", HttpMethod.POST,
				countryCodetHttpEntity, Currency.class);

		Currency response = responseEntity.getBody();
		//return ceateProductResponseDTO(dResponse, p);
		System.out.println(response);
		return amount * response.getConversionFactor().doubleValue();
	}
	public Double getDefaultConversionFactor(String countryCode, double amount) {
		return 1.00;
	}

}
