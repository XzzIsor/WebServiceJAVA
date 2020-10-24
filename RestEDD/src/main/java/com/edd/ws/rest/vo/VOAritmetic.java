package com.edd.ws.rest.vo;

import java.util.ArrayList;

public class VOAritmetic {
	ArrayList<Double> numbers = new ArrayList<Double>();
	String result;
	String operation;
	
	public ArrayList<Double> getNumbers() {
		return numbers;
	}
	public void setNumbers(ArrayList<Double> numbers) {
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
