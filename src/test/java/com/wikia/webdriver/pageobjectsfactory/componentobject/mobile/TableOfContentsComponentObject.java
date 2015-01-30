package com.wikia.webdriver.pageobjectsfactory.componentobject.mobile;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mobile.MobileBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class TableOfContentsComponentObject extends MobileBasePageObject {

  public TableOfContentsComponentObject(WebDriver driver) {
    super(driver);
  }

  private String wikiTOC = "wiki/TOC#";

  @FindBy(css = "#wkTOC.active")
  private WebElement tocSideMenu;
  @FindBy(css = ".toc-list.level")
  private WebElement tocList;
  @FindBy(css = "#wkTOCHandle")
  private WebElement closeTOCbutton;
  @FindBys(@FindBy(css = ".toc-list:first-child > li > a"))
  private List<WebElement> tocLevel1Sections;

  public void verifyTocElements() {
    waitForElementByElement(tocSideMenu);
    waitForElementByElement(tocList);
    PageObjectLogging.log("verifyTocElements", "toc elements verified", true);
  }

  public void closeToc() {
    waitForElementByElement(closeTOCbutton);
    closeTOCbutton.click();
    PageObjectLogging.log("closeToc", "toc closed", true);
  }

  public String clickOnLevel1Section(int number, String wikiURL) {
    WebElement tocElement = tocLevel1Sections.get(number);
    String href = tocElement.getAttribute("href");
    tocElement.click();
    PageObjectLogging.log("clickOnLevel1Section", "toc level 1 clicked", true);
    return href.replace(wikiURL + wikiTOC, "");
  }
}
