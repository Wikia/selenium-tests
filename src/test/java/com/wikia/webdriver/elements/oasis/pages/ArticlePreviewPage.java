package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.elements.oasis.components.articlepreview.MobilePreviewModal;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePreviewPage extends WikiBasePageObject {

  @FindBy(css = "#wpPreviewMobile")
  private WebElement mobilePreviewButton;

  @FindBy(css = ".mobile-preview")
  private WebElement mobilePreviewModal;

  @FindBy(css = ".mobile-preview iframe")
  private WebElement mobilePreviewIframe;

  private static final String WIKI_NAME = "mercuryautomationtesting";
  private static final String ARTICLE_PREVIEW_PAGE = "/wiki/ArticlePreview";
  private static final String[] EDIT_QUERY_PARAM = {"action=edit"};

  private Navigate navigate;

  public ArticlePreviewPage() {
    super();

    navigate = new Navigate();
  }

  public ArticlePreviewPage navigateToArticlePreviewPageInEditMode() {
    String host = UrlBuilder.getHostForWiki(WIKI_NAME);

    navigate.toPage(host, ARTICLE_PREVIEW_PAGE, EDIT_QUERY_PARAM);

    return this;
  }

  public MobilePreviewModal clickOnMobilePreviewButton() {
    wait.forElementClickable(mobilePreviewButton);
    mobilePreviewButton.click();

    wait.forElementVisible(mobilePreviewModal);
    driver.switchTo().frame(mobilePreviewIframe);

    return new MobilePreviewModal();
  }
}
