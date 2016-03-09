/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test(groups = {"VetArticleComments", "VetTests", "Media"})
public class VetArticleCommentsTests extends NewTestTemplate {

  @Test(groups = {"VetArticleComments_001"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "MAIN-6430", comment = "the test should pass, the related issue is not permanent")
  public void VetArticleComments_001_Provider() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();

    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("microsoft");

    VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(video.getUrl());
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    article.submitComment();
    article.verifyCommentVideo(video.getTitle());
  }

  @Test(groups = {"VetArticleComments_002"})
  @Execute(asUser = User.USER)
  public void VetArticleComments_002_Library() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    String desiredVideoName = vetOptions.getVideoName();
    vetOptions.submit();
    article.submitComment();
    article.verifyCommentVideo(desiredVideoName);
  }
}
