package com.responsestatus;

import java.io.FileInputStream;
import java.util.Properties;


public class Testbase {

	//Logger log = Logger.getLogger(report.class.getName());
	

	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;

	public String getAppProperty(String object) throws Exception {
		FileInputStream fs = new FileInputStream("../MedApp-API/Prop/config.properties");
		Properties app = new Properties();
		app.load(fs);
		return app.getProperty(object);

	}
	
	
	
	}


