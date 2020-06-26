package com.microservice.example.forex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forex-rate")
public class ForexController {

	@Autowired
	private ForexRepository forexRepository;

	@Autowired
	private Environment environment;

	@RequestMapping("/conversion/from/{from}/to/{to}")
	public ExchangeValue getForexConversionRate(@PathVariable String from, @PathVariable String to) {

		ExchangeValue ex = forexRepository.findByFromAndTo(from, to);

		ex.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return ex;
	}
}
