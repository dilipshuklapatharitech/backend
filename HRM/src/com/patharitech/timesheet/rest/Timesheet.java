package com.patharitech.timesheet.rest;
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
import com.patharitech.timesheet.co.EmpTimeCO;
import com.patharitech.timesheet.co.TimesheetCO;
import com.patharitech.timesheet.dao.TimesheetDAOImpl;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.co.HRMLoginUser;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.user.rest.User;
import com.patharitech.utility.HibernateUtil;
import com.patharitech.utility.ResponseConfig;


@Path("/timesheet")

public class Timesheet {
	Logger logger = LoggerFactory.getLogger(User.class);
	TimesheetDAOImpl objTimesheetDAOImpl;
	
	public Timesheet()
	{
		objTimesheetDAOImpl = new TimesheetDAOImpl();
	}
	@SuppressWarnings("unused")
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTSheet(TimesheetCO objTimesheetCO)
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		responses.setLink("http://patharitech.com/");
		responses.setLocation("add");
		Response.Status objRespStat = null;

		if(objTimesheetCO != null)
		{
			
				result = objTimesheetDAOImpl.addTimesheet(objTimesheetCO);
				if(result)
				{
					strMessage=" timesheet added successfuly";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{
					strMessage="timesheet could not be added";
					objRespStat = Response.Status.UNAUTHORIZED;

				}
			}
			
			
			responses.setMessage(strMessage);
			responses.setStatus(objRespStat.getStatusCode());	
			
			//return Response.status(objRespStat).entity(responses).build();
		
		
		return Response.status(objRespStat).entity(responses).build();
		
		
		
		
		
	}
	@SuppressWarnings("unused")
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteTSheet(TimesheetCO objTimesheetCO)
	
	{
		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		if(objTimesheetCO != null)
		{
			if(objTimesheetCO.getTsid() != null)
			{
				result = objTimesheetDAOImpl.deleteUser(objTimesheetCO);
				if(result)
				{
					strMessage="deletion  successful";
					objRespStat = Response.Status.ACCEPTED;
				}
				else
				{

					strMessage="Timesheetid required";
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
	public Response searchTSheet(TimesheetCO objTimesheetCO)
	{

		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		logger.info("userid for search "+objTimesheetCO.getTsid());
		objTimesheetCO = objTimesheetDAOImpl.searchTimesheet(objTimesheetCO);
		Response objResponse = null;
		if(objTimesheetCO!= null)
		{
			objResponse = Response.ok(objTimesheetCO, MediaType.APPLICATION_JSON).build();
		}
		else
		{
			objResponse = Response.status(Response.Status.NOT_FOUND).entity("user not found").build();
		}
		return objResponse;
		
	}
	@SuppressWarnings("unused")
	@GET
	@Path("/getallempts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpTSheet(UserCO objuserCO)
	{

		boolean result = false;
		String strMessage="";
		com.patharitech.utility.ResponseConfig responses = new ResponseConfig();
		Response.Status objRespStat = null; 
		
	    TimesheetCO objTSCO =new TimesheetCO();
	    objTSCO.setUserid(objuserCO.getUserid());
	   TimesheetCO[] objArrTimesheetCO = objTimesheetDAOImpl.getEmpTimesheet(objTSCO);
	  
		
		 return null;
	}
	
	

}
