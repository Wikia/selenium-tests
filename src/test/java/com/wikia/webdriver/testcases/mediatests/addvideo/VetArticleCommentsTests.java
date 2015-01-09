/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetArticleCommentsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetArticleComments_001", "VetArticleComments", "Media"})
	public void VetArticleComments_001_Provider() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		MiniEditorComponentObject editor = article.triggerCommentArea();
		VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL2);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		article.submitComment();
		article.verifyCommentVideo(VideoContent.YOUTUBE_VIDEO_URL2_NAME);
	}

	@Test(groups = {"VetArticleComments_002", "VetArticleComments", "Media"})
	public void VetArticleComments_002_Library() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		MiniEditorComponentObject editor = article.triggerCommentArea();
		VetAddVideoComponentObject vetAddingVideo = editor.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
		vetOptions.setCaption(PageContent.CAPTION);
		String desiredVideoName = vetOptions.getVideoName();
		vetOptions.submit();
		article.submitComment();
		article.verifyCommentVideo(desiredVideoName);
	}
}
