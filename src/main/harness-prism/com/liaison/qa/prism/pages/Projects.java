package com.liaison.qa.prism.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.liaison.prism.common.PrismCommonBase;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class Projects extends PrismCommonBase {

	public Projects(WebDriver driver) {
		super(driver);

	}
	
	
	public By selectproject(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::strong[text()='"+ name +"']/ancestor::span/following-sibling::span[a]");
		return by;
	}
	
	public By editprojectname(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[1]/button");
		return by;
	}
	
	public By enternewprojectname(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[2]/input");
		return by;
	}
	
	public By editprojectnamesave(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[2]/button[1]");
		return by;
	}
	
	public By verifyNewprojectname(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[3]");
		return by;
	}
	
	public By deleteprojectname(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::button[contains(text(),'Delete Item')]");
		return by;
	}
	
	public By deleteprojectconfirm(String name) {
		By by = By.xpath("//div[div[contains(text(),'Delete Item "+ name +"?')]]/following-sibling::div/button[text()='Confirm']");
		return by;
	}
	
	@FindBy(how = How.LINK_TEXT, using = "New Root Project")
	public WebElement newRootProject;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Create New Root Project')]/following-sibling::div/input")
	public WebElement projectname;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Create New Root Project')]/following-sibling::div/div/button[1]")
	public WebElement createproject;

	public void clicknewProject() throws Exception {
		super.click(newRootProject, "New Root Project");

	}

	public void enterprojectName(String name) throws Exception {
		super.type(projectname, name, "Project name");
	}

	public void clickcreate() throws Exception {
		super.click(createproject, "Create the project");
	}
	
	public void clickprojectoption(String name) throws Exception {
		super.click(mDriver.findElement(selectproject(name)), name + "Project");
	}
	
	public void clickeditproject(String name) throws Exception {
		super.click(mDriver.findElement(editprojectname(name)), "Edit "+ name + "Project name");
	}

	public void enterprojectname(String name, String newname) throws Exception {
		super.type(mDriver.findElement(enternewprojectname(name)), newname ,"Edit "+ name + "Project name");
	}
	
	public void clicksaveeditname(String name) throws Exception {
		super.click(mDriver.findElement(editprojectnamesave(name)), "Edit "+ name + "Project name");
	}
	
	public void clickdeleteproject(String name) throws Exception {
		super.click(mDriver.findElement(deleteprojectname(name)), "Delete "+ name + "Project name");
	}
	
	public void clickconfirmdeleteproject(String name) throws Exception {
		super.click(mDriver.findElement(deleteprojectconfirm(name)), "Confirm Delete "+ name + "Project name");
	}
	
	public boolean createnewrootproject(String projectname) {

		try {

			this.clicknewProject();
			this.enterprojectName(projectname);
			this.clickcreate();
			verifyNotification("Root Project " + projectname + " Created");
			return true;
		} catch (Exception e) {
			ATUReports.add("Failed to create project. " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}

	public boolean editrootproject(String projectname, String newname) {

		try {

			this.clickprojectoption(projectname);
			this.clickeditproject(projectname);
			this.enterprojectname(projectname, newname);
			this.clicksaveeditname(projectname);
			
			verifyNotification("Item Was Succesfully Renamed");
			
			if(!(mDriver.findElement(verifyNewprojectname(newname)).getText().equals(newname))) {
				throw new RuntimeException("Project name does not matches with the new project name.");
			}
			
			this.clickprojectoption(newname);
			return true;
		} catch (Exception e) {
			ATUReports.add("Failed to edit project name. " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}
	
	public boolean deleterootproject(String projectname) {

		try {

			this.clickprojectoption(projectname);
			this.clickdeleteproject(projectname);
			this.clickconfirmdeleteproject(projectname);
			verifyNotification(projectname + " Has Been Deleted");
			return true;
		} catch (Exception e) {
			ATUReports.add("Failed to delete project. " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}
}
