package com.wikia.webdriver.testcases.mobilewikitests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.ImageReview;

import org.testng.annotations.Test;

@Test(groups = "ImageReview")
public class ImageReviewTests extends NewTestTemplate {

  private static final int STAFF_NAVBAR_BUTTONS_NUMBER = 2;
  private static final int REVIEWER_NAVBAR_BUTTONS_NUMBER = 1;
  private static final int STAFF_ACTION_BUTTONS_NUMBER = 4;
  private static final int REVIEWER_ACTION_BUTTONS_NUMBER = 1;
  private static final String ANON_ALERT_MESSAGE = "Unable to load image review\n×";
  private static final String UNREVIEWED_VIEW_MESSAGE = "UNREVIEWED";

  @Execute(asUser = User.ANONYMOUS)
  @Test(groups = "anonCannotEnterImageReview")
  public void anonCannotEnterImageReview() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isAlertNotificationDisplayed());
    Assertion.assertEquals(imageReviewPage.getAlertNotificationText(), ANON_ALERT_MESSAGE);
  }

  @Execute(asUser = User.USER)
  @Test(groups = "regularUserCannotEnterImageReview")
  public void regularUserCannotEnterImageReview() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isAlertNotificationDisplayed());
    Assertion.assertEquals(imageReviewPage.getAlertNotificationText(), ANON_ALERT_MESSAGE);
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = "verifyImageReviewElementsForStaffUser")
  public void verifyImageReviewElementsForStaffUser() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isTitleDisplayed());
    Assertion.assertTrue(imageReviewPage.isTopMessageDisplayed());
    Assertion.assertTrue(imageReviewPage.isContextLinkButtonInModalDisplayed());

    Assertion.assertEquals(imageReviewPage.getNavbarButtonsNumber(), STAFF_NAVBAR_BUTTONS_NUMBER);
    Assertion.assertEquals(imageReviewPage.getActionButtonsNumber(), STAFF_ACTION_BUTTONS_NUMBER);
    Assertion.assertEquals(imageReviewPage.getCurrentStatusText(), UNREVIEWED_VIEW_MESSAGE);

    Assertion.assertTrue(imageReviewPage.areNavbarButtonsClickable());
  }

  @Execute(asUser = User.IMAGE_REVIEWER)
  @Test(groups = "verifyImageReviewElementsForImageReviewerUser")
  public void verifyImageReviewElementsForImageReviewerUser() {
    ImageReview imageReviewPage = new ImageReview().open();

    Assertion.assertTrue(imageReviewPage.isTitleDisplayed());
    Assertion.assertTrue(imageReviewPage.isTopMessageDisplayed());
    Assertion.assertTrue(imageReviewPage.isContextLinkButtonInModalDisplayed());

    Assertion.assertEquals(imageReviewPage.getNavbarButtonsNumber(), REVIEWER_NAVBAR_BUTTONS_NUMBER);
    Assertion.assertEquals(imageReviewPage.getActionButtonsNumber(), REVIEWER_ACTION_BUTTONS_NUMBER);

    Assertion.assertTrue(imageReviewPage.areNavbarButtonsClickable());
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = "verifySummaryBackButtonForStaffUser")
  public void verifySummaryBackButtonForStaffUser() {
    ImageReview imageReviewPage = new ImageReview().open();
    String imageReviewURL = imageReviewPage.getCurrentUrl();

    ImageReview summaryPage = imageReviewPage.clickShowSummaryButton();
    Assertion.assertTrue(summaryPage.isSummaryDialogDisplayed());
    Assertion.assertNotEquals(imageReviewURL, summaryPage.getCurrentUrl());
    Assertion.assertTrue(summaryPage.isBackButtonDisplayed());

    imageReviewPage = summaryPage.clickBackButton();
    Assertion.assertTrue(imageReviewPage.isTopMessageDisplayed());
    Assertion.assertEquals(imageReviewURL, imageReviewPage.getCurrentUrl());
  }

  @Execute(asUser = User.STAFF)
  @Test(groups = "verifySummaryPageElementsForStaffUser")
  public void verifySummaryPageElementsForStaffUser() {
    ImageReview summaryPage = new ImageReview().open().clickShowSummaryButton();

    Assertion.assertTrue(summaryPage.isSummaryDialogDisplayed());
    Assertion.assertTrue(summaryPage.isBackButtonDisplayed());
    Assertion.assertTrue(summaryPage.isShowStatisticsButtonDisplayed());
    Assertion.assertTrue(summaryPage.isStatisticsDateInputDisplayed(0));
    Assertion.assertTrue(summaryPage.isStatisticsDateInputDisplayed(1));
    Assertion.assertTrue(summaryPage.isDownloadCsvDisplayed());
    Assertion.assertTrue(summaryPage.isShowHistoryInputDisplayed());
    Assertion.assertTrue(summaryPage.isShowHistoryButtonDisplayed());
  }

}
