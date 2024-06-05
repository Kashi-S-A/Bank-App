package com.BankingApplication.BankApplication.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseStructure<T> {

	private int statusCode;
	private String message;
	private T data;
	private List<T> listdata;
}

