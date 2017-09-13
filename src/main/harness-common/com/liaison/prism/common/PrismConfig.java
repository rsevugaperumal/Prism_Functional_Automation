package com.liaison.prism.common;

import javax.xml.xpath.XPath;

import org.w3c.dom.Document;

public class PrismConfig {
private static boolean initializedControlFile = false;
	
	/**
	 * controlDoc and languageDoc contain the control and language XML files, respectively
	 * They need to be static to avoid wasting memory (no need to have multiple instances of these documents open).
	 */
	private static Document controlDoc;
	
	/**
	 * controlXpath and languageXpath are used to search through the controlDoc and languageDoc.
	 * These are also static, as only one instance is required.
	 */
	private static XPath controlXpath;
	
	
	public static Document getControlDoc(){
		if (!initializedControlFile){
			initializeControlFile();
		}
		
		return(controlDoc);
	}
	
	public static XPath getControlXpath(){
		if (!initializedControlFile){
			initializeControlFile();
		}
		
		return(controlXpath);
	}
	
	/**
	 * Open the Control.xml file and create a new xPath for parsing it.
	 */
	public static void initializeControlFile(String controlFileName) {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		String xmlControlFile = "src\\test\\resources\\configuration\\" + controlFileName;
		controlDoc = PrismStaticUtility.openXMLFile(xmlControlFile);
		controlXpath = PrismStaticUtility.getXMLXPath();
		initializedControlFile = true;
	}
	
	/**
	 * Initialize with default control file.
	 */
	public static void initializeControlFile(){
		initializeControlFile("Control.xml");
	}
}
