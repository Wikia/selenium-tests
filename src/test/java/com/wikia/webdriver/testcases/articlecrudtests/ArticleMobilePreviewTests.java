package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.MobilePreviewEditModePageObject;
import org.testng.annotations.Test;

@Test(groups = "ArticleMobilePreviewTests")
@Execute(onWikia = "mediawiki119", asUser = User.USER)
public class ArticleMobilePreviewTests extends NewTestTemplate {

  @Test()
  public void isInfoboxDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview(PageContent.PORTABLE_INFOBOX_01);

    Assertion.assertTrue(preview.isInfoboxDisplayed());
    Assertion.assertTrue(preview.isHeroImageDisplayed());
    Assertion.assertTrue(preview.isDataComponentDisplayed());
    Assertion.assertTrue(preview.isTitleComponentDisplayed());
  }

  @Test()
  public void isArticleTextDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview(PageContent.PORTABLE_INFOBOX_01);

    Assertion.assertTrue(preview.isArticleTextDisplayed());
  }

  @Test()
  public void isVideoDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview("MobilePreviewVideoTest");

    Assertion.assertTrue(preview.isVideoDisplayed());
  }

  @Test()
  public void isImageWithCaptionDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview("MobilePreviewImageTest");

    Assertion.assertTrue(preview.isImageDisplayed());
    Assertion.assertTrue(preview.isImageCaptionDisplayed());
  }
}
