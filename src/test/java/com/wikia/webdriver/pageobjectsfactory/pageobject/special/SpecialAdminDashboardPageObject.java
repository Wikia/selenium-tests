package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialAdminDashboardPageObject extends SpecialPageObject {

  @FindBy(css = ".specialcsstool")
  private WebElement cssToolButton;

  public SpecialAdminDashboardPageObject(WebDriver driver) {
    super(driver);
  }

  public SpecialCssPageObject clickCssTool() {
    wait.forElementVisible(cssToolButton);
    scrollAndClick(cssToolButton);
    LOG.success("clickCssTool", "click on special CSS tool");
    return new SpecialCssPageObject(driver);
  }
}
