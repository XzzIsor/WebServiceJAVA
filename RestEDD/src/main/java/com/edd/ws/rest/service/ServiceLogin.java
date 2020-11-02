package com.edd.ws.rest.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/login")
public class ServiceLogin {

	@GET
	@Path("/send")
	@Produces(MediaType.TEXT_HTML)
	public String sendMessage(@QueryParam("user") String user) {
		String response = "Semen de burro";
		if(user.equals("admin")) {
			response = "Abemus Papa";
		}
		
		return "<html><title>" + "Frase perrona" + "</title>" +
		"<body><h1>" + response + "</h1><h2> Terrible </h2></body></html>";
	}
   
    
}
