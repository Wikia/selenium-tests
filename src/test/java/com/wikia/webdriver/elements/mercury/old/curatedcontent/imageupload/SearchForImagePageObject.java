package com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload;

import com.wikia.webdriver.elements.mercury.old.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

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
