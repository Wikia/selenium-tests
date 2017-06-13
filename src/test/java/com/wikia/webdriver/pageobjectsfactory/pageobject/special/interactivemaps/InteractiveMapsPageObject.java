package com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreateAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

public class InteractiveMapsPageObject extends ArticlePageObject {

  @FindBy(css = ".wikia-maps-create-map")
  protected WebElement createMapUnderContribute;
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
  @FindBy(css = ".no-maps")
  private WebElement emptyStateSection;

  public CreateAMapComponentObject clickCreateAMap() {
    wait.forElementVisible(createAMapButton);
    scrollAndClick(createAMapButton);
    PageObjectLogging.log("clickCreateAMap", "create a map button clicked", true, driver);
    return new CreateAMapComponentObject(driver);
  }

  public InteractiveMapPageObject clickMapWithIndex(int mapIndex) {
    WebElement selectedMap = mapList.get(mapIndex);
    wait.forElementVisible(selectedMap);
    selectedMap.click();
    PageObjectLogging.log("clickMap", "Selected map clicked", true);
    return new InteractiveMapPageObject();
  }

  public String getMapLink(int mapIndex) {
    return mapList.get(mapIndex).getAttribute("href");
  }

  public String getMapTitle(int mapIndex) {
    return mapTitleList.get(mapIndex).getText();
  }

  public InteractiveMapPageObject openMap(String wikiURL, int id) {
    getUrl(wikiURL + URLsContent.SPECIAL_MAPS + '/' + id);
    return new InteractiveMapPageObject();
  }

  public InteractiveMapPageObject openEscapedFragmentMap(String wikiURL, String id) {
    getUrl(wikiURL + URLsContent.SPECIAL_MAPS + '/' + id + URLsContent.ESCAPED_FRAGMENT);
    return new InteractiveMapPageObject();
  }

  public void verifyCreateMapButtonExists() {
    wait.forElementVisible(createAMapButton);
    Assert.assertEquals(isElementOnPage(createAMapButton), true);
  }

  public void verifyAmountMapOnTheList() {
    wait.forElementVisible(mapCollection.get(0));
    Assert.assertEquals(mapCollection.size(), 10);
    PageObjectLogging.log("verifyAmountMapOnTheList",
        "There are " + mapCollection.size() + " maps on the list", true);
  }

  public void verifyCorrectPagination() {
    wait.forElementVisible(paginationList);
    Assert.assertEquals(isElementOnPage(paginationList), true);
    wait.forElementVisible(paginationNext);
    Assert.assertEquals(isElementOnPage(paginationNext), true);
    PageObjectLogging.log("verifyCorrectPagination", "Paggination was showed", true);
  }

  public void verifyCreateMapModalNotExists() {
    Assertion.assertEquals(isElementOnPage(createMapModal), false,
        "Create map modal was not closed");
  }

  public void verifyEmptyState() {
    Assertion.assertTrue(isElementOnPage(emptyStateSection), "Expecting a empty state");
    PageObjectLogging.log("verifyCorrectPagination", "Paggination was showed", true, driver);
  }
}
