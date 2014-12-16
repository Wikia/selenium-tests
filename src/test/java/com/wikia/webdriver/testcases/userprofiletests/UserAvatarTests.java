package com.wikia.webdriver.testcases.userprofiletests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.signup.UserProfilePageObject;

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
		avatar.uploadAvatar(PageContent.FILE);
		avatar.saveProfile();
		profile.verifyAvatar(credentials.userNameStaffId);
		String avatarURL = profile.getAvatarUrl();
		profile.verifyURLStatus(200, avatarURL);
	}

	@Test(
			groups = {"AvatarTest", "AvatarTest_002"},
			dependsOnMethods = "AvatarTest_001_uploadAvatar"
	)
	public void AvatarTest_002_removeAvatar_QAART_477() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		UserProfilePageObject profile = base.openProfilePage(credentials.userNameStaff, wikiURL);
		profile.clickRemoveAvatar();
		profile.verifyAvatar(URLsContent.AVATAR_GENERIC);
	}
}
