package com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Rodriuki on 29/07/15.
 */
public class WikiTextShortCutsComponentObject extends SourceEditModePageObject{

  @FindBy (css = "a[onclick*='Category']")
  private WebElement category;


  public WikiTextShortCutsComponentObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public SourceEditModePageObject clickCategory() {
    waitForElementByElement(category);
    category.click();
    return new SourceEditModePageObject(driver);
  }
}
