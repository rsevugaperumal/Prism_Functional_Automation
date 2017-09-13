package com.liaison.qa.prism.uitests.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.liaison.prism.common.PrismStaticUtility;
import com.liaison.prism.common.PrismStaticUtility.Browser;
import com.liaison.qa.prism.pages.A_LoginLogoutPage;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class, MethodListener.class })
public class A_LoginLogoutTest {
	
	public static WebDriver driver;
	public A_LoginLogoutPage lp;
	
	{
		System.setProperty("atu.reporter.config", "atu.properties");
	}

	@Parameters({ "browser"})
	@BeforeTest(groups= {"Base","com.prism"})
	public void Launchbrowser(String browser) throws InterruptedException {
	
		driver = PrismStaticUtility.getDriver(Browser.ChromeBrowser);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.navigate().to("https://10.0.25.225:6060/PrismSecurity/login");
		driver.navigate().to(PrismStaticUtility.getConfigXmlValue("applicationurl"));
		Thread.sleep(3000);
		lp = PageFactory.initElements(driver, A_LoginLogoutPage.class);
		lp.logintoapplication(PrismStaticUtility.getConfigXmlValue("Company"), PrismStaticUtility.getConfigXmlValue("Username"),PrismStaticUtility.getConfigXmlValue("Password"));
	}
	
	@AfterTest(groups= {"Base", "com.prism"})
	public void Quitbrowser() {
		driver.quit();
	}
}