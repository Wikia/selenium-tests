package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MobilePreviewEditModePageObject extends BasePageObject {

  @FindBy(css = ".mobile-preview")
  private WebElement previewModal;

  @FindBy(css = ".mobile-preview iframe")
  private WebElement mobilePreviewIframe;

  @FindBy(css = ".portable-infobox")
  private WebElement infobox;

  @FindBy(css = ".portable-infobox .pi-title")
  private WebElement infoboxTitle;

  @FindBy(css = ".portable-infobox .pi-hero")
  private WebElement infoboxHeroImage;

  @FindBy(css = ".portable-infobox .pi-data")
  private WebElement infoboxData;

  @FindBy(css = ".ember-container .article-body")
  private WebElement previewArticleBody;

  public MobilePreviewEditModePageObject() {
    super();
  }

  public MobilePreviewEditModePageObject openMobilePreview(String articleName) {
    EditMode editMode = new ArticlePageObject().open(articleName).navigateToArticleEditPage();
    editMode.mobilePreviewArticle();
    wait.forElementVisible(previewModal);
    driver.switchTo().frame(mobilePreviewIframe);

    return this;
  }

  public MobilePreviewEditModePageObject infoboxIsDisplayed() {
    wait.forElementVisible(infobox);
    Assertion.assertTrue(infobox.isDisplayed());

    return this;
  }

  public MobilePreviewEditModePageObject infoboxHeroImageIsDisplayed() {
    wait.forElementVisible(infoboxHeroImage);
    Assertion.assertTrue(infoboxHeroImage.isDisplayed());

    return this;
  }

  public MobilePreviewEditModePageObject infoboxData() {
    wait.forElementVisible(infoboxData);
    Assertion.assertTrue(infoboxData.isDisplayed());

    return this;
  }

  public MobilePreviewEditModePageObject infoboxTitle() {
    wait.forElementVisible(infoboxTitle);
    Assertion.assertTrue(infoboxTitle.isDisplayed());

    return this;
  }
}
