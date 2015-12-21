package br.com.cardoso.guilherme.desafioTJ.data.jpa.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}
	
}
