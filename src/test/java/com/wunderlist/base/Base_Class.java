package com.wunderlist.base;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.wunderlist.commonUtils.ReportLib;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class  Base_Class extends ReportLib
{

	public static RequestSpecification Request;
	public static Response response;
	
	public Logger logg;
	
	@BeforeClass
	public void setUp()
	{
		logg=Logger.getLogger("RestAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logg.setLevel(Level.DEBUG);
	}
	public ArrayList<String> listHeader() {
		String filepath = "D:\\sanjeev\\RestAPI\\postData";
		 List list = new ArrayList();
		 try {
		     BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
		     String lineText = null;
		     while ((lineText = lineReader.readLine()) != null) {
		      list.add(lineText);
		     }
		     lineReader.close();
		 } catch (IOException ex) {
		     System.err.println(ex);
		 }
		 return (ArrayList<String>) list;
		} 
}
