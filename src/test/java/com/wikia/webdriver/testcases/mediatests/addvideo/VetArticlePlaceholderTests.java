package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.SourceModeContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

@Test(groups = {"VetTests", "Media", "VideoArticlePlacehoder"})
public class VetArticlePlaceholderTests extends NewTestTemplate {
  @Test(groups = {"VideoArticlePlacehoder_001"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  @RelatedIssue(issueID = "WW-525")
  public void userCanAddVideoByUrlUsingPlaceholder() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    ArticlePageObject article = new ArticlePageObject().open();
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_002"})
  @Execute(asUser = User.USER, onWikia = "mobileregressiontesting")
  @RelatedIssue(issueID = "WW-525")
  public void userCanAddVideoFromWikiaUsingPlaceholder() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    ArticlePageObject article = new ArticlePageObject().open();
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_003"})
  @Execute(asUser = User.STAFF, onWikia = "mobileregressiontesting")
  @RelatedIssue(issueID = "WW-525")
  public void userCanAddVideoByUrlUsingPlaceholderInEditMode() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    // Added cause of MAIN-6374 issue
    new ArticlePageObject().open();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    VetAddVideoComponentObject vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_004"})
  @Execute(asUser = User.STAFF, onWikia = "mobileregressiontesting")
  @RelatedIssue(issueID = "WW-525")
  public void userCanAddVideoFromWikiaUsingPlaceholderInEditMode() {
    new ArticleContent().push(SourceModeContent.PLACEHOLDERS);

    // Added cause of MAIN-6374 issue
    new ArticlePageObject().open();

    VisualEditModePageObject visualEditMode = new VisualEditModePageObject().open();
    VetAddVideoComponentObject vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyVideo();
  }
}
