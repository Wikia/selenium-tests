/**
 *
 */
package com.wikia.webdriver.testcases.followingtests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialFollowPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVideosPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.testng.annotations.Test;

public class FollowVideosTests extends NewTestTemplate {

  String videoName;

  @Test(groups = "FollowVideo")
  @Execute(asUser = User.USER)
  public void FollowVideo_001_setup() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialVideosPageObject special = base.openSpecialVideoPage(wikiURL);
    WatchPageObject watch = special.unfollowVideo(wikiURL, special.getRandomVideo());
    watch.confirmWatchUnwatch();
    special.verifyPageUnfollowed();
    videoName = special.getHeaderText();
  }

  @Test(groups = "FollowVideo", dependsOnMethods = {"FollowVideo_001_setup"})
  @Execute(asUser = User.USER)
  public void FollowVideo_002_follow() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    FilePagePageObject file = base.openFilePage(wikiURL, videoName);
    file.follow();
  }

  @Test(groups = {"FollowVideo", "Follow"}, dependsOnMethods = {"FollowVideo_002_follow"})
  @Execute(asUser = User.USER)
  public void FollowVideo_003_verify() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    SpecialFollowPageObject follow = new SpecialFollowPageObject(driver, wikiURL);
    follow.verifyFollowedImageVideo(videoName);
  }

}
