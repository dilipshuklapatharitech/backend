package com.patharitech.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectemp")

public class ProjectEmp {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int projecemptid;

	public int getProjecemptid() {
		return projecemptid;
	}


	public void setProjecemptid(int projecemptid) {
		this.projecemptid = projecemptid;
	}


	public String getProjectid() {
		return projectid;
	}


	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Column(name = "projectid", length = 200)
	String projectid;
	
	@Column(name = "userid", length = 100)
	String userid;
	

	@Column(name = "startdate", length = 100)
	String startdate;
	
	@Column(name = "enddate", length = 100)
	String endtdate;
	
	
	@Column(name = "status", length = 100)
	String status;
	
	

}
