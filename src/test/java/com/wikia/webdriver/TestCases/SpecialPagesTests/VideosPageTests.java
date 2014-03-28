package com.wikia.webdriver.TestCases.SpecialPagesTests;

import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

public class VideosPageTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

    @Test(groups = {"VideosPage", "VideosPageTest_001"})
    public void VideosPageTest_001() {
        WikiBasePageObject base = new WikiBasePageObject(driver);
	    base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
        SpecialVideosPageObject specialVideos = base.openSpecialVideoPageMostRecent(wikiURL);
	    specialVideos.verifyDeleteViaGlobalNotifications();
    }

	@Test(groups = {"VideosPage", "VideosPageTest_001"})
	public void VideosPageTest_001() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialVideosPageObject specialVideos = base.openSpecialVideoPageMostRecent(wikiURL);
		specialVideos.verifyDeleteViaVideoNotPresent();

	}

}