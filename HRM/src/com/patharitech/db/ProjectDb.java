package com.patharitech.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.patharitech.project.co.ProjectCO;
import com.patharitech.user.co.UserCO;

//import com.patharitech.user.co.UserCO;

@Entity
@Table(name = "projectdb")
public class ProjectDb {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int projectid;
	
	@Column(name = "name", length = 200)
	String name;
	
	@Column(name = "startdate", length = 100)
	String startdate;
	
	@Column(name = "enddate", length = 100)
	String endtdate;
	
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	@Column(name = "clientid", length = 100)
	String clientid;
	
	@Column(name = "pmanagers", length = 100)
	String pmuserids;
	
	@Column(name = "status", length = 100)
	String status;
	

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndtdate() {
		return endtdate;
	}

	public void setEndtdate(String endtdate) {
		this.endtdate = endtdate;
	}


	public String getPmuserids() {
		return pmuserids;
	}

	public void setPmuserids(String pmuserids) {
		this.pmuserids = pmuserids;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static ProjectDb  mapProjectCOToDB( ProjectCO  ObjProjectCO)
	{
		ProjectDb objProjectDb = new ProjectDb();
		if(ObjProjectCO.getClientid()!=null)
		{
			//objProjectDb.setClientid(Integer.parseInt(ObjProjectCO.getClientid()));
		}
		objProjectDb.setEndtdate(ObjProjectCO.getEnddate());
		objProjectDb.setName(ObjProjectCO.getName());
		objProjectDb.setPmuserids(ObjProjectCO.getPmanagers());
		if(ObjProjectCO.getProjectid()!=null)
		{
			objProjectDb.setProjectid(Integer.parseInt(ObjProjectCO.getProjectid()));
		}
		
		//objProjectDb.setStartdate(ObjProjectCO.getStartdate());
		objProjectDb.setStatus(ObjProjectCO.getStatus());
		
		return objProjectDb;
	}
	
	public static ProjectCO  mapProjectDBToCO(ProjectDb objProjectDb)
	{
		ProjectCO objProjectCO= new ProjectCO();
		if(objProjectDb.getClientid() != null)
		{
			objProjectCO.setClientid(objProjectDb.getClientid());
		}
		objProjectCO.setClientid(objProjectDb.getClientid());
		objProjectCO.setEnddate(objProjectDb.getEndtdate());
		objProjectCO.setName(objProjectDb.getName());
		objProjectCO.setPmanagers(objProjectDb.getPmuserids());
		//objProjectCO.setProjectid(objProjectDb.getProjectid());
		objProjectCO.setStartdate(objProjectDb.getStartdate());
		objProjectCO.setStatus(objProjectDb.getStatus());
		
		
		
		return objProjectCO;
	}

	

	 
	
	
}


