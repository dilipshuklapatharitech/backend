package com.patharitech.project.dao;

import com.patharitech.client.co.ClientCO;
import com.patharitech.project.co.ProjectCO;

public interface ProjectDao {
	public boolean CreateProj(ProjectCO objprojCO);
	public boolean deleteProj(ProjectCO objprojCO);
	public ClientCO searchClient(ClientCO objClientCO);
	public boolean updateProject(ProjectCO objProjectCO);
	public ProjectCO searchProject(ProjectCO objProjectCO);
	public ProjectCO[] getAllUsers() ;

}
