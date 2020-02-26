package com.patharitech.user.rest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.patharitech.db.UserDb;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.co.HRMLoginUser;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.utility.HibernateUtil;
import com.patharitech.utility.ResponseConfig;


@Path("/user")
public class User{
	Logger logger = LoggerFactory.getLogger(User.class);

	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public String getString() {
		return "Connection Created for Login API";

	}
	UserDAOImpl objUserDAOImpl;
	
	public User()
	{
		objUserDAOImpl = new UserDAOImpl();
	}
	
	@SuppressWarnings("unused")
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser( @Valid UserCO objUserCO)  {	
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		responses.setLink("http://patharitech.com/");
		responses.setLocation("add");
		Response.Status objRespStat = null; 
		
		if(objUserCO != null)
		{
			if(objUserCO.getEmail() != null)
			{
				result = objUserDAOImpl.addUser(objUserCO);
				if(result)
				{
					strMessage="added successfuly";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{
					strMessage="user creation failed";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
			}
			
			
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			//return Response.status(objRespStat).entity(responses).build();
		
		}
		return Response.status(objRespStat).entity(responses).build();
	}
	@SuppressWarnings("unused")
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(UserCO objUserCO)  {	
		String username = objUserCO.getUsername();
		
		
		//String password = objUserCO.getPassword();
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		
		if(objUserCO != null)
		{
			if(objUserCO.getUserid() != null)
			{
				result = objUserDAOImpl.deleteUser(objUserCO);
				if(result)
				{
					strMessage="deletion  successful";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{

					strMessage="userid required";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
			}}
			
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			return Response.status(objRespStat).entity(responses).build();
		
	}

	@SuppressWarnings("unused")
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(UserCO input)  {
		logger.info("Entering into HRM login Service");
		logger.debug("Entering into HRM login Service");
		System.out.println("Reached login service");
		String username = input.getUsername();
		String password = input.getPassword();
		
		UserDAOImpl objUserDAOImpl = new UserDAOImpl();
		UserCO objUserCO =  objUserDAOImpl.getUser(username, password);
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		responses.setLink("http://patharitech.com/");
		responses.setLocation("login");
		String strMessage = "";
		Response.Status objRespStat = null; 
		
		if(objUserCO != null)
		{
			if(objUserCO.getEmail() != null)
			{
				strMessage="Login successful";
				objRespStat = Response.Status.ACCEPTED;
			}
			
		}
		else
		{
			strMessage="Wrong Username or Password provided. Please try again";
			objRespStat = Response.Status.UNAUTHORIZED;
			


		}
		responses.setMessage(strMessage);
		responses.setStatus(objRespStat.getStatusCode());	
		
		return Response.status(objRespStat).entity(responses).build();
	}
	@SuppressWarnings("unused")
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(UserCO objUserCO )  {
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		
		if(objUserCO != null)
		{
			if(objUserCO.getUserid() != null)
			{
				result = objUserDAOImpl.updateUser(objUserCO);
				if(result)
				{
					strMessage="updation  successful";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{

					strMessage="updation failed";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
			}}
			
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			return Response.status(objRespStat).entity(responses).build();
	}
	@SuppressWarnings("unused")
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response getAll( ) 
	{
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		UserCO[] objArrUserCO = null;
		objArrUserCO = objUserDAOImpl.getAllUsers();
		
		Response objResponse = null;
		if(objArrUserCO!= null)
		{
			objResponse = Response.ok(objArrUserCO, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			objResponse = Response.status(Response.Status.NOT_FOUND).entity("It Seems some issue.").build();
		}
		return objResponse;
	
	
	  
	}
	@SuppressWarnings("unused")
	@GET
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search( UserCO objUserCO ) 
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		//UserCO objUserCO = null;
		logger.info("userid for search "+objUserCO.getUserid());
		objUserCO = objUserDAOImpl.searchUser(objUserCO);
		Response objResponse = null;
		if(objUserCO!= null)
		{
			objResponse = Response.ok(objUserCO, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			objResponse = Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		return objResponse;
		
		
	
			
}		
	
}

	

