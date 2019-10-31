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

public class TC008_PATCH2 extends Base_Class 
{
	@BeforeClass
	public void patch() throws InterruptedException 
	{
		logg.info(" PATCH Request ");
		RestAssured.baseURI="https://a.wunderlist.com/api/v1";
		Request=RestAssured.given();
		Request.header("Content-Type","application/json");
		Request.header("X-Access-Token","edb719da640b8e01f2b9ae9808c115938d12e4af892a6db513d582214025");
		Request.header("X-Client-ID","df0e5ab117c827fc70bd");

	}

	@Test
	void patch_1() throws InvalidFormatException, IOException 
	{
		String filepath = "D:\\sanjeev\\RestAPI\\postData";


		List list = new ArrayList();
		BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
		String lineText = null;
		while ((lineText = lineReader.readLine()) != null) {
			list.add(lineText);
		}
		lineReader.close(); 

		ExtentTest test1 = ex.createTest("PATCH2", "Check validation for PATCH2 Request");
		JSONObject json1 = new JSONObject();
		json1.put("title", list.get(5));
		json1.put("revision", Integer.parseInt((String) list.get(6)));
		
	//	System.out.println(list.get(4));
		 
        Request.pathParam("id",   409066859);

		Request.header("Content-Type","application/json");
		Request.body(json1.toString());

		response = Request.request(Method.PATCH, "/lists/{id}");
		response.prettyPrint();

		int code = response.getStatusCode();
		Assert.assertEquals(code, 200);
		test1.log(Status.INFO, "Status code of first PATCH2 Request");
	}
}