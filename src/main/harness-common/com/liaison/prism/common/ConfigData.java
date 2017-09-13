package com.liaison.prism.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigData {

	public static Properties getConfiguration(String filelocation, String fileName) {

		Properties properties = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filelocation + fileName.toLowerCase() + ".txt");
			properties.load(input);
		} catch (IOException e) {
			return new Properties();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return properties;
	}

	public static void saveConfiguration(String fileLocation, String fileName, Properties properties) {

		File dir = new File(fileLocation);
		if (!dir.exists()) {
			dir.mkdir();
		}

		OutputStream output = null;

		try {
			output = new FileOutputStream(fileLocation + fileName.toLowerCase() + ".txt");
			properties.store(output, null);
		} catch (IOException e) {
			throw new RuntimeException("save configuration failed");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static void setData(String fileLocation, String fileName, String property, String value) {

		Properties properties = getConfiguration(fileLocation, fileName);
		properties.setProperty(property, value);
		saveConfiguration(fileLocation, fileName, properties);
	}

	public static String getData(String fileLocation, String fileName, String value) {

		Properties properties = getConfiguration(fileLocation, fileName);
		System.out.println(properties.getProperty(value));
		return properties.getProperty(value);
	}
}