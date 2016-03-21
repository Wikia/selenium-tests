package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.MobilePreviewEditModePageObject;

import org.testng.annotations.Test;

/**
 * @ownshership: Content West-Wing
 */
@Test(groups = "ArticleMobilePreviewTests")
@Execute(onWikia = "mediawiki119")
public class ArticleMobilePreviewTests extends NewTestTemplate{

  @Execute(asUser = User.USER)
  public void openMobilePreview() {
    MobilePreviewEditModePageObject preview = new MobilePreviewEditModePageObject();
    preview.openMobilePreview("test4324242");

  }



}
