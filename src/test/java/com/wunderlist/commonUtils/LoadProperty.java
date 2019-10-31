package com.wunderlist.commonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class LoadProperty
{
	static String var = null;

	public static String getVar(String key,String file)
	{
		Properties props = new Properties();
		String path = System.getProperty("user.dir");
		try {
			// load a properties file
			path = path + "/resources/";
			
			if (file=="config")
			{
				props.load(new FileInputStream(path + "config.properties"));
			}
			if (key != null) 
			{
				var =  props.getProperty(key);
			}
			else
			{
				System.out.println("Got null value in key, you might have provided a null value while calling getVar(String key)");
			}

		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return var;
}
}
