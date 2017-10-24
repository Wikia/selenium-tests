package com.wikia.webdriver.testcases.userprofile;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.globalnav.GlobalNavigation;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

import org.testng.annotations.Test;

/**
 * Test uploadAvatar 1. Open user profile page User:Username and add avatar 2. Verify that avatar
 * appeared on user page, and on global navigation
 *
 * Test clickAvatar 1. Open wikia page and click user avatar on global navigation 2. Make sure you
 * were redirected to User page
 *
 * Test removeAvatar 1. Open user profile page User:Username and remove avatar 2. Verify that avatar
 * was removed from user page, and placeholder appeared on global navigation 3. Log out and verify
 * that avatar is not visible on global navigation
 */
@Test(groups = "userProfile-userAvatar")
@Execute(onWikia = "sustainingtestchat")
public class UserAvatar extends NewTestTemplate {

  @Test(groups = "UserAvatar_clickOnAvatarOpensUserMenu")
  @Execute(asUser = User.SUS_STAFF2)
  public void clickOnAvatarOpensUserMenu() {
    new SpecialVersionPage().open();

    GlobalNavigation userAvatar = new GlobalNavigation();
    userAvatar.clickUserAvatar();

    Assertion.assertTrue(userAvatar.isUserMenuOpened());
  }

  @Test(groups = "UserAvatar_userCanEnterHisProfileFromUserMenu")
  @Execute(asUser = User.SUS_STAFF2)
  public void userCanEnterHisProfileFromUserMenu(){
    new SpecialVersionPage().open();

    GlobalNavigation userAvatar = new GlobalNavigation();
    userAvatar.clickUserAvatar().clickViewProfile();

    UserProfilePage profile = new UserProfilePage();
    profile.verifyProfilePage(User.SUS_STAFF2.getUserName());
  }

  @Test(groups = "UserAvatar_staffUserCanUploadAvatar")
  @Execute(asUser = User.SUS_STAFF2)
  public void staffUserCanUploadAvatar() {
    UserProfilePage
        profile =
        new UserProfilePage().open(User.SUS_STAFF2.getUserName());
    AvatarComponentObject avatar = profile.clickEditAvatar();
    profile.verifyAvatar();
    String avatarUrl = profile.getAvatarImageSrc();
    avatar.uploadAvatar(PageContent.FILE);
    avatar.saveProfile();
    profile.verifyAvatarChanged(avatarUrl);
    String changedAvatarUrl = profile.getAvatarImageSrc();
    profile.verifyAvatarVisible();
    Assertion.assertNotEquals(changedAvatarUrl, avatarUrl);
    profile.verifyURLStatus(200, changedAvatarUrl);
  }


  @Test(groups = "UserAvatar_staffUserCanRemoveAvatar", dependsOnMethods = "staffUserCanUploadAvatar")
  @Execute(asUser = User.SUS_STAFF2)
  public void staffUserCanRemoveAvatar() {
    UserProfilePage profile = new UserProfilePage().open(
        User.SUS_STAFF2.getUserName());
    String avatarUrl = profile.getAvatarImageSrc();
    profile.clickRemoveAvatar();
    profile.verifyAvatar();

    profile.openWikiPage(); //user needs to visit other page to get avatar refreshed
    UserProfilePage changedProfile = new UserProfilePage().open(
        User.SUS_STAFF2.getUserName());

    changedProfile.verifyAvatarChanged(avatarUrl);
    String changedAvatarUrl = changedProfile.getAvatarImageSrc();
    changedProfile.verifyAvatarVisible();
    Assertion.assertNotEquals(changedAvatarUrl, avatarUrl);
    changedProfile.verifyURLStatus(200, changedAvatarUrl);
  }
}
