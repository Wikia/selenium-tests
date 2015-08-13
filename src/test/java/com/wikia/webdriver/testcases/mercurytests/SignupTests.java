package com.wikia.webdriver.testcases.mercurytests;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.users.CreateUser;
import com.wikia.webdriver.common.users.TestUser;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.SignupPageObject;

/**
 * @ownership Social
 */
@Test(groups = {"MercurySignupTests", "Mercury"})
public class SignupTests extends NewTestTemplate {

  @Test(groups = "MercurySignupTest_001")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_001_successfulSignup() {
    signUp(new CreateUser().create()).verifyAvatarAfterSignup();
  }

  @Test(groups = "MercurySignupTest_002")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_002_signupErrorEmailInUse() {
    signUp(new CreateUser().withEmail("qaart001@gmail.com").create()).verifyEmailInUseError();
  }

  @Test(groups = "MercurySignupTest_003")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_003_signupErrorUsernameTaken() {
    String userNameTaken = "bekcunning";

    signUp(new CreateUser().withName(userNameTaken).create()).verifyUsernameTakenError();
  }

  @Test(groups = "MercurySignupTest_004")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_004_signupErrorBadPassword() {
    signUp(new CreateUser().withPass("WRONG PASS").create()).verifyPasswordError();
  }

  @Test(groups = "MercurySignupTest_005")
  @Execute(onWikia = "mobileregressiontesting")
  public void MercurySignupTest_005_signupErrorTooYoungUser() {
    DateTime wrongBirthDate = new DateTime(2009, 12, 12, 12, 0, 0);

    signUp(new CreateUser().withBirthday(wrongBirthDate).create()).verifyBirthdateError();
  }

  @Test(groups = "MercurySignupTest_006")
  @Execute(onWikia = "ja.ja-test")
  public void MercurySignupTest_006_japaneseUserSignup() {
    String japanName = "ユーザー" + DateTime.now().getMillis();
    String japanPAssword = "ユーザー" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(japanName).withPass(japanPAssword).create())
        .verifyAvatarAfterSignup();
  }

  private SignupPageObject signUp(TestUser user) {
    return new SignupPageObject(driver).openMobileSignupPage(wikiURL).signUp(user.getUserName(),
        user.getPassword(), user.getEmail(), user.getBirthdate());
  }
}
