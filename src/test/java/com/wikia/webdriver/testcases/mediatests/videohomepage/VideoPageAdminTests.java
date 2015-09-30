package com.wikia.webdriver.testcases.mediatests.videohomepage;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.FeaturedVideoAdminPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.LatestVideoAdminPageObject;


/**
 * Created by Liz Lee on 6/18/14.
 * 
 * @ownership Content X-Wing
 */
public class VideoPageAdminTests extends NewTestTemplate {

  @Test(groups = {"VideoPageAdmin_001", "Media", "VideoPageAdminTest"})
  @Execute(asUser = User.STAFF, onWikia = URLsContent.VIDEO_TEST_WIKI)
  public void VideoPageAdmin_001_AddFeaturedVideo() {
    FeaturedVideoAdminPageObject featuredVideoAdminObject =
        new FeaturedVideoAdminPageObject(driver).open();

    // Add video 1
    VetAddVideoComponentObject vetAddingVideo = featuredVideoAdminObject.clickAddVideo();
    vetAddingVideo.addVideoByUrl(VideoContent.PREMIUM_VIDEO_URL);
    featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME);

    // Save the form and navigate back to featured form
    LatestVideoAdminPageObject latestVideoAdminPageObject =
        featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
    featuredVideoAdminObject = latestVideoAdminPageObject.clickFeaturedTab(driver);
    featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME);

    // Add video 2 and make sure video title has changed
    vetAddingVideo = featuredVideoAdminObject.clickAddVideo();
    vetAddingVideo.addVideoByUrl(VideoContent.PREMIUM_VIDEO_URL2);
    featuredVideoAdminObject.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME2);
    featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
  }
}
