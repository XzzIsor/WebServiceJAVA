package com.edd.ws.rest.vo;

import java.util.ArrayList;

public class Archivo {
	private String type;
	private ArrayList<String> nodes = new ArrayList<>();
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<String> nodes) {
		this.nodes = nodes;
	}
}
