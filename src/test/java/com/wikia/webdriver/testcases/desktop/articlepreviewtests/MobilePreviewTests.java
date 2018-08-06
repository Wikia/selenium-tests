package com.wikia.webdriver.testcases.desktop.articlepreviewtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.pages.ArticlePreviewPage;

import org.testng.annotations.Test;

@Test(groups = "MobilePreview")
@Execute(onWikia = "mercuryautomationtesting")
public class MobilePreviewTests extends NewTestTemplate {

  @Test(groups = "desktop-articlePreview-mobilePreviewIsRenderedCorrectlyInModal")
  public void mobilePreviewIsRenderedCorrectlyInModal() {
    new ArticlePreviewPage().navigateToArticlePreviewPageInEditMode()
        .clickOnMobilePreviewButton()
        .heroImageIsPresent()
        .infoboxIsPresent()
        .articleTableIsPresent()
        .mediaGalleryIsPresent()
        .linkedMediaGalleryIsPresent()
        .singleImageIsPresent()
        .singleVideoIsPresent();
  }
}
