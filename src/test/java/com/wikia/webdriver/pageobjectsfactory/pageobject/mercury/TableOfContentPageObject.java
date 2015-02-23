package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @authors: Rodrigo Gomez, Łukasz Nowak, Tomasz Napieralski
 */
public class TableOfContentPageObject extends BasePageObject {

  @FindBy(css = "nav.table-of-contents")
  private WebElement tocAll;
  @FindBy(css = "section.article-body h2")
  private List<WebElement> allH2;
  @FindBy(css = "nav.table-of-contents a")
  private List<WebElement> listOfLinks;
  @FindBy(css = "nav.table-of-contents ol")
  private WebElement TOCMenu;
  @FindBy(css = "nav.table-of-contents button")
  private WebElement tocButton;
  @FindBy(xpath = "//section[contains(@class, 'article-body')]/h1[position() = 1]/following-sibling::*[1]")
  private WebElement tocUnderH1;

  public TableOfContentPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTOCDisplayed() {
    return tocAll.isDisplayed();
  }

  public boolean isH2OnPage() {
    return allH2.size() > 0;
  }

  public boolean isH2AndTOC() {
    return isH2OnPage() && isTOCDisplayed();
  }

  public boolean isTOCUnderArticleName() throws WebDriverException {
    waitForElementByElement(tocUnderH1);
    return tocUnderH1.isDisplayed() && tocUnderH1.getTagName().equals("nav");
  }

  public void clickOnTOC() {
    waitForElementByElement(tocButton);
    tocButton.click();
  }

  public boolean isUserMovedToRightSection(int index) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    waitForElementVisibleByElement(listOfLinks.get(index));
    listOfLinks.get(index).click();
    String h2PosString =
        js.executeScript(
            "return Math.floor($('section.article-body h2').eq(" + index + ").offset().top)")
            .toString();
    int h2Pos = Integer.parseInt(h2PosString);
    int windowYPos =
        Integer.parseInt(js.executeScript("return $(window).scrollTop()").toString());
    return h2Pos == windowYPos;
  }

  public boolean isH2PaddingTopMoreThan(int index, int value) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String h2PaddingString =
        js.executeScript("return $('h2').eq(" + index + ").css('padding-top')").toString();
    h2PaddingString = h2PaddingString.substring(0, h2PaddingString.length() - 2);
    int h2Padding = Integer.parseInt(h2PaddingString);
    return h2Padding >= value;
  }

  public boolean isTOCMenuVisible() {
    return !TOCMenu.getCssValue("display").equals("none");
  }
}
