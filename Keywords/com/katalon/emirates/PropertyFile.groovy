package com.katalon.emirates

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration



public class PropertyFile {

	@Keyword
	def String readValuePropertyFile(String key) {
		String propertyFilePath = RunConfiguration.getProjectDir()+"/TestFiles/cons.properties";

		// Create a Properties object
		Properties properties = new Properties();

		try {
			// Load the property file
			properties.load(new FileInputStream(propertyFilePath));
			String value = properties.getProperty(key);

			System.out.println("Value: " + value);
			return value
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Keyword
	def writeValuePropertyFile(String key, String value) {
		// Path to the property file
		String propertyFilePath = RunConfiguration.getProjectDir()+"/TestFiles/katalon.properties"

		// Load the property file
		Properties properties = new Properties()
		FileInputStream inputStream = new FileInputStream(propertyFilePath)
		properties.load(inputStream)
		inputStream.close()

		// Set the new value
		properties.setProperty(key, value)

		// Write the updated properties to the file
		FileOutputStream outputStream = new FileOutputStream(propertyFilePath)
		properties.store(outputStream, null)
		outputStream.close()
	}
}
