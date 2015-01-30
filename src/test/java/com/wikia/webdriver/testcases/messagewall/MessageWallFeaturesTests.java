/**
 *
 */
package com.wikia.webdriver.testcases.messagewall;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWallAddLinkComponentObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak <p/> 1. Write message in source mode, 2. Write message in bold
 *         font, 3. Write message in italic font, 4. Write message with internal link, 5. Write
 *         message with external link, 6. Write message with image,
 */
public class MessageWallFeaturesTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  @Test(groups = {"MessageWallFeatures_001", "MessageWallFeatures"})
  public void MessageWallFeatures_001_sourceMode() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickSourceModeButton();
    wall.writeSourceMode(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_002", "MessageWallFeatures"})
  public void MessageWallFeatures_002_boldMode() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickBoldButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageBoldText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_003", "MessageWallFeatures"})
  public void MessageWallFeatures_003_italicMode() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.clickItalicButton();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageItalicText(title, message, credentials.userName);
  }

  @Test(groups = {"MessageWallFeatures_004", "MessageWallFeatures"})
  public void MessageWallFeatures_004_image() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
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

  @Test(groups = {"MessageWallFeatures_005", "MessageWallFeatures"})
  public void MessageWallFeatures_005_internalLink() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.writeTitle(title);
    wall.triggerMessageArea();
    NewMessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addInternalLink(PageContent.REDIRECT_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyInternalLink(title, PageContent.REDIRECT_LINK, PageContent.TEXT_LINK, wikiURL);
  }

  @Test(groups = {"MessageWallFeatures_006", "MessageWallFeatures"})
  public void MessageWallFeatures_006_externalLink() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName, credentials.password, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName, wikiURL);
    String title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    wall.writeTitle(title);
    wall.triggerMessageArea();
    NewMessageWallAddLinkComponentObject addLink = wall.clickLinkButton();
    addLink.addExternalLink(PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK);
    wall.submit();
    wall.verifyExternalLink(title, PageContent.EXTERNAL_LINK, PageContent.TEXT_LINK, wikiURL);
  }
}
