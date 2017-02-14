/**
 *
 */
package com.wikia.webdriver.testcases.messagewall;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWallAddLinkComponentObject;

import org.testng.annotations.Test;

public class MessageWallFeaturesTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  @Test(groups = {"MessageWallFeatures_001", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanWriteMessageInSourceMode() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickSourceModeButton();
    wall.writeSourceMode(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_002", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanWriteMessageInBold() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageBoldText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_003", "MessageWallFeatures", "MessageWallTests"}, invocationCount = 10)
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "SOC-2094", comment = "test should pass locally. The ticket is about instability")
  public void userCanWriteMessageInItallic() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickItalicButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageItalicText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_004", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanWriteMessageWithImage() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.writeTitle(title);
    wall.triggerMessageArea();
    PhotoAddComponentObject photoAddPhoto = wall.clickImageButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    wall.submit();
    wall.verifyImageAdded(title);
  }

  @Test(groups = {"MessageWallFeatures_005", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanWriteMessageWithInternalLink() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.writeTitle(title);
    wall.triggerMessageArea();
    MessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addInternalLink(PageContent.REDIRECT_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyInternalLink(title, PageContent.REDIRECT_LINK, PageContent.TEXT_LINK, wikiURL);
  }

  @Test(groups = {"MessageWallFeatures_006", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER)
  public void userCanWriteMessageWithExternalLink() {
    MessageWall wall = new MessageWall(driver).open(credentials.userName);
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.writeTitle(title);
    wall.triggerMessageArea();
    MessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addExternalLink(PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyExternalLink(title, PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK, wikiURL);
  }
}
