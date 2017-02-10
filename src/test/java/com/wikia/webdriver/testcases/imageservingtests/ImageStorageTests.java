package com.wikia.webdriver.testcases.imageservingtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePage;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

public class ImageStorageTests extends NewTestTemplate {

  String imageURL;
  String imageThumbnailURL;

  @Test(groups = {"ImageStorageTests", "ImageStorage_001"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.USER_2)
  public void ImageStorage_001_deleteImage() {
    SpecialNewFilesPage filesPage =
        new SpecialNewFilesPage().openSpecialNewFiles(wikiURL);
    filesPage.addPhoto();
    filesPage.selectFileToUpload(PageContent.FILE);
    String fileName = DateTime.now().getMillis() + PageContent.FILE;
    filesPage.clickOnMoreOptions();
    filesPage.setFileName(fileName);
    filesPage.checkIgnoreAnyWarnings();
    filesPage.clickUploadButton();
    filesPage.verifyFileUploaded(fileName);

    FilePage file = new FilePage().open(fileName, true);
    imageURL = file.getImageUrl();
    imageThumbnailURL = file.getImageThumbnailUrl();
    file.verifyURLStatus(200, imageURL);
    file.verifyURLStatus(200, imageThumbnailURL);

    file.loginAs(User.STAFF);

    DeletePageObject delete = file.deletePage();
    delete.submitDeletion();
    Assertion.assertTrue(filesPage.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");

    filesPage.verifyURLStatus(404, imageURL);
    filesPage.verifyURLStatus(404, imageThumbnailURL);

    SpecialRestorePageObject restore =
        delete.getBannerNotifications().clickUndeleteLinkInBannerNotification();
    restore.giveReason(PageContent.CAPTION);
    restore.restorePage();
    Assertion.assertTrue(restore.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");

    file.verifyURLStatus(200, imageURL);
    file.verifyURLStatus(200, imageThumbnailURL);

    file.deletePage();
    delete.submitDeletion();
  }

  @Test(groups = {"ImageStorageTests", "ImageStorage_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  @RelatedIssue(issueID = "QAART-921")
  public void ImageStorage_002_moveImage() {
    String fileName = DateTime.now().getMillis() + PageContent.FILE;

    new SpecialNewFilesPage()
        .openSpecialNewFiles(wikiURL)
        .addPhoto()
        .selectFileToUpload(PageContent.FILE)
        .hideWarnings()
        .clickOnMoreOptions()
        .setFileName(fileName)
        .checkIgnoreAnyWarnings()
        .clickUploadButton()
        .verifyFileUploaded(fileName);

    FilePage file = new FilePage().open(fileName, true);
    RenamePageObject renamePage = file.renameUsingDropdown();

    String imageNewName = DateTime.now().getMillis() + PageContent.FILERENAME;
    renamePage.rename(imageNewName, true);

    Assertion.assertTrue(file.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");
    file.verifyHeader(imageNewName);
    file = new FilePage().open(imageNewName, true);
    renamePage = file.renameUsingDropdown();
    renamePage.rename(fileName, true);

    Assertion.assertTrue(file.getBannerNotifications().isNotificationMessageVisible(),
                         "Banner notification message is not visible");
    file.verifyHeader(fileName);

    DeletePageObject delete = file.deletePage();
    delete.submitDeletion();
  }
}
