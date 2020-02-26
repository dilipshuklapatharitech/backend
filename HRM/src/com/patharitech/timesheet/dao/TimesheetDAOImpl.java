package com.patharitech.timesheet.dao;

import org.hibernate.Session;
import java.util.Date; 
import java.text.ParseException; 
import java.text.SimpleDateFormat; 

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patharitech.db.TimesheetDb;
import com.patharitech.db.UserDb;
import com.patharitech.timesheet.co.TimesheetCO;
import com.patharitech.user.co.UserCO;
import com.patharitech.user.dao.UserDAOImpl;
import com.patharitech.utility.HibernateUtil;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import java.time.format.DateTimeFormatter;  
import java.time.LocalDate;    
import java.util.Calendar; 
import java.util.GregorianCalendar; 



//import com.ehrc.mhms.mhd.db.domain.Grievance_Patients;
//import com.ehrc.mhms.mhd.db.domain.Grievance_Patients;


public class TimesheetDAOImpl implements TimesheetDAO
{
	static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@SuppressWarnings("rawtypes")
	@Override
	public boolean addTimesheet(TimesheetCO objTimesheetCO)
	{
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		// get Session
		ses = HibernateUtil.getSession();
		if (objTimesheetCO == null) {
			logger.info(" Timesheet Object is null");
			result= false;
		}
		else
		{
			TimesheetDb objTimesheetDb =TimesheetDb.mapTimesheetCOToDB(objTimesheetCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objTimesheetDb);
				logger.info("timesheet created");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("timesheet problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
		
	}
	public boolean deleteUser(TimesheetCO objTimesheetCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		// get Session
		ses = HibernateUtil.getSession();

		if (objTimesheetCO == null) {
			logger.info(" timesheet Object is null");
			result= false;
		}
		else
		{
			TimesheetDb objTimesheetDb = TimesheetDb.mapTimesheetCOToDB(objTimesheetCO);
			try {
				tx = ses.beginTransaction();
			
				ses.delete(objTimesheetDb);
			
				logger.info("timesheet record deleted");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("timesheet record deletion problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
		
		
	}
	public boolean updateTS(TimesheetCO objTimesheetCO) {
		Session ses = null;
		Transaction tx = null;
		boolean result=false;
		
		// get Session
		ses = HibernateUtil.getSession();

		if (objTimesheetCO == null) {
			logger.info(" timesheet Object is null");
			result= false;
		}
		else
		{
			TimesheetDb objTimesheetDb = TimesheetDb.mapTimesheetCOToDB(objTimesheetCO);
			try {
				tx = ses.beginTransaction();
				ses.saveOrUpdate(objTimesheetDb);
				logger.info("timesheet record updated");
				tx.commit();
				result= true;

			} // try
			catch (Exception e) {
				logger.error("timesheet record updation problem", e);
				tx.rollback();
				result= false;

			} finally {
				HibernateUtil.closeSession();
			}
		}
		return result;
		
	}
	public TimesheetCO searchTimesheet(TimesheetCO objTimesheetCO) {
		Session ses = null;
		Transaction tx = null;
		TimesheetDb objTimesheetrDb = new TimesheetDb();
		ses = HibernateUtil.getSession();
		String tsid=" ";
		tsid=objTimesheetCO.getTsid();
		TimesheetDb  gDomain=null;
		try {
			tx = ses.beginTransaction();
			
			

			TypedQuery<TimesheetDb> query = ses.createQuery("Select gd from TimesheetDb gd where gd.tsid = :tsid", TimesheetDb.class);
			
			query.setParameter("tsid", Integer.parseInt(tsid));
			List<TimesheetDb> list = query.getResultList();
			
			for(TimesheetDb gri:list) {
				objTimesheetrDb=gri;
				objTimesheetCO=TimesheetDb.mapTimesheetDBToCO(objTimesheetrDb);
				
				
			}
		}catch(Exception e){
			logger.error("ID not found ::",e);			
		}finally {
			HibernateUtil.closeSession();
		}

			//Query query = ses.createSQLQuery(SQL_QUERY).addEntity(UserDb.class);
			//List list = query.getResultList();
		
			
			return objTimesheetCO;
	}
	public TimesheetCO[] getEmpTimesheet(TimesheetCO objTSCO) {
		Session ses = null;
		Transaction tx = null;
		TimesheetDb objTimesheetrDb = new TimesheetDb();
		ses = HibernateUtil.getSession();
		String userid=" ";
		userid=objTSCO.getUserid();
		TimesheetDb  gDomain=null;
		TimesheetDb[] objTSDb = {new TimesheetDb()};
		TimesheetCO[] objArrTSCO = { new TimesheetCO()};
		int lsize=0;
		
			
		try {
			tx = ses.beginTransaction();
			
			

			TypedQuery<TimesheetDb> query = ses.createQuery("Select ud from TimesheetDb ud where ud.userid = :userid and stage='pending'", TimesheetDb.class);
			query.setParameter("userid",userid);
			List<TimesheetDb> list = query.getResultList();
			List<TimesheetDb> list1=null;
			lsize=list.size();
			if(lsize>0)
			{	
			logger.info("Questions fetched :-> " + lsize);
			objTSDb = new TimesheetDb[lsize];
			objArrTSCO =  new TimesheetCO[lsize];
			

			for (int i = 0; i < lsize; i++) {
				objTSDb[i] = (TimesheetDb) list.get(i);
				objArrTSCO[i] = TimesheetDb.mapTimesheetDBToCO(objTSDb[i]);
			}
			}
			else
			{
				

				query = ses.createQuery("Select  ud  from TimesheetDb ud where ud.userid = :userid and "
						+ "ud.tsid in (Select  max(ud1.tsid)  from TimesheetDb ud1 where ud1.userid = ud.userid)", TimesheetDb.class);
				query.setParameter("userid", userid);
				list1 = query.getResultList();
				int lsize1=list1.size();
				TimesheetDb  objTSDb1=null;
				
				if(lsize1 > 0)
				{
					objTSDb1= (TimesheetDb) list1.get(0);
					if(objTSDb1 != null)
					logger.info("getting max timesheetid for the user:-> " + objTSDb1.getTsid() +" userid ="+objTSDb1.getUserid());
					String sDate1=objTSDb1.getTodate();
					 Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(sDate1);  
					 LocalDate date2=java.time.LocalDate.now();
					  //System.out.println(sDate1+"\t"+date1);  
					 SimpleDateFormat sdfo = new SimpleDateFormat("dd-MM-yyyy"); 
					 Date d1 = sdfo.parse("date1"); 
				     Date d2 = sdfo.parse("date2");
				   //  if (d1.after(d2)) { 
				    	 
				    	 Calendar c1 = Calendar.getInstance();
				    	 Calendar c2 = Calendar.getInstance();

				    	 c1.setTime(d1);
				    	 c2.setTime(d2);

				    	 //int yearDiff = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
				    	// int monthDiff = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
				    	// int dayDiff = c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);

				    	

				    	 if(c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)){
				    	     // code
				    	 }


				        //} 
				  
				        else if (d1.before(d2)) { 
				  
				          
				            System.out.println("Date1 is before Date2"); 
				        } 
				  
				        else if (d1.equals(d2)) { 
				  
				           
				            System.out.println("Date1 is equal to Date2"); 
				        } 
				     
				     
					
					
				}
				
				
			}

			

		} catch (Exception e) {
			logger.error("DB error in Max :- ", e);
			tx.rollback();
		}finally {
				HibernateUtil.closeSession();
	
		
		
		}
		 return objArrTSCO;
		
		
		
	}


	}


