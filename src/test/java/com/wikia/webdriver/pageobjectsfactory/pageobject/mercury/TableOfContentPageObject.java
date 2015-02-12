package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

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

  public TableOfContentPageObject(WebDriver driver) {
    super(driver);
  }

  public boolean isTOCDisplayed() {
    try {
      if (tocAll.isDisplayed()) {
        return true;
      }
    } catch (NoSuchElementException e) {
    }
    return false;
  }

  public boolean areH2onPage() {
    if (allH2.size() > 0) {
      return true;
    }
    return false;
  }

  public void verifyNoH2NoTOC() {
    Assertion.assertFalse(areH2onPage() && isTOCDisplayed(), "TOC is displayed");
  }

  public boolean verifyIfH2ThenTOC() {
    String methodName = "verifyIfH2ThenTOC";
    if (areH2onPage() && isTOCDisplayed()) {
      PageObjectLogging.log(methodName, "TOC is displayed", true);
      return true;
    } else {
      PageObjectLogging.log(methodName, "TOC isn't displayed", false);
      return false;
    }
  }

  public void verifyTOCUnderArticleName() {
    int h1Pos = 0;
    boolean h1Found = false;
    int index = 0;
    if (verifyIfH2ThenTOC()) {
      for (WebElement element : articleBodyTags) {
        if (element.getTagName().equals("h1") && !h1Found) {
          h1Pos = index++;
          h1Found = true;
          continue;
        }
        if (element.getTagName().equals("nav")
            && element.getAttribute("class").contains("table-of-content") && index > 0 && h1Found) {
          Assertion.assertTrue(h1Pos + 1 == index, "TOC isn't under article name");
          break;
        }
        ++index;
      }
    }
  }

  public void verifyTapOnElementScrollToSection(int index) {
    String methodName = "verifyTapOnElementScrollToSection";
    JavascriptExecutor js = (JavascriptExecutor) driver;
    try {
      waitForElementByElement(tocAll);
      tocAll.click();
      waitForElementVisibleByElement(listOfLinks.get(index));
      listOfLinks.get(index).click();
      int h2Pos =
          Integer.parseInt(js.executeScript(
              "return Math.floor($('section.article-body h2').eq(" + index + ").offset().top)")
              .toString());
      int windowYPos =
          Integer.parseInt(js.executeScript("return $(window).scrollTop()").toString());
      String h2PaddingString =
          js.executeScript("return $('h2').eq(" + index + ").css('padding-top')").toString();
      h2PaddingString = h2PaddingString.substring(0, h2PaddingString.length() - 2);
      int h2Padding = Integer.parseInt(h2PaddingString);
      Assertion.assertTrue(h2Pos == windowYPos, "User wasn't moved to right section");
      Assertion.assertTrue(h2Padding >= 40, "Header padding top is < 40");
    } catch (NoSuchElementException | ElementNotVisibleException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }

  private void logVisibilityOfTOCMenu(boolean expectVisibility, String methodName) {
    if (TOCMenu.getCssValue("display").equals("none")) {
      PageObjectLogging.log(methodName, "TOC menu isn't displayed", !expectVisibility);
    } else {
      PageObjectLogging.log(methodName, "TOC menu is displayed", expectVisibility);
    }
  }

  public void verifyTapOnTOCCollapseOrExpandMenu() {
    String methodName = "verifyTapOnTOCCollapseOrExpandMenu";
    try {
      waitForElementByElement(tocAll);
      logVisibilityOfTOCMenu(false, methodName);
      tocAll.click();
      logVisibilityOfTOCMenu(true, methodName);
      tocAll.click();
      logVisibilityOfTOCMenu(true, methodName);
    } catch (NoSuchElementException | ElementNotVisibleException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }
}
