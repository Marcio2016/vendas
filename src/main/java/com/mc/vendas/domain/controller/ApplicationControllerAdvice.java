package com.mc.vendas.domain.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.mc.vendas.domain.exeption.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationerros(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();
		
		List<String> messages = bindingResult.getAllErrors()
		.stream().map(errors -> errors.getDefaultMessage())
		.collect(Collectors.toList());
		
		return new ApiErrors(messages);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusExecption(ResponseStatusException ex) {
		String messageErro = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageErro);
		return new ResponseEntity(apiErrors, codigoStatus);
	}
}
