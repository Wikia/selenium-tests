package com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;


/**
 * 
 * @author Karol
 *
 */
public class CreateNewWikiPageObjectStep4 extends BasePageObject{

	public CreateNewWikiPageObjectStep4(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	
	}
	


}
