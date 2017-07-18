package com.wikia.webdriver.testcases.messagewall;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWallAddLinkComponentObject;

@Execute(onWikia = "sustainingtest")
public class MessageWallFeaturesTests extends NewTestTemplate {

  @Test(groups = {"MessageWallFeatures_001", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageInSourceMode() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickSourceModeButton();
    wall.writeSourceMode(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, User.USER_MESSAGE_WALL.getUserName());
  }

  @Test(groups = {"MessageWallFeatures_002", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageInBold() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.setTitle(title);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.submit();
    wall.verifyMessageBoldText(title, message, User.USER_MESSAGE_WALL.getUserName());
  }

  @Test(groups = {"MessageWallFeatures_003", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageInItallic() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.setTitle(title);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    wall.clickItalicButton();
    mini.switchAndWrite(message);
    wall.submit();
    wall.verifyMessageItalicText(title, message, User.USER_MESSAGE_WALL.getUserName());
  }

  @Test(groups = {"MessageWallFeatures_004", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageWithImage() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.setTitle(title);
    wall.triggerMessageArea();
    PhotoAddComponentObject photoAddPhoto = wall.clickImageButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    wall.submit();
    wall.verifyImageAdded(title);
  }

  @Test(groups = {"MessageWallFeatures_005", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageWithInternalLink() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.setTitle(title);
    wall.triggerMessageArea();
    MessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addInternalLink(PageContent.REDIRECT_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyInternalLink(title, PageContent.REDIRECT_LINK, PageContent.TEXT_LINK, wikiURL);
  }

  @Test(groups = {"MessageWallFeatures_006", "MessageWallFeatures", "MessageWallTests"})
  @Execute(asUser = User.USER_MESSAGE_WALL)
  public void userCanWriteMessageWithExternalLink() {
    MessageWall wall = new MessageWall().open(User.USER_MESSAGE_WALL.getUserName());
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.setTitle(title);
    wall.triggerMessageArea();
    MessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addExternalLink(PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyExternalLink(title, PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK, wikiURL);
  }
}
