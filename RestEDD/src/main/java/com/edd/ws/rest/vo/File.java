package com.edd.ws.rest.vo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class File {
	
	public File(Archivo archive) {
		try {
			FileWriter w = new FileWriter("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
			BufferedWriter bw = new BufferedWriter(w);
			
			bw.write(archive.getType() + "\n");
			for(int i = 0; i < archive.getNodes().size(); i++) {
				bw.write(archive.getNodes().get(i)+ "\n");
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
