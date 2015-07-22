/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Content X-Wing
 */
public class VetArticlePlaceholderTests extends NewTestTemplate {

  @Test(groups = {"VideoArticlePlacehoder_001", "VideoArticlePlacehoder", "Media"})
  @Execute(asUser = User.USER)
  public void Placeholders_001_PublishedProvider() {
    String wikiURL = urlBuilder.getUrlForWiki("mobileregressiontesting");
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
  @Execute(asUser = User.USER)
  public void Placeholders_002_PublishedLibrary() {
    String wikiURL = urlBuilder.getUrlForWiki("mobileregressiontesting");
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
  @Execute(asUser = User.STAFF)
  public void Placeholders_003_EditModeProvider() {
    String wikiURL = urlBuilder.getUrlForWiki("mobileregressiontesting");
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
  @Execute(asUser = User.STAFF)
  public void Placeholders_004_EditModeLibrary() {
    String wikiURL = urlBuilder.getUrlForWiki("mobileregressiontesting");
    WikiBasePageObject base = new WikiBasePageObject(driver);
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
