package com.wikia.webdriver.TestCases.SpecialPagesTests;

import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Actions.DeletePageObject;
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

		filePage.clickTab( FilePagePageObject.HISTORY_TAB );
		filePage.verifySelectedTab("history");

		filePage.clickTab( FilePagePageObject.ABOUT_TAB );
		filePage.verifySelectedTab("about");

		filePage.clickTab( FilePagePageObject.METADATA_TAB );
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
		base.logInCookie(credentials.userName, credentials.password, wikiURL);

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
		// Go to Special:Videos to add a video
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver) ;
		specialVideos.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);

		// Add a Youtube video we'll delete
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL4);

		// Verify the video is actually there
		specialVideos.verifyVideoAdded(VideoContent.youtubeVideoURL4FileTitle);

		// Now delete the video
		FilePagePageObject filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		DeletePageObject deletePage = filePage.deletePage();
		deletePage.submitDeletion();

		// Go back to the file page and make sure its gone
		filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.verifyEmptyFilePage();
	}

	/**
	 * Verify that a video can be deleted from the File page
	 *
	 * @author garth
	 */
	@Test(groups = {"FilePage", "filePage005_deleteFromHistory", "Media"})
	public void filePage005_deleteFromHistory() {
		// Go to Special:Videos to add a video
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver) ;
		specialVideos.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);

		// Add a Youtube video we'll delete
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL4);

		// Verify the video is actually there
		specialVideos.verifyVideoAdded(VideoContent.youtubeVideoURL4FileTitle);

		// Go to the history tab and add a second video to test deleting a version
		FilePagePageObject filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.selectHistoryTab();
		filePage.replaceVideo(VideoContent.youtubeVideoURL5);

		// Load the file page again, should have the same name
		filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.verifyEmbeddedVideoIsPresent();

		// Go to the history tab and verify there are at least two videos
		filePage.selectHistoryTab();
		filePage.verifyVersionCountAtLeast(2);

		// Delete the second version
		DeletePageObject deletePage = filePage.deleteVersion( 2 );
		deletePage.submitDeletion();

		// Load the file page again, should have the same name
		filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.verifyEmbeddedVideoIsPresent();

		// Delete the first version and thus the whole page
		deletePage = filePage.deleteVersion( 1 );
		deletePage.submitDeletion();

		// Go back to the file page and make sure its gone
		filePage = specialVideos.openFilePage(wikiURL, VideoContent.youtubeVideoURL4FileName);
		filePage.verifyEmptyFilePage();
	}
}
