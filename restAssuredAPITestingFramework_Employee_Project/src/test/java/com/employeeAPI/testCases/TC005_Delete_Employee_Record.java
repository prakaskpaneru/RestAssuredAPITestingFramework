package com.employeeAPI.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeAPI.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_Delete_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		logger.info("======TC005_Delete_Employee_Record Started ===============");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		response = httpRequest.request(Method.GET, "/employees");
		
		//Get JsonPath object instance from response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Capture ID from existing records
		String empID = jsonPathEvaluator.getString("data[0].id");
		logger.info("Employee ID at 10 INdex =====>>>" +empID );
						
		response = httpRequest.request(Method.DELETE, "/delete/"+empID);//Pass Id to delete
		
		Thread.sleep(5000);
	}
	
//	@Test
//	void checkResponseBody()
//	{
//		logger.info("==============Checking Response Body ============");
//		String responseBody = response.getBody().asString();
//		logger.info("=======Response Body========="+responseBody);
//		Assert.assertEquals(responseBody.contains("successfully! deleted Records"), true);
////		Assert.assertEquals(rep.contains(empName), true);
////		Assert.assertEquals(responseBody.contains(empAge), true);
//	}
//	
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
	
	@AfterClass
	void tearDown()
	{
		logger.info("=================Finished Exeecuting TC004_Put_Employee_Record Test ================");
	}	

}
