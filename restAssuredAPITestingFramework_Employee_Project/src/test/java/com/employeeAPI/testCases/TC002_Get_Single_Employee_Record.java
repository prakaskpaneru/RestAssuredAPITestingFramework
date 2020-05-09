package com.employeeAPI.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_Record extends TestBase {
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException
	{
		logger.info("======TC002_Get_Single_Employee_Record Started ===============");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		
//		response = httpRequest.request(Method.GET, "/employees");
//		
////		
//		//Get JsonPath object instance from response interface
//				JsonPath jsonPathEvaluator = response.jsonPath();
//				
//				//Capture ID from existing records
//				String empID = jsonPathEvaluator.getString("data[0].id");
//				logger.info("Employee ID at 10 INdex =====>>>" +empID );
		
		response = httpRequest.request(Method.GET, "/employee/"+empID);
				
		Thread.sleep(3000);
	}
	
//	@Test
//	void checkResponseBody()
//	{
//		logger.info("===============Checking Response Body==========");
//		String responseBody = response.getBody().asString();
//		logger.info("Response Body :" + responseBody);
////		Assert.assertTrue(responseBody!=null);
//		Assert.assertEquals(responseBody.contains(empID), true);
//	}
	
//	@Test
//	void checkStatusCode()
//	{
//		logger.info("============== Checking Status Code =============");
//		int statusCode = response.getStatusCode();
//		logger.info("============== Status Code :" + statusCode);
//		Assert.assertEquals(200, statusCode);
//	}
//	
	@Test
	void checkResponseTime()
	{
		logger.info("============== Checking Response Time =============");
		long responseTime = response.getTime();
		logger.info("Response Time :" + responseTime);
		Assert.assertTrue(responseTime<5000);
	}

//	@Test
//	void checkStatusLine()
//	{
//		logger.info("============== Checking Status Line =============");
//		String statusLine = response.getStatusLine();
//		logger.info("Status Line :" + statusLine);
//		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
//	}
	
	@Test
	void checkContentType()
	{
		logger.info("============== Checking Content Type =============");
		String contentType = response.header("Content-Type");
		logger.info("Content Type :" + contentType);
		Assert.assertEquals("application/json;charset=utf-8", contentType);
	}
	
	@Test
	void checkServerType()
	{
		logger.info("============== Checking Server Type =============");
		String serverType = response.header("Server");
		logger.info("Server Type :" + serverType);
		Assert.assertEquals(serverType, "nginx/1.16.0");
	}
	
//	@Test
//	void checkContentEncoding()
//	{
//		logger.info("============== Checking Content Encoding ===================");
//		String contentEncoding = response.header("Content-Encoding");
//		logger.info("Content Encoding :" + contentEncoding);
//		Assert.assertEquals(contentEncoding, "gzip");
//	}
//	
	@Test
	void checkContentLength()
	{
		logger.info("============== Checking Content Length =============");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length :" + contentLength);
//		if(Integer.parseInt(contentLength)<100)
//		{
//			logger.warn("Content Length is less than 100 characters");
//		}
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
	
//	@Test
//	void checkCookie()
//	{
//		logger.info("============== Checking Cookies =============");
//		String cookie = response.getCookie("PHPSESSID");
////		Assert.assertEquals(cookie, "29401a4bb53fa0576dc8de14c76067df");
//	}
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("=================Finished Exeecuting TC002_Get_Single_Employee_Record Test ================");
	}

}
