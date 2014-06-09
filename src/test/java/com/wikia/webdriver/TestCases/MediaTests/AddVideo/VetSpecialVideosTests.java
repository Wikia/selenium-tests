/**
 *
 */
package com.wikia.webdriver.TestCases.MediaTests.AddVideo;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class VetSpecialVideosTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();

	@Test(groups = {"VetTests001", "VetTests", "SpecialVideo"})
	public void SpecialVideos_001_Provider() {
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver) ;
		specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		specialVideos.verifyVideoAdded(VideoContent.youtubeVideoURL2name);
	}

	@Test(enabled = false, groups = {"VetTests002", "VetTests", "SpecialVideo"})
	public void SpecialVideos_002_Library() {
		SpecialVideosPageObject specialVideos = new SpecialVideosPageObject(driver) ;
		specialVideos.logInCookie(credentials.userName, credentials.password, wikiURL);
		specialVideos.openSpecialVideoPage(wikiURL);
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByQuery(VideoContent.wikiaVideoQuery, 0);
		specialVideos.verifyVideoAdded(vetAddingVideo.getVideoName());
	}

}
