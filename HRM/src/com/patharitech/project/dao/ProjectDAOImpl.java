package com.patharitech.project.dao;

//package com.patharitech.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.annotations.common.util.impl.Log_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.utility.HibernateUtil;
import com.patharitech.db.ProjectDb;
import com.patharitech.db.UserDb;
import com.patharitech.project.co.ProjectCO;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.rest.User;
import com.patharitech.utility.HibernateUtil;

public class ProjectDAOImpl {
	Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	public boolean CreateProj(ProjectCO objprojCO) {

		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		ses = HibernateUtil.getSession();

		if (objprojCO == null) {
		//	logger.info(" User Object is null");
			result= false;
		}
		else
		{
			//UserDb objUserDb = UserDb.mapUserCOToDB(objUserCO);
			
			ProjectDb objProjectDb = ProjectDb.mapProjectCOToDB(objprojCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objProjectDb);
				//logger.info("User record created");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("Project record creation problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
			
	
}
	public boolean deleteProj(ProjectCO objprojCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		ses = HibernateUtil.getSession();

		if (objprojCO == null) {
			logger.info(" User Object is null");
			result= false;
		}
		else
		{
			ProjectDb objProjectDb = ProjectDb.mapProjectCOToDB(objprojCO);
			try {
				tx = ses.beginTransaction();
			
				ses.delete(objProjectDb);
			
				logger.info("project deleted");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("project deletion problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
	}
	
	public boolean updateProject(ProjectCO objProjectCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		// get Session
		ses = HibernateUtil.getSession();
		if (objProjectCO == null) {
			logger.info(" User Object is null");
			result= false;
		}
		else
		{
			ProjectDb objProjectDb = ProjectDb.mapProjectCOToDB(objProjectCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objProjectDb);
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

		
	}
	public ProjectCO searchProject(ProjectCO objProjectCO) {
		Session ses = null;
		Transaction tx = null;
		String projectid=" ";
		projectid=objProjectCO.getProjectid();
		ProjectDb objProjectDb = new ProjectDb();
		ses = HibernateUtil.getSession();
		
		ProjectDb  gDomain=null;
		try {
			tx = ses.beginTransaction();
			
			

			TypedQuery<ProjectDb> query = ses.createQuery("Select pd from ProjectDb pd where pd.projectid = :projectid", ProjectDb.class);
			
			query.setParameter("projectid", Integer.parseInt(projectid));
			List<ProjectDb> list = query.getResultList();
			
			for(ProjectDb gri:list) {
				objProjectDb=gri;
				objProjectCO=ProjectDb.mapProjectDBToCO(objProjectDb);
				
				
			}
		}catch(Exception e){
			logger.error("ID not found ::",e);			
		}finally {
			HibernateUtil.closeSession();
		}

		return objProjectCO ;
	}
	public ProjectCO[] getAllUsers() {
		Session ses = null;
		Transaction tx = null;
		ProjectDb[] objProjectDb = {new ProjectDb()};
		ProjectCO[] objArrProjectCO = { new ProjectCO()};
		ses = HibernateUtil.getSession();
		String SQL_QUERY = "select * from projectdb where status ='1'";
		try {
			tx = ses.beginTransaction();

			Query query = ses.createSQLQuery(SQL_QUERY).addEntity(ProjectDb.class);
			List list = query.getResultList();
			tx.commit();
			logger.info("Questions fetched :-> " + list.size());
			objProjectDb = new ProjectDb[list.size()];
			objArrProjectCO =  new ProjectCO[list.size()];

			for (int i = 0; i < list.size(); i++) {
				objProjectDb[i] = (ProjectDb) list.get(i);
				objArrProjectCO[i] = ProjectDb. mapProjectDBToCO(objProjectDb[i]);
			}
			

			

		} catch (Exception e) {
			logger.error("DB error in Max :- ", e);
			tx.rollback();
		}finally {
				HibernateUtil.closeSession();
	
		
		
		}
		 return objArrProjectCO;
		
		
	}
}
