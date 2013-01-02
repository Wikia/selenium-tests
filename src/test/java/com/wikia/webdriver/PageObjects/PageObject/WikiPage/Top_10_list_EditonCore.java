package com.wikia.webdriver.PageObjects.PageObject.WikiPage;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

// This class was created because there are 2 very similar (but not the same) PageObjects: Special:CreateTopList  and Special:EditTopList
// Special:CreateTopList extends Top_10_list_EditonCore
// Special:EditTopList   extends Top_10_list_EditonCore
// Both of the two PageObjects share many methods from Top_10_list_EditonCore. However, they have some unique methods specific to their own nature. Those are distinguished in their class-bodies

public class Top_10_list_EditonCore extends WikiBasePageObject {

	@FindBy(css="#description")
	private WebElement descriptionField; 
	
	By itemInputsList = new By.ByCssSelector("li.ListItem div.ItemName input");
	
	public Top_10_list_EditonCore(WebDriver driver, String Domain) {
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}

	public void addDescription(String description) {
		waitForElementByElement(descriptionField);
		sendKeys(descriptionField, description);
		PageObjectLogging.log("addDescription", "add Description to top 10 list: "+description, true, driver);
	}
	
	public void addItem(int index, String itemName) {
		List<WebElement> list = driver.findElements(itemInputsList);
		if (list.size()>0) {	
		WebElement itemInput = list.get(index);
		waitForElementByElement(itemInput);
		itemInput.sendKeys(itemName);
		PageObjectLogging.log("addItem", "add item number: "+index+" with name: "+itemName, true, driver);				
		}
		else {
			PageObjectLogging.log("addItem", "No item input fields on the page", false, driver);					
		}
	}
}
