package com.wunderlist.TestCases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.Base_Class;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC009_PUT1 extends Base_Class 
{
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
	void put_Data() throws InvalidFormatException, IOException 
	{
		String filepath = "D:\\sanjeev\\RestAPI\\postData";


		List list = new ArrayList();
		BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
		String lineText = null;
		while ((lineText = lineReader.readLine()) != null) {
			list.add(lineText);
		}
		lineReader.close(); 

		
		JSONObject json1 = new JSONObject();
		json1.put("title", list.get(7));
		json1.put("revision", Integer.parseInt((String) list.get(8)));

		//	System.out.println(list.get(4));

		Request.pathParam("id",   409064536);

		Request.header("Content-Type","application/json");
		Request.body(json1.toString());

		response = Request.request(Method.PUT, "/lists/{id}");
		response.prettyPrint();

	}

//
//	@Test
//	public void check_Response()
//	{
//		ExtentTest tst = ex.createTest("PUT_CASE1_RESPONSE", "Check Put1 Request ");
//		String response_body=response.getBody().asString();
//		tst.log(Status.INFO, "Response Code for Patch2 Request");
//		Assert.assertEquals(response_body.contains("successfully implemented"), true);
//		
//		if(response_body.contains("successfully implemented"))
//		{
//
//			tst.log(Status.PASS, "Test Passed");
//
//		}
//		else
//		{
//			tst.log(Status.FAIL, "Test failed");
//	
//		
//		}
//		
//
//	}

	@Test
	public void check_status()
	{
		ExtentTest tst = ex.createTest("PUT_CASE1_STATUS", "Check Put1 Request ");
		int code = response.getStatusCode();
		tst.log(Status.INFO, "Response Code for Put2 Request");
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
}