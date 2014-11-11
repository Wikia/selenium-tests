package com.wikia.webdriver.testcases.mediatests.modal;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.PreviewEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject.Components;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetModalWidthTests extends NewTestTemplate{

	/**
	 * 1. Verify width in visual mode, source mode, preview, and published page,
	 * 2. Verify width in vet modal when page edited,
	 */

	Credentials credentials = config.getCredentials();

	String articleTitle;

	final static int width = 250;

	@Test(groups = {"VetModalWidth", "VetModalwidth_001", "Media"})
	public void Vet_Modal_001_widthOnPage() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		articleTitle = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditModePageObject visualEditMode = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		VetAddVideoComponentObject vetAddingVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddingVideo
				.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.adjustWith(width);
		vetOptions.submit();
		visualEditMode.verifyVideoWidth(width);
		SourceEditModePageObject sourceEditMode = visualEditMode.clickSourceButton();
		sourceEditMode.verifySourceModeEnabled();
		sourceEditMode.verifyVideoWidth(width);
		PreviewEditModePageObject previewMode = sourceEditMode.previewArticle();
		previewMode.verifyVideoWidth(width);
		previewMode.closePreviewModal();
		ArticlePageObject article = sourceEditMode.submitArticle();
		article.verifyVideoWidth(width);
	}

	@Test(groups = {"VetModalWidth", "VetModalwidth_002", "Media"}, dependsOnMethods = "Vet_Modal_001_widthOnPage")
	public void Vet_Modal_002_widthInModal() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		VisualEditModePageObject visualEditModePageObject = base.navigateToArticleEditPageCK(wikiURL, articleTitle);
		VetOptionsComponentObject vetOptions =
				(VetOptionsComponentObject) visualEditModePageObject.modifyComponent(Components.Video);
		vetOptions.verifyVideoWidth(width);
	}
}
