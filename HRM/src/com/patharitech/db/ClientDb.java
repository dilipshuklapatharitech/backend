package com.patharitech.db;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.patharitech.client.co.ClientCO;
import com.patharitech.user.co.UserCO;
@Entity
@Table(name = "clientdb")
public class ClientDb {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int clientid;

	@Column(name = "name", length = 200)
	@Email
	String name;
	
	@Column(name = "company", length = 100)
	String company;
	
	@Column(name = "location", length = 100)
	String location;
	
	
	public int getClientid() {
		return clientid;
	}

	public void setClientid(int clientid) {
		this.clientid = clientid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public static ClientDb mapClientCOToDB(ClientCO objClientCO)
	{
		ClientDb objClientDb = new ClientDb();
		objClientDb.setCompany(objClientCO.getCompany());
		objClientDb.setLocation(objClientCO.getLocation());
		objClientDb.setName(objClientCO.getName());
		
		
		
		return objClientDb;
	}

	public static ClientCO mapClientDBToCO(ClientDb objClientDb) {
		ClientCO objClientCO = new ClientCO();
		objClientCO.setCompany(objClientDb.getCompany());
		objClientCO.setLocation(objClientDb.getLocation());
		objClientCO.setName(objClientDb.getName());
		//objClientCO.setClientid(Integer.parseInt(objClientDb.getClientid()));
		
		
		return objClientCO;
		
	}

	
	

}
