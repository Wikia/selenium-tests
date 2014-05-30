package com.wikia.webdriver.TestCases.VideoTests.Providers;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.DataProvider.VideoUrlProvider;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetProvidersTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
			dataProviderClass = VideoUrlProvider.class,
			dataProvider = "videoUrl",
			groups = {"VetProvidersArticle", "VetProvidersTests_001"}
	)
	public void VetProvidersTests_001_article(String videoUrl, String videoName) {
		PageObjectLogging.log("", videoUrl, true);
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);
		ArticlePageObject article = base.openRandomArticle(wikiURL);
		VisualEditModePageObject visualEditMode = article.goToCurrentArticleEditPage();
		visualEditMode.clearContent();
		VetAddVideoComponentObject vetAddVideo = visualEditMode.clickVideoButton();
		VetOptionsComponentObject vetOptions = vetAddVideo.addVideoByUrl(videoUrl);
		vetOptions.submit();
		visualEditMode.verifyVideo();
		visualEditMode.submitArticle();
		article.verifyVideo();
		article.verifyVideoName(videoName);
	}
}
