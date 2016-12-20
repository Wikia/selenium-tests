package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ImageReview;

import org.testng.annotations.Test;

@Test(groups = "ImageReview")
public class ImageReviewTests extends NewTestTemplate {

  private final int STAFF_NAVBAR_BUTTONS_NUMBER = 2;
  private final int STAFF_ACTION_BUTTONS_NUMBER = 4;
  private final String ANON_ALERT_MESSAGE = "Unable to load image review\n×";
  private final String UNREVIEWED_VIEW_MESSAGE = "UNREVIEWED";

  @Execute(asUser = User.ANONYMOUS)
  @Test(groups = "anonCannotEnterImageReview")
  public void anonCannotEnterImageReview() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isAlertNotificationVisible());
    Assertion.assertEquals(imageReviewPage.getAlertNotificationText(), ANON_ALERT_MESSAGE);
  }

  @Execute(asUser = User.USER)
  @Test(groups = "regularUserCannotEnterImageReview")
  public void regularUserCannotEnterImageReview() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isAlertNotificationVisible());
    Assertion.assertEquals(imageReviewPage.getAlertNotificationText(), ANON_ALERT_MESSAGE);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = "verifyImageReviewElementsForStaffUser")
  public void verifyImageReviewElementsForStaffUser() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isTitleVisible());
    Assertion.assertTrue(imageReviewPage.isTopMessageVisible());
    Assertion.assertTrue(imageReviewPage.isContextLinkButtonInModalVisible());

    Assertion.assertTrue(imageReviewPage.areNavbarButtonsClickable());
    Assertion.assertTrue(imageReviewPage.areButtonsForImageClickable());

    Assertion.assertEquals(imageReviewPage.getNavbarButtonsNumber(), STAFF_NAVBAR_BUTTONS_NUMBER);
    Assertion.assertEquals(imageReviewPage.getActionButtonsNumber(), STAFF_ACTION_BUTTONS_NUMBER);
    Assertion.assertEquals(imageReviewPage.getCurrentStatusText(), UNREVIEWED_VIEW_MESSAGE);
    Assertion.assertTrue(imageReviewPage.getImagesToReviewNumber() > 0);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = "verifyImageInformationModalElements")
  public void verifyImageInformationModalElements() {
    ImageReview imageReviewPage = new ImageReview().open().clickImage(0);

    Assertion.assertTrue(imageReviewPage.isImageInfoInModalVisible());
    Assertion.assertTrue(imageReviewPage.isImageInModalVisible());
    Assertion.assertTrue(imageReviewPage.isContextLinkInModalVisible());
    Assertion.assertTrue(imageReviewPage.isImageHistoryInModalVisible());
  }

}
