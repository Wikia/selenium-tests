package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.Assertion;
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

@Test(groups = {"VetArticleComments", "VetTests", "Media"})
public class VetArticleCommentsTests extends NewTestTemplate {

  @Test(groups = {"VetArticleComments_001"})
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SUS-758",
      comment = "This issue is related to API call and should not cause permanent "
          + "test failure. Otherwise the failure must be caused by other issue")
  public void RegularUserCanAddVideoInArticleCommentEditorByProvidingYoutubeVideoUrl() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();

    YoutubeVideo video = YoutubeVideoProvider.getLatestVideoForQuery("microsoft");
    String expectedCaption = PageContent.CAPTION + article.getTimeStamp();

    vetAddingVideo.addVideoByUrl(video.getUrl()).setCaption(expectedCaption).submit();

    Assertion.assertTrue(article.getArticleComment().isVideoVisible());

    article.submitComment();
    Assertion.assertEquals(article.getArticleComment().getLatestCommentCaption(), expectedCaption,
        "Latest comment caption doesn't equal to expected caption");
  }

  @Test(groups = {"VetArticleComments_002"})
  @Execute(asUser = User.USER)
  public void RegularUserCanAddVideoInArticleCommentEditorByFindingWikiaVideo() {
    new ArticleContent().clear();

    ArticlePageObject article = new ArticlePageObject().open();
    MiniEditorComponentObject editor = article.triggerCommentArea();
    VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();

    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    String desiredVideoName = vetOptions.getVideoName();
    vetOptions.submit();

    Assertion.assertTrue(article.getArticleComment().isVideoVisible(), "Video is not visible");

    article.submitComment();
    Assertion.assertTrue(article.isVideoCommentPresent(desiredVideoName),
        "Video comment doesn't have desired name");
  }
}
