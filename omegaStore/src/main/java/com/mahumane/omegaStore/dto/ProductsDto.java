package com.mahumane.omegaStore.dto;


import jakarta.validation.constraints.NotBlank;


public record ProductsDto (int id,
		@NotBlank
		String name,
		
		@NotBlank
		String description,
		
		@NotBlank
		String category,
		
		@NotBlank
		String coin_code,
		
		@NotBlank
		String price,
		
		int discounts
		
		) {

}
