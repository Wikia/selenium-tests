/**
 *
 */
package com.wikia.webdriver.TestCases.VideoTests.AddVideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetArticleCommentsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetArticleComments_001", "VetArticleComments"})
	public void VetArticleComments_001_Provider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		MiniEditorComponentObject editor = article.triggerCommentArea();
		VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		article.submitComment();
		article.verifyCommentVideo(VideoContent.youtubeVideoURL2name);
	}

	@Test(groups = {"VetArticleComments_002", "VetArticleComments"})
	public void VetArticleComments_002_Library() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		MiniEditorComponentObject editor = article.triggerCommentArea();
		VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		vetOptions.setCaption(PageContent.caption);
		String desiredVideoName = vetOptions.getVideoName();
		vetOptions.submit();
		article.submitComment();
		article.verifyCommentVideo(desiredVideoName);
	}
}
