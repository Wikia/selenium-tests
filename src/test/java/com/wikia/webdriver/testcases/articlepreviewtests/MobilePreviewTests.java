package com.wikia.webdriver.testcases.articlepreviewtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.ArticlePreviewPage;

import org.testng.annotations.Test;

@Test(groups = "Mercury_MobilePreview")
@Execute(onWikia = "mercuryautomationtesting")
public class MobilePreviewTests extends NewTestTemplate {

  @Test(groups = "oasis-articlePreview-mobilePreviewIsRenderedCorrectlyInModal")
  public void mobilePreviewIsRenderedCorrectlyInModal() {
    new ArticlePreviewPage()
        .navigateToArticlePreviewPageInEditMode()
        .clickOnMobilePreviewButton()
        .heroImageIsPresent()
        .infoboxIsPresent()
        .tableOfContentsIsPresent()
        .articleTableIsPresent()
        .mediaGalleryIsPresent()
        .linkedMediaGalleryIsPresent()
        .singleImageIsPresent()
        .singleVideoIsPresent();
  }
}
