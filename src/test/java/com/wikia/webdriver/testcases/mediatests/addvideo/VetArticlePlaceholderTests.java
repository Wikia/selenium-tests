/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.ExecuteAs;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetArticlePlaceholderTests extends NewTestTemplate {

  @Test(groups = {"VideoArticlePlacehoder_001", "VideoArticlePlacehoder", "Media"})
  @ExecuteAs(user = User.USER)
  public void Placeholders_001_PublishedProvider() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject(driver).goToArticleDefaultContentEditPage(wikiURL,
            articleTitle);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyArticleTitle(articleTitle);
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_002", "VideoArticlePlacehoder", "Media"})
  @ExecuteAs(user = User.USER)
  public void Placeholders_002_PublishedLibrary() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject(driver).goToArticleDefaultContentEditPage(wikiURL,
            articleTitle);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyArticleTitle(articleTitle);
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_003", "VideoArticlePlacehoder", "Media"})
  @ExecuteAs(user = User.STAFF)
  public void Placeholders_003_EditModeProvider() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject(driver).goToArticleDefaultContentEditPage(wikiURL,
            articleTitle);
    VetAddVideoComponentObject vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyTitle(articleTitle);
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_004", "VideoArticlePlacehoder", "Media"})
  @ExecuteAs(user = User.STAFF)
  public void Placeholders_004_EditModeLibrary_QAART_496() {
    new ArticlePageObject(driver).openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + DateTime.now().getMillis();
    VisualEditModePageObject visualEditMode =
        new VisualEditModePageObject(driver).goToArticleDefaultContentEditPage(wikiURL,
            articleTitle);
    VetAddVideoComponentObject vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyTitle(articleTitle);
    article.verifyVideo();
  }


}
