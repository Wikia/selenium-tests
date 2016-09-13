package com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.AddPinComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

public class InteractiveMapPageObject extends BasePageObject {

  private final By escapedFragmentMetaDataTag = By.cssSelector("meta[name='fragment']");
  @FindBy(css = ".WikiaPageHeader>h1")
  private WebElement createdMapTitle;
  @FindBy(css = ".point-type.enabled > span")
  private List<WebElement> createdPinNames;
  @FindBy(css = "#filterMenu")
  private WebElement filterBox;
  @FindBy(css = "iframe[name=wikia-interactive-map]")
  private WebElement mapFrame;
  @FindBy(css = ".leaflet-draw-draw-marker")
  private WebElement addPinButton;
  @FindBy(css = ".leaflet-control-embed-map-code-button")
  private WebElement embedMapCodeButton;
  @FindBy(css = "#intMapEmbedMap")
  private WebElement embedMapDialog;
  @FindBy(css = ".code-sample.small")
  private WebElement embedMapCodeSmall;
  @FindBy(css = ".code-sample.medium")
  private WebElement embedMapCodeMedium;
  @FindBy(css = ".code-sample.large")
  private WebElement embedMapCodeLarge;
  @FindBy(css = "button[data-size='small']")
  private WebElement embedMapCodeSmallButton;
  @FindBy(css = "button[data-size='medium']")
  private WebElement embedMapCodeMediumButton;
  @FindBy(css = "button[data-size='large']")
  private WebElement embedMapCodeLargeButton;
  @FindBy(css = ".code-sample")
  private WebElement embedCode;
  @FindBy(css = "#editPointTypes")
  private WebElement editPinTypesButton;
  @FindBy(css = ".leaflet-marker-icon")
  private List<WebElement> pinCollection;
  @FindBy(css = ".leaflet-popup-content")
  private WebElement popUpContent;
  @FindBy(css = ".description > h3")
  private WebElement pinTitle;
  @FindBy(css = "#allPointTypes")
  private WebElement allPinTypes;
  @FindBy(css = ".point-type.enabled")
  private List<WebElement> enabledPinTypesCollection;
  @FindBy(css = "li[class=point-type]")
  private List<WebElement> disabledPinTypesCollection;
  @FindBy(css = ".leaflet-control-zoom-in")
  private WebElement zoomInButton;
  @FindBy(css = ".leaflet-control-zoom-out")
  private WebElement zoomOutButton;
  @FindBy(css = ".description")
  private WebElement pinDescription;
  @FindBy(css = ".edit-poi-link")
  private WebElement pinEditLink;
  @FindBy(css = ".photo")
  private WebElement pinPopupImage;
  @FindBy(css = ".leaflet-tile-loaded")
  private List<WebElement> mapImagesCollection;
  @FindBy(css = ".filter-menu-header > span")
  private WebElement filterBoxTitle;
  @FindBy(css = ".wikia-interactive-maps-page-header .drop")
  private WebElement actionDropDown;
  @FindBy(css = ".wikia-interactive-maps-page-header .WikiaMenuElement #deleteMap")
  private WebElement deleteMapButton;
  @FindBy(css = ".wikia-interactive-maps-page-header .WikiaMenuElement #undeleteMap")
  private WebElement restoreMapButton;
  @FindBy(css = ".poi-article-link")
  private WebElement poiArticleLink;
  @FindBy(css = ".wikia-interactive-map h2")
  private WebElement poiCategorySection;
  @FindBy(css = ".wikia-interactive-map dt")
  private WebElement poiNameSection;
  @FindBy(css = ".wikia-interactive-map dd")
  private WebElement poiDescriptionSection;
  @FindBy(css = ".wikia-interactive-map-wrapper")
  private WebElement mapWrapper;

  public void clickEmbedMapCodeButton() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(embedMapCodeButton);
    embedMapCodeButton.click();
    PageObjectLogging.log("clickEmbedMapCodeButton", "Embed map code button clicked", true, driver);
    driver.switchTo().defaultContent();
  }

  public void clickEmbedMapCodeButton(embedMapDialogButtons button) {
    switch (button) {
      case SMALL:
        embedMapCodeSmallButton.click();
        break;
      case MEDIUM:
        embedMapCodeMediumButton.click();
        break;
      case LARGE:
        embedMapCodeLargeButton.click();
        break;
      default:
        throw new NoSuchElementException("Non-existing dialog button selected");
    }
  }

  public CreatePinTypesComponentObject clickEditPinTypesButton() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(filterBox);
    wait.forElementVisible(editPinTypesButton);
    editPinTypesButton.click();
    PageObjectLogging.log("clickEditPinTypesButton", "Edit Pin Types button were clicked", true,
        driver);
    driver.switchTo().defaultContent();
    return new CreatePinTypesComponentObject(driver);
  }

  public void clickZoomInButton() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementClickable(zoomInButton);
    zoomInButton.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickZoomInButton", "Map zoom in was clicked", true, driver);
  }

  public void clickZoomOutButton() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementClickable(zoomOutButton);
    zoomOutButton.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickZoomOutButton", "Map zoom out was clicked", true, driver);
  }

  public void clickOnPin(Integer pinListPosition, boolean... noFrame) {
    if (noFrame.length == 0) {
      wait.forElementVisible(mapFrame);
      driver.switchTo().frame(mapFrame);
    }
    wait.forElementVisible(pinCollection.get(pinListPosition));
    jsActions.scrollToElement(pinCollection.get(pinListPosition));
    Actions actions = new Actions(driver);
    actions.moveToElement(pinCollection.get(pinListPosition));
    actions.click().perform();
    PageObjectLogging.log("clickOnPin", "Pin was clicked", true, driver);
  }

  public void clickOnSingleEnabledCategory() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX).click();
    PageObjectLogging.log("clickOnSingleEnabledCategory", "Single enabled category was clicked",
        true);
    driver.switchTo().defaultContent();
  }

  public void clickOnSingleDisabledCategory() {
    driver.switchTo().defaultContent();
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(disabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    disabledPinTypesCollection.get(0).click();
    PageObjectLogging.log("clickOnSingleDisabledCategory", "Single disabled category was clicked",
        true);
    driver.switchTo().defaultContent();
  }

  public void clickOnAllPinTypes() {
    driver.switchTo().defaultContent();
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(allPinTypes);
    allPinTypes.click();
    PageObjectLogging.log("clickOnAllCategories", "All categories were clicked", true);
    driver.switchTo().defaultContent();
  }

  public AddPinComponentObject clickOnEditPin() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(pinEditLink);
    pinEditLink.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickOnEditPin", "Pin edit link was clicked", true);
    return new AddPinComponentObject(driver);
  }

  public void clickOnFilterBoxTitle() {
    wait.forElementVisible(mapFrame);
    jsActions.scrollToElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(filterBoxTitle);
    Actions actions = new Actions(driver);
    actions.moveToElement(filterBoxTitle).click().perform();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickOnFilterBoxTitle", "Filter box title was clicked", true);
  }

  public void clickOpenPinTitle(boolean... noFrame) {
    if (noFrame.length == 0) {
      wait.forElementVisible(mapFrame);
      driver.switchTo().frame(mapFrame);
    }
    poiArticleLink.click();
    driver.switchTo().defaultContent();
  }

  public String getEmbedMapWikiCode() {
    return "<imap map-id='" + mapFrame.getAttribute("data-mapid") + "'/>";
  }

  public String getEmbedMapID() {
    return mapFrame.getAttribute("data-mapid");
  }

  public String getOpenPinName() {
    wait.forElementVisible(pinTitle);
    return pinTitle.getText();
  }

  public String getEmbedMapCode() {
    wait.forElementVisible(embedCode);
    return embedCode.getText();
  }

  public AddPinComponentObject placePinInMap() {
    wait.forElementVisible(mapFrame);
    jsActions.scrollElementIntoViewPort(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(addPinButton);
    addPinButton.click();
    Actions actions = new Actions(driver);
    actions.moveToElement(mapImagesCollection.get(0));
    actions.click().perform();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("placePinInMap", "Pin was placed in map", true);
    return new AddPinComponentObject(driver);
  }

  public void verifyMapOpened() {
    wait.forElementVisible(mapFrame);
    jsActions.scrollToElement(mapWrapper);
    jsActions.execute("$('.wikia-bar .arrow').click()");
    driver.switchTo().frame(mapFrame);
    driver.switchTo().defaultContent();
    PageObjectLogging.log("verifyMapOpened", "Map was opened", true);
  }

  public void verifyMapOpenedForDeleteMapTests() {
    wait.forElementVisible(mapFrame);
    PageObjectLogging.log("verifyMapOpenedForDeleteMapTests", "Map was opened", true);
  }

  public void verifyCreatedMapTitle(String mapTitle) {
    wait.forElementVisible(createdMapTitle);
    Assertion.assertEquals(createdMapTitle.getText(), mapTitle);
  }

  public void verifyCreatedPinTypesForNewMap() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(filterBox);
    Assertion.assertEquals(1, enabledPinTypesCollection.size());
    driver.switchTo().defaultContent();
  }

  public void verifyEmbedMapDialog() {
    wait.forElementVisible(embedMapDialog);
    isElementOnPage(embedMapCodeSmall);
  }

  public void verifyEmbedMapCode(embedMapDialogButtons button) {
    switch (button) {
      case SMALL:
        wait.forElementVisible(embedMapCodeSmall);
        break;
      case MEDIUM:
        wait.forElementVisible(embedMapCodeMedium);
        break;
      case LARGE:
        wait.forElementVisible(embedMapCodeLarge);
        break;
      default:
        throw new NoSuchElementException("Non-existing dialog button selected");
    }
    PageObjectLogging.log("verifyEmbedMapCode", button + "embed map code was correctly displayed",
        true);
  }

  public void verifyPopUpVisible() {
    wait.forElementVisible(pinDescription);
    Assertion.assertEquals(isElementOnPage(pinDescription), true);
    driver.switchTo().defaultContent();
  }

  public void verifyAllPinTypesIsCheck() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(allPinTypes);
    wait.forElementVisible(enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    if (allPinTypes.getAttribute("class").contains("enabled")) {
      PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were checked", true);
    } else {
      PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were not checked", false);
    }
    driver.switchTo().defaultContent();
  }

  public void verifyAllPinTypesIsUncheck() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(allPinTypes);
    if (!allPinTypes.getAttribute("class").contains("enabled")) {
      PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were unchecked", true);
    } else {
      PageObjectLogging.log("verifyAllPointTypesIsUnCheck", "All pin types were checked", false);
    }
    driver.switchTo().defaultContent();
  }

  public void verifyPinTypesAreUncheck() {
    Assertion.assertEquals(0, enabledPinTypesCollection.size(), "Pin types were unchecked");
  }

  public void verifyPinNotExists(String pinTitle) {
    int pinSize = pinCollection.size() - 1;
    while (pinSize >= 0) {
      if (pinCollection.get(pinSize).getText().contains(pinTitle)) {
        break;
      }
      pinSize--;
    }
    Assertion.assertEquals(pinSize, -1, "Pin was deleted correctly");
  }

  public void verifyPinTypesAreCheck() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    Assertion.assertEquals(disabledPinTypesCollection.size(), 0);
    driver.switchTo().defaultContent();
  }

  public void verifyPinDataWasChanged(String pinName, String pinDesc) {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(popUpContent);
    jsActions.scrollToElement(popUpContent);
    wait.forElementVisible(pinTitle);
    wait.forElementVisible(pinDescription);
    Assertion.assertNotEquals(pinTitle.getText(), pinName);
    Assertion.assertNotEquals(pinDescription.getText(), pinDesc);
  }

  public void verifyControlButtonsAreVisible() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    wait.forElementVisible(embedMapCodeButton);
    Assertion.assertEquals(isElementOnPage(embedMapCodeButton), true);
    Assertion.assertEquals(isElementOnPage(zoomInButton), true);
    Assertion.assertEquals(isElementOnPage(zoomOutButton), true);
    PageObjectLogging.log("verifyControlButtonsAreVisible",
        "embedMap, zoom in/out buttons were visible", true);
    driver.switchTo().defaultContent();
  }

  public void verifyPinPopupImageIsVisible() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(isElementOnPage(pinPopupImage), true);
  }

  public void verifyPinTypeExists(String pinTypeName) {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    while (createdPinNames.size() - 1 != 0) {
      if (createdPinNames.get(createdPinNames.size() - 1).getText().contains(pinTypeName)) {
        Assertion.assertEquals(createdPinNames.get(createdPinNames.size() - 1).getText(),
            pinTypeName);
      } else {
        PageObjectLogging.log("verifyPinTypeExist",
            "Pin type with name " + pinTypeName + " does not exist", true);

      }
      break;
    }
    driver.switchTo().defaultContent();
  }

  public void verifyPinTitleLink() {
    wait.forElementVisible(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(isElementOnPage(poiArticleLink), true);
    driver.switchTo().defaultContent();
  }

  public WikiArticleEditMode openEmbedMapPageEdit(String wikiURL) {
    ArticlePageObject article = new ArticlePageObject();
    article.navigateToArticleEditPage(wikiURL, URLsContent.EMBEDED_MAP_ARTICLE);
    return new WikiArticleEditMode(driver);
  }

  public DeleteAMapComponentObject deleteMap() {
    wait.forElementVisible(actionDropDown);
    actionDropDown.click();
    deleteMapButton.click();
    return new DeleteAMapComponentObject(driver);
  }

  public DeleteAMapComponentObject restoreMap() {
    wait.forElementVisible(actionDropDown);
    actionDropDown.click();
    restoreMapButton.click();
    return new DeleteAMapComponentObject(driver);
  }

  public void verifyEscapedFragmentMetaTag() {
    wait.forElementVisible(createdMapTitle);
    wait.forElementPresent(escapedFragmentMetaDataTag);
    PageObjectLogging.log("verifyEscapedFragmentMetaTag", "Escaped fragment meta tag is in DOM",
        true);
  }

  public void verifyPoiCategoryTitle() {
    wait.forElementVisible(poiCategorySection);
    PageObjectLogging.log("verifyPoiCategoryTitle", "Poi category section is displayed", true);
  }

  public void verifyPoiPointTitle() {
    wait.forElementVisible(poiNameSection);
    PageObjectLogging.log("verifyPoiPointTitle", "Poi name section is displayed", true);
  }

  public void verifyPoiPointDescription() {
    wait.forElementVisible(poiDescriptionSection);
    PageObjectLogging.log("verifyPoiPointDescription", "Poi description section is displayed",
        true);
  }

  public void verifyPontoGetRequest(NetworkTrafficInterceptor networkTab) {
    if (networkTab.getEntryByUrlPart("maps.wikia-services.com") != null) {
      PageObjectLogging.log("verifyPontoGetRequest", "Ponto request came", true);
    } else {
      throw new NoSuchElementException("Request from ponto did not come");
    }
  }

  public enum embedMapDialogButtons {
    SMALL, MEDIUM, LARGE
  }
}
