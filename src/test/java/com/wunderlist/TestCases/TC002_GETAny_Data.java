package com.wunderlist.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.Base_Class;
import com.wunderlist.commonUtils.ReportLib;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_GETAny_Data extends Base_Class
{
	@BeforeClass
	public void get() throws InterruptedException
	{
		logg.info(" GET Request ");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");
		Request.log().all();
		response =Request.request(Method.GET,"/lists/408409255");

		Thread.sleep(3000);
	}

	@Test
	public void check_Response()
	{
		ExtentTest tst = ex.createTest("GET2_CASE2_RESPONSE", "Check GET Request by ID");

		logg.info(" Checking Response Body for GET Request By ID");
		String responseBody=response.prettyPrint();
		logg.info("Response_Body----"+responseBody);
		tst.log(Status.INFO, "Response Body for GET Request by ID");
		Assert.assertEquals(responseBody.contains("408409255"),true);
		if(responseBody.contains("408409255"))
		{

			tst.log(Status.PASS, "Test Passed");

		}
		else
		{
			tst.log(Status.FAIL, "Test failed");
	
		
		}

		
		
		
	}
	
	@Test
	public void check_statusCode()
	{
		ExtentTest tst = ex.createTest("GET2_CASE2_STATUS", "Check GET Request by ID");
		int code=response.getStatusCode();
		logg.info("Status_Code------"+code);
		tst.log(Status.INFO, "Status Code for GET Request by ID");
		Assert.assertEquals(code, 200);
		
		if(code==200)
		{
			tst.log(Status.PASS, "Test Passed");

		}
		else
		{
			tst.log(Status.FAIL, "Test failed");
	
		}


	}

	@AfterClass
	public void AtEnd()
	{
		logg.info(" Finish");
	}
}
