package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.category.CategoryPageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class PortableInfobox extends BasePageObject {

  @FindBy(css = ".pi-image")
  private WebElement image;

  @FindBy(css = ".pi-title")
  private WebElement title;

  @FindBy(css = ".pi-image img")
  private WebElement imageTag;

  @FindBy(css = ".WikiaLightbox")
  private WebElement lightbox;

  @FindBy(css = ".portable-infobox")
  private WebElement layout;

  @FindBy(css = ".tabberlive")
  private WebElement tabber;

  @FindBy(css = ".tabbertab .image")
  private WebElement tabberImage;

  @FindBy(css = "body")
  private WebElement bodyElement;

  @FindBy(css = ".header-title")
  private WebElement headerTitle;

  @FindBy(css = ".reference")
  private WebElement referenceElements;

  @FindBy(css = ".pi-data-label")
  private List<WebElement> h3Elements;

  @FindBy(css = ".newcategory")
  private WebElement categoryLinkInInfobox;

  @FindBy(css = ".pi-image")
  private List<WebElement> imagesWrappers;

  @FindBy(css = "h3.pi-data-label.pi-secondary-font")
  private List<WebElement> horizontalItemLabels;

  @FindBy(css = "div.pi-data-value")
  private List<WebElement> horizontalItemValues;

  @FindBy(css = ".poem")
  private List<WebElement> internalLinksInsidePoemTag;

  @FindBy(css = ".poem a[href*='redlink']")
  private List<WebElement> externalLinksInsidePoemTag;

  @FindBy(css = ".pi-navigation")
  private List<WebElement> navigation;

  @FindBy(css = ".pi-header")
  private List<WebElement> groupHeadersWrappers;

  @FindBy(css = "#articleCategories .category a")
  private List<WebElement> categories;

  @FindBy(css = "a[href*='redlink']")
  private List<WebElement> redLinks;

  @FindBy(css = ".pi-item .external")
  private List<WebElement> externalLinks;

  @FindBy(css = "b")
  private List<WebElement> boldElements;

  @FindBy(css = "i")
  private List<WebElement> italicElements;

  @FindBy(css = ".portable-infobox a[href*='/wiki/']")
  private List<WebElement> internalLinks;

  @FindBy(css = ".pi-data-value ul li")
  private List<WebElement> unorderedElements;

  @FindBy(css = ".pi-data-value ol li")
  private List<WebElement> orderedElements;

  @FindBy(css = ".pi-header")
  private List<WebElement> h3Titles;

  @FindBy(css = ".pi-title")
  private List<WebElement> titles;

  @FindBy(css = ".pi-data-label")
  private List<WebElement> itemLabels;

  @FindBy(css = ".pi-data-value")
  private List<WebElement> itemValues;

  public PortableInfobox() {
    super();
  }

  public String getBackgroundColor() {
    return layout.getCssValue("background-color");
  }

  public String getLinkRedirectTitle(WebElement element) {
    wait.forElementVisible(element);
    jsActions.scrollToElement(element);

    return element.getAttribute("href");
  }

  public String getDataLabelTextWithIndex(int index) {
    WebElement selectedLabel = itemLabels.get(index);
    wait.forElementVisible(selectedLabel);

    return selectedLabel.getText();
  }

  public String getExternalLinkRedirectTitle(int index) {
    return getLinkRedirectTitle(externalLinks.get(index));
  }

  public String getInternalLinkRedirectTitle(int index) {
    return getLinkRedirectTitle(internalLinks.get(index));
  }

  public WebElement getGroupHeader(int index) {
    return groupHeadersWrappers.get(index);
  }

  public String getTitleTextWithIndex(int index) {
    WebElement selectedTitle = titles.get(index);
    wait.forElementVisible(selectedTitle);

    return selectedTitle.getText();
  }

  public PortableInfobox waitForUrlToContain(String target) {
    this.waitForStringInURL(target);

    return this;
  }

  public String getCategoryLinkName() {
    wait.forElementVisible(categoryLinkInInfobox);

    return categoryLinkInInfobox.getText();
  }

  public String getDataImageName() {
    wait.forElementVisible(imageTag);

    return imageTag.getAttribute("data-image-name");
  }

  public PortableInfobox clickLink(WebElement element) {
    wait.forElementVisible(element);
    jsActions.scrollToElement(element);
    element.click();

    return this;
  }

  public PortableInfobox clickExternalLinkWithIndex(int index) {
    return clickLink(externalLinks.get(index));
  }

  public PortableInfobox clickInternalLinkWithIndex(int index) {
    return clickLink(internalLinks.get(index));
  }

  public CreateArticleModalComponentObject clickRedLinkWithIndex(int index) {
    WebElement selectedRedLink = redLinks.get(index);
    clickLink(selectedRedLink);

    return new CreateArticleModalComponentObject(driver);
  }

  public PortableInfobox clickImage() {
    wait.forElementVisible(image);
    image.click();

    return this;
  }

  public CategoryPageObject clickCategoryLink() {
    wait.forElementVisible(categoryLinkInInfobox);
    scrollAndClick(categoryLinkInInfobox);

    return new CategoryPageObject();
  }

  public CategoryPageObject clickCategoryWithIndex(int index) {
    wait.forElementVisible(categories.get(index));
    scrollAndClick(categories.get(index));

    return new CategoryPageObject();
  }

  public PortableInfobox open(String articleTitle) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) +
           URLsContent.WIKI_DIR + articleTitle);

    return this;
  }

  public String getHorizontalItemLabelFontSize(int index) {
    return horizontalItemLabels.get(index).getCssValue("font-size");
  }

  public String getHorizontalItemValuesFontSize(int index) {
    return horizontalItemValues.get(index).getCssValue("font-size");
  }

  public String getItemValuesFontSize(int index) {
    return itemValues.get(index).getCssValue("font-size");
  }

  public String getItemLabelsFontSize(int index) {
    return itemLabels.get(index).getCssValue("font-size");
  }

  public String getOrderedElementFontSize(int index) {
    return orderedElements.get(index).getCssValue("font-size");
  }

  public String getUnorderedElementFontSize(int index) {
    return unorderedElements.get(index).getCssValue("font-size");
  }

  private boolean isElementVisible(WebElement element) {
    try {
      return element.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public boolean isImageVisible() {
    return isElementVisible(image);
  }

  public PortableInfobox isTabberVisible() {
    wait.forElementVisible(tabber);
    Assertion.assertEquals(isElementOnPage(tabber), true);

    return this;
  }

  public boolean isTabberImageVisible() {
    return isElementVisible(tabberImage);
  }

  public boolean isInfoboxTitleVisible() {
    return isElementVisible(title);
  }

  public boolean isLightboxVisible() {
    return isElementVisible(lightbox);
  }

  public boolean isInfoboxNavigationElementVisible(int index) {
    return isElementVisible(navigation.get(index));
  }

  public int getInternalNavigationLinksNumber() {
    return internalLinksInsidePoemTag.size();
  }

  public int getExternalNavigationLinksNumber() {
    return externalLinksInsidePoemTag.size();
  }

  public boolean areQuotationMarksPresented() {
    boolean questionMarkPresented = false;
    for(int i=0; i<h3Elements.size(); i++) {
      wait.forElementVisible(h3Elements.get(i));
      if (h3Elements.get(i).getText().contains("?")) {
        questionMarkPresented = true;
      }
    }
    return questionMarkPresented;
  }

  public int getBoldElementsNumber() {

    return boldElements.size();
  }

  public int getItalicElementsNumber() {

    return italicElements.size();
  }

  public int getHeadersNumber() {

    return h3Titles.size();
  }

  public boolean isReferenceElementVisible() {
    return isElementVisible(referenceElements);
  }

  public boolean isHeaderPaddingLeftAndRightEqual(int index) {
    String left = getGroupHeader(index).getCssValue("padding-left");
    String right = getGroupHeader(index).getCssValue("padding-right");
    return left.equalsIgnoreCase(right);
  }

  public boolean imageContainsDiv(int index) {
    return imagesWrappers.get(index).getTagName().contains("div");
  }

  public boolean titleContainsDiv(int index) {
    return titles.get(index).getTagName().contains("div");
  }

  public boolean headerContainsDiv(int index) {
    return groupHeadersWrappers.get(index).getTagName().contains("div");
  }

  public boolean infoboxContainsEmptyTag() {
    return layout.getText().contains("Default");
  }
}
