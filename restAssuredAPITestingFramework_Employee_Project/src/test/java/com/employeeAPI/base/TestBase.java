package com.employeeAPI.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID = "9";//empID hardcoded for the get and post request
	
	public Logger logger;
	
	@BeforeClass
	public void setUp()
	{
		logger = Logger.getLogger("EmployeeRestAPI"); //Logger added
		
		PropertyConfigurator.configure("log4j.properties");//Logger added with specifying the properties file
		
		logger.setLevel(Level.DEBUG);
	}

}
