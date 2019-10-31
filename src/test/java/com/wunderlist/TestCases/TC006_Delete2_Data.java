package com.wunderlist.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wunderlist.base.Base_Class;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC006_Delete2_Data extends Base_Class{
	
	
	@BeforeClass
	public void delete() throws InterruptedException
	{
		logg.info("Delete Request");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");
		
		response =Request.request(Method.GET,"/lists");
		Request.log().all();
		JsonPath jsonPathEvaluator=response.jsonPath();
		
		String id=Integer.toString(jsonPathEvaluator.get("[1].id"));
		response =Request.request(Method.DELETE,"/delete/"+id);

		Thread.sleep(3000);
	}
	
	@Test
	public void check_Response()
	{
		String response_body=response.getBody().asString();
		Assert.assertEquals(response_body.contains("successfully deleted record"), true);
		
	}
	
	@Test
	public void check_Status()
	{
		logg.info("Delete Operation Started");
		int code=response.getStatusCode();
		response.then().log().all();
		logg.info("Status_Code------"+code);

		Assert.assertEquals(code, 204);

		
		
	}


}
