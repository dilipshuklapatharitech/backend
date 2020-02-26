package com.patharitech.utility;

import javax.servlet.ServletContext;

import org.glassfish.jersey.server.ResourceConfig;

import com.patharitech.utility.LoadConfig;

public class RestMain extends ResourceConfig{
	
	public RestMain(@javax.ws.rs.core.Context ServletContext context) throws Exception {
		
		LoadConfig loadconfig = new LoadConfig();
		packages("com.patharitech.client.rest");
		packages("com.patharitech.project.rest");
		packages("com.patharitech.user.rest");
		packages("com.patharitech.timesheet.rest");
	
		
	
	}

}
