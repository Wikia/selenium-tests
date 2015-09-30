package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.imageupload;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.BasePageObject;

/**
 * @ownership: Content X-Wing
 */
public class SearchForImagePageObject extends BasePageObject {

  @FindBy(css = "input#search")
  private WebElement searchInput;
  @FindBys(@FindBy(css = ".search-results img"))
  private List<WebElement> images;

  public SearchForImagePageObject(WebDriver driver) {
    super(driver);
  }

  public void type(String searchPhrase) {
    waitAndSendKeys(searchInput, searchPhrase);
  }

  public CroppingToolPageObject clickOnImage(int imageIndex) {
    waitAndClick(images.get(imageIndex));
    return new CroppingToolPageObject(driver);
  }
}
