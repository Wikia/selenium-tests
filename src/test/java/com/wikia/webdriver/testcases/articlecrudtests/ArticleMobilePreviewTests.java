package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.MobilePreviewEditModePageObject;
import org.testng.annotations.Test;

@Test(groups = "ArticleMobilePreviewTests")
@Execute(onWikia = "mediawiki119")
public class ArticleMobilePreviewTests extends NewTestTemplate {

  @Execute(asUser = User.USER)
  public void verifyInfoboxDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview(PageContent.PORTABLE_INFOBOX_01)
        .infoboxIsDisplayed()
        .infoboxHeroImageIsDisplayed()
        .infoboxTitle()
        .infoboxData();
  }

  @Execute(asUser = User.USER)
  public void verifyArticleTextIsDisplayed() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview(PageContent.PORTABLE_INFOBOX_01).isArticleTextIsDisplayed();
  }

}
