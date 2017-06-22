package com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DiffPagePageObject extends BasePageObject {

  @FindBy(css = "#mw-content-text table.diff")
  private WebElement diffTable;

  public DiffPagePageObject(WebDriver driver) {
    super();
    // TODO Auto-generated constructor stub
  }

  public void verifyDiffTablePresent() {
    wait.forElementVisible(diffTable);
    PageObjectLogging.log("Verify diff table", "diff table is visible", true);
  }

  public boolean isDiffTableVisible(){
    return diffTable.isDisplayed();
  }

}
