package com.wunderlist.commonUtils;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ReportLib
{
	ExtentHtmlReporter extentHTML ;
	public static ExtentReports ex=new ExtentReports();

	@BeforeSuite	
	public void reportSetUp()
	{
		extentHTML = new ExtentHtmlReporter("Report.html");
		ex = new ExtentReports();
		ex.attachReporter(extentHTML);
	}

	
}
