package com.liaison.prism.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class PrismCommonBase {

	protected WebDriver mDriver;
	protected float timeout;
	int time = Integer.parseInt(PrismStaticUtility.getConfigXmlValue("Explicittimeout"));

	public PrismCommonBase(WebDriver driver) {
		mDriver = driver;
		timeout = Integer.parseInt(PrismStaticUtility.getConfigXmlValue("Explicittimeout"));
	}

	public void click(WebElement we, String object) throws Exception {
		try {
			waitforvisibilityofelement(we);
			waitforelementtobeclickable(we);
			we.click();
			ATUReports.add(object.trim() + " is available and is clicked", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(1000);
		} catch (Exception e) {
			ATUReports.add(
					"Unable to perform click operation on the element " + object.trim() + "ERROR :"
							+ e.getMessage().split("\n")[0].trim(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void type(WebElement we, String toType, String object) throws Exception {

		try {
			waitforvisibilityofelement(we);
			we.clear();
			we.sendKeys(toType);
			ATUReports.add(object.trim() + " is enabled and value is entered", toType, LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(1000);
		} catch (Exception e) {
			ATUReports.add(
					"Unable to perform Type operation on the element " + object.trim() + "ERROR :"
							+ e.getMessage().split("\n")[0].trim(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void selectByVisibleText(WebElement we, String text, String object) throws Exception {
		try {
			Select sel = new Select(we);
			sel.selectByVisibleText(text);
			ATUReports.add(object.trim() + " is available and is selected", LogAs.PASSED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			Thread.sleep(1000);
		} catch (Exception e) {
			ATUReports.add(
					"Unable to perform select operation on the element " + object.trim() + "ERROR :"
							+ e.getMessage().split("\n")[0].trim(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}
	
	public void selectByValue(WebElement we, String value, String object) throws InterruptedException {
		try {
			Select sel = new Select(we);
			sel.selectByValue(value);
			Reporter.log(object + " is available and is clicked");
			Thread.sleep(1000);
		} catch (Exception e) {
			Reporter.log(object + " is not found");
		}
	}

	public void clickElementtoscrollpage(WebElement element, String name) {
		try {
			try {
				((JavascriptExecutor) mDriver).executeScript("arguments[0].scrollIntoView(true);", element);
				ATUReports.add(name + " is available and is clicked", LogAs.PASSED,
						new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			} catch (Exception e) {
				throw e;
			}
			element.click();
		} catch (Exception e) {

			ATUReports.add(
					"Unable to perform Click operation on the element " + name.trim() + "ERROR :"
							+ e.getMessage().split("\n")[0].trim(),
					LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void waitforvisibilityofelement(WebElement we) {
		try {
			new WebDriverWait(mDriver, time).until(ExpectedConditions.visibilityOf(we));
		} catch (WebDriverException e) {

			ATUReports.add(we + "  is not Visible and timeout of " + time, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void waitforelementtobeclickable(WebElement we) {
		try {
			new WebDriverWait(mDriver, time).until(ExpectedConditions.elementToBeClickable(we));
		} catch (WebDriverException e) {
			ATUReports.add(we + "  is not clickable and timeout of " + time, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void waitforinvisibilityofelement(By by) {
		try {
			new WebDriverWait(mDriver, time).until(ExpectedConditions.invisibilityOfElementLocated(by));
		} catch (WebDriverException e) {
			ATUReports.add(by + " did not disappear and timeout of " + time, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void waitforpresenceofelement(By by) {
		try {
			new WebDriverWait(mDriver, time).until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (WebDriverException e) {
			ATUReports.add(by + " is not present and timeout of " + time, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}

	public void verifyNotification(String expectednotification) throws Exception {
		String actualnotification = getnotification();
		if (!(actualnotification.equals(expectednotification))) {
			throw new RuntimeException(actualnotification);
		}
		Thread.sleep(5000);
	}

	public String getnotification() {
		By by = By.xpath("//ul[@id='noty_bottomRight_layout_container']/descendant::span");
		String notificationtext = null;
		waitforpresenceofelement(by);
		waitforvisibilityofelement(mDriver.findElement(by));
		notificationtext = mDriver.findElement(by).getText();
		ATUReports.add(notificationtext, LogAs.INFO, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		return notificationtext;
	}
	
	public void mouseActions(WebElement we, String name){
		try {
		Actions act = new Actions(mDriver);
		act.moveToElement(we).build().perform();
		}catch (Exception e) {
			ATUReports.add("Failed to execute mouse action on " + name + ". Error: " + e, LogAs.FAILED,
					new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			throw e;
		}
	}
	
	public boolean scrollElementtoView(WebElement element, String name) {
		try {
			((JavascriptExecutor) mDriver).executeScript("arguments[0].scrollIntoView(true);", element);
			return true;
		} catch (Exception e) {
			return false;
		}
}
}