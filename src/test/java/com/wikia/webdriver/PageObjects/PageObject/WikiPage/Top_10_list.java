package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class Top_10_list extends WikiArticlePageObject{

	@FindBy(css="#WikiaPageHeader h1")
	WebElement wikiaHeader;
	@FindBy(css="#ca-viewsource")
	WebElement editButton;
	@FindBy(css="#WikiaArticle a.image img")
	WebElement photoOnThePage;
	
	By itemsContentList = new By.ByCssSelector("#toplists-list-body div.ItemContent");
	
	public Top_10_list(WebDriver driver, String Domain, String wikiArticle) {
		super(driver, Domain, wikiArticle);
		PageFactory.initElements(driver, this);
	}

	public void verifyTop10listPageTitle(String title) {
		waitForElementByElement(wikiaHeader);
		waitForTextToBePresentInElementByElement(wikiaHeader, title);
		PageObjectLogging.log("verifyTop10listPageTitle", "verify title is: "+title, true, driver);
		
	}

	public void verifyItemPresent(int index, String itemName) {
		List<WebElement> list = driver.findElements(itemsContentList);
		if (list.size()>0) {			
			WebElement itemInput = list.get(index-1);
			waitForElementByElement(itemInput);
			PageObjectLogging.log("verifyItemPresent", "verify item number: "+index+" with name: "+itemName, true, driver);		
		}
		else {
			PageObjectLogging.log("verifyItemPresent", "No items found on the page", false, driver);					
		}
	}

	public Top_10_list_EditMode clickEditAsAnon(String top10listName) {
		waitForElementByElement(editButton);
		executeScript("$('#ca-viewsource').click()");
		PageObjectLogging.log("clickEdit", "click on edit button", true, driver);
		return new Top_10_list_EditMode(driver, top10listName);				
	}

	public void verifyPhotoOnTop10page(String photoName) {
		waitForElementByElement(photoOnThePage);
		waitForValueToBePresentInElementsAttributeByElement(photoOnThePage, "alt", photoName);
		PageObjectLogging.log("verifyPhotoOnTop10page", "verify that the following photo is present on the page: "+photoName, true, driver);		
	}

}
