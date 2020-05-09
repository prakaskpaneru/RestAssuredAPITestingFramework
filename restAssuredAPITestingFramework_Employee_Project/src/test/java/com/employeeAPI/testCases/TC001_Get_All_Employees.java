package com.employeeAPI.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_Get_All_Employees extends TestBase{
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException
	{
		logger.info("======TC001_Get_All_Employees Started ===============");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("===============Checking Response Body==========");
		String responseBody = response.getBody().asString();
		logger.info("Response Body :" + responseBody);
		Assert.assertTrue(responseBody!=null);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("============== Checking Status Code =============");
		int statusCode = response.getStatusCode();
		logger.info("============== Status Code :" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	void checkResponseTime()
	{
		logger.info("============== Checking Response Time =============");
		long responseTime = response.getTime();
		logger.info("Response Time :" + responseTime);
		if(responseTime>5000)
		{
			logger.warn("Response time is greater than 5 Seconds");
		}
		Assert.assertTrue(responseTime<5000);
	}

	@Test
	void checkStatusLine()
	{
		logger.info("============== Checking Status Line =============");
		String statusLine = response.getStatusLine();
		logger.info("Status Line :" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}
	
	@Test
	void checkContentType()
	{
		logger.info("============== Checking Content Type =============");
		String contentType = response.header("Content-Type");
		logger.info("Content Type :" + contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
	}
	
	@Test
	void checkServerType()
	{
		logger.info("============== Checking Server Type =============");
		String serverType = response.header("Server");
		logger.info("Server Type :" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
	@Test
	void checkContentEncoding()
	{
		logger.info("============== Checking Content Encoding =============");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding :" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}
	
	@Test
	void checkContentLength()
	{
		logger.info("============== Checking Content Length =============");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length :" + contentLength);
		if(Integer.parseInt(contentLength)<100)
		{
			logger.warn("Content Length is less than 100 characters");
		}
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
	}
	
	@Test
	void checkCookie()
	{
		logger.info("============== Checking Cookies =============");
		String cookie = response.getCookie("PHPSESSID");
		System.out.println("==========Cookie============="+cookie);
//		Assert.assertEquals(cookie, "29401a4bb53fa0576dc8de14c76067df");
	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("=================Finished Exeecuting TC001_Get_All_Employees Test ================");
	}

}
