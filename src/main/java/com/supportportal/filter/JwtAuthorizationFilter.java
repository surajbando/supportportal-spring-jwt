package com.supportportal.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import com.supportportal.constant.SecurityConstant;
import com.supportportal.utility.JWTTokenProvider;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JWTTokenProvider jwtTokenProvider;
	
	
	
	public JwtAuthorizationFilter(JWTTokenProvider jwtTokenProvider) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(request.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
			response.setStatus(200);
		} else {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeader == null || authorizationHeader.startsWith(SecurityConstant.TOKEN_HEADER)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		
	}

}
