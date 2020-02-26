package com.patharitech.user.dao;

//import com.ehrc.mhms.mhd.db.domain.PortalWiseFaqs;
import com.patharitech.db.UserDb;
import com.patharitech.user.co.UserCO;

public interface UserDAO {
	
		public UserCO  getUser(String username, String password);

		public boolean  addUser(UserCO objUserCO);

		public boolean deleteUser(UserCO objUserCO) ;
		
		public UserCO[] getAllUsers();
		
		public   UserCO searchUser(UserCO objUserCO);


}
