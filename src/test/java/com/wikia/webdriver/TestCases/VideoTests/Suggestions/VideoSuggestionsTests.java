/**
 *
 */
package com.wikia.webdriver.TestCases.VideoTests.Suggestions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.URLBuilder.UrlBuilder;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VideoSuggestionsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * TC001 - Verifies video name field is editable for Non-Premium Wikia
	 * videos
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */


	private String testedPage;
	private String testedWiki;

	@DataProvider
	private static final Object[][] wikis() {
		return new Object[][] {
			{"callofduty", "Frank_Woods"}
		};
	}

	@Factory(dataProvider="wikis")
		public VideoSuggestionsTests(String wikiName, String path) {
			super();
			UrlBuilder urlBuilder = new UrlBuilder(config.getEnv());
			testedPage = urlBuilder.getUrlForPath(wikiName, path);
			testedWiki = urlBuilder.getUrlForWiki(wikiName);
		}

	@Test(groups = { "VideoSuggestions_001", "VideoSuggestions" })
	public void Vet_Tests_001_VerifyVideoSuggestionsIsDisplayed() {
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.logInCookie(credentials.userName,
				credentials.password, testedWiki);
		driver.get(testedPage);
		WikiArticleEditMode edit = article.edit();
		VetAddVideoComponentObject vetAddingVideo = edit.clickVideoButton();
		vetAddingVideo.verifySuggestionsIsDisplayed();
		vetAddingVideo.clickCloseButton();
		edit.clickOnPublishButton();
	}

}
