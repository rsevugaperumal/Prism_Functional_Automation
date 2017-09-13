package com.liaison.qa.prism.uitests.tests;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.liaison.prism.common.ReadCSV;
import com.liaison.qa.prism.pages.Addingpredicate;
import com.liaison.qa.prism.pages.Uploadmappage;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class E_UploadMaptest extends A_LoginLogoutTest {
	public Uploadmappage ua;
	public Addingpredicate cp;

	@Test(groups = {"Maps", "com.prism"})
	public void a_uploadmaps() {
		ua = PageFactory.initElements(driver, Uploadmappage.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\uploadartifactfile.csv");
		for (String[] datas : data) {
			if (ua.uploadmap(datas[1], datas[2], datas[3])) {
				ATUReports.add("Maps uploaded successfully.", LogAs.PASSED,	new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
	
	@Test(groups = {"Maps", "com.prism"})
	public void b_createMapspredicate() {
		cp = PageFactory.initElements(driver, Addingpredicate.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Addingpredicatesbetweenmap.csv");
		for (String[] datas : data) {
			if (cp.createpredicate(datas[0], datas[1], datas[2], datas[3], "Maps")) {
				ATUReports.add("Predicate created for map successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
	
	@Test(groups = {"Maps", "com.prism"})
	public void c_editmapname() {
		ua = PageFactory.initElements(driver, Uploadmappage.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\uploadartifactfile.csv");
		for (String[] datas : data) {
			if (ua.renameMap(datas[2], datas[4])) {
				ATUReports.add("Maps renmaed successfully.", LogAs.PASSED,	new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
	
	@Test(groups = {"Maps", "com.prism"})
	public void d_deletemapname() {
		ua = PageFactory.initElements(driver, Uploadmappage.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\uploadartifactfile.csv");
		for (String[] datas : data) {
			if (ua.deletemap(datas[4])) {
				ATUReports.add("Maps renmaed successfully.", LogAs.PASSED,	new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
}