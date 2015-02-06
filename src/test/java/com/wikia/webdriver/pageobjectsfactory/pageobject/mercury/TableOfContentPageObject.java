package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    } catch (NoSuchElementException e) {}
    return false;
  }
  
  public boolean areH2onPage() {
    if (allH2.size() > 0) {
      return true;
    }
    return false;
  }
  
  public void verifyNoH2NoTOC() {
    String methodName = "verifyNoH2NoTOC";
    if (areH2onPage() && isTOCDisplayed()) {
      PageObjectLogging.log(methodName, "TOC is displayed", false);
    } else {
      PageObjectLogging.log(methodName, "TOC isn't displayed", true);
    }
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
    String methodName = "verifyTOCUnderArticleName";
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
        if (element.getTagName().equals("nav") && element.getAttribute("class").contains("table-of-content") && index > 0 && h1Found) {
          if (h1Pos + 1 == index) {
            PageObjectLogging.log(methodName, "TOC is under article name", true);
          } else {
            PageObjectLogging.log(methodName, "TOC isn't under article name", false);
          }
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
      int h2Pos = Integer.parseInt(js.executeScript("return Math.floor($('section.article-body h2').eq(" + index + ").offset().top)").toString());
      int windowYPos = Integer.parseInt(js.executeScript("return $(window).scrollTop()").toString());
      String temp = js.executeScript("return $('h2').eq(" + index + ").css('padding-top')").toString();
      temp = temp.substring(0, temp.length()-2);
      int h2Padding = Integer.parseInt(temp);
      if (h2Pos == windowYPos) {
        PageObjectLogging.log(methodName, "User was moved to right section", true);
      } else {
        PageObjectLogging.log(methodName, "User wasn't moved to right section", false);
      }
      if (h2Padding >= 40) {
        PageObjectLogging.log(methodName, "Header padding top is >= 40", true);
      } else {
        PageObjectLogging.log(methodName, "Header padding top is < 40", false);
      }
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    } catch (ElementNotVisibleException e) {
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
    } catch (NoSuchElementException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    } catch (ElementNotVisibleException e) {
      PageObjectLogging.log(methodName, e.getMessage(), false);
    }
  }
}
