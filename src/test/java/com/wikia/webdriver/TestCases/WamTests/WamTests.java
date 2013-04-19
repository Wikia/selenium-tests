package com.wikia.webdriver.TestCases.WamTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WAM.WamPageObject;

public class WamTests extends TestTemplate{

	@Test(groups= {"wam", "wam001"})
	public void wam_001(){
		WamPageObject wam = new WamPageObject(driver);
		wam.getWamPage();
		List<String> urlPage1 = wam.getUrls();
		wam.clickNextButton();
		List<String> urlPage2 = wam.getUrls();
		wam.compareListNotEquals(urlPage1, urlPage2);
	}
}
