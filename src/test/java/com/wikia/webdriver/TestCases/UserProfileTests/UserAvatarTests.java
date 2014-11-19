package com.wikia.webdriver.TestCases.UserProfileTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditProfile.AvatarComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp.UserProfilePageObject;

/**
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class UserAvatarTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(
			groups = {"AvatarTest", "AvatarTest_001"}
	)
	public void AvatarTest_001_uploadAvatar() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject profile = base.openProfilePage(credentials.userNameStaff, wikiURL);
		AvatarComponentObject avatar = profile.clickEditAvatar();
		avatar.uploadAvatar(PageContent.file);
		avatar.saveProfile();
		profile.verifyAvatar(credentials.userNameStaffId);
		String avatarURL = profile.getAvatarUrl();
		profile.verifyURLStatus(200, avatarURL);
	}

	@Test(
			groups = {"AvatarTest", "AvatarTest_002"},
			dependsOnMethods = "AvatarTest_001_uploadAvatar"
	)
	public void AvatarTest_002_removeAvatar() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject profile = base.openProfilePage(credentials.userNameStaff, wikiURL);
		profile.clickRemoveAvatar();
		profile.verifyAvatar(URLsContent.AVATAR_GENERIC);
	}
}
