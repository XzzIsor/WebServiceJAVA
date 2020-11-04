package com.edd.ws.rest.vo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fichero {

	private FileReader file;
	private BufferedReader bufferFile;
	private ArrayList<String> text = new ArrayList<String>();

	public Fichero(String path) {
		super();
		ReadFile(path);
	}

	public ArrayList<String> getText() {
		return text;
	}

	private void ReadFile(String path) {
		try {
			file = new FileReader(path);
			bufferFile = new BufferedReader(file);
			String line;
			while ((line = bufferFile.readLine()) != null)
				text.add(line);
			
		} catch (IOException e) {
			System.out.println("probably the file's path it's wrong");
			e.printStackTrace();
		}
	}
}
