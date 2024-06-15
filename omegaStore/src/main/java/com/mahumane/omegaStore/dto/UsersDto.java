package com.mahumane.omegaStore.dto;



import jakarta.validation.constraints.NotBlank;

public record UsersDto(
		int id,
		
		@NotBlank
		String name,
		
		@NotBlank
		String pass,
		String roles
		
		) {}
