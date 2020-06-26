package com.microservice.example.forex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForexRepository extends JpaRepository<ExchangeValue, Long> {

	
	public ExchangeValue findByFromAndTo(String from, String to);
}
