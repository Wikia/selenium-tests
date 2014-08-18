package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.InteractiveMaps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.InteractiveMaps.CreateAMapComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

import java.util.List;

import junit.framework.Assert;

/**
* @author Rodrigo 'RodriGomez' Molinero
* @author: Lukasz Jedrzejczak
*
*/

public class InteractiveMapsPageObject extends BasePageObject{

	public InteractiveMapsPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//UI Mapping
	@FindBy(css = "#createMap")
	private WebElement createAMapButton;
	@FindBy(css = ".map-list>li>a")
	private List<WebElement> mapList;
	@FindBy(css = ".map-list>li>a>h3")
	private List<WebElement> mapTitleList;
	@FindBy(css = ".map-list > li")
	private List<WebElement> mapCollection;
	@FindBy(css = ".Pagination")
	private WebElement paginationList;
	@FindBy(css = ".next")
	private WebElement paginationNext;
	
	public CreateAMapComponentObject clickCreateAMap() {
		waitForElementByElement(createAMapButton);
		scrollAndClick(createAMapButton);
		PageObjectLogging.log("clickCreateAMap", "create a map button clicked",  true, driver);
		return new CreateAMapComponentObject(driver);
	}
	
	public InteractiveMapPageObject clickMapWithIndex(int mapIndex) {
		WebElement selectedMap = mapList.get(mapIndex);
		waitForElementByElement(selectedMap);
		selectedMap.click();
		PageObjectLogging.log("clickMap", "Selected map clicked", true);
		return new InteractiveMapPageObject(driver);
	}
	
	public String getMapLink(int mapIndex) {
		String mapLink = mapList.get(mapIndex).getAttribute("href");
		return mapLink;
	}
	
	public String getMapTitle(int mapIndex) {
		String mapTitle = mapTitleList.get(mapIndex).getText();
		return mapTitle;
	}
	
	public void verifyCreateMapButtonExist() {
		waitForElementByElement(createAMapButton);
		Assert.assertEquals(checkIfElementOnPage(createAMapButton), true);
	}
	
	public void verifyAmountMapOnTheList() {
		waitForElementByElement(mapCollection.get(0));
		Assert.assertEquals(mapCollection.size(), 10);
		PageObjectLogging.log("verifyAmountMapOnTheList", "There is " + mapCollection.size() + " maps on the list", true);
	}
	
	public void verifyCorrectPagination() {
		waitForElementByElement(paginationList);
		Assert.assertEquals(checkIfElementOnPage(paginationList), true);
		waitForElementByElement(paginationNext);
		Assert.assertEquals(checkIfElementOnPage(paginationNext), true);
		PageObjectLogging.log("verifyCorrectPagination", "Paggination was showed", true);
	}
}
