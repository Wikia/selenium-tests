/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.video.YoutubeVideo;
import com.wikia.webdriver.common.core.video.YoutubeVideoProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
@Test(groups = {"VetArticleComments", "Media"})
public class VetArticleCommentsTests extends NewTestTemplate {

  @Test(groups = {"VetArticleComments_001"})
  @ExecuteAs(user = User.USER)
  public void VetArticleComments_001_Provider_QAART_509() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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
  @ExecuteAs(user = User.USER)
  public void VetArticleComments_002_Library() {
    ArticlePageObject article = new ArticlePageObject(driver).openRandomArticle(wikiURL);
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
