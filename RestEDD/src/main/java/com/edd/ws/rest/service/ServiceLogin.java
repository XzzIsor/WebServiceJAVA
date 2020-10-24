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

import com.edd.ws.rest.vo.VOAritmetic;
import com.edd.ws.rest.vo.VOUser;

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
	
	
	@GET
	@Path("/validuserget")
	@Produces(MediaType.APPLICATION_JSON)
	public VOUser validateUser(@QueryParam ("user") String user, @QueryParam ("password") String password) {
		System.out.println("User is " + user);
		System.out.println("User is " + password);
		VOUser vo = new VOUser();
		vo.setUser(user);
		vo.setPassword(password);
		vo.setValidate("Usuario invalido");
		if (vo.getUser().equals("admin") && vo.getPassword().equals("1234")) {
			vo.setSum(Integer.parseInt(password) + 2000);
            vo.setValidate("Usuario Valido");
        }
		return vo;
	}
	
	
    @POST
    @Path("/validuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VOUser validUser(VOUser user){
    	user.setValidate("Usuario invalido"); 
    	System.out.println("User is: "+ user.getUser());
    	System.out.println("Password is: "+ user.getPassword());
        if (user.getUser().equals("admin") && user.getPassword().equals("1234")) {
            user.setValidate("Usuario Valido");
        }
        return user;
    } 
    
    @POST
    @Path("/calculate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public VOAritmetic calculateThings(VOAritmetic operator) {
    	int result = 0;
    	if(operator.getOperation().equals("sum")) {
    		for(int i = 0; i < operator.getNumbers().size(); i++ ) {
    			result += operator.getNumbers().get(i) ;
    		}
    	}
    	if(operator.getOperation().equals("sub")) {
    		for(int i = 0; i < operator.getNumbers().size(); i++ ) {
    			result -= operator.getNumbers().get(i) ;
    		}
    	}
    	if(operator.getOperation().equals("mul")) {
    		result = 1;
    		for(int i = 0; i < operator.getNumbers().size(); i++ ) {
    			result *= operator.getNumbers().get(i) ;
    		}
    	}
    	operator.setResult(Integer.toString(result));
    	return operator;
    }
    
    @GET
    @Path("/array")
    @Produces(MediaType.APPLICATION_JSON)
    public VOAritmetic fillArray(@QueryParam("number") String num1) {
    	VOAritmetic test = new VOAritmetic();
    	ArrayList<Double> array = new ArrayList<>();
    	test.setResult("Nada equisde");
    	test.setOperation("Nada :u");
    	
    	for(int i = 0; i < Integer.parseInt(num1); i++) {
    		array.add(Math.random());
    	}
    	
    	test.setNumbers(array);
    	return test;
    	
    }
    
}
