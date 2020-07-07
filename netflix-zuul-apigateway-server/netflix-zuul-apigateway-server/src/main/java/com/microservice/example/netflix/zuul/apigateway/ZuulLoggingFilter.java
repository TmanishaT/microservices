package com.microservice.example.netflix.zuul.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	// creating Logger object
	private Logger logger = LoggerFactory.getLogger(ZuulLoggingFilter.class);

	@Override
	public Object run() throws ZuulException {
		// getting the current HTTP request that is to be handle
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		// prints the detail of the requestin the log
		logger.info("request -> {} request uri-> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true; // executing filter for every request
	}

	@Override
	public int filterOrder() {
		return 1; // setting filter order to 1
	}

	@Override
	public String filterType() {
		return "pre"; // intercept all the request before execution
	}

}
