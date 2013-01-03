package com.wikia.webdriver.PageObjects.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;

public class SpecialFollowPageObject extends BasePageObject{

	public SpecialFollowPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public SpecialFollowPageObject openFollowingPage(){
		getUrl(Global.DOMAIN+"/wiki/Special:Following");
		PageObjectLogging.log("openFollowingPage", "following page opened", true, driver);
		return new SpecialFollowPageObject(driver);
	}
	
	public void verifyFollowedArticle(){
		
	}

}
