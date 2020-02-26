package com.patharitech.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.patharitech.client.co.ClientCO;
import com.patharitech.project.co.ProjectCO;
import com.patharitech.user.co.UserCO;
@Entity
@Table(name = "emptimedb")


public class EmpTimeDb {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	@Column(name = "timesheet_id", length = 200)
	@Email
	String timesheet_id;
	
	@Column(name = "user_id", length = 100)
	String user_id;
	
	@Column(name = "date", length = 100)
	String date;
	
	@Column(name = "description", length = 100)
	String description;
	
	@Column(name = "hour", length = 100)
	String hour;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTimesheet_id() {
		return timesheet_id;
	}

	public void setTimesheet_id(String timesheet_id) {
		this.timesheet_id = timesheet_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	
	


}
