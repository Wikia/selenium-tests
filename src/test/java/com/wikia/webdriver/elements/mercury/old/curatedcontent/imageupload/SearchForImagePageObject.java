package com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload;

import com.wikia.webdriver.common.core.elemnt.Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchForImagePageObject {

  @FindBy(css = "input#search")
  private WebElement searchInput;
  @FindBys(@FindBy(css = ".search-results img"))
  private List<WebElement> images;

  private Wait wait;
  private WebDriver driver;

  public SearchForImagePageObject(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public SearchForImagePageObject type(String searchPhrase) {
    wait.forElementVisible(searchInput);
    searchInput.sendKeys(searchPhrase);

    return this;
  }

  public CroppingToolPageObject clickOnImage(int imageIndex) {
    WebElement image = images.get(imageIndex);

    wait.forElementVisible(image);
    image.click();

    return new CroppingToolPageObject(driver);
  }
}
