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
  @FindBy(css = ".portable-infobox-navigation a[href*='/wiki']")
  private WebElement pInfoExternalLink;
  @FindBy(css = ".portable-infobox-navigation a[href*='redlink']")
  private WebElement pInfoRedlLink;
  @FindBy(css = "b")
  private List<WebElement> boldElements;
  @FindBy(css = "i")
  private List<WebElement> italicElements;
  @FindBy(css = ".portable-infobox-navigation a[href*='cleanup4']")
  private WebElement pInfoInternalLink;
  @FindBy(css = ".WikiaLightbox")
  private WebElement lightbox;
  @FindBy(css = ".portable-infobox-layout-default")
  private WebElement infoboxLayout;
  @FindBy(css = ".tabberlive")
  private WebElement tabber;
  @FindBy(css = ".tabbertab > .image")
  private WebElement tabberImage;
  @FindBy(css = ".portable-infobox-item-value ul li")
  private WebElement unorderedListElement;
  @FindBy(css = ".portable-infobox-item-value ol li")
  private WebElement orderedListElement;
  @FindBy(css = ".reference")
  private WebElement referenceElements;
  @FindBy(css = ".portable-infobox-item-label")
  private WebElement h3Elements;
  @FindBy(css = "#CreatePageModalDialog")
  private WebElement createArticleModal;
  @FindBy(css = "button[data-event=create]")
  private WebElement addAPageButton;

  public PortableInfoboxPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getBackgroundColor() {
    return infoboxLayout.getAttribute("background-color");
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

  public void verifyOrderedListPresence(){
    waitForElementByElement(orderedListElement);
    Assertion.assertEquals(checkIfElementOnPage(orderedListElement), true);
  }

  public void verifyUnorderedListPresence(){
    waitForElementByElement(unorderedListElement);
    Assertion.assertEquals(checkIfElementOnPage(unorderedListElement), true);
  }


  public String getExternalLinkRedirectTitle() {
    waitForElementByElement(pInfoRedlLink);
    return pInfoRedlLink.getAttribute("href");
  }

  public String getInternalLinkRedirectTitle() {
    waitForElementByElement(pInfoInternalLink);
    return pInfoInternalLink.getAttribute("href");
  }

  public void clickExternalLink() {
    waitForElementByElement(pInfoExternalLink);
    pInfoExternalLink.click();
  }

  public void clickInternalLink() {
    waitForElementByElement(pInfoInternalLink);
    pInfoInternalLink.click();
  }

  public void clickImage() {
    waitForElementByElement(pInfoImage);
    pInfoImage.click();
  }

  public void compareURLAndExternalLink(String externalLinkName, String externalNavigatedURL) {
    waitForElementByElement(pInfoImage);
    Assertion.assertEquals(externalLinkName, externalNavigatedURL);
  }

  public void compareURLAndInternalLink(String internalLinkName, String internalNavigatedURL) {
    waitForElementByElement(pInfoImage);
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

  public CreateArticleModalComponentObject clickRedLink() {
    waitForElementByElement(pInfoRedlLink);
    pInfoRedlLink.click();
    return new CreateArticleModalComponentObject(driver);
  }

  public void verifyCreateNewArticleModal() {
    waitForElementByElement(addAPageButton);
  }

  public void verifyCategoryInArticlePage(String catName) {
    //
    //
  }
}
