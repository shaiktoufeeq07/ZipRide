package com.alpha.ZipRide.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidSourceCity extends RuntimeException{

	public InvalidSourceCity() {
		super();
	}

	
}
