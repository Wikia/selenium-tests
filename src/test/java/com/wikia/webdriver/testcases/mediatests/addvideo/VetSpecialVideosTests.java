/**
 *
 */
package com.wikia.webdriver.testcases.mediatests.addvideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class VetSpecialVideosTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetTests001", "VetTests", "SpecialVideo", "Media"})
	public void SpecialVideos_001_Provider() {
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
		specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL2);
		specialVideos.verifyVideoAdded(VideoContent.YOUTUBE_VIDEO_URL2_NAME);
	}

	@Test(enabled = false, groups = {"VetTests002", "VetTests", "SpecialVideo", "Media"})
	public void SpecialVideos_002_Library() {
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver);
		specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByQuery(VideoContent.WIKIA_VIDEO_QUERY, 0);
		specialVideos.verifyVideoAdded(vetAddingVideo.getVideoName());
	}

}
