/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetArticlePlaceholderTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"VideoArticlePlacehoder_001", "VideoArticlePlacehoder", "Media"})
  public void Placeholders_001_PublishedProvider() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    base.openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject
        visualEditMode =
        base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyArticleTitle(articleTitle);
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_002", "VideoArticlePlacehoder", "Media"})
  public void Placeholders_002_PublishedLibrary() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    base.openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject
        visualEditMode =
        base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyArticleTitle(articleTitle);
    VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.submit();
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_003", "VideoArticlePlacehoder", "Media"})
  public void Placeholders_003_EditModeProvider() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    base.openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject
        visualEditMode =
        base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
    VetAddVideoComponentObject
        vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyTitle(articleTitle);
    article.verifyVideo();
  }

  @Test(groups = {"VideoArticlePlacehoder_004", "VideoArticlePlacehoder", "Media"})
  public void Placeholders_004_EditModeLibrary() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
    base.openRandomArticle(wikiURL);
    String articleTitle = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
    VisualEditModePageObject
        visualEditMode =
        base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
    VetAddVideoComponentObject
        vetAddingVideo =
        (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VIDEO_PLACEHOLDER);
    VetOptionsComponentObject
        vetOptions =
        vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    visualEditMode.verifyVideo();
    ArticlePageObject article = visualEditMode.submitArticle();
    article.verifyTitle(articleTitle);
    article.verifyVideo();
  }


}
