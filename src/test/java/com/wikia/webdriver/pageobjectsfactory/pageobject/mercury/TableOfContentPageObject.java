package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Tomasz Napieralski
 */
public class TableOfContentPageObject extends MercuryBasePageObject {

  @FindBy(css = "nav.table-of-contents")
  private WebElement tocAll;
  @FindBy(css = "section.article-body h2")
  private List<WebElement> allH2;
  @FindBy(css = "section.article-body > *")
  private List<WebElement> articleBodyTags;
  @FindBy(css = "nav.table-of-contents a")
  private List<WebElement> listOfLinks;
  @FindBy(css = "nav.table-of-contents ol")
  private WebElement TOCMenu;
  @FindBy(css = "nav.table-of-contents button")
  private WebElement tocButton;

  public TableOfContentPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTOCDisplayed() {
    if (tocAll.isDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isH2OnPage() {
    if (allH2.size() > 0) {
      return true;
    }
    return false;
  }

  public boolean isH2AndTOC() {
    if (isH2OnPage() && isTOCDisplayed()) {
      return true;
    }
    return false;
  }

  public boolean isTOCUnderArticleName() {
    int h1Pos = 0;
    boolean h1Found = false;
    int index = 0;
    if (isH2AndTOC()) {
      for (WebElement element : articleBodyTags) {
        if (element.getTagName().equals("h1") && !h1Found) {
          h1Pos = index++;
          h1Found = true;
          continue;
        }
        if (element.getTagName().equals("nav")
            && element.getAttribute("class").contains("table-of-content")
            && index > 0 && h1Found && h1Pos + 1 == index) {
          return true;
        }
        ++index;
      }
    }
    return false;
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
    if (h2Pos == windowYPos) {
      return true;
    }
    return false;
  }

  public boolean isH2PaddingTopMoreThan(int index, int value) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String h2PaddingString =
        js.executeScript("return $('h2').eq(" + index + ").css('padding-top')").toString();
    h2PaddingString = h2PaddingString.substring(0, h2PaddingString.length() - 2);
    int h2Padding = Integer.parseInt(h2PaddingString);
    if (h2Padding >= value) {
      return true;
    }
    return false;
  }

  public boolean isTOCMenuVisible() {
    if (TOCMenu.getCssValue("display").equals("none")) {
      return false;
    }
    return true;
  }
}
