package com.agl.petcatalog.utils;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigFileUtils {
	private final static Logger LOGGER = Logger.getLogger(ConfigFileUtils.class.getName());
	
	public static Properties initialize(String filePath)  {
		/*
		 * Input Type  : Nil
		 * Return Type : Nil
		 * Description : This method is used to initialize the config property file.
		 */
		SortedProperties testdataConfig = new SortedProperties();
		try {
			testdataConfig.load(new FileInputStream(filePath));
		} 
		catch (Exception e) {LOGGER.log(Level.SEVERE,"script exception"+e);}
		return testdataConfig;
	}
	
	public static String readConfigFile(String key,String filePath) {
		/*
		 * Input Type  : String
		 * Return Type : String
		 * Description : This method will fetch the property value for given key
		 */
		String propertyValue=null;
		try{
			Properties testdataConfig=initialize(filePath);
			propertyValue = testdataConfig.getProperty(key);
		}
		catch (Exception e) {
			LOGGER.log(Level.SEVERE,"script exception"+e);
		}
		return propertyValue; 
	}
}

/*
 * Sorting the property keys
 */
@SuppressWarnings("serial")
class SortedProperties extends Properties {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Enumeration keys() {
		Enumeration keysEnum = super.keys();
		Vector<String> keyList = new Vector<String>();
		while(keysEnum.hasMoreElements()){
			keyList.add((String)keysEnum.nextElement());
		}
		Collections.sort(keyList);
		return keyList.elements();
	}
}