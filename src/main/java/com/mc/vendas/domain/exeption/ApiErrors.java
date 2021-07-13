package com.mc.vendas.domain.exeption;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErrors {

	@Getter
	private List<String> errors;
	
	public ApiErrors(List<String> erros) {
		this.errors = erros;
	}
	
	public ApiErrors(String message) {
		this.errors = Arrays.asList(message);
	}
	
}
