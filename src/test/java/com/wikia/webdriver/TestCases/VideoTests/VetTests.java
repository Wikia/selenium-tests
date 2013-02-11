package com.wikia.webdriver.TestCases.VideoTests;

import org.apache.http.entity.ContentProducer;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

// https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0AtG89yMxyGSadEtPY28ydDB4czkydXNmMkJVQ2NGR0E#gid=7

public class VetTests extends TestTemplate {

	@Test(groups = { "VetTests001", "VetTests" })
	public void Vet_Tests_001_addAvideoOnSpecialVideos() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		SpecialVideosPageObject specialVideos = wiki.openSpecialVideoPage();
		VetAddVideoComponentObject vetAddingVideo = specialVideos.clickAddAVideo();
		vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL2);
		specialVideos.verifyVideoAdded(VideoContent.youtubeVideoURL2name);
	}
	
	
}
