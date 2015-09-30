package com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

public class DiffPagePageObject extends BasePageObject {

  @FindBy(css = "#mw-imagepage-content table.diff")
  private WebElement diffTable;

  public DiffPagePageObject(WebDriver driver) {
    super(driver);
    // TODO Auto-generated constructor stub
  }

  public void verifyDiffTablePresent() {
    wait.forElementVisible(diffTable);
    LOG.success("Verify diff table", "diff table is visible");
  }

}
