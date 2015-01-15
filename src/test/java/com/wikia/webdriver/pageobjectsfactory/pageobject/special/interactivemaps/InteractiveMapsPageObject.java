package com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * @author Rodrigo 'RodriGomez' Molinero
 * @author: Lukasz Jedrzejczak
 */

public class InteractiveMapsPageObject extends ArticlePageObject {

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
	@FindBy(css = "#intMapCreateMapModal")
	private WebElement createMapModal;
	@FindBy(css = ".wikia-maps-create-map")
	protected WebElement createMapUnderContribute;
	@FindBy(css = ".no-maps")
	private WebElement emptyStateSection;

	public InteractiveMapsPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public CreateAMapComponentObject clickCreateAMap() {
		waitForElementByElement(createAMapButton);
		scrollAndClick(createAMapButton);
		PageObjectLogging.log("clickCreateAMap", "create a map button clicked", true, driver);
		return new CreateAMapComponentObject(driver);
	}

	public CreateAMapComponentObject clickCreateAMapUnderContributeButton() {
		waitForElementByElement(contributeDropdown);
		scrollAndClick(contributeDropdown);
		waitForElementVisibleByElement(createMapUnderContribute);
		scrollAndClick(createMapUnderContribute);
		PageObjectLogging.log("clickCreateAMapUnderContributeButton", "create a map button under contribute button clicked", true, driver);
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
		return mapList.get(mapIndex).getAttribute("href");
	}

	public String getMapTitle(int mapIndex) {
		return mapTitleList.get(mapIndex).getText();
	}

	public InteractiveMapPageObject openMap(String wikiURL, int id) {
		getUrl(wikiURL + URLsContent.SPECIAL_MAPS + '/' + id);
		return new InteractiveMapPageObject(driver);
	}

	public InteractiveMapPageObject openEscapedFragmentMap(String wikiURL, String id) {
		getUrl(wikiURL + URLsContent.SPECIAL_MAPS + '/' + id + URLsContent.ESCAPED_FRAGMENT);
		return new InteractiveMapPageObject(driver);
	}

	public void verifyCreateMapButtonExists() {
		waitForElementByElement(createAMapButton);
		Assert.assertEquals(checkIfElementOnPage(createAMapButton), true);
	}

	public void verifyAmountMapOnTheList() {
		waitForElementByElement(mapCollection.get(0));
		Assert.assertEquals(mapCollection.size(), 10);
		PageObjectLogging.log("verifyAmountMapOnTheList", "There are " + mapCollection.size() + " maps on the list", true);
	}

	public void verifyCorrectPagination() {
		waitForElementByElement(paginationList);
		Assert.assertEquals(checkIfElementOnPage(paginationList), true);
		waitForElementByElement(paginationNext);
		Assert.assertEquals(checkIfElementOnPage(paginationNext), true);
		PageObjectLogging.log("verifyCorrectPagination", "Paggination was showed", true);
	}

	public void verifyCreateMapModalNotExists() {
		Assertion.assertEquals(checkIfElementOnPage(createMapModal), false, "Create map modal was not closed");
	}

	public void verifyEmptyState() {
		Assertion.assertTrue(checkIfElementOnPage(emptyStateSection), "Expecting a empty state");
		PageObjectLogging.log("verifyCorrectPagination", "Paggination was showed", true, driver);
	}
}
