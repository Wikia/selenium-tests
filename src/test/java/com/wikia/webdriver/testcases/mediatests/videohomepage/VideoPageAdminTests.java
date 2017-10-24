package com.wikia.webdriver.testcases.mediatests.videohomepage;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.imageutilities.ImageGenerator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.FeaturedVideoContainer;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddImageComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.FeaturedVideoAdminPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage.LatestVideoAdminPageObject;
import org.testng.annotations.Test;

import java.util.List;

public class VideoPageAdminTests extends NewTestTemplate {

  @Test(groups = {"VideoPageAdmin_001", "Media", "VideoPageAdminTest", "VideosPage"})
  @RelatedIssue(issueID = "SUS-2391", comment = "Cannot add a video on staging environment")
  @Execute(asUser = User.STAFF, onWikia = URLsContent.VIDEO_TEST_WIKI)
  public void VideoPageAdmin_001_AddFeaturedVideo() {

    FeaturedVideoAdminPageObject featuredVideoAdminObject = new FeaturedVideoAdminPageObject(driver).open();
    List<FeaturedVideoContainer> featuredVideoContainerList = featuredVideoAdminObject.getVideoContainers();

    for (FeaturedVideoContainer container : featuredVideoContainerList) {
      container.scrollToContainer();
      // Add video 1
      VetAddVideoComponentObject vetAddingVideoModal = container.clickAddVideo();
      vetAddingVideoModal.addVideoWithoutDetailsByUrl(VideoContent.PREMIUM_VIDEO_URL2);

      // Add image 1
      ImageGenerator generator = new ImageGenerator(1024, 461);
      generator.generateImageWithRandomText();
      String imagePath = generator.getImageAbsolutePath();

      VetAddImageComponentObject addImageModal = container.openAddImageModal();
      addImageModal.uploadImage(imagePath);
      container.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME2);
      container.verifyImageAdded(imagePath);
    }

    // Save the form and navigate back to featured form
    LatestVideoAdminPageObject latestVideoAdminPageObject = featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);

    featuredVideoAdminObject = latestVideoAdminPageObject.clickFeaturedTab(driver);
    FeaturedVideoContainer firstContainer = featuredVideoAdminObject.getVideoContainers().stream().findFirst().get();
    firstContainer.scrollToContainer();
    firstContainer.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME2);

    // Add video 2 and make sure video title has changed
    VetAddVideoComponentObject vetAddingVideoModal = firstContainer.clickAddVideo();
    vetAddingVideoModal.addVideoWithoutDetailsByUrl(VideoContent.PREMIUM_VIDEO_URL);
    firstContainer.verifyVideoAdded(VideoContent.PREMIUM_VIDEO_NAME);
    featuredVideoAdminObject.clickSaveFeaturedVideoForm(driver);
  }
}
