package com.wikia.webdriver.testcases.articlepreviewtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.api.TemplateContent;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.oasis.pages.ArticlePreviewPage;

import org.testng.annotations.Test;

@Test(groups = "MobilePreview")
@Execute(onWikia = "mercuryautomationtesting")
public class MobilePreviewTests extends NewTestTemplate {

  private static final String ARTICLE_PREVIEW_CONTENT =
          ContentLoader.loadWikiTextContent("Mercury_ArticlePreview");
  private static final String SIMPLE_INFOBOX_TEMPLATE =
          ContentLoader.loadWikiTextContent("SimpleInfobox_Template");

  @Test(groups = "oasis-articlePreview-mobilePreviewIsRenderedCorrectlyInModal")
  public void mobilePreviewIsRenderedCorrectlyInModal() {
    new TemplateContent().push(SIMPLE_INFOBOX_TEMPLATE, "SimpleInfobox");
    new ArticleContent().push(ARTICLE_PREVIEW_CONTENT, "ArticlePreview");
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
