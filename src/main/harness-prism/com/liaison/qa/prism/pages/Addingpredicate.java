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

public class Addingpredicate extends PrismCommonBase {

	public Addingpredicate(WebDriver driver) {
		super(driver);
	}

	public By project(String project) {
		By by = By.xpath("//strong[text()='" + project + "']");
		return by;
	}

	public By selectPinComponent(String objectname) {
		By by = By.xpath("//span[a[strong[text()='" + objectname + "']]]/preceding-sibling::span[3]/a[i]");
		return by;
	}
	
	public By selectPredicateComponent(String objectname) {
		By by = By.xpath("//span[a[strong[text()='" + objectname + "']]]/preceding-sibling::span[1]/a[i]");
		return by;
	}
	
	public By usecase(String usecase) {
		By by = By.xpath("//strong[text()='" + usecase + "']");
		return by;
	}

	@FindBy(how = How.XPATH, using = "//a[text()='Projects']")
	public WebElement projectstab;

	/*@FindBy(how = How.XPATH, using = "//strong[text()='Maps']")
	public WebElement selectMaps;*/

	@FindBy(how = How.XPATH, using = "//button[text()='Upload']")
	public WebElement uploadfile;

	@FindBy(how = How.XPATH, using = "//strong[text()='Component Files']")
	public WebElement selectinterfaces;

	@FindBy(how = How.XPATH, using = "//a[text()='Create Predicate']")
	public WebElement selectcreatepredicate;
	
	@FindBy(how = How.XPATH, using = "//div[text()='Predicate:']/following-sibling::span/select")
	public WebElement selectPredicate;

	@FindBy(how = How.XPATH, using = "//button[text()='Add Predicate']")
	public WebElement AddPredicate;
	
		public void clickselectproject(String projectname) throws Exception{
		super.clickElementtoscrollpage(mDriver.findElement(project(projectname)), "Select the project");
		super.click(mDriver.findElement(project(projectname)), "Select the project");
	}

	public void clickprojectstab() throws Exception{
		super.click(projectstab, "Projects tab");
	}
	
	public void selectcreatepredicate() throws Exception{
		super.click(selectcreatepredicate, "Select Create Predicate");
	}
	
	public void selectPredicate(String predicate) throws Exception {
		super.selectByVisibleText(selectPredicate, predicate ,"Select Predicate");
	}

	public void clickAddPredicate() throws Exception {
		super.click(AddPredicate, "Add Predicate");
	}
	
	public void clickselectpinobject(String projectname) {
		super.clickElementtoscrollpage(mDriver.findElement(selectPinComponent(projectname)), "Select the project");
	}
	
	public void clickselectusecase(String usecase) throws Exception{
		   super.click(mDriver.findElement(usecase(usecase)), "select the "+usecase);
		}
	
	public void checkisPredicateCreated(String object, String Predicate) throws Exception {
		super.click(mDriver.findElement(selectPredicateComponent(object)), "Select predicate for " + object);
		if(super.scrollElementtoView(mDriver.findElement(By.xpath("//div[div[text()='Predicates']]/descendant::span[text()='" + Predicate +"']")), object + Predicate)) {
			ATUReports.add("Verification predicate creation is successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		} else {
			ATUReports.add("Verification predicate creation is failed.", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	
	public boolean createpredicate(String project, String object1, String object2,String Predicate, String usecase) {
		try {
			this.clickselectusecase(usecase);
			this.clickselectpinobject(object1);
			this.clickselectpinobject(object2);
			this.selectcreatepredicate();
			this.selectPredicate(Predicate);
			this.clickAddPredicate();
			verifyNotification("Predicate Created");
			return true;
		} catch (Exception e) {
			ATUReports.add("Failed to create predicate. Error: " + e, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			return false;
		}
	}
}
