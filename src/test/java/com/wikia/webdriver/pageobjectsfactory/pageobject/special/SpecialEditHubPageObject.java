package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class SpecialEditHubPageObject extends BasePageObject {

  @FindBy(css = "#date-picker .ui-datepicker")
  private WebElement edithubDashboardCalendar;

  public SpecialEditHubPageObject(WebDriver driver) {
    super(driver);
  }

  public void verifyCalendarAppears() {
    wait.forElementVisible(edithubDashboardCalendar);
    LOG.success("verifyCalendarAppears", "Curators calendar visible");
  }
}
