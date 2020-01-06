/**
 * 
 *//*
package com.service.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

*//**
 * @author vijpande
 *
 *//*
@Component
@Order(1)
public class TransactionFilter implements Filter {

	org.slf4j.Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        LOG.info("Starting a transaction for req : {}",  req.getRequestURI());
 
        chain.doFilter(request, response);
        LOG.info("Committing a transaction for req : {}",      req.getRequestURI());
        LOG.info("Logging Response :{}",  response.getContentType());
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 LOG.info("Init Filter has started : " + filterConfig.getFilterName());
		 LOG.debug("Init Filter has started : " + filterConfig.getInitParameterNames());
		 LOG.debug("Init Filter has started : " + filterConfig.getServletContext());
		 
		
	}

	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

    // other methods 
}*/