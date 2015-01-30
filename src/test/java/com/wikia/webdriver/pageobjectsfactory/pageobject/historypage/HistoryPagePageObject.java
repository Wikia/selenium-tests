package com.wikia.webdriver.pageobjectsfactory.pageobject.historypage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.diffpage.DiffPagePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HistoryPagePageObject extends BasePageObject {

  public HistoryPagePageObject(WebDriver driver) {
    super(driver);
    // TODO Auto-generated constructor stub
  }

  @FindBy(css = ".historysubmit")
  private List<WebElement> historySubmit;

  @FindBy(css = ".WikiaPageHeaderDiffHistory h1 strong")
  private WebElement diffHeader;

  public DiffPagePageObject goToDiffPageFromHistoryPage() {
    historySubmit.get(0).click();
    waitForTextToBePresentInElementByElement(diffHeader, "Changes");
    return new DiffPagePageObject(driver);
  }
}
