package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedEditorFormPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by wikia on 2015-09-01.
 */
public class CroppingToolPageObject extends BasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement doneButton;

  public CroppingToolPageObject(WebDriver driver) {
    super(driver);
  }

  public CuratedEditorFormPageObject clickDone() {
    waitAndClick(doneButton);
    return new CuratedEditorFormPageObject(driver);
  }
}
