package com.wikia.webdriver.PageObjectsFactory.PageObject.Hubs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjectsFactory.PageObject.HubBasePageObject;

public class VideoGamesHubPageObject extends HubBasePageObject {


	
	
	public VideoGamesHubPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}


	

}
