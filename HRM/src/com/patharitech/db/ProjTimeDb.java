package com.patharitech.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "projtimedb")

public class ProjTimeDb {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int id;

	@Column(name = "proj_id", length = 200)
	@Email
	String proj_id;
	
	@Column(name = "user_id", length = 100)
	String user_id;
	
	
	@Column(name = "start", length = 100)
	String start;
	
	@Column(name = "end", length = 100)
	String end;
	
	@Column(name = "status", length = 100)
	String status;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProj_id() {
		return proj_id;
	}

	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
