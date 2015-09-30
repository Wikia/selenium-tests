package com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiffPagePageObject extends BasePageObject {

  @FindBy(css = "#mw-imagepage-content table.diff")
  private WebElement diffTable;

  public DiffPagePageObject(WebDriver driver) {
    super(driver);
    // TODO Auto-generated constructor stub
  }

  public void verifyDiffTablePresent() {
    wait.forElementVisible(diffTable);
    LOG.log("Verify diff table", "diff table is visible", LOG.Type.SUCCESS);
  }

}
