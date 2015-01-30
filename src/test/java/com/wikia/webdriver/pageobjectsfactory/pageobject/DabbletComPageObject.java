package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.EmbedMapComponentObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * @author Łukasz Nowak
 */

public class DabbletComPageObject extends BasePageObject {


  @FindBy(css = "#html")
  private WebElement htmlBox;
  @FindBy(css = "#css")
  private WebElement cssBox;
  @FindBy(css = "#result")
  private WebElement resultBox;

  public DabbletComPageObject(WebDriver driver) {
    super(driver);
  }

  private String outUrl = "http://dabblet.com/";


  public void openOutPage() {
    getUrl(outUrl);
  }

  public void typeHtmlCode(String htmlCode) {
    waitForElementVisibleByElement(cssBox);
    cssBox.clear();
    waitForElementVisibleByElement(htmlBox);
    htmlBox.clear();
    htmlBox.sendKeys(htmlCode);
    PageObjectLogging.log("verifyMapEmbed", "map was embeded", true, driver);
  }

  public void verifyMapEmbed() {
    waitForElementVisibleByElement(resultBox);
    driver.switchTo().frame(resultBox);
    driver.switchTo().frame(0);
    EmbedMapComponentObject embedMap = new EmbedMapComponentObject(driver);
    embedMap.verifyBranFooterVisible();
    driver.switchTo().defaultContent();
  }
}
