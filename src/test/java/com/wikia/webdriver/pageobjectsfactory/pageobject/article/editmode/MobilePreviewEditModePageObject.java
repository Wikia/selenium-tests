package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.By;
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

  @FindBy(css = ".article-content p")
  private WebElement articleContent;

  @FindBy(css = ".article-content .article-video")
  private WebElement articleVideo;

  @FindBy(css = ".article-content .article-image img")
  private WebElement articleImage;

  @FindBy(css = ".article-content .article-image figcaption")
  private WebElement articleImageCaption;

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

  public boolean isInfoboxDisplayed() {
    wait.forElementPresent(By.cssSelector(".portable-infobox"));

    return infobox.isDisplayed();
  }

  public boolean isHeroImageDisplayed() {
    wait.forElementPresent(By.cssSelector(".portable-infobox .pi-hero"));

    return infoboxHeroImage.isDisplayed();
  }

  public boolean isDataComponentDisplayed() {
    wait.forElementPresent(By.cssSelector(".portable-infobox .pi-data"));

    return infoboxData.isDisplayed();
  }

  public boolean isTitleComponentDisplayed() {
    wait.forElementPresent(By.cssSelector(".portable-infobox .pi-title"));

    return infoboxTitle.isDisplayed();
  }

  public boolean isArticleTextDisplayed() {
    wait.forElementPresent(By.cssSelector(".article-content p"));
    scrollToSelector(".article-content p");

    return articleContent.isDisplayed();
  }

  public boolean isVideoDisplayed() {
    wait.forElementPresent(By.cssSelector(".article-content .article-video"));

    return articleVideo.isDisplayed();
  }

  public boolean isImageDisplayed() {
    wait.forElementPresent(By.cssSelector(".article-content .article-image img"));

    return articleImage.isDisplayed();
  }

  public boolean isImageCaptionDisplayed() {
    wait.forElementPresent(By.cssSelector(".article-content .article-image figcaption"));

    return articleImageCaption.isDisplayed();
  }
}
