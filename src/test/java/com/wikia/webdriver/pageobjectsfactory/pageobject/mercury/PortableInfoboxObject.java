package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownshership: Content West-Wing
 */
public class PortableInfoboxObject extends BasePageObject {

  @FindBy(css= ".pi-hero .article-image")
  private WebElement mainImage;
  @FindBy(css= ".portable-infobox .pi-hero-title")
  private WebElement title;
  @FindBy(css= ".pi-item .pi-data-label")
  private List<WebElement> dataLabels;
  @FindBy(css =".pi-item .pi-data-value")
  private List<WebElement> dataValues;

  public PortableInfoboxObject(WebDriver driver) {
    super(driver);
  }

  public PortableInfoboxObject isMainImageVisible() {
    wait.forElementVisible(mainImage);
    Assertion.assertEquals(isElementOnPage(mainImage), true);
    return this;
  }

  public PortableInfoboxObject verifyDataItemsVisibility() {
    Assertion.assertFalse(dataLabels.isEmpty());
    Assertion.assertFalse(dataValues.isEmpty());
    return this;
  }

  public PortableInfoboxObject isTitleVisible() {
    wait.forElementVisible(title);
    Assertion.assertEquals(isElementOnPage(title), true);
    return this;
  }

}
