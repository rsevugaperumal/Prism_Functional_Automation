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

public class Uploadmappage extends PrismCommonBase {

	public Uploadmappage(WebDriver driver) {
		super(driver);
	}

	public By selectmapdelete(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::strong[text()='"+ name +"']/ancestor::span/following-sibling::span[a]");
		return by;
	}
	
	public By deletemapname(String name) {
		By by = By.xpath("//div[@id='leftPane']/descendant::div[span[a[strong[text()='"+ name +"']]]]/following-sibling::div/descendant::button[contains(text(),'Obsolete Item')]");
		return by;
	}
	
	public By deletemapconfirm(String name) {
		By by = By.xpath("//div[div[contains(text(),'Obsolete Item "+ name +"?')]]/following-sibling::div/button[text()='Confirm']");
		return by;
	}
	
	public By project(String project) {
		By by = By.xpath("//strong[text()='" + project + "']");
		return by;
	}

	public By selectmap(String mapname) {
		By by = By.xpath("//strong[text()='" + mapname + "']");
		return by;
	}

	public By selectprojectintab(String project) {
		By by = By.xpath("//a[text()='" + project + "']");
		return by;
	}
	public By selectEditMap(String objectname) {
		By by = By.xpath("//span[a[strong[text()='" + objectname + "']]]/following-sibling::span[1]/a[i]");
		return by;
	}
	
	public By selectEditMapname(String objectname) {
		By by = By.xpath("//div[span[a[strong[text()='" + objectname + "']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[1]/button");
		return by;
	}
	
	public By enterNewMapname(String objectname) {
		By by = By.xpath("//div[span[a[strong[text()='" + objectname + "']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[2]/input");
		return by;
	}
	
	public By saveNewMapname(String objectname) {
		By by = By.xpath("//div[span[a[strong[text()='" + objectname + "']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[2]/button[1]");
		return by;
	}
	
	public By verifyNewMapname(String objectname) {
		By by = By.xpath("//div[span[a[strong[text()='" + objectname + "']]]]/following-sibling::div/descendant::div[span[contains(text(),'Name')]]/div[1]/span[3]");
		return by;
	}
	
	
	@FindBy(how = How.XPATH, using = "//a[text()='Toggle Inputs']")
	public WebElement toggleinput;

	@FindBy(how = How.XPATH, using = "//a[text()='Projects']")
	public WebElement projectstab;

	@FindBy(how = How.XPATH, using = "//a[text()='Upload Artifact']")
	public WebElement upload;

	@FindBy(how = How.ID, using = "artifact")
	public WebElement choosefile;

	@FindBy(how = How.XPATH, using = "//button[text()='Upload']")
	public WebElement uploadfile;

	@FindBy(how = How.XPATH, using = "//strong[text()='Maps']")
	public WebElement selectMaps;

	public void clicktoggleinput() throws Exception {
		super.click(toggleinput, "Toggle input button");

	}

	public void clickselectproject(String projectname) throws Exception {
		super.clickElementtoscrollpage(mDriver.findElement(project(projectname)), "Select the project");
		super.click(mDriver.findElement(project(projectname)), "Select the project");
	}

	public void clickuploadartifact() throws Exception {
		super.click(upload, "Upload Artifact button");
	}

	public void clickprojectstab() throws Exception {
		super.click(projectstab, "Projects tab");
	}

	public void passthefile(String filelocation) throws Exception {
		super.type(choosefile, filelocation, "file to upload");
	}

	public void clickuploadfile() throws Exception {
		super.click(uploadfile, "Upload a map");
	}
	
	
	public void clickselectmaps() throws Exception{
	   super.click(selectMaps, "select the maps");
	}
	
	public void clickselectparticularmaps(String mapname) throws Exception{
		   super.click(mDriver.findElement(selectmap(mapname)), "select the particular maps");
		}
	
	public void clickselectprojects(String projectname) throws Exception{
		 super.click(mDriver.findElement(project(projectname)), "Select the project");
	}
	
	public void clickselectprojecttab(String project) throws Exception{
		 super.click(mDriver.findElement(selectprojectintab(project)), "Select the project in tab");
	}
	
	public void clickeditmap(String project) throws Exception{
		 super.click(mDriver.findElement(selectEditMap(project)), "Select Edit Map");
	}
	
	public void clickeditmapname(String project) throws Exception{
		 super.click(mDriver.findElement(selectEditMapname(project)), "Select Edit Map name");
	}
	
	public void typeNewmapname(String project, String newname) throws Exception{
		 super.type(mDriver.findElement(enterNewMapname(project)), newname, "Enter new map name");
	}
	
	public void saveNewmapname(String project) throws Exception{
		 super.click(mDriver.findElement(saveNewMapname(project)), "Save new map name");
	}
	
	public void clickmapoption(String name) throws Exception {
		super.click(mDriver.findElement(selectmapdelete(name)), name + "Project");
	}
	
	public void clickdeletemap(String name) throws Exception {
		super.click(mDriver.findElement(deletemapname(name)), "Delete "+ name + "Project name");
	}
	
	public void clickconfirmdeletemap(String name) throws Exception {
		super.click(mDriver.findElement(deletemapconfirm(name)), "Confirm Delete "+ name + "Project name");
	}
	
	public boolean uploadmap(String project, String mapname, String filelocation){
		try{
			this.clicktoggleinput();
			this.clickprojectstab();
			this.clickselectproject(project);
			this.clickuploadartifact();
			this.passthefile(filelocation);
			this.clickuploadfile();
			verifyNotification("Files uploaded successfully");
//			waitforinvisibilityofelement(By.xpath("//button[text()='Upload']"));
			this.clickselectmaps();
			this.clickselectparticularmaps(mapname);
			waitforpresenceofelement(selectprojectintab(mapname));
			waitforvisibilityofelement(mDriver.findElement(selectprojectintab(mapname)));
			this.clickselectprojecttab(project);
			return true;
		} catch(Exception e){
			ATUReports.add("Failed to upload Maps. Error: " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;	
		}
		
	}
	
	public boolean renameMap(String mapname, String newmapname){
		try{
			this.clickeditmap(mapname);
			this.clickeditmapname(mapname);
			this.typeNewmapname(mapname, newmapname);
			this.saveNewmapname(mapname);
			
			if(!(mDriver.findElement(verifyNewMapname(newmapname)).getText().equals(newmapname))) {
				throw new RuntimeException("Map name does not matches with the new map name.");
			}
			
			this.clickeditmap(newmapname);
			return true;
		} catch(Exception e){
			ATUReports.add("Failed to Rename Map. Error: " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;	
		}
		
	}
	
	public boolean deletemap(String mapname) {

		try {

			this.clickmapoption(mapname);
			this.clickdeletemap(mapname);
			this.clickconfirmdeletemap(mapname);
			verifyNotification(mapname + " Has Been Obsoleted");
			return true;
		} catch (Exception e) {
			ATUReports.add("Failed to delete project. " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}
	
}
