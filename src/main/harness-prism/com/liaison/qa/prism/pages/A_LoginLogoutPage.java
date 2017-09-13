package com.liaison.qa.prism.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.liaison.prism.common.PrismCommonBase;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class A_LoginLogoutPage extends PrismCommonBase{
	
	public A_LoginLogoutPage (WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.ID, using = "companyLogin")
	public WebElement company;
	
	@FindBy(how = How.ID, using = "userLogin")
	public WebElement username;
	
	@FindBy(how = How.ID, using = "passLogin")
	public WebElement password;
	
	@FindBy(how = How.ID, using = "loginButton")
	public WebElement loginbutton;
	
	@FindBy(how= How.XPATH, using = "//a[contains(text(),'Toggle Inputs')]")
	public WebElement toggleButton;
	
	public void selectcompany (String companyname) throws Exception {
		super.selectByValue(company, companyname, "Company name");
	}
	
	public void enterusername (String user) throws Exception {
		super.type(username, user, "Username");
	}
	
	public void enterpassword (String pass) throws Exception {
		super.type(password, pass, "Password");
	}
	
	public void clicklogin () throws Exception {
		super.click(loginbutton, "Login Button");
	}
	
	
	public void clickToggleInputs () throws Exception {
		super.click(toggleButton, "Toggle Button");
	}
	
	public boolean logintoapplication (String companyname, String username, String password) {
		try{
			this.selectcompany(companyname);
			this.enterusername(username);
			this.enterpassword(password);
			this.clicklogin();
			this.clickToggleInputs();
			return true;
		}catch (Exception e){
			ATUReports.add("Failed to Login to Application.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}
	
}
