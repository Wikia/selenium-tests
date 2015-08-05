package com.wikia.webdriver.pageobjectsfactory.componentobject.wikitextshortcuts;

import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Rodriuki on 29/07/15.
 */
public class WikiTextShortCutsComponentObject extends SourceEditModePageObject{

  @FindBy (css = "a[onclick*='Category']")
  private List<WebElement> categories;


  public WikiTextShortCutsComponentObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public WebElement getCategoryShortcut(int index) {
    return categories.get(index);
  }

  public SourceEditModePageObject clickCategory() {
    WebElement categoryShortcut = getCategoryShortcut(1);
    wait.forElementVisible(categoryShortcut);
    categoryShortcut.click();
    return new SourceEditModePageObject(driver);
  }
}
