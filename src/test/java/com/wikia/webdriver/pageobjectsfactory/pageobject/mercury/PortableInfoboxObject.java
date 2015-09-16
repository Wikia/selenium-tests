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
  @FindBy(css= ".portable-infobox .pi-expand-button")
  private WebElement expandButton;
  @FindBy(css =".portable-infobox .new")
  private List<WebElement> internalLinksToEmptyArticle;
  @FindBy(css = ".portable-infobox a[href*='/wiki/']")
  private List<WebElement> internalLinks;
  @FindBy(css =".portable-infobox .external")
  private List<WebElement> externalLinks;
  @FindBy(css= ".pi-item .pi-data-label")
  private List<WebElement> dataLabels;
  @FindBy(css =".pi-item .pi-data-value")
  private List<WebElement> dataValues;

  public PortableInfoboxObject(WebDriver driver) {
    super(driver);
  }

  public PortableInfoboxObject expandInfobox() {
    wait.forElementVisible(expandButton);
    expandButton.click();
    return this;
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

  public PortableInfoboxObject areLinksVisible() {
    expandInfobox();
    //Assertion.assertFalse(internalLinks.isEmpty());
    Assertion.assertFalse(externalLinks.isEmpty());
    //Assertion.assertFalse(internalLinksToEmptyArticle.isEmpty());
    return this;
  }


}
