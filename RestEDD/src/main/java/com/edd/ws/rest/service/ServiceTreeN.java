package com.edd.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.edd.ws.rest.vo.Archivo;
import com.edd.ws.rest.vo.File;
import com.edd.ws.rest.vo.VOResponse;
import com.edd.ws.rest.vo.index;

@Path("/tree")
public class ServiceTreeN {
	
	
	@POST
	@Path("/start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public index inicializateArchive(Archivo response) {
		File file = new File(response);
		Archivo archive = response;
		index tree = new index(archive);
		return tree;
	}
	
	@GET
	@Path("/sons")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendSons(@QueryParam("node") String data) {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.showSons(data));
		return response;
	}
	
	@GET
	@Path("/sibli")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendSiblings(@QueryParam ("node") String data) {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.showSiblings(data));
		return response;
	}
	
	@GET
	@Path("/leaves")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendLeaves() {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.showLeaves());
		return response;
	}
	
	@GET
	@Path("/level")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendNodesLvl(@QueryParam ("lvl") String data) {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.showNodesByLevel(data));
		return response;
	}
	
	@GET
	@Path("/witdh")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendTreeByWidth() {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.showTree());
		return response;
	}
	
	@GET
	@Path("/prune")
	@Produces(MediaType.APPLICATION_JSON)
	public index sendPruneTree(@QueryParam ("lvl") String data) {
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		tree.pruneTree(data);
		return tree;
	}
	
	@GET
	@Path("/branch")
	@Produces(MediaType.APPLICATION_JSON)
	public VOResponse sendBranches() {
		VOResponse response = new VOResponse();
		index tree = new index("C:/Users/JUAN ESTEBAN/Desktop/Values.txt");
		response.setResponse(tree.traverseBranches());
		return response;
	}
	
	
	
}
