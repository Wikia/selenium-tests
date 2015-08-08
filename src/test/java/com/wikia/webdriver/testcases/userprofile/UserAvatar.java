package com.wikia.webdriver.testcases.userprofile;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialVersionPage;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 * @author Michal 'justnpT' Nowierski
 *
 *         Test uploadAvatar 1. Open user profile page User:Username and add avatar 2. Verify that
 *         avatar appeared on user page, and on global navigation
 *
 *         Test clickAvatar 1. Open wikia page and click user avatar on global navigation 2. Make
 *         sure you were redirected to User page
 *
 *         Test removeAvatar 1. Open user profile page User:Username and remove avatar 2. Verify
 *         that avatar was removed from user page, and placeholder appeared on global navigation 3.
 *         Log out and verify that avatar is not visible on global navigation
 */
@Test(groups = {"AvatarTest"})
public class UserAvatar extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"AvatarTest_001"})
  @Execute(asUser = User.STAFF)
  public void uploadAvatar() {
    UserProfilePageObject
        profile =
        new UserProfilePageObject(driver).openProfilePage(credentials.userNameStaff, wikiURL);
    AvatarComponentObject avatar = profile.clickEditAvatar();
    avatar.uploadAvatar(PageContent.FILE);
    avatar.saveProfile();
    profile.verifyAvatar(credentials.userNameStaffId);
    profile.verifyAvatarVisible();
    String avatarURL = profile.getAvatarUrl();
    profile.verifyURLStatus(200, avatarURL);
  }

  @Test(groups = {"AvatarTest_002"}, dependsOnMethods = "uploadAvatar")
  @Execute(asUser = User.STAFF)
  public void clickAvatar() {
    new SpecialVersionPage(driver).open();

    UserProfilePageObject profile = new UserProfilePageObject(driver).clickOnAvatar();
    profile.verifyProfilePage(credentials.userNameStaff);
  }

  @Test(groups = {"AvatarTest_003"}, dependsOnMethods = "uploadAvatar")
  @Execute(asUser = User.STAFF)
  public void removeAvatar() {
    UserProfilePageObject profile = new UserProfilePageObject(driver).openProfilePage(
        credentials.userNameStaff, wikiURL);
    profile.clickRemoveAvatar();
    profile.verifyAvatar(URLsContent.AVATAR_GENERIC);
    profile.verifyAvatarPlaceholder();
    profile.logOut(driver);
    profile.verifyAvatarNotPresent();
  }
}
