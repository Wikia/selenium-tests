package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by Rodriuki on 11/06/15.
 */
public class PortableInfoboxPageObject extends WikiBasePageObject {

  @FindBy(css = ".portable-infobox-image-wrapper")
  private WebElement pInfoImage;
  @FindBy(css = ".portable-infobox-title")
  private WebElement pInfoTitle;
  @FindBy(css = ".portable-infobox-header-font")
  private WebElement pInfoTitleH3;
  @FindBy(css = ".portable-infobox-item-value a[href*='org']")
  private WebElement pInfoExternalLink;
  @FindBy(css = "b")
  private List<WebElement> boldElements;
  @FindBy(css = "i")
  private List<WebElement> italicElements;
  @FindBy(css = ".portable-infobox-item-value a[href*='cleanup4']")
  private WebElement pInfoInternalLink;
  @FindBy(css = ".WikiaLightbox")
  private WebElement lightbox;
  @FindBy(css = ".portable-infobox-layout-default")
  private WebElement infoboxLayout;
  @FindBy(css = ".tabberlive")
  private WebElement tabber;
  @FindBy(css = ".tabbertab > .image")
  private WebElement tabberImage;


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

  public void verifyTabberImagePresece() {
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


  public void verifyChangedBackground(String oldBackgroundValue, String newBackgroundValue)
  {
    Assertion.assertEquals(oldBackgroundValue, newBackgroundValue);
  }

}
