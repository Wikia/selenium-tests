package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialCreateTopListPageObject extends Top_10_list_EditonCore{

	@FindBy(css=".FormButtons input")
	private WebElement createlistButton; 
	@FindBy(css="#list_name")
	private WebElement listNameField; 
	
	public SpecialCreateTopListPageObject(WebDriver driver, String Domain, String top_10_list_Name) {
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}

	public Top_10_list clickCreateList() {
		waitForElementByElement(createlistButton);
		clickAndWait(createlistButton);
		return new Top_10_list(driver, Domain, this.articlename);
	}

	public void verifyListName(String listName) {
		waitForElementByElement(listNameField);
		waitForValueToBePresentInElementsAttributeByElement(listNameField, "value", listName);
		PageObjectLogging.log("verifyListName", "verify that list has its name visible: "+listName, true, driver);		
	}






}
