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

public class Uploadinterfacepage extends PrismCommonBase {

		public Uploadinterfacepage (WebDriver driver){
			super(driver);
		}
		
		public By project(String project) {
			By by = By.xpath("//strong[text()='" + project + "']");
			return by;
		}

		public By selectmap(String Interfacename) {
			By by = By.xpath("//strong[text()='" + Interfacename + "']");
			return by;
		}
		
		public By selectprojectintab(String project) {
		By by = By.xpath("//a[text()='" + project + "']");
		return by;
	}
		
		@FindBy (how= How.XPATH, using= "//a[text()='Toggle Inputs']")
		public WebElement toggleinput;
		
		@FindBy (how= How.XPATH, using= "//a[text()='Upload Artifact']")
		public WebElement upload;
		
		@FindBy (how= How.ID, using= "artifact")
		public WebElement choosefile;
		
		@FindBy (how= How.XPATH, using= "//button[text()='Upload']")
		public WebElement uploadfile;
		
		@FindBy (how= How.XPATH, using= "//strong[text()='Interfaces']")
		public WebElement selectinterfaces;
		
		public void clicktoggleinput() throws Exception{
			super.click(toggleinput, "Toggle input button");
		
		}
		
		public void clickselectproject(String projectname) throws Exception{
			super.clickElementtoscrollpage(mDriver.findElement(project(projectname)), "Select the project");
			super.click(mDriver.findElement(project(projectname)), "Select the project");
		}
		
		public void clickuploadartifact() throws Exception{
			super.click(upload, "Upload Artifact button");
		}
		
		public void passthefile(String filelocation) throws Exception{
			super.type(choosefile, filelocation,"file to upload");
		}
		
		public void clickuploadfile() throws Exception{
			super.click(uploadfile, "Upload a interface");
		}
		
		
		public void clickselectinterface() throws Exception{
		   super.click(selectinterfaces, "select the interfaces");
		}
		
		public void clickselectparticularinterface(String mapname) throws Exception{
			   super.click(mDriver.findElement(selectmap(mapname)), "select the particular interfaces");
			}
		
		public void clickselectprojects(String projectname) throws Exception{
			 super.click(mDriver.findElement(project(projectname)), "Select the project");
		}
		
		public void clickselectprojecttab(String project) throws Exception{
			super.clickElementtoscrollpage(mDriver.findElement(selectprojectintab(project)), "Select the project");
			 super.click(mDriver.findElement(selectprojectintab(project)), "Select the project in tab");
		}
		
		
		public boolean uploadartifact(String project, String Interfacename, String filelocation){
			try{
				this.clicktoggleinput();
				this.clickselectproject(project);
				this.clickuploadartifact();
				this.passthefile(filelocation);
				this.clickuploadfile();
				waitforinvisibilityofelement(By.xpath("//button[text()='Upload']"));
				verifyNotification("Files uploaded successfully");
				this.clickselectinterface();
				this.clickselectparticularinterface(Interfacename);
				this.clickselectprojecttab(project);
				return true;
			} catch(Exception e){
				ATUReports.add("Failed to upload Artifact. Error: " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				return false;
			}
		}
	}


