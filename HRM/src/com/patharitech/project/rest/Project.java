package com.patharitech.project.rest;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.project.co.ProjectCO;
import com.patharitech.project.dao.ProjectDAOImpl;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.user.rest.User;
import com.patharitech.utility.ResponseConfig;
@Path("/project")
public class Project {
	
	//ProjectDAOImpl 	objProjDAOImpl = new ProjectDAOImpl();
	Logger logger = LoggerFactory.getLogger(User.class);
	ProjectDAOImpl objProjDAOImpl;
	public Project()
	{
		objProjDAOImpl = new ProjectDAOImpl();
	}
	
	
	@SuppressWarnings("unused")
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	
	
	public Response CreateProject(ProjectCO objprojCO)
	
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		responses.setLink("http://patharitech.com/");
		responses.setLocation("add");
		if(objprojCO != null)
		{
			
				result = objProjDAOImpl.CreateProj(objprojCO);
				if(result)
				{
					strMessage="created successfuly";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{
					strMessage="could not create";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
		}
			
			
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			//return Response.status(objRespStat).entity(responses).build();
		
		
		return Response.status(objRespStat).entity(responses).build();
	
		
		
		
	  }

	@SuppressWarnings( "unused")
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteProject(ProjectCO objprojCO)
	{
		
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		boolean result = false;
		String strMessage="";
		if(objprojCO != null)
		{
			
			
				result = objProjDAOImpl.deleteProj(objprojCO);
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
	public Response update(ProjectCO objProjectCO ) 
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		responses.setLink("http://patharitech.com/");
		responses.setLocation("update");
		if(objProjectCO != null)
		{
			if(objProjectCO.getProjectid() != null)
			{
				result = objProjDAOImpl.updateProject(objProjectCO);
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
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response search( ProjectCO objProjectCO ) 
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		logger.info("userid for search "+objProjectCO.getProjectid());
	    objProjectCO = objProjDAOImpl.searchProject(objProjectCO);
		Response objResponse = null;
		if(objProjectCO!= null)
		{
			objResponse = Response.ok(objProjectCO, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			objResponse = Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		return objResponse;
	}
	
	
	@SuppressWarnings("unused")
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll( ) 
	{
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		ProjectCO[] objArrProjectCO = null;
		objArrProjectCO = objProjDAOImpl.getAllUsers();
		
		Response objResponse = null;
		if(objArrProjectCO!= null)
		{
			objResponse = Response.ok(objArrProjectCO, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			objResponse = Response.status(Response.Status.NOT_FOUND).entity("It Seems some issue.").build();
		}
		return objResponse;
	
	
	}
	
	
	

}
