package com.wikia.webdriver.testcases.mercurytests.old;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.common.users.CreateUser;
import com.wikia.webdriver.common.users.TestUser;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.SignupPageObject;
import org.joda.time.DateTime;
import org.testng.annotations.Test;
@Test(groups = "Mercury_Signup")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SignupTests extends NewTestTemplate {

  private void init() {
    new Navigate().toPage(MercurySubpages.MAIN_PAGE);
  }

  @Test(groups = "MercurySignupTest_001")
  public void MercurySignupTest_001_successfulSignup() {
    init();
    signUp(new CreateUser().create()).verifyAvatarAfterSignup();
  }

  @Test(groups = "MercurySignupTest_002")
  public void anonCanSignUpWithEmailAlreadyInUse() {
    init();
    signUp(new CreateUser().withEmail("qaart001@gmail.com").create()).verifyAvatarAfterSignup();
  }

  @Test(groups = "MercurySignupTest_003")
  public void MercurySignupTest_003_signupErrorUsernameTaken() {
    init();
    String userNameTaken = "bekcunning";

    signUp(new CreateUser().withName(userNameTaken).create()).verifyUsernameTakenError();
  }

  @Test(groups = "MercurySignupTest_004")
  public void MercurySignupTest_004_signupErrorBadPassword() {
    init();
    String random = "User" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(random).withPass(random).create()).verifyPasswordError();
  }

  @Test(groups = "MercurySignupTest_005")
  public void MercurySignupTest_005_signupErrorTooYoungUser() {
    init();
    DateTime wrongBirthDate = new DateTime(2009, 12, 12, 12, 0, 0);

    signUp(new CreateUser().withBirthday(wrongBirthDate).create()).verifyBirthdateError();
  }

  @Test(groups = "MercurySignupTest_006")
  @Execute(onWikia = "ja.ja-test")
  public void MercurySignupTest_006_japaneseUserSignup() {
    init();
    String japanName = "ユーザー" + DateTime.now().getMillis();
    String japanPAssword = "ユーザザー" + DateTime.now().getMillis();

    signUp(new CreateUser().withName(japanName).withPass(japanPAssword).create())
        .verifyAvatarAfterSignup();
  }

  private SignupPageObject signUp(TestUser user) {
    return new SignupPageObject(driver).openMobileSignupPage().signUp(user.getUserName(),
        user.getPassword(), user.getEmail(), user.getBirthdate());
  }
}
