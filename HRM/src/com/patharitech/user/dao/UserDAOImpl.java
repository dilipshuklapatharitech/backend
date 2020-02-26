package com.patharitech.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.utility.HibernateUtil;
//import com.ehrc.mhms.mhd.db.domain.Grievance_Patients;
//import com.ehrc.mhms.mhd.db.domain.Grievance_Patients;
import com.patharitech.db.UserDb;
import com.patharitech.project.co.ProjectCO;
import com.patharitech.user.co.UserCO;

public class UserDAOImpl implements UserDAO {
	
	
		static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

		@SuppressWarnings("rawtypes")
		@Override
		public UserCO  getUser(String username, String password) {
			
			logger.debug("Fetching user name = ");
			Session ses = null;
			Transaction tx = null;
			//List<User> l1=new ArrayList();
			UserCO objUserCO= null;
			
			ses = HibernateUtil.getSession();
			int result = 0;
			String SQL_QUERY = "select * from userdb where username ilike :username and password ilike :password";
			try {
				tx = ses.beginTransaction();

				Query query = ses.createSQLQuery(SQL_QUERY).addEntity(UserDb.class);
				query.setParameter("username", "%"+username+"%");
				query.setParameter("password", "%"+password+"%");
				List list = query.getResultList();
				tx.commit();
				if(list != null && list.size() > 0)
				{
					UserDb objUserDb=(UserDb)list.get(0);
					objUserCO = UserDb.mapUserDBToCO(objUserDb);
				}
				logger.debug("Checking if user exists = " + result);
			
	
			} catch (Exception e) {
				logger.error("DB error in Max :- ", e);
				tx.rollback();
			} finally {
				HibernateUtil.closeSession();
			}
			return objUserCO;
			
		}

		
		@SuppressWarnings("rawtypes")
		@Override
		public boolean addUser(UserCO objUserCO) {
			
			Session ses = null;
			Transaction tx = null;
			boolean result=false;
			
			// get Session
			ses = HibernateUtil.getSession();

			if (objUserCO == null) {
				logger.info(" User Object is null");
				result= false;
			}
			else
			{
				UserDb objUserDb = UserDb.mapUserCOToDB(objUserCO);
				try {
					tx = ses.beginTransaction();
					ses.saveOrUpdate(objUserDb);
					logger.info("User record created");
					tx.commit();
					result= true;

				} // try
				catch (Exception e) {
					logger.error("User record creation problem", e);
					tx.rollback();
					result= false;

				} finally {
					HibernateUtil.closeSession();
				}
			}
			return result;
		}


		@SuppressWarnings("rawtypes")
		@Override
		



		public boolean deleteUser(UserCO objUserCO) {
			Session ses = null;
			Transaction tx = null;
			boolean result=false;
			
			// get Session
			ses = HibernateUtil.getSession();

			if (objUserCO == null) {
				logger.info(" User Object is null");
				result= false;
			}
			else
			{
				UserDb objUserDb = UserDb.mapUserCOToDB(objUserCO);
				try {
					tx = ses.beginTransaction();
				
					ses.delete(objUserDb);
				
					logger.info("User record deleted");
					tx.commit();
					result= true;

				} // try
				catch (Exception e) {
					logger.error("User record deletion problem", e);
					tx.rollback();
					result= false;

				} finally {
					HibernateUtil.closeSession();
				}
			}
			return result;
			
			
			
			 }


		public boolean updateUser(UserCO objUserCO) {

			Session ses = null;
			Transaction tx = null;
			boolean result=false;
			
			// get Session
			ses = HibernateUtil.getSession();

			if (objUserCO == null) {
				logger.info(" User Object is null");
				result= false;
			}
			else
			{
				UserDb objUserDb = UserDb.mapUserCOToDB(objUserCO);
				try {
					tx = ses.beginTransaction();
					ses.saveOrUpdate(objUserDb);
					logger.info("User record updated");
					tx.commit();
					result= true;

				} // try
				catch (Exception e) {
					logger.error("User record updation problem", e);
					tx.rollback();
					result= false;

				} finally {
					HibernateUtil.closeSession();
				}
			}
			return result;
			//return false;
		}
		public UserCO[] getAllUsers() {
			Session ses = null;
			Transaction tx = null;
			UserDb[] objUserDb = {new UserDb()};
			UserCO[] objArrUserCO = { new UserCO()};
			ses = HibernateUtil.getSession();
			// status=1 is for all the active users and staus=0 is for ala the inactive users
			String SQL_QUERY = "select * from userdb where status ='1'";
			try {
				tx = ses.beginTransaction();

				Query query = ses.createSQLQuery(SQL_QUERY).addEntity(UserDb.class);
				List list = query.getResultList();
				tx.commit();
				logger.info("Questions fetched :-> " + list.size());
				objUserDb = new UserDb[list.size()];
				objArrUserCO =  new UserCO[list.size()];

				for (int i = 0; i < list.size(); i++) {
					objUserDb[i] = (UserDb) list.get(i);
					objArrUserCO[i] = UserDb.mapUserDBToCO(objUserDb[i]);
				}
				

				

			} catch (Exception e) {
				logger.error("DB error in Max :- ", e);
				tx.rollback();
			}finally {
					HibernateUtil.closeSession();
		
			
			
			}
			 return objArrUserCO;
		}


		public UserCO searchUser(UserCO objUserCO) {
			Session ses = null;
			Transaction tx = null;
			UserDb objUserDb = new UserDb();
			
			ses = HibernateUtil.getSession();
			String userid=" ";
			userid=objUserCO.getUserid();
			UserDb  gDomain=null;
			// get Session
			//Integer intValue = (Integer) userid;
			
			// status=1 is for all the active users and staus=0 is for ala the inactive users
			//String SQL_QUERY = "select username,email from userdb where userid = :uid";
			try {
				tx = ses.beginTransaction();
				
				

				TypedQuery<UserDb> query = ses.createQuery("Select ud from UserDb ud where ud.userid = :userid", UserDb.class);
				query.setParameter("userid", Integer.parseInt(userid));
				List<UserDb> list = query.getResultList();
				
				for(UserDb gri:list) {
					objUserDb=gri;
					objUserCO=UserDb.mapUserDBToCO(objUserDb);
					
					
				}
			}catch(Exception e){
				logger.error("ID not found ::",e);			
			}finally {
				HibernateUtil.closeSession();
			}

				//Query query = ses.createSQLQuery(SQL_QUERY).addEntity(UserDb.class);
				//List list = query.getResultList();
			
				
				return objUserCO;
		}


		


		
}
		
			
			
			
			
			
			
			
			
			
			
			
		

		



