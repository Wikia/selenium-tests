package com.wikia.webdriver.testcases.imageservingtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.driverprovider.UseUnstablePageLoadStrategy;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.RenamePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialNewFilesPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialRestorePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak
 *         <p/>
 *         1. Delete image, verify 404 status, restore image, verify 200 status 2. Move image,
 *         verify status
 */
public class ImageStorageTests extends NewTestTemplate {

  String imageURL;
  String imageThumbnailURL;

  @Test(groups = {"ImageStorageTests", "ImageStorage_001"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.USER_2)
  public void ImageStorage_001_deleteImage() {
    SpecialNewFilesPageObject filesPage =
        new SpecialNewFilesPageObject(driver).openSpecialNewFiles(wikiURL);
    filesPage.addPhoto();
    filesPage.selectFileToUpload(PageContent.FILE);
    String fileName = DateTime.now().getMillis() + PageContent.FILE;
    filesPage.clickOnMoreOrFewerOptions();
    filesPage.setFileName(fileName);
    filesPage.checkIgnoreAnyWarnings();
    filesPage.clickUploadButton();
    filesPage.verifyFileUploaded(fileName);

    FilePagePageObject file =
        new SpecialNewFilesPageObject(driver).openFilePage(wikiURL, fileName, true);
    imageURL = file.getImageUrl();
    imageThumbnailURL = file.getImageThumbnailUrl();
    file.verifyURLStatus(200, imageURL);
    file.verifyURLStatus(200, imageThumbnailURL);

    file.loginAs(User.STAFF);

    DeletePageObject delete = file.deletePage();
    delete.submitDeletion();
    filesPage.verifyNotificationMessage();

    filesPage.verifyURLStatus(404, imageURL);
    filesPage.verifyURLStatus(404, imageThumbnailURL);

    SpecialRestorePageObject restore = delete.undeleteByFlashMessage();
    restore.giveReason(PageContent.CAPTION);
    restore.restorePage();
    restore.verifyNotificationMessage();

    file.verifyURLStatus(200, imageURL);
    file.verifyURLStatus(200, imageThumbnailURL);

    file.deletePage();
    delete.submitDeletion();
  }

  @Test(groups = {"ImageStorageTests", "ImageStorage_002"})
  @UseUnstablePageLoadStrategy
  @Execute(asUser = User.STAFF)
  public void ImageStorage_002_moveImage() {
    SpecialNewFilesPageObject filesPage =
        new SpecialNewFilesPageObject(driver).openSpecialNewFiles(wikiURL);
    filesPage.addPhoto();
    filesPage.selectFileToUpload(PageContent.FILE);
    String fileName = DateTime.now().getMillis() + PageContent.FILE;
    filesPage.clickOnMoreOrFewerOptions();
    filesPage.setFileName(fileName);
    filesPage.checkIgnoreAnyWarnings();
    filesPage.clickUploadButton();
    filesPage.verifyFileUploaded(fileName);

    FilePagePageObject file = filesPage.openFilePage(wikiURL, fileName, true);
    RenamePageObject renamePage = file.renameUsingDropdown();
    String imageNewName = DateTime.now().getMillis() + PageContent.FILERENAME;
    renamePage.rename(imageNewName, true);
    file.verifyNotificationMessage();
    file.verifyHeader(imageNewName);
    file = filesPage.openFilePage(wikiURL, imageNewName, true);
    renamePage = file.renameUsingDropdown();
    renamePage.rename(fileName, true);
    file.verifyNotificationMessage();
    file.verifyHeader(fileName);

    DeletePageObject delete = file.deletePage();
    delete.submitDeletion();
  }
}
