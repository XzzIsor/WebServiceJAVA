package com.edd.ws.rest.vo;

import java.util.ArrayList;

public class VOAritmetic {
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	String result;
	String operation;
	
	public ArrayList<Integer> getNumbers() {
		return numbers;
	}
	public void setNumbers(ArrayList<Integer> numbers) {
		this.numbers = numbers;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
