package com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;

public class FilePagePageObject extends WikiBasePageObject {

  public static final int ABOUT_TAB = 0;
  public static final int HISTORY_TAB = 1;
  public static final int METADATA_TAB = 2;

  @FindBys(@FindBy(css = "ul.tabs li a"))
  private List<WebElement> tabList;
  @FindBy(css = ".fullImageLink")
  private WebElement fileEmbedded;
  @FindBys(@FindBy(css = ".tabs li"))
  private List<WebElement> tabs;
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

  public FilePagePageObject open(String fileName, boolean noRedirect) {
    String url =
        urlBuilder.getUrlForWiki() + URLsContent.WIKI_DIR + URLsContent.FILE_NAMESPACE + fileName;
    if (noRedirect) {
      url = urlBuilder.appendQueryStringToURL(url, "redirect=no");
    }
    getUrl(url);

    return this;
  }

  public FilePagePageObject open(String fileName) {
    return open(fileName, false);
  }

  public void clickTab(int tab) {
    WebElement currentTab = tabList.get(tab);
    wait.forElementVisible(currentTab);
    scrollAndClick(currentTab);
    PageObjectLogging.log("clickTab", tab + " selected", true);
  }

  public void selectHistoryTab() {
    clickTab(HISTORY_TAB);
  }

  public void verifySelectedTab(String tabName) {
    wait.forElementVisible(tabBody);
    Assertion.assertEquals(tabBody.getAttribute("data-tab-body"), tabName);
    PageObjectLogging.log("verified selected tab", tabName + " selected", true);
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

  public void verifyEmbeddedVideoIsPresent() {
    wait.forElementVisible(fileEmbedded);
    PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible",
        true);
  }

  public void verifyEmptyFilePage() {
    wait.forElementVisible(noFileText);
    PageObjectLogging.log("verifyEmbeddedVideoIsPresent", "Verified embedded video is visible",
        true);
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

  public void replaceVideo(String url) {
    wait.forElementVisible(reuploadLink);
    scrollAndClick(reuploadLink);

    uploadFileURL.sendKeys(url);
    PageObjectLogging.log("replaceVideo", url + " typed into url field", true);

    wait.forElementVisible(addButton);
    scrollAndClick(addButton);
    PageObjectLogging.log("replaceVideo", "add url button clicked", true, driver);
  }

  public DeletePageObject deleteVersion(int num) {
    scrollAndClick(historyDeleteLinks.get(num - 1));

    PageObjectLogging.log("deletePage", "delete page opened", true);

    return new DeletePageObject(driver);
  }
}
