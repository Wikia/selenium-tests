package com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @ownshership: Content West-Wing
 */
public class MobilePreviewEditModePageObject extends BasePageObject {

  @FindBy(css = ".modalcontent .ArticlePreview")
  private WebElement previewModal;

  public MobilePreviewEditModePageObject() {
    super();
  }

  public MobilePreviewEditModePageObject openMobilePreview(String articleName) {
    EditMode editMode = new ArticlePageObject().open(articleName).navigateToArticleEditPage();
    editMode.mobilePreviewArticle();
    wait.forElementVisible(previewModal);


    return this;
  }

}
