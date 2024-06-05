package com.mahumane.omegaStore.infrastructure;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mahumane.omegaStore.repository.UsersRepository;
import com.mahumane.omegaStore.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter{

	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private TokenService tokenService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		
		if(token != null) {

		 var subject = tokenService.getSubject(token.replace("Bearer ", ""));
		 var user = usersRepository.findByName(subject);
		 
		 var authentication = new UsernamePasswordAuthenticationToken(user,null,  user.getAuthorities());
		 
		 SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
