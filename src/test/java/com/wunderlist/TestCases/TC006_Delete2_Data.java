package com.wunderlist.TestCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.Base_Class;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC006_Delete2_Data extends Base_Class {
	
	
	
	@BeforeClass
	public void put() throws InterruptedException 
	{
		logg.info(" PUT Request ");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");

	}

	
	
	
	@Test
	public void Delete_1()
	{
		ExtentTest tst = ex.createTest("DELETE_Case2", "Check Delete Request ");	

		logg.info("***** Checking DELETE Request of lists *****");

		response=Request.request(Method.GET,"/lists");
		JsonPath i=response.jsonPath();
		int id=i.get("[22].id");
		System.out.println(id);

		JSONObject requestParams = new JSONObject();
		requestParams.put("revision",1);
		Request.queryParams(requestParams); 
		
		
		Request.pathParam("id",id);

		Response response =Request.request(Method.DELETE,"/lists/{id}");
		response.prettyPrint();

		int code = response.getStatusCode();
		Assert.assertEquals(code, 204);
		if(code==204)
		{
			tst.log(Status.PASS, "Test Passed");

		}
		else
		{
			tst.log(Status.FAIL, "Test failed");
	
		}
	} 

}

