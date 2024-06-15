package com.mahumane.omegaStore.infrastructure;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorsConfig{
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> error400(MethodArgumentNotValidException ex){
		var error = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(error.stream().map(dataError::new).toList());
	}
	

	
	
	private record dataError(String field, String message) {
		public dataError(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}

}


