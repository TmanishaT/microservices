package com.microservice.example.forex;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currency")
public class CurrencyConvertorController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@RequestMapping("/convert/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversion convert(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal amount) {
		Map<String, String> uris = new HashMap<>();
		uris.put("from", from);
		uris.put("to", to);

		ResponseEntity<CurrencyConversion> response = new RestTemplate().getForEntity(
				"http://localhost:8000/forex-rate/conversion/from/{from}/to/{to}", CurrencyConversion.class, uris);

		CurrencyConversion res = response.getBody();
		return new CurrencyConversion(res.getId(), from, to, res.getConversionRate(), res.getPort(), amount,
				amount.multiply(res.getConversionRate()));
	}

	//Feign proxy provide a better altrernative than RESTTemplate client to call the REST API
	@RequestMapping("/feign-convert/from/{from}/to/{to}/amount/{amount}")
	public CurrencyConversion convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal amount) {

		CurrencyConversion response = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversion(response.getId(), from, to, response.getConversionRate(), response.getPort(),
				amount, amount.multiply(response.getConversionRate()));
	}
}
