package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.PortableInfobox;
import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @ownshership: Content West-Wing
 */
public class PortableInfoboxObject extends BasePageObject {

  @FindBy(css = ".portable-infobox")
  private WebElement infoboxWrapper;
  @FindBy(css= ".pi-hero .article-image")
  private WebElement mainImage;
  @FindBy(css= ".portable-infobox .pi-hero-title")
  private WebElement title;
  @FindBy(css= ".portable-infobox .pi-expand-button")
  private WebElement expandButton;
  @FindBy(css=".article-content .collapsed")
  private WebElement infoboxIsCollapsed;
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
  @FindBy(css = "b")
  private List<WebElement> boldElements;
  @FindBy(css = "i")
  private List<WebElement> italicElements;
  @FindBy(css = "portable-infobox .reference")
  private List<WebElement> references;

  public PortableInfoboxObject(WebDriver driver) {
    super(driver);
  }

  public PortableInfoboxObject clickExpandButton() {
    wait.forElementVisible(expandButton);
    expandButton.click();
    return this;
  }

  public PortableInfoboxObject isMainImageVisible() {
    wait.forElementVisible(mainImage);
    Assertion.assertEquals(isElementOnPage(mainImage), true);
    return this;
  }

  public PortableInfoboxObject isTitleVisible() {
    wait.forElementVisible(title);
    Assertion.assertEquals(isElementOnPage(title), true);
    return this;
  }

  public PortableInfoboxObject isInfoboxCollapsed() {
    Assertion.assertEquals(isElementOnPage(infoboxIsCollapsed), true);
    return this;
  }

  public PortableInfoboxObject isInfoboxExpanded() {
    Assertion.assertEquals(infoboxWrapper.getAttribute("style"), "height: auto;");
    return this;
  }


  public PortableInfoboxObject verifyDataItemsVisibility() {
    Assertion.assertFalse(dataLabels.isEmpty());
    Assertion.assertFalse(dataValues.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyLinksVisibility() {
    //Assertion.assertFalse(internalLinks.isEmpty());
    Assertion.assertFalse(externalLinks.isEmpty());
    //Assertion.assertFalse(internalLinksToEmptyArticle.isEmpty());
    return this;
  }

  public PortableInfoboxObject verifyReferencesVisibility() {
    Assertion.assertFalse(references.isEmpty());
    return this;
  }

  public PortableInfoboxObject areBoldElementsPresented() {
    Assertion.assertFalse(boldElements.isEmpty());
    return this;
  }

  public PortableInfoboxObject areItalicElementsPresnted() {
    Assertion.assertFalse(italicElements.isEmpty());
    return this;
  }



}
