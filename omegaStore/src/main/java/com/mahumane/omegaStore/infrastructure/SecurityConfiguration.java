package com.mahumane.omegaStore.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration {

	
	@Autowired
	private FilterToken filterToken;
	@Bean
	public SecurityFilterChain chain (HttpSecurity http) throws Exception {
				http.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/user/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/user/register").permitAll()
						.requestMatchers(HttpMethod.GET, "/products/{id}").permitAll()
						.requestMatchers(HttpMethod.GET, "/products").permitAll()
						.requestMatchers(HttpMethod.GET, "/products/search").permitAll()
						.anyRequest().authenticated()
						
						)
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class);
			return	http.build();				
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

