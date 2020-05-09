package com.employeeAPI.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;
import com.employeeAPI.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC003_Post_Employee_Record extends TestBase{
	
	RequestSpecification httpRequest;
	Response response;
	
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("======TC003_Post_Employee_Record Started ===============");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", empName);
		requestBody.put("salary", empSalary);
		requestBody.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestBody.toJSONString());
		
		response = httpRequest.request(Method.POST, "/create");
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody()
	{
		logger.info("==============Checking Response Body ============");
		String responseBody = response.getBody().asString();
		logger.info("=======Response Body========="+responseBody);
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
	
	@Test
	void checkStatusLine()
	{
		logger.info("============== Checking Status Line =============");
		String statusLine = response.getStatusLine();
		logger.info("Status Line :" + statusLine);
		Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
	}
	
	@Test
	void checkStatusCode()
	{
		logger.info("============== Checking Status Code =============");
		int statusCode = response.getStatusCode();
		logger.info("============== Status Code :" + statusCode);
		Assert.assertEquals(200, statusCode);
	}
	
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

	@AfterClass
	void tearDown()
	{
		logger.info("=================Finished Exeecuting TC003_Get_Single_Employee_Record Test ================");
	}

}
