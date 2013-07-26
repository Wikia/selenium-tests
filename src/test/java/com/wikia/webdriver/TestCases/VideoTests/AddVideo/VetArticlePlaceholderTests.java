/**
 *
 */
package com.wikia.webdriver.TestCases.VideoTests.AddVideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetArticlePlaceholderTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VideoArticlePlacehoder_001", "VideoArticlePlacehoder"})
	public void Placeholders_001_PublishedProvider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		base.openRandomArticle(wikiURL);
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyArticleTitle(articleTitle);
		VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.submit();
		article.verifyVideo();
	}

	@Test(groups = {"VideoArticlePlacehoder_002", "VideoArticlePlacehoder"})
	public void Placeholders_002_PublishedLibrary() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		base.openRandomArticle(wikiURL);
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyArticleTitle(articleTitle);
		VetAddVideoComponentObject vetAddingVideo = article.clickAddVideoPlaceholder();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.submit();
		article.verifyVideo();
	}

	@Test(groups = {"VideoArticlePlacehoder_003", "VideoArticlePlacehoder"})
	public void Placeholders_003_EditModeProvider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
		VetAddVideoComponentObject vetAddingVideo = (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VideoPlaceholder);
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyTitle(articleTitle);
		article.verifyVideo();
	}

	@Test(groups = {"VideoArticlePlacehoder_004", "VideoArticlePlacehoder"})
	public void Placeholders_004_EditModeLibrary() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		base.openRandomArticle(wikiURL);
		String articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.goToArticleDefaultContentEditPage(wikiURL, articleTitle);
		VetAddVideoComponentObject vetAddingVideo = (VetAddVideoComponentObject) visualEditMode.modifyComponent(Components.VideoPlaceholder);
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		ArticlePageObject article  = visualEditMode.submitArticle();
		article.verifyTitle(articleTitle);
		article.verifyVideo();
	}


}
