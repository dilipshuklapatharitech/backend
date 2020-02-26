package com.patharitech.client.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.client.co.ClientCO;
import com.patharitech.db.ClientDb;
import com.patharitech.db.UserDb;
import com.patharitech.project.dao.ProjectDAOImpl;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.user.rest.User;
import com.patharitech.utility.HibernateUtil;

public class ClientDaoImpl {
	Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);

	public boolean addUser(ClientCO objClientCO) {
		

		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		ses = HibernateUtil.getSession();

		if (objClientCO == null) {
			logger.info(" clinet Object is null");
			result= false;
		}
		else
		{
			ClientDb objClientDb = ClientDb.mapClientCOToDB(objClientCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objClientDb);
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

	public boolean deleteUser(ClientCO objClientCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		ses = HibernateUtil.getSession();
		

		if (objClientCO == null) {
			logger.info(" client Object is null");
			result= false;
		}
		else
		{
			ClientDb objClientDb = ClientDb.mapClientCOToDB(objClientCO);
			try {
				tx = ses.beginTransaction();
			
				ses.delete(objClientDb);
			
				logger.info("client record deleted");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("client record deletion problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
		

		
	}

	public boolean updateClient(ClientCO objClientCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		// get Session
		ses = HibernateUtil.getSession();

		if (objClientCO == null) {
			logger.info(" Client Object is null");
			result= false;
		}
		else
		{
			ClientDb objClientDb =ClientDb.mapClientCOToDB(objClientCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objClientDb);
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

	public ClientCO searchClient(ClientCO objClientCO) {
		Session ses = null;
		Transaction tx = null;
		ClientDb objClientDb = new ClientDb();
		ses = HibernateUtil.getSession();
		String clientid=" ";
		clientid=objClientCO.getClientid();
		ClientDb  gDomain=null;
		try {
			tx = ses.beginTransaction();
			
			

			TypedQuery<ClientDb> query = ses.createQuery("Select cd from ClientDb cd where cd.clientid = :clientid", ClientDb.class);
			query.setParameter("clientid", Integer.parseInt(clientid));
			List<ClientDb> list = query.getResultList();
			
			for(ClientDb gri:list) {
				objClientDb=gri;
				objClientCO=ClientDb.mapClientDBToCO(objClientDb);
				
				
			}
		}catch(Exception e){
			logger.error("ID not found ::",e);			
		}finally {
			HibernateUtil.closeSession();
		}
		
		
		
		
		return objClientCO;
	}

}
