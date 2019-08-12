package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.desktop.components.notifications.Notification;
import com.wikia.webdriver.elements.communities.desktop.components.notifications.NotificationType;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.stream.Collectors;

public class ForumManageBoardsPageObject extends WikiBasePageObject {

  @FindBy(css = "#CreateNewBoardButton")
  private WebElement createBoardButton;
  @FindBy(css = "[name='boardTitle']")
  private WebElement boardTitleField;
  @FindBy(css = "[name='boardDescription']")
  private WebElement boardDescriptionField;
  @FindBy(css = ".submit")
  private WebElement boardSubmitButton;
  @FindBy(css = "[name='boardTitle']")
  private WebElement deleteBoardConfirmationField;
  @FindBy(css = "[name='destinationBoardId']")
  private WebElement mergeToBoard;
  @FindBy(css = ".submit")
  private WebElement deleteAndMergeButton;
  @FindBy(xpath = "//ul[@class='boards']/li//a")
  private WebElement firstForumLink;
  @FindBy(xpath = "//ul[@class='boards']//li[2]//a")
  private WebElement secondForumLink;

  private void openCreateNewBoardForm() {
    wait.forElementVisible(createBoardButton);
    createBoardButton.click();
    Log.log("openCreateNewBoardForm", "create new board form opened", true);
  }

  private void typeBoardTitle(String title) {
    wait.forElementVisible(boardTitleField);
    boardTitleField.sendKeys(title);
    Log.log("typeBoardTitle", "board title: '" + title + "' typed in", true);
  }

  private void typeBoradDescription(String description) {
    wait.forElementVisible(boardDescriptionField);
    boardDescriptionField.sendKeys(description);
    Log.log("typeBoardDescription", "board description: '" + description + "' typed in", true);
  }

  private void submitNewBoard() {
    wait.forElementVisible(boardSubmitButton);
    scrollAndClick(boardSubmitButton);
    Log.log("submitNewBoard", "new board submitted", true);
  }

  public void createNewBoard(String title, String description) {
    openCreateNewBoardForm();
    typeBoardTitle(title);
    typeBoradDescription(description);
    submitNewBoard();
  }

  public boolean verifyBoardCreated(String title, String description) {
    wait.forElementVisible(By.xpath("//ul/li//a[contains(text(), '" + title.replaceAll("_", " ")
                                    + "')]/../../../p[contains(text(), '" + description + "')]"));
    return true;
  }

  private void clickDeleteForum(String name) {
    WebElement deleteButton = wait.forElementVisible(By.xpath(
        "//a[contains(text(), '" + name + "')]/../..//img[@class='sprite trash']"));
    scrollAndClick(deleteButton);
    Log.log("clickDeleteForum", "delete forum button clicked", true);
  }

  private void confirmDeleteForum(String deletedName, String mergerdName) {
    wait.forElementVisible(deleteBoardConfirmationField);
    deleteBoardConfirmationField.sendKeys(deletedName);
    Select select = new Select(mergeToBoard);
    select.selectByVisibleText(mergerdName);
    Log.log("confirmDeleteForum", "delete forum form populated", true);
  }

  private void clickDeleteAndMergeForum() {
    wait.forElementVisible(deleteAndMergeButton);
    scrollAndClick(deleteAndMergeButton);
    Log.log("clickDeleteAndMergeForum", "delete and merge button clicked", true);
  }

  private void verifyForumDeletedText(String deletedName) {
    Assertion.assertListContains(getNotifications(NotificationType.CONFIRM).stream()
                                     .map(Notification::getMessage)
                                     .collect(Collectors.toList()),
                                 "\"Board:" + deletedName + "\" has been deleted."
    );
    Log.log("verifyForumDeletedText", "forum deleted text verified", true);
  }

  public void deleteForum(String sourceForumName, String destinationForumName) {
    clickDeleteForum(sourceForumName);
    confirmDeleteForum(sourceForumName, destinationForumName);
    clickDeleteAndMergeForum();
    verifyForumDeletedText(sourceForumName);
  }

  public boolean verifyForumExists(String forumName, String wikiURL) {
    String temp = driver.getCurrentUrl();
    try {
      getUrl(wikiURL + "/wiki/Board:" + URLEncoder.encode(forumName, "UTF-8").replace("+", "_"));
      wait.forElementVisible(By.xpath(
              "//h1[contains(text(), '" + forumName.replace("_", " ") + "')]"));
    } catch (UnsupportedEncodingException e) {
      Log.info("Forum not exists", e);
      return false;
    }
    getUrl(temp);
    return true;
  }

  public boolean verifyForumNotExists(String forumName, String wikiURL) {
    String temp = driver.getCurrentUrl();
    try {

      getUrl(wikiURL + "/wiki/Board:" + URLEncoder.encode(forumName, "UTF-8").replace("+", "_"));
    } catch (UnsupportedEncodingException e) {
      Log.info("Forum exists", e);
      return false;
    }
    wait.forElementNotVisible(By.xpath(
            "//div//h4//a[contains(text(), '" + forumName.replace("_", " ") + "')]"));
    getUrl(temp);
    return true;
  }

  private void clickModifyForum(String forumName) {
    WebElement editPecil = wait.forElementVisible(By.xpath(
        "//a[contains(text(), '" + forumName + "')]/../..//img[@class='sprite edit-pencil']"));
    scrollAndClick(editPecil);
    Log.log("clickModifyForum", "modify forum button clicked", true);
  }

  private void clearEditBoardFields() {
    wait.forElementVisible(boardTitleField);
    wait.forElementVisible(boardDescriptionField);
    boardTitleField.clear();
    boardDescriptionField.clear();
    Log.log("clickEditBoardFields", "edit boards fields cleared", true);
  }

  public void editForum(String forumName, String newTitle, String newDescription) {
    clickModifyForum(forumName);
    clearEditBoardFields();
    typeBoardTitle(newTitle);
    typeBoradDescription(newDescription);
    submitNewBoard();
  }

  public int getBoardPosition(String boardName) {
    return driver.findElement(By.xpath("//a[contains(text(), '" + boardName + "')]/.."))
        .getLocation()
        .getY();
  }

  public void clickMoveDown(String forumName) {
    WebElement down = wait.forElementVisible(By.xpath(
        "//a[contains(text(), '" + forumName + "')]/../..//span[@class='movedown']"));
    down.click();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Log.info("Sleep interrupted while moving forum", e);
    }
  }

  public void clickMoveUp(String forumName) {
    WebElement up = wait.forElementVisible(By.xpath(
        "//a[contains(text(), '" + forumName + "')]/../..//span[@class='moveup']"));
    up.click();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Log.info("Sleep interrupted while moving forum", e);
    }
  }
}
