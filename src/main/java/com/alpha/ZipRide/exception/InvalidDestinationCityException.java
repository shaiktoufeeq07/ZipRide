package com.alpha.ZipRide.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidDestinationCityException extends RuntimeException{

	public InvalidDestinationCityException() {
		super();
	}

}
