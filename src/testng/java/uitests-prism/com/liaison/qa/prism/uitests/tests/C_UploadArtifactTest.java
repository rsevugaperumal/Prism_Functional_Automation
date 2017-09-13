package com.liaison.qa.prism.uitests.tests;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.liaison.prism.common.ReadCSV;
import com.liaison.qa.prism.pages.Addingpredicate;
import com.liaison.qa.prism.pages.UploadArtifact;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class C_UploadArtifactTest extends A_LoginLogoutTest{
	public Addingpredicate cp;
	public UploadArtifact uc;
	
	@Test(groups = {"Artifact", "com.prism", "negative"})
	public void a_uploadArtifact() {
		uc = PageFactory.initElements(driver, UploadArtifact.class);

		
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\uploadCompfile.csv");
		for (String[] datas : data) {
			
			if (uc.uploadartifact(datas[1], datas[2], datas[3])) {
				ATUReports.add("Artifact uploaded successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}

	@Test(groups = {"Artifact", "com.prism"})
	public void b_createArtifactPredicate() {
		
		cp = PageFactory.initElements(driver, Addingpredicate.class);
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Addingpredicatesbetweencomp.csv");
		for (String[] datas : data) {

			if (cp.createpredicate(datas[0], datas[1], datas[2], datas[3], "Component Files")) {
				ATUReports.add("Predicate created successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}

}
