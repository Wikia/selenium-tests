package com.wikia.webdriver.pageobjectsfactory.pageobject.special.interactivemaps;

import com.wikia.webdriver.common.contentpatterns.InteractiveMapsContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.AddPinComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.CreatePinTypesComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.DeleteAMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMapDialog;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Łukasz Jędrzejczak
 * @author Łukasz Nowak (Dyktus)
 */
public class InteractiveMapPageObject extends BasePageObject {

  @FindBy(css = "#map")
  private WebElement map;
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
  @FindBy(css = ".leaflet-map-pane.leaflet-zoom-anim")
  private WebElement zoomAnim;
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

  private By escapedFragmentMetaDataTag = By.cssSelector("meta[name='fragment']");

  public InteractiveMapPageObject(WebDriver driver) {
    super(driver);
  }

  public enum embedMapDialogButtons {
    SMALL, MEDIUM, LARGE;
  }

  public void clickEmbedMapCodeButton() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(embedMapCodeButton);
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
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(filterBox);
    waitForElementVisibleByElement(editPinTypesButton);
    editPinTypesButton.click();
    PageObjectLogging.log("clickEditPinTypesButton",
                          "Edit Pin Types button were clicked", true, driver);
    driver.switchTo().defaultContent();
    return new CreatePinTypesComponentObject(driver);
  }

  public void clickZoomInButton() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementClickableByElement(zoomInButton);
    zoomInButton.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickZoomInButton", "Map zoom in was clicked", true, driver);
  }

  public void clickZoomOutButton() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementClickableByElement(zoomOutButton);
    zoomOutButton.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickZoomOutButton", "Map zoom out was clicked", true, driver);
  }

  public void clickOnPin(Integer pinListPosition) {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(pinCollection.get(pinListPosition));
    scrollToElement(pinCollection.get(pinListPosition));
    Actions actions = new Actions(driver);
    actions.moveToElement(pinCollection.get(pinListPosition));
    actions.click().perform();
    PageObjectLogging.log("clickOnPin", "Pin was clicked", true, driver);
  }

  public void clickOnSingleEnabledCategory() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(
        enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX).click();
    PageObjectLogging.log("clickOnSingleEnabledCategory",
                          "Single enabled category was clicked", true);
    driver.switchTo().defaultContent();
  }

  public void clickOnSingleDisabledCategory() {
    driver.switchTo().defaultContent();
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementByElement(disabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    disabledPinTypesCollection.get(0).click();
    PageObjectLogging.log("clickOnSingleDisabledCategory",
                          "Single disabled category was clicked", true);
    driver.switchTo().defaultContent();
  }

  public void clickOnAllPinTypes() {
    driver.switchTo().defaultContent();
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(allPinTypes);
    allPinTypes.click();
    PageObjectLogging.log("clickOnAllCategories", "All categories were clicked", true);
    driver.switchTo().defaultContent();
  }

  public AddPinComponentObject clickOnEditPin() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementByElement(pinEditLink);
    pinEditLink.click();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickOnEditPin", "Pin edit link was clicked", true);
    return new AddPinComponentObject(driver);
  }

  public void clickOnFilterBoxTitle() {
    waitForElementVisibleByElement(mapFrame);
    scrollToElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementByElement(filterBoxTitle);
    Actions actions = new Actions(driver);
    actions.moveToElement(filterBoxTitle).click().perform();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("clickOnFilterBoxTitle", "Filter box title was clicked", true);
  }

  public void clickOpenPinTitle() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
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
    waitForElementByElement(pinTitle);
    return pinTitle.getText();
  }

  public String getEmbedMapCode() {
    waitForElementByElement(embedCode);
    return embedCode.getText();
  }

  public AddPinComponentObject placePinInMap() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(addPinButton);
    addPinButton.click();
    Actions actions = new Actions(driver);
    actions.moveToElement(mapImagesCollection.get(0));
    actions.click().perform();
    driver.switchTo().defaultContent();
    PageObjectLogging.log("placePinInMap", "Pin was placed in map", true);
    return new AddPinComponentObject(driver);
  }

  public void verifyMapOpened() {
    waitForElementVisibleByElement(mapFrame);
    scrollToElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    driver.switchTo().defaultContent();
    PageObjectLogging.log("verifyMapOpened", "Map was opened", true);
  }

  public void verifyMapOpenedForDeleteMapTests() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    driver.switchTo().defaultContent();
    PageObjectLogging.log("verifyMapOpenedForDeleteMapTests", "Map was opened", true);
  }

  public void verifyCreatedMapTitle(String mapTitle) {
    waitForElementByElement(createdMapTitle);
    Assertion.assertEquals(mapTitle, createdMapTitle.getText());
  }

  public void verifyCreatedPinTypesForNewMap() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(filterBox);
    Assertion.assertEquals(1, enabledPinTypesCollection.size());
    driver.switchTo().defaultContent();
  }

  public void verifyEmbedMapDialog() {
    waitForElementByElement(embedMapDialog);
    checkIfElementOnPage(embedMapCodeSmall);
  }

  public void verifyEmbedMapCode(embedMapDialogButtons button) {
    switch (button) {
      case SMALL:
        waitForElementVisibleByElement(embedMapCodeSmall);
        break;
      case MEDIUM:
        waitForElementVisibleByElement(embedMapCodeMedium);
        break;
      case LARGE:
        waitForElementVisibleByElement(embedMapCodeLarge);
        break;
      default:
        throw new NoSuchElementException("Non-existing dialog button selected");
    }
    PageObjectLogging.log("verifyEmbedMapCode",
                          button + "embed map code was correctly displayed", true);
  }

  public void verifyPopUpVisible() {
    waitForElementVisibleByElement(pinDescription);
    Assertion.assertEquals(checkIfElementOnPage(pinDescription), true);
    driver.switchTo().defaultContent();
  }

  public void verifyZoomMap() {
    waitForElementByElement(zoomAnim);
    PageObjectLogging.log("verifyZoomMap", "Map was zoomed", true, driver);
  }

  public void verifyAllPinTypesIsCheck() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementByElement(allPinTypes);
    waitForElementByElement(enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    if (allPinTypes.getAttribute("class").contains("enabled")) {
      PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were checked", true);
    } else {
      PageObjectLogging.log("verifyAllPointTypesIsCheck", "All pin types were not checked", false);
    }
    driver.switchTo().defaultContent();
  }

  public void verifyAllPinTypesIsUncheck() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(allPinTypes);
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
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(
        enabledPinTypesCollection.get(InteractiveMapsContent.PIN_TYPE_INDEX));
    Assertion.assertEquals(disabledPinTypesCollection.size(), 0);
    driver.switchTo().defaultContent();
  }

  public void verifyPinDataWasChanged(String pinName, String pinDesc) {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(popUpContent);
    scrollToElement(popUpContent);
    waitForElementVisibleByElement(pinTitle);
    waitForElementVisibleByElement(pinDescription);
    Assertion.assertNotEquals(pinName, pinTitle.getText());
    Assertion.assertNotEquals(pinDesc, pinDescription.getText());
  }

  public void verifyControlButtonsAreVisible() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    waitForElementVisibleByElement(embedMapCodeButton);
    Assertion.assertEquals(checkIfElementOnPage(embedMapCodeButton), true);
    Assertion.assertEquals(checkIfElementOnPage(zoomInButton), true);
    Assertion.assertEquals(checkIfElementOnPage(zoomOutButton), true);
    PageObjectLogging.log("verifyControlButtonsAreVisible",
                          "embedMap, zoom in/out buttons were visible", true);
    driver.switchTo().defaultContent();
  }

  public void verifyPinPopupImageIsVisible() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), true);
  }

  public void verifyPinPopupImageNotExist() {
    waitForElementByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(checkIfElementOnPage(pinPopupImage), false);
  }

  public void verifyPinPopUp() {
    Assertion.assertEquals(checkIfElementOnPage(pinDescription), true);
    Assertion.assertEquals(checkIfElementOnPage(pinEditLink), true);
  }

  public void verifyMapWasNotLoaded() {
    Assertion.assertEquals(checkIfElementOnPage(map), false);
  }

  public void verifyPinTypeExists(String pinTypeName) {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    while (createdPinNames.size() - 1 != 0) {
      if (createdPinNames
          .get(createdPinNames.size() - 1)
          .getText()
          .contains(pinTypeName)
          ) {
        Assertion.assertEquals(
            pinTypeName,
            createdPinNames.get(createdPinNames.size() - 1).getText());
      } else {
        PageObjectLogging.log(
            "verifyPinTypeExist",
            "Pin type with name " + pinTypeName + " does not exist",
            true
        );

      }
      break;
    }
    driver.switchTo().defaultContent();
  }

  public void verifyPinTitleLink() {
    waitForElementVisibleByElement(mapFrame);
    driver.switchTo().frame(mapFrame);
    Assertion.assertEquals(checkIfElementOnPage(poiArticleLink), true);
    driver.switchTo().defaultContent();
  }

  public WikiArticleEditMode openEmbedMapPageEdit(String wikiURL) {
    getUrl(wikiURL + URLsContent.EMBEDED_MAP_EDITPAGE);
    return new WikiArticleEditMode(driver);
  }

  public VisualEditorAddMapDialog switchBackToVETab() {
    switchToBrowserTab(0);
    return new VisualEditorAddMapDialog(driver);
  }

  public DeleteAMapComponentObject deleteMap() {
    waitForElementByElement(actionDropDown);
    actionDropDown.click();
    deleteMapButton.click();
    return new DeleteAMapComponentObject(driver);
  }

  public DeleteAMapComponentObject restoreMap() {
    waitForElementByElement(actionDropDown);
    actionDropDown.click();
    restoreMapButton.click();
    return new DeleteAMapComponentObject(driver);
  }

  public void verifyOpenMapId(String mapIdActual, String mapIdExpected) {
    Assertion.assertEquals(mapIdExpected, mapIdActual);
  }

  public void verifyEscapedFragmentMetaTag() {
    waitForElementByElement(createdMapTitle);
    waitForElementPresenceByBy(escapedFragmentMetaDataTag);
    PageObjectLogging.log("verifyEscapedFragmentMetaTag",
                          "Escaped fragment meta tag is in DOM", true);
  }

  public void verifyPoiCategoryTitle() {
    waitForElementByElement(poiCategorySection);
    PageObjectLogging.log("verifyPoiCategoryTitle", "Poi category section is displayed", true);
  }

  public void verifyPoiPointTitle() {
    waitForElementByElement(poiNameSection);
    PageObjectLogging.log("verifyPoiPointTitle", "Poi name section is displayed", true);
  }

  public void verifyPoiPointDescription() {
    waitForElementByElement(poiDescriptionSection);
    PageObjectLogging.log("verifyPoiPointDescription",
                          "Poi description section is displayed", true);
  }
}
