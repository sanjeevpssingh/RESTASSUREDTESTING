package com.wunderlist.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
import com.wunderlist.base.Base_Class;

import io.restassured.RestAssured;
import io.restassured.http.Method;
//import com.wunderlist.commonUtils.ReportLib;;

public class TC001_GETAll_Data extends Base_Class  {

	@BeforeClass
	public void get_First() throws InterruptedException
	{
		logg.info(" First GET Request");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");
		Request.log().all();
		response =Request.request(Method.GET,"/lists");

		Thread.sleep(2000);
	}

	@Test
	public void check_Response()
	{
		ExtentTest tst = ex.createTest("GET", "Checking validation for GET Request");

		logg.info(" Checking validation for GET Request");
		String response_body=response.prettyPrint();
		logg.info("Response_Body----"+response_body);
		Assert.assertTrue(response_body!=null);
		tst.log(Status.INFO, "Response Body for GET Request");
	}
	
	
	
	@Test
	public void check_statusCode()
	{
		ExtentTest tst = ex.createTest("GET", "Checking validation for GET Request");

		int code=response.getStatusCode();
		logg.info("Status_Code----"+code);

		Assert.assertEquals(code, 200);
		tst.log(Status.INFO, "Status code for GET Request");
	}

	@AfterClass
	public void At_End()
	{
		logg.info("Finish");
	}
}



