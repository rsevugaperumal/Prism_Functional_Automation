package com.liaison.prism.common;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class PrismStaticUtility {
	public enum Browser {
		DefaultBrowser, FirefoxBrowser, ChromeBrowser, InternetExplorerBrowser
	}

	public static String currentbrowser;

	public static WebDriver getDriver(Browser whichBrowser) {
		try{
		Browser browser = whichBrowser;
		// Using the browser just obtained, initialize the driver variable.
		WebDriver driver = null;
		switch (browser) {
		case InternetExplorerBrowser:

			System.setProperty("webdriver.ie.driver", getConfigXmlValue("IEDriver"));
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("ensureCleanSession", true);
			capabilities.setCapability("ignoreZoomSetting", true);
			capabilities.setCapability("requireWindowFocus", true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
			WebElement wElement = driver.findElement(By.xpath("html"));
			wElement.sendKeys(Keys.LEFT_CONTROL + "0");
			wElement.sendKeys(Keys.CLEAR);
			wElement.sendKeys(Keys.LEFT_CONTROL + "-");
			wElement.sendKeys(Keys.CLEAR);
			driver.manage().window().maximize();
			currentbrowser = "InternetExplorerBrowser";
			Reporter.log("Browser set as Internet Explorer.");
			break;
		case ChromeBrowser:
			System.setProperty("webdriver.chrome.driver", getConfigXmlValue("ChromeDriver"));
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			currentbrowser = "ChromeBrowser";
			Reporter.log("Browser set as Chrome.");
			break;
		case FirefoxBrowser:
		case DefaultBrowser:
		default:

			System.setProperty("webdriver.gecko.driver", getConfigXmlValue("GeckoDriver"));
			try {
				driver = new FirefoxDriver(firefoxcapabilities());
			} catch (Exception exception) {
				if (exception.getMessage().contains("Cannot find firefox binary in PATH")) {
					FirefoxBinary firefoxbin = new FirefoxBinary(
							new File(PrismStaticUtility.getConfigXmlValue("FirefoxBinary")));
					driver = new FirefoxDriver(firefoxbin, null, firefoxcapabilities());
				} else {
					ATUReports.add("Failed to set Firefox Binary. Error: ", LogAs.FAILED,
							new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			driver.manage().window().maximize();
			currentbrowser = "FirefoxBrowser";
			Reporter.log("Browser set as Firefox.");
			break;
		}
		return driver;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		
	}

	public static String getConfigXmlValue(String xmlElement) {
		String evalStr = "//" + xmlElement + "/text()";
		try {
			return PrismConfig.getControlXpath().evaluate(evalStr, PrismConfig.getControlDoc());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Return a new xPath.
	 * 
	 * For each XML file, there needs to be a separate instance of an xPath to
	 * parse the XML file.
	 * 
	 * @return new xPath.
	 */
	public static XPath getXMLXPath() {
		XPathFactory xPath = XPathFactory.newInstance();
		return xPath.newXPath();
	}

	public static Document openXMLFile(String xmlFile) {
		Document doc = null;
		DocumentBuilder builder = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		try {
			doc = builder.parse(xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	/**
	 * Set Desired capabilites for the firefox browser.
	 * 
	 * @return DesiredCapabilities
	 */
	public static DesiredCapabilities firefoxcapabilities() {
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(true);
		profile.setPreference("browser.download.folderList", 2);
		// profile.setPreference("browser.download.dir", downloadPath);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/msword, application/csv, application/ris, text/csv, data:image/png, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.useDownloadDir", true);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.closeWhenDone", true);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("browser.download.panel.shown", false);
		dc.setCapability(FirefoxDriver.PROFILE, profile);
		return dc;
	}
}