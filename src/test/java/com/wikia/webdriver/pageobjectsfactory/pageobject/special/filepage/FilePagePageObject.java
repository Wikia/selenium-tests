package com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs.VisualEditorAddMediaDialog.ImageLicense;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * @author liz_lux
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 */

public class FilePagePageObject extends WikiBasePageObject {

  public static final int ABOUT_TAB = 0;
  public static final int HISTORY_TAB = 1;
  public static final int METADATA_TAB = 2;

  @FindBys(@FindBy(css = "ul.tabs li a"))
  private List<WebElement> tabList;
  @FindBy(css = "section[data-listing-type='local'] h3.page-listing-title a")
  private WebElement appearsListing;
  @FindBy(css = "section[data-listing-type='local'] div.page-list-pagination img.right")
  private WebElement localPageNext;
  @FindBy(css = "section[data-listing-type='local'] div.page-list-pagination img.left")
  private WebElement localPagePrev;
  @FindBy(css = ".fullImageLink")
  private WebElement fileEmbedded;
  @FindBy(css = ".filehistory .video-thumb")
  private WebElement videoThumbnail;
  @FindBys(@FindBy(css = ".tabs li"))
  private List<WebElement> tabs;
  @FindBy(css = ".video-provider a")
  private WebElement provider;
  @FindBy(css = "div#mw-imagepage-nofile")
  private WebElement noFileText;
  @FindBy(css = "li#mw-imagepage-reupload-link a")
  private WebElement reuploadLink;
  @FindBy(css = "#wpWikiaVideoAddUrl")
  private WebElement uploadFileURL;
  @FindBy(css = "div.submits input")
  private WebElement addButton;
  @FindBys(@FindBy(css = "table.filehistory tr td:nth-child(1)>a"))
  private List<WebElement> historyDeleteLinks;
  @FindBy(css = ".boilerplate b")
  private WebElement imgLicensePlate;
  @FindBy(css = ".tabBody.selected")

  private WebElement tabBody;

  public FilePagePageObject(WebDriver driver) {
    super(driver);
  }

  public FilePagePageObject(WebDriver driver, String fileName) {
    super(driver);
  }

  public void clickTab(int tab) {
    WebElement currentTab = tabList.get(tab);
    wait.forElementVisible(currentTab);
    scrollAndClick(currentTab);
    LOG.logResult(
        "clickTab",
        tab + " selected",
        true
    );
  }

  public void selectAboutTab() {
    clickTab(ABOUT_TAB);
  }

  public void selectHistoryTab() {
    clickTab(HISTORY_TAB);
  }

  public void selectMetadataTab() {
    clickTab(METADATA_TAB);
  }

  public void verifySelectedTab(String tabName) {
    wait.forElementVisible(tabBody);
    Assertion.assertEquals(tabBody.getAttribute("data-tab-body"), tabName);
    LOG.logResult(
        "verified selected tab",
        tabName + " selected",
        true
    );
  }

  public void refreshAndVerifyTabs(int tab) {

    String tabName;

    if (tab == ABOUT_TAB) {
      tabName = "about";
    } else if (tab == HISTORY_TAB) {
      tabName = "history";
    } else {
      tabName = "metadata";
    }

    clickTab(tab);
    verifySelectedTab(tabName);
    refreshPage();
    verifySelectedTab(tabName);
  }

  // Page forward in the local "appears on" section
  public void clickLocalAppearsPageNext() {
    localPageNext.click();
    LOG
        .logResult("clickLocalAppearsPageNext", "local appears page next button clicked", true);
  }

  // Page backward in the local "appears on" section
  public void clickLocalAppearsPagePrev() {
    localPagePrev.click();
    LOG
        .logResult("clickLocalAppearsPagePrev", "local appears page preview button clicked", true);
  }

  // Verify that a specific video title is in the "Appears on these pages" list
  public void verifyAppearsOn(String articleName) {
    Assertion.assertTrue(appearsListing.getText().equals(articleName));
  }

  public void verifyEmbeddedVideoIsPresent() {
    wait.forElementVisible(fileEmbedded);
    LOG
        .logResult("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
  }

  public void verifyEmptyFilePage() {
    wait.forElementVisible(noFileText);
    LOG
        .logResult("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible", true);
  }

  public void verifyThumbnailIsPresent() {
    wait.forElementVisible(videoThumbnail);
    LOG.log("verifythumbnailIsPresent", "Verified thumbnail is visible", LOG.Type.SUCCESS);
  }

  public String getImageUrl() {
    return fileEmbedded.findElement(By.cssSelector("a")).getAttribute("href");
  }

  public String getImageThumbnailUrl() {
    return fileEmbedded.findElement(By.cssSelector("img")).getAttribute("src");
  }

  public void verifyTabsExistVideo() {
    String[] expectedTabs = {"about", "history", "metadata"};
    Assertion.assertEquals(expectedTabs.length, tabs.size());
    verifyTabsExist(expectedTabs);
  }

  public void verifyTabsExistImage() {
    String[] expectedTabs = {"about", "history"};
    Assertion.assertTrue(expectedTabs.length <= tabs.size());
    verifyTabsExist(expectedTabs);
  }

  public void verifyTabsExist(String[] expectedTabs) {
    for (int i = 0; i < expectedTabs.length; i++) {
      String tab = tabs.get(i).getAttribute("data-tab");
      Assertion.assertEquals(tab, expectedTabs[i]);
    }
  }

  public void verifyVideoAutoplay(boolean status) {
    String providerName = provider.getText().toLowerCase();
    VideoComponentObject video = new VideoComponentObject(driver, fileEmbedded);
    video.verifyVideoAutoplay(providerName, status);
  }

  public void replaceVideo(String url) {
    wait.forElementVisible(reuploadLink);
    scrollAndClick(reuploadLink);

    uploadFileURL.sendKeys(url);
    LOG.log("replaceVideo", url + " typed into url field", LOG.Type.SUCCESS);

    wait.forElementVisible(addButton);
    scrollAndClick(addButton);
    LOG.log("replaceVideo", "add url button clicked", true, driver);
  }

  public void verifyVersionCountAtLeast(int count) {
    Assertion.assertTrue(historyDeleteLinks.size() >= count, "Version count is at least " + count);
  }

  public DeletePageObject deleteVersion(int num) {
    scrollAndClick(historyDeleteLinks.get(num - 1));

    LOG.log("deletePage", "delete page opened", LOG.Type.SUCCESS);

    return new DeletePageObject(driver);
  }

  public void verifyImageLicense(ImageLicense imageLicense) {
    Assertion.assertStringContains(
        imgLicensePlate.getText(), imageLicense.getText()
    );
  }
}
