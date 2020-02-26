package com.patharitech.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.apache.log4j.PropertyConfigurator;

public class LoadConfig {
	static Logger logger = LoggerFactory.getLogger(LoadConfig.class);
	
	public  String CONFIG_PATH = null;

	String username;
	static Properties prop = null;
	
	public static String getConfigValue(String key)
	{
		return prop.getProperty(key);
		
	}
	
	public static void setProperties(Properties prop1)
	{
		prop = prop1;
	}
	
	
	public LoadConfig()
	{
		
		
		
		CONFIG_PATH = System.getProperty("org.patharitech.hrmconfig");
//		CONFIG_PATH= "/home/iiitb/mhms/config/";
	
		//PropertyConfigurator.configure(CONFIG_PATH+"/"+"log4j"+strEnv+".properties");
		logger.info("config path.... :- "+CONFIG_PATH);
		Properties prop = new Properties();
		InputStream input = null;

		
		try {

			input = new FileInputStream(CONFIG_PATH+"/"+"credentials.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			//System.out.println(prop.getProperty("URL"));
			LoadConfig.setProperties(prop);
			//System.out.println(prop.getProperty("dbpassword"));
			
			//ConfigConstants.REG_ORG_FILE_PATH = prop.getProperty("REG_UPLOAD_PATH");

		} catch (IOException ex) {
			logger.error("",ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("",e);
				}
			}
		}
	}
	


}
