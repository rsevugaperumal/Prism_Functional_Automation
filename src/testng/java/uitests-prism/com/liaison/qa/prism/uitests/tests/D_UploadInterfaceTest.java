package com.liaison.qa.prism.uitests.tests;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.liaison.prism.common.ReadCSV;
import com.liaison.qa.prism.pages.Addingpredicate;
import com.liaison.qa.prism.pages.Uploadinterfacepage;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class D_UploadInterfaceTest extends A_LoginLogoutTest {
	public Uploadinterfacepage ub;
	public Addingpredicate cp;

	@Test(groups = {"Interface", "com.prism"})
	public void a_uploadinterface() {
		ub = PageFactory.initElements(driver, Uploadinterfacepage.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\uploadinterfacefile.csv");
		for (String[] datas : data) {
			if (ub.uploadartifact(datas[1], datas[2], datas[3])) {
				ATUReports.add("Interface uploaded successfully.", LogAs.PASSED,	new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}

	@Test(groups = {"Interface", "com.prism"})
	public void b_createInterfacePredicate() {
		cp = PageFactory.initElements(driver, Addingpredicate.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Addingpredicatesbetweeninterface.csv");
		for (String[] datas : data) {
			if (cp.createpredicate(datas[0], datas[1], datas[2], datas[3], "Interfaces")) {
				ATUReports.add("Predicate created for interface successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}

}