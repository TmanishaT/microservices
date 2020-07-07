package com.microservice.example.forex;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "forex-service") // Declares that this is a Feign Client and
										// the url at which forex-service is
										// present is localhost:8100 , used when 1 microservice directly calls other microservice
@FeignClient(name="netflix-zuul-api-gateway-server")
//@RibbonClient(name = "forex-service")//change it to neflix zull apigateway name to provide LB via Zuul
@RibbonClient(name = "netflix-zuul-api-gateway-server")
public interface CurrencyExchangeServiceProxy {

	@RequestMapping("/forex-service/forex-rate/conversion/from/{from}/to/{to}")//the URI of service we want to consume 
	public CurrencyConversion retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
