package com.mahumane.omegaStore.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductsDto (int id,
		@NotBlank
		String name,
		
		@NotBlank
		String description,
		
		@NotBlank
		String category,
		
		@NotBlank
		String coin_code,
		
		@NotNull
		@Min(value = 0)
		Double price,
		
		int discounts
		
		) {

}
