package com.patharitech.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.patharitech.timesheet.co.TimesheetCO;

@Entity
@Table(name = "timesheetdb")

public class TimesheetDb {

	@Column(name = "todate", length = 100)
	String todate;
	
	@Column(name = "projectid", length = 100)
	String projectid;
	
	@Column(name = "stage", length = 100)
	String stage;
	
	@Column(name = "status", length = 100)
	String status;
	@Column(name = "comment", length = 100)
	String comment;
	

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int tsid;
	@Column(name = "fromdate", length = 200)
	
	String fromdate;
	

	@Column(name = "userid", length = 100)
	String userid;
	
	
	public String getUserid() {
		return userid;
	}




	public void setUserid(String userid) {
		this.userid = userid;
	}




	
	
	
	
	
	public int getTsid() {
		return tsid;
	}




	public void setTsid(int tsid) {
		this.tsid = tsid;
	}






	public String getProjectid() {
		return projectid;
	}




	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}




	public String getStage() {
		return stage;
	}




	public void setStage(String stage) {
		this.stage = stage;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public String getComment() {
		return comment;
	}




	public void setComment(String comment) {
		this.comment = comment;
	}




	
	public String getFromdate() {
		return fromdate;
	}




	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}




	public String getTodate() {
		return todate;
	}




	public void setTodate(String todate) {
		this.todate = todate;
	}




	
	

	public static TimesheetDb mapTimesheetCOToDB(TimesheetCO objTimesheetCO) {
		TimesheetDb objTimesheetDb = new TimesheetDb();
		objTimesheetDb.setProjectid(objTimesheetCO.getProjectid());
		objTimesheetDb.setStage(objTimesheetCO.getStage());
		objTimesheetDb.setStatus(objTimesheetCO.getStatus());
		objTimesheetDb.setFromdate(objTimesheetCO.getFromdate());
		objTimesheetDb.setTodate(objTimesheetCO.getTodate());
		objTimesheetDb.setComment(objTimesheetCO.getComment());
		objTimesheetDb.setUserid(objTimesheetCO.getUserid());
		
		
		if(objTimesheetCO.getTsid()!= null)
		{
			objTimesheetDb.setTsid(Integer.parseInt(objTimesheetCO.getTsid()));
		}
		

		return objTimesheetDb;
		
	}




	public static TimesheetCO mapTimesheetDBToCO(TimesheetDb objTimesheetrDb) {

		TimesheetCO objTimesheetCO = new TimesheetCO();
		objTimesheetCO.setProjectid(objTimesheetrDb.getProjectid());
		objTimesheetCO.setStage(objTimesheetrDb.getStage());
		objTimesheetCO.setStatus(objTimesheetrDb.getStatus());
		objTimesheetCO.setFromdate(objTimesheetrDb.getFromdate());
		objTimesheetCO.setTodate(objTimesheetrDb.getTodate());
		objTimesheetCO.setComment(objTimesheetrDb.getComment());
		objTimesheetCO.setTsid((objTimesheetCO.getTsid()));
		objTimesheetCO.setUserid(objTimesheetCO.getUserid());
		

		return objTimesheetCO;

	}

	


}
