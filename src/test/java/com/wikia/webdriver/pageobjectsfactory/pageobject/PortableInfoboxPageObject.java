package com.wikia.webdriver.pageobjectsfactory.pageobject;

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

  @FindBy(css = ".portable-infobox-image-wrapper")
  private WebElement pInfoImage;
  @FindBy(css = ".portable-infobox-title")
  private WebElement pInfoTitle;
  @FindBy(css = ".portable-infobox-header")
  private List<WebElement> pInfoTitleH3;
  @FindBy(css = ".portable-infobox .external")
  private WebElement pInfoExternalLink;
  @FindBy(css = ".portable-infobox-navigation a[href*='redlink']")
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
  @FindBy(css = ".portable-infobox-item-value ul li")
  private List <WebElement> unorderedElementList;
  @FindBy(css = ".portable-infobox-item-value ol li")
  private List <WebElement> orderedElementList;
  @FindBy(css = ".reference")
  private WebElement referenceElements;
  @FindBy(css = ".portable-infobox-item-label")
  private WebElement h3Elements;
  @FindBy(css = "#CreatePageModalDialog")
  private WebElement createArticleModal;
  @FindBy(css = "button[data-event=create]")
  private WebElement addAPageButton;
  @FindBy(css = ".portable-infobox-item-value .newcategory")
  private WebElement categoryLink;
  @FindBy(css = ".portable-infobox-item-label")
  private WebElement itemLabel;
  @FindBy(css = ".portable-infobox-item-value")
  private WebElement itemValue;
  @FindBy(css = "h3.portable-infobox-item-label.portable-infobox-secondary-font")
  private WebElement horizontalItemLabel;
  @FindBy(css = "div.portable-infobox-item-value")
  private WebElement horizontalItemValue;


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

  public List<WebElement> getHeaderElements() {
    return pInfoTitleH3;
  }

  public void verifyImagePresence() {
    waitForElementByElement(pInfoImage);
    Assertion.assertEquals(checkIfElementOnPage(pInfoImage), true);
  }

  public void verifyTabberPresence() {
    waitForElementByElement(tabber);
    Assertion.assertEquals(checkIfElementOnPage(tabber), true);
  }

  public void verifyTabberImagePresence() {
    waitForElementByElement(tabberImage);
    Assertion.assertEquals(checkIfElementOnPage(tabberImage), true);
  }

  public void verifyInfoboxTitlePresence() {
    waitForElementByElement(pInfoTitle);
    Assertion.assertEquals(checkIfElementOnPage(pInfoTitle), true);
  }

  public void verifyLightboxPresence() {
    waitForElementByElement(lightbox);
    Assertion.assertEquals(checkIfElementOnPage(lightbox), true);
  }

  public String getExternalLinkRedirectTitle() {
    waitForElementByElement(pInfoExternalLink);
    return pInfoExternalLink.getAttribute("href");
  }

  public String getInternalLinkRedirectTitle(int index) {
    waitForElementByElement(pInfoInternalLinks.get(index));
    return pInfoInternalLinks.get(index).getAttribute("href");
  }

  public WebElement getItemLabel() {
    return itemLabel;
  }

  public WebElement getItemValue() {
    return itemValue;
  }

  public WebElement getOrderedListItem(int index) {
    WebElement orderedListItem = orderedElementList.get(index);
    return orderedListItem;
  }

  public WebElement getUnorderedListElement(int index) {
    WebElement unorderedListItem = unorderedElementList.get(index);
    return unorderedListItem;
  }

  public WebElement getHorizontalItemLabel() {
    return horizontalItemLabel;
  }

  public WebElement getHorizontalItemValue() {
    return horizontalItemValue;
  }

  public void clickExternalLink() {
    waitForElementByElement(pInfoExternalLink);
    pInfoExternalLink.click();
  }

  public void clickInternalLink(int index) {
    scrollToElement(pInfoInternalLinks.get(index));
    waitForElementByElement(pInfoInternalLinks.get(index));
    pInfoInternalLinks.get(index).click();
  }

  public void clickImage() {
    waitForElementByElement(pInfoImage);
    pInfoImage.click();
  }

  public void clickCategoryLink() {
    waitForElementByElement(categoryLink);
    scrollAndClick(categoryLink);
  }

  public void compareURLAndExternalLink(String externalLinkName, String externalNavigatedURL) {
    Assertion.assertEquals(externalLinkName, externalNavigatedURL);
  }

  public void compareURLAndInternalLink(String internalLinkName, String internalNavigatedURL) {
    Assertion.assertEquals(internalLinkName, internalNavigatedURL);
  }

  public String getimgSrc() {
    waitForElementByElement(pInfoImage);
    return pInfoImage.getAttribute("src");
  }


  public void verifyChangedBackground(String oldBackgroundValue, String newBackgroundValue) {
    Assertion.assertEquals(oldBackgroundValue, newBackgroundValue);
  }

  public void verifyQuotationMarksPresence() {
    waitForElementByElement(h3Elements);
    String h3ElementsString = h3Elements.getText();
    Assertion.assertStringContains("\"URL\"", h3ElementsString);
  }

  public void verifyReferencesPresence() {
    waitForElementByElement(referenceElements);
  }

  public CreateArticleModalComponentObject clickRedLink(int i) {
    scrollToElement(pInfoRedlLink.get(i));
    waitForElementVisibleByElement(pInfoRedlLink.get(i));
    WebElement redLinkChose = pInfoRedlLink.get(i);
    redLinkChose.click();
    return new CreateArticleModalComponentObject(driver);
  }

  public void verifyCreateNewArticleModal() {
    waitForElementByElement(addAPageButton);
  }

  public void verifyCategoryInArticlePage(String catName) {
    // Same as previous case, waiting on Ludwik
  }

  public void compareFontSizes(WebElement firstElement, WebElement secondElement) {
    String firstFontSize = firstElement.getCssValue("font-size");
    String secondFontSize = secondElement.getCssValue("font-size");
    Assertion.assertEquals(firstFontSize, secondFontSize);
  }
}
