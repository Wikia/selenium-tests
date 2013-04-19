package com.wikia.webdriver.PageObjectsFactory.PageObject.WAM;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class WamPageObject extends BasePageObject{

	@FindBy(className="wam-tabs")
	private WebElement wamTabs;
	@FindBy(className="paginator-next")
	private WebElement dropdownSelector;
	@FindBys(@FindBy(css="td a"))
	private List<WebElement> wikiUrls;
	
	
	
	public WamPageObject(WebDriver driver) {
		super(driver);
	}
	
	
	public WamPageObject getWamPage(){
		getUrl(Global.LIVE_DOMAIN+"WAM");
		waitForElementByElement(wamTabs);
		return new WamPageObject(driver);
	}
	
	public List<String> getUrls(){
		List<String> urls = new ArrayList<String>();
		for(WebElement elem:wikiUrls){
			urls.add(elem.getText());
		}
		return urls;
	}
	
	public void compareListNotEquals(List<String> list1, List<String> list2){
		for (int i=0; i<list1.size(); i++){
			Assertion.assertNotEquals(list1.get(i), list2.get(i)); 
		}
	}
	
	public void clickNextButton(){
		waitForElementByElement(nextButton);
		nextButton.click();
	}
	
}
