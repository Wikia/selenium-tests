package com.wikia.webdriver.testcases.mediatests.modal;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

import org.testng.annotations.Test;

public class VetModalWidthTests extends NewTestTemplate {

  /**
   * 1. Verify width in visual mode, source mode, preview, and published page, 2. Verify width in
   * vet modal when page edited,
   */

  Credentials credentials = Configuration.getCredentials();

  String articleTitle;

  final static int WIDTH = 250;

  @Test(groups = {"VetModalWidth", "VetModalwidth_001", "VetTests", "Media"})
  @Execute(asUser = User.USER)
  public void Vet_Modal_001_widthOnPage() {
    WikiBasePageObject base = new WikiBasePageObject();
    articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject
        visualEditMode =
        base.navigateToArticleEditPage(wikiURL, articleTitle);
    VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.adjustWith(WIDTH);
    vetOptions.submit();
    visualEditMode.verifyVideoWidth(WIDTH);
    SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
    sourceEditMode.verifySourceModeEnabled();
    sourceEditMode.verifyVideoWidth(WIDTH);
    PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
    previewMode.verifyVideoWidth(WIDTH);
    previewMode.closePreviewModal();
    ArticlePageObject article = sourceEditMode.submitArticle();
    article.verifyVideoWidth(WIDTH);
  }

  @Test(groups = {"VetModalWidth", "VetModalwidth_002", "VetTests",
                  "Media"}, dependsOnMethods = "Vet_Modal_001_widthOnPage")
  @Execute(asUser = User.USER)
  public void Vet_Modal_002_widthInModal() {
    WikiBasePageObject base = new WikiBasePageObject();
    VisualEditModePageObject
        visualEditModePageObject =
        base.navigateToArticleEditPage(wikiURL, articleTitle);
    VetOptionsComponentObject vetOptions =
        (VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.VIDEO);
    vetOptions.verifyVideoWidth(WIDTH);
  }
}
