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
import com.wunderlist.commonUtils.ExcelLib;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;

public class TC004_POST2_Data extends Base_Class
{

	@BeforeClass
	public void post2() throws InterruptedException
	{
		logg.info("POST Request");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");
		Request.log().all();
		response =Request.request(Method.POST,"/lists");

		Thread.sleep(3000);
	}
	@Test
	public void checkPOST_operation() throws InvalidFormatException, IOException
	{


		String filepath = "D:\\sanjeev\\RestAPI\\postData";
		List list = new ArrayList();
		BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
		String lineText = null;
		while ((lineText = lineReader.readLine()) != null) {
			list.add(lineText);
		}
		lineReader.close(); 
		
		ExtentTest tst = ex.createTest("POST_1", "Check validation of POST2 Request");
		JSONObject json = new JSONObject();
		
		json.put("title", list.get(2));
		Request.header("Content-Type","application/json");
		Request.body(json.toString());
		

		response =Request.request(Method.POST,"/lists");
		response.then().log().all();


		int code = response.getStatusCode();
		Assert.assertEquals(code, 201);
     	tst.log(Status.INFO, "Status code of POST_2 Request");
	}

}
