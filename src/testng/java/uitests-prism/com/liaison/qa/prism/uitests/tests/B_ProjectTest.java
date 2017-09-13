package com.liaison.qa.prism.uitests.tests;

import java.util.List;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.liaison.prism.common.ReadCSV;
import com.liaison.qa.prism.pages.Projects;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class B_ProjectTest extends A_LoginLogoutTest {

	public Projects cp;

	@Test(groups = {"Projects"})
	public void a_createProject(){
		cp = PageFactory.initElements(driver, Projects.class);
		
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Projectmodification.csv");
		for (String[] datas : data) {
			if (cp.createnewrootproject(datas[0])) {
				ATUReports.add("Project created successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
	
	@Test(groups = {"Projects"})
	public void b_editProject(){
		cp = PageFactory.initElements(driver, Projects.class);
		
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Projectmodification.csv");
		for (String[] datas : data) {
			if (cp.editrootproject(datas[0],datas[1])) {
				ATUReports.add("Project edited successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}
	
	@Test(groups = {"Projects"})
	public void c_deleteProject(){
		cp = PageFactory.initElements(driver, Projects.class);
		
		List<String[]> data = ReadCSV.readData("src\\test\\testdata\\Projectmodification.csv");
		for (String[] datas : data) {
			if (cp.deleterootproject(datas[1])) {
				ATUReports.add("Project deleted successfully.", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}
		}
	}

}
