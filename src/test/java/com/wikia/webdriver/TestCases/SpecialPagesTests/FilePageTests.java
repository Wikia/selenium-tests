package com.wikia.webdriver.TestCases.SpecialPagesTests;

import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.DiffPage.DiffPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HistoryPage.HistoryPagePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.FilePage.FilePagePageObject;

public class FilePageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	/**
	 * Verify functionality of tabs on file pages in Oasis.  When a tab
	 * is clicked, the corresponding content should be displayed.
	 *
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage001_tabs", "Media"})
	public void filePage001_tabs() {
		FilePagePageObject filePage = new FilePagePageObject(driver);
		filePage.openFilePage(wikiURL, URLsContent.fileName001);

		filePage.verifySelectedTab("about");

		filePage.selectTab(1);
		filePage.verifySelectedTab("history");

		filePage.selectTab(0);
		filePage.verifySelectedTab("about");

		filePage.selectTab(2);
		filePage.verifySelectedTab("metadata");
	}

	/**
	 * Verify that file page tabs will save their state for logged in users
	 * when they navigate away from the page and back to it.
	 *
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage002_tabsLoggedIn", "Media"})
	public void filePage002_tabsLoggedIn() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);

		FilePagePageObject filePage = base.openFilePage(wikiURL, URLsContent.fileName001);

		filePage.refreshAndVerifyTabs(0);
		filePage.refreshAndVerifyTabs(1);
		filePage.refreshAndVerifyTabs(2);
	}

	/**
	 * Verify if a diff table is present on a diff page.
	 * Note that not all diff pages have diff tables but the one specified does.
	 *
	 * @author "Liz Lee"
	 */
	@Test(groups = {"FilePage", "filePage003_diffPage", "Media"})
	public void filePage003_diffPage() {

		WikiBasePageObject base = new WikiBasePageObject(driver);
		HistoryPagePageObject historyPage = base.openFileHistoryPage(URLsContent.fileName001, wikiURL);

		DiffPagePageObject diffPage = historyPage.goToDiffPageFromHistoryPage();
		diffPage.verifyDiffTablePresent();
	}

	/**
	 * Verify that a video can be deleted from the File page
	 *
	 * @author garth
	 */
	@Test(groups = {"FilePage", "filePage004_delete", "Media"})
	public void filePage004_delete() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password);

		// First add the video we'll delete
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver) ;
		specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL4);

		// Now delete the video
		FilePagePageObject filePage = base.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.verifyEmbeddedVideoIsPresent();
		filePage.deletePage();
		filePage.verifyEmptyFilePage();
	}
}
