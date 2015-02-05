package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public class TableOfContentPageObject extends MercuryBasePageObject {

  @FindBy(css = "nav.table-of-contents")
  private WebElement tocAll;
  @FindBy(css = "h2")
  private List<WebElement> allH2;
  @FindBy(css = "section.article-body > *")
  private List<WebElement> articleBodyTags;
  @FindBy(css = "nav.table-of-contents a")
  private List<WebElement> listOfLinks;
  
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
    JavascriptExecutor js = (JavascriptExecutor) driver;
    int topBarHeight = Integer.parseInt(js.executeScript("return $('div.site-logo').height()").toString());
    int h2Pos = Integer.parseInt(js.executeScript("return $('h2').eq(" + index + ").offset().top").toString());
    System.out.println(topBarHeight + " " + h2Pos);
    tocAll.click();
    listOfLinks.get(index).click();
    System.out.println(Integer.parseInt(js.executeScript("return $(window).scrollTop()").toString()));
//    System.out.println(listOfLinks.get(index).getAttribute("href"));
  }
}
