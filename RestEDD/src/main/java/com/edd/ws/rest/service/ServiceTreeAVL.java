package com.edd.ws.rest.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.edd.ws.rest.vo.VONodo;
import com.edd.ws.rest.vo.VOTreeAVL;

@Path("/tree")
public class ServiceTreeAVL {
	VOTreeAVL treeAVL = new VOTreeAVL();
	
	@GET
	@Path("/insert")
	@Produces(MediaType.APPLICATION_JSON)
	public VOTreeAVL createTree(@QueryParam("route") String treeRoute) {
		String[] tree = treeRoute.split(",");
		for(String node: tree) {
			System.out.println("Se ingreso " + node);
			treeAVL.insertar(node);
		}
		return treeAVL;
		
	}
	
}
