package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.google.sitebricks.client.Web;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Rodriuki on 11/06/15.
 * Created by nikodamn on 20/07/15
 */
public class PortableInfoboxPageObject extends WikiBasePageObject {

  @FindBy(css = ".pi-image")
  private WebElement pInfoImage;
  @FindBy(css = ".pi-title")
  private WebElement pInfoTitle;
  @FindBy(css = ".pi-header")
  private List<WebElement> pInfoTitleH3;
  @FindBy(css = ".pi-item .external")
  private WebElement pInfoExternalLink;
  @FindBy(css = ".pi-navigation a[href*='redlink']")
  private List<WebElement> pInfoRedlLink;
  @FindBy(css = "b")
  private List<WebElement> boldElements;
  @FindBy(css = "i")
  private List<WebElement> italicElements;
  @FindBy(css = ".portable-infobox a[href*='/wiki/']")
  private List <WebElement> pInfoInternalLinks;
  @FindBy(css = ".WikiaLightbox")
  private WebElement lightbox;
  @FindBy(css = ".portable-infobox")
  private WebElement infoboxLayout;
  @FindBy(css = ".tabberlive")
  private WebElement tabber;
  @FindBy(css = ".tabbertab .image")
  private WebElement tabberImage;
  @FindBy(css = ".pi-data-value ul li")
  private List <WebElement> unorderedElementList;
  @FindBy(css = ".pi-data-value ol li")
  private List <WebElement> orderedElementList;
  @FindBy(css = ".reference")
  private WebElement referenceElements;
  @FindBy(css = ".pi-data-label")
  private WebElement h3Elements;
  @FindBy(css = "button[data-event=create]")
  private WebElement addAPageButton;
  @FindBy(css = ".pi-data-value .newcategory")
  private WebElement categoryLink;
  @FindBy(css = ".pi-data-label")
  private WebElement itemLabel;
  @FindBy(css = ".pi-data-value")
  private WebElement itemValue;
  @FindBy(css = "h3.pi-data-label.pi-secondary-font")
  private WebElement horizontalItemLabel;
  @FindBy(css = "div.pi-data-value")
  private WebElement horizontalItemValue;
  @FindBy(css = ".pi-navigation")
  private List<WebElement> navigationElements;
  @FindBy(css = ".pi-header")
  private List<WebElement> groupHeadersWrappers;
  @FindBy(css = ".pi-image")
  private WebElement imageWrapper;
  @FindBy(css = ".pi-title")
  private WebElement titleWrapper;

  public PortableInfoboxPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getBackgroundColor() {
    return infoboxLayout.getCssValue("background-color");
  }

  public List<WebElement> getBoldElements(){
    return boldElements;
  }

  public List<WebElement> getItalicElements() {
    return italicElements;
  }

  public List<WebElement> getHeaderElements() { return pInfoTitleH3; }

  public void verifyImagePresence() {
    wait.forElementVisible(pInfoImage);
    Assertion.assertEquals(isElementOnPage(pInfoImage), true);
  }

  public void verifyTabberPresence() {
    wait.forElementVisible(tabber);
    Assertion.assertEquals(isElementOnPage(tabber), true);
  }

  public void verifyTabberImagePresence() {
    wait.forElementVisible(tabberImage);
    Assertion.assertEquals(isElementOnPage(tabberImage), true);
  }

  public void verifyInfoboxTitlePresence() {
    wait.forElementVisible(pInfoTitle);
    Assertion.assertEquals(isElementOnPage(pInfoTitle), true);
  }

  public void verifyLightboxPresence() {
    wait.forElementVisible(lightbox);
    Assertion.assertEquals(isElementOnPage(lightbox), true);
  }

  public String getExternalLinkRedirectTitle() {
    wait.forElementVisible(pInfoExternalLink);
    return pInfoExternalLink.getAttribute("href");
  }

  public String getInternalLinkRedirectTitle(int index) {
    wait.forElementVisible(pInfoInternalLinks.get(index));
    return pInfoInternalLinks.get(index).getAttribute("href");
  }

  public WebElement getItemLabel() { return itemLabel; }

  public WebElement getItemValue() { return itemValue; }

  public WebElement getOrderedListItem(int index) {
    return orderedElementList.get(index);
  }

  public WebElement getUnorderedListElement(int index) {
    return unorderedElementList.get(index);
  }

  public WebElement getHorizontalItemLabel() {
    return horizontalItemLabel;
  }

  public WebElement getHorizontalItemValue() {
    return horizontalItemValue;
  }

  public WebElement getNavigationElements(int index) { return navigationElements.get(index); }

  public WebElement getGroupHeader(int index) { return groupHeadersWrappers.get(index); }

  public WebElement getImageWrapper() { return imageWrapper; }

  public WebElement getTitleWrapper() { return titleWrapper; }

  public void clickExternalLink() {
    wait.forElementVisible(pInfoExternalLink);
    pInfoExternalLink.click();
  }

  public void clickInternalLink(int index) {
    scrollToElement(pInfoInternalLinks.get(index));
    wait.forElementVisible(pInfoInternalLinks.get(index));
    pInfoInternalLinks.get(index).click();
  }

  public void clickImage() {
    wait.forElementVisible(pInfoImage);
    pInfoImage.click();
  }

  public void clickCategoryLink() {
    wait.forElementVisible(categoryLink);
    scrollAndClick(categoryLink);
  }

  public void compareURLAndExternalLink(String externalLinkName, String externalNavigatedURL) {
    Assertion.assertEquals(externalLinkName, externalNavigatedURL);
  }

  public void compareURLAndInternalLink(String internalLinkName, String internalNavigatedURL) {
    Assertion.assertEquals(internalLinkName, internalNavigatedURL);
  }

  public String getimgSrc() {
    wait.forElementVisible(pInfoImage);
    return pInfoImage.getAttribute("src");
  }


  public void verifyChangedBackground(String oldBackgroundValue, String newBackgroundValue) {
    Assertion.assertEquals(oldBackgroundValue, newBackgroundValue);
  }

  public void verifyQuotationMarksPresence() {
    wait.forElementVisible(h3Elements);
    String h3ElementsString = h3Elements.getText();
    Assertion.assertStringContains("\"URL\"", h3ElementsString);
  }

  public void verifyReferencesPresence() {
    wait.forElementVisible(referenceElements);
  }

  public CreateArticleModalComponentObject clickRedLink(int i) {
    scrollToElement(pInfoRedlLink.get(i));
    wait.forElementVisible(pInfoRedlLink.get(i));
    WebElement redLinkChose = pInfoRedlLink.get(i);
    redLinkChose.click();
    return new CreateArticleModalComponentObject(driver);
  }

  public void verifyCreateNewArticleModal() {
    wait.forElementVisible(addAPageButton);
  }

  public void verifyCategoryInArticlePage(String catName) {
    // Same as previous case, waiting on Ludwik
  }

  public void verifyPadding(WebElement element) {
    String leftPadding = element.getCssValue("padding-left");
    String rightPadding = element.getCssValue("padding-right");
    Assertion.assertEquals(leftPadding, rightPadding);
  }

  public void verifyDivsNotAppearing(WebElement element) {
    String tagName = element.getTagName();
    Assertion.assertNotEquals(tagName, "div");
  }

  public void compareFontSizes(WebElement firstElement, WebElement secondElement) {
    String firstFontSize = firstElement.getCssValue("font-size");
    String secondFontSize = secondElement.getCssValue("font-size");
    Assertion.assertEquals(firstFontSize, secondFontSize);
  }

  public String getInfoboxContent() {
    return infoboxLayout.getText();
  }

  public void verifyEmptyTags(String infoboxContent) {
    Assertion.assertStringContains(infoboxContent, "Default");
  }
}
