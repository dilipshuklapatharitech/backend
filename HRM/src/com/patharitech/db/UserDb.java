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
import com.patharitech.timesheet.co.TimesheetCO;
import com.patharitech.user.co.UserCO;

@Entity
@Table(name = "userdb")

public class UserDb {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	int userid;

	@Column(name = "email", length = 200)
	@Email
	String email;
	
	@Column(name = "phone_no", length = 100)
	String phone_no;
	
	@Column(name = "company_name", length = 100)
	String company_name;
	
	@Column(name = "role", length = 100)
	String role;
	
	@Column(name = "password", length = 100)
	String password;
	
	@Column(name = "username", length = 100)
	String username;
	
	@Column(name = "name", length = 100)
	String name;
	

	@Column(name = "status", length = 100)
	String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDb [userid=" + userid + ", email=" + email+",phone_no="+ phone_no+",company_name="+ company_name+",role="+role+",password="+
						password+",username="+ username +"]"
		;		
	}
	
	public static UserDb mapUserCOToDB(UserCO objUserCO)
	{
		UserDb objUserDb = new UserDb();
		objUserDb.setCompany_name(objUserCO.getCompany_name());
		objUserDb.setEmail(objUserCO.getEmail());
		objUserDb.setPassword(objUserCO.getPassword());
		objUserDb.setPhone_no(objUserCO.getPhone_no());
		objUserDb.setRole(objUserCO.getRole());
		objUserDb.setName(objUserCO.getName());
		objUserDb.setUsername(objUserCO.getUsername());
		objUserDb.setStatus(objUserCO.getStatus());
		if(objUserCO.getUserid() != null)
		{
			objUserDb.setUserid(Integer.parseInt(objUserCO.getUserid()));
		}
		objUserDb.setUsername(objUserCO.getUsername());

		return objUserDb;
	}
	
	public static UserCO mapUserDBToCO(UserDb objUserDb)
	{
		UserCO objUserCO = new UserCO();
		objUserCO.setCompany_name(objUserDb.getCompany_name());
		objUserCO.setUserid(objUserDb.getUserid()+"");
		objUserCO.setEmail(objUserDb.getEmail());
		objUserCO.setPassword(objUserDb.getPassword());
		objUserCO.setPhone_no(objUserDb.getPhone_no());
		objUserCO.setUsername(objUserDb.getUsername());
		objUserCO.setName(objUserDb.getName());
		objUserCO.setStatus(objUserDb.getStatus());
		
		
		return objUserCO;
	}

	

}
