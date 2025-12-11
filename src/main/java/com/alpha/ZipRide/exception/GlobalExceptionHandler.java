package com.alpha.ZipRide.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alpha.ZipRide.ResponceStructure;


@RestControllerAdvice 
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponceStructure<String> CustomerNotFoundHandler(CustomerNotFoundException ex){
		
		ResponceStructure<String> rs = new ResponceStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Customer Not Found in the Current City");
		rs.setData("Customer Not Found");
		return rs;
	}
	
	@ExceptionHandler(InvalidSourceCity.class)
	public ResponceStructure<String> InvalidExcetionHandler(InvalidSourceCity ex){
		
		ResponceStructure<String> rs = new ResponceStructure<String>();
		rs.setStatuscode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("sorce city not found");
		rs.setData("city Not Found");
		return rs;
	}
	
	@ExceptionHandler(InvalidDestinationCityException.class)
	public ResponceStructure<String> invalidDestinationHandler(InvalidDestinationCityException ex) {

	    ResponceStructure<String> rs = new ResponceStructure<>();
	    rs.setStatuscode(HttpStatus.BAD_REQUEST.value());
	    rs.setMessage("Invalid destination city");
	    rs.setData("invalid");

	    return rs;
	}


}
