package com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WikiTextShortCutsComponentObject extends SourceEditModePageObject{

  @FindBy (css = "a[onclick*='Category']")
  private List<WebElement> categories;

  public WikiTextShortCutsComponentObject(WebDriver driver) {
    super();
  }

  public SourceEditModePageObject clickCategory(int index) {
    wait.forElementVisible(categories.get(index));
    categories.get(index).click();
    return new SourceEditModePageObject();
  }
}
