package com.patharitech.client.rest;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.client.co.ClientCO;
import com.patharitech.client.dao.ClientDaoImpl;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.user.rest.User;
import com.patharitech.utility.ResponseConfig;

@Path("/client")

public class Client {
	Logger logger = LoggerFactory.getLogger(Client.class);
	ClientDaoImpl objClientDaoImpl=null;
	public Client()
	{
		objClientDaoImpl = new ClientDaoImpl();
	}
	
	@SuppressWarnings("unused")
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	
	
	public Response addClient(  ClientCO objClientCO) 
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		responses.setLink("http://patharitech.com/");
		responses.setLocation("add");
		Response.Status objRespStat = null; 
		
		if(objClientCO != null)
		{
			
				ClientDaoImpl objClientDAOImpl=new ClientDaoImpl();
				result = objClientDAOImpl.addUser(objClientCO);
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
			
		
		
		
		
		return Response.status(objRespStat).entity(responses).build();
		
	}
	 
	@SuppressWarnings("unused")
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response deleteClient(  ClientCO objClientCO) 
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		responses.setLink("http://patharitech.com/");
		responses.setLocation("delete");
		Response.Status objRespStat = null; 
		
		

		if(objClientCO != null)
		{
			
				ClientDaoImpl objClientDAOImpl=new ClientDaoImpl();
				result = objClientDAOImpl.deleteUser(objClientCO);
				if(result)
				{
					strMessage="deleted successfuly";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{
					strMessage="deletion failed";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
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
		
		public Response update(  ClientCO objClientCO) 
		{
		
			boolean result = false;
			String strMessage="";
			com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
			Response.Status objRespStat = null; 
			if(objClientCO != null)
				       
				   result = objClientDaoImpl.updateClient(objClientCO);
				
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
				
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			return Response.status(objRespStat).entity(responses).build();	
			
		}
	    @SuppressWarnings("unused")
		@GET
		@Path("/search")
		@Produces(MediaType.APPLICATION_JSON)
	    public Response search( ClientCO objClientCO ) 
	    {

			boolean result = false;
			String strMessage="";
			com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
			Response.Status objRespStat = null; 
			logger.info("userid for search "+objClientCO.getClientid());
			objClientCO = objClientDaoImpl.searchClient(objClientCO);
			Response objResponse = null;
			if(objClientCO!= null)
			{
				objResponse = Response.ok(objClientCO, MediaType.APPLICATION_JSON).build();
			}
			else
			{
				objResponse = Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
			}
			return objResponse;
			
	    }


}
