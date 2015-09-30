package com.wikia.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.EmbedMapComponentObject;


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

  private String outUrl = "http://dabblet.com/";

  public DabbletComPageObject(WebDriver driver) {
    super(driver);
  }

  public void openOutPage() {
    getUrl(outUrl);
  }

  public void typeHtmlCode(String htmlCode) {
    wait.forElementVisible(cssBox);
    cssBox.clear();
    wait.forElementVisible(htmlBox);
    htmlBox.clear();
    htmlBox.sendKeys(htmlCode);
    LOG.success("verifyMapEmbed", "map was embeded", true);
  }

  public void verifyMapEmbed() {
    wait.forElementVisible(resultBox);
    driver.switchTo().frame(resultBox);
    driver.switchTo().frame(0);
    EmbedMapComponentObject embedMap = new EmbedMapComponentObject(driver);
    embedMap.verifyBranFooterVisible();
    driver.switchTo().defaultContent();
  }
}
