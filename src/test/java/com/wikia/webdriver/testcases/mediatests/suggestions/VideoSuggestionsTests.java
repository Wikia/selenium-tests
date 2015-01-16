/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.suggestions;

import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VideoSuggestionsTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * TC001 - Verifies video name field is editable for Non-Premium Wikia
	 * videos
	 *
	 * @author Rodrigo 'RodriGomez' Molinero
	 */

	@DataProvider
	private static final Object[][] wikis() {
		return new Object[][]{
			{"callofduty", "Frank_Woods"}
		};
	}

	@Test(
		groups = {"VideoSuggestions_001", "VideoSuggestions", "Media"},
		dataProvider = "wikis"
	)
	public void Vet_Tests_001_VerifyVideoSuggestionsIsDisplayed(String wikiName, String articleName) {
		wikiURL = urlBuilder.getUrlForWiki(wikiName);

		ArticlePageObject article = new ArticlePageObject(driver);
		article.logInCookie(credentials.userName, credentials.password, wikiURL);
		article = article.openArticleByName(wikiURL, articleName);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		VetAddVideoComponentObject vetAddingVideo = ck.clickVideoButton();
		vetAddingVideo.verifySuggestionsIsDisplayed();
		WikiArticleEditMode edit = vetAddingVideo.clickCloseButton();
		edit.clickOnPublishButton();
		edit.logOut(wikiURL);
	}
}
