package com.wikia.webdriver.testcases.mediatests.modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

@Test(groups = {"VetModalWidth", "VetTests", "Media"})
public class VetModalWidthTests extends NewTestTemplate {

  final static int MODAL_WIDTH = 250;
  String articleTitle;

  @Test(groups = {"VetModalwidth_001"})
  @Execute(asUser = User.USER)
  public void Vet_Modal_001_modalWidth() {
    WikiBasePageObject base = new WikiBasePageObject();
    articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();

    VisualEditModePageObject visualEditMode = base.navigateToArticleEditPage(wikiURL, articleTitle);

    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();

    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.adjustWith(MODAL_WIDTH);
    vetOptions.submit();
    Assertion.assertEquals(visualEditMode.getVideoWidth(), MODAL_WIDTH);

    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    Assertion.assertEquals(sourceEditMode.getVideoWidth(), MODAL_WIDTH);

    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    Assertion.assertEquals(previewMode.getVideoWidth(), MODAL_WIDTH);

    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    Assertion.assertEquals(article.getVideoThumbnailWidth(), MODAL_WIDTH);
  }

  @Test(groups = {"VetModalwidth_002"}, dependsOnMethods = "Vet_Modal_001_modalWidth")
  @Execute(asUser = User.USER)
  public void Vet_Modal_002_modalWidthOnEditing() {
    WikiBasePageObject base = new WikiBasePageObject();

    VisualEditModePageObject visualEditModePageObject =
        base.navigateToArticleEditPage(wikiURL, articleTitle);
    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);

    Assertion.assertEquals(vetOptions.getVideoWidth(), MODAL_WIDTH);
  }
}
