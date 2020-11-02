package com.edd.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.edd.ws.rest.vo.Archivo;
import com.edd.ws.rest.vo.index;

@Path("/tree")
public class ServiceTreeN {
	index tree = new index();
	@POST
	@Path("/start")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public index inicializateArchive(Archivo response) {
		Archivo archive = response;
		tree = new index(archive);
		return tree;
	}

}
