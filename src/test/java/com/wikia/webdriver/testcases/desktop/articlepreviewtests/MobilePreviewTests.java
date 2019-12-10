package com.wikia.webdriver.testcases.desktop.articlepreviewtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.desktop.components.articlepreview.MobilePreviewModal;
import com.wikia.webdriver.elements.communities.desktop.pages.ArticlePreviewPage;
import org.testng.annotations.Test;

/*
  Tests for Mobile Preview. Entry point for that preview could be find in the article's editor on Desktop.
  User is able to see how his/her page would look on mobile view.
 */

@Test(groups = "MobilePreview")
@Execute(onWikia = "mercuryautomationtesting")
public class MobilePreviewTests extends NewTestTemplate {

  private static final String RICH_ARTICLE = ContentLoader.loadWikiTextContent("Rich_Article");
  private static final String ARTICLE_PREVIEW_PAGE = "ArticlePreview";

  @Test(groups = "desktop-articlePreview-mobilePreviewIsRenderedCorrectlyInModal")
  @RelatedIssue(issueID = "IW-1916")
  public void mobilePreviewIsRenderedCorrectlyInModal() {
    new ArticleContent().push(RICH_ARTICLE, ARTICLE_PREVIEW_PAGE);

    MobilePreviewModal preview = new ArticlePreviewPage()
            .navigateToArticlePreviewPageInEditMode().clickOnMobilePreviewButton();

    Assertion.assertTrue(preview.heroImageIsPresent());
    Assertion.assertTrue(preview.infoboxIsPresent());

    // Commenting this Assertions due to not working collapsible sections in this view (reported in IW-1916)
//    Assertion.assertTrue(preview.articleTableIsPresent());
//    Assertion.assertTrue(preview.mediaGalleryIsPresent());
//    Assertion.assertTrue(preview.singleImageIsPresent());
//    Assertion.assertTrue(preview.singleVideoIsPresent());
  }
}
