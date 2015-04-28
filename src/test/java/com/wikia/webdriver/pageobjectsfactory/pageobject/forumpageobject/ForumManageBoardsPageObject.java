package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ForumManageBoardsPageObject extends BasePageObject {

  public ForumManageBoardsPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

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
    waitForElementByElement(createBoardButton);
    createBoardButton.click();
    PageObjectLogging.log("openCreateNewBoardForm", "create new board form opened", true);
  }

  private void typeBoardTitle(String title) {
    waitForElementByElement(boardTitleField);
    boardTitleField.sendKeys(title);
    PageObjectLogging.log("typeBoardTitle", "board title: '" + title + "' typed in", true);
  }

  private void typeBoradDescription(String description) {
    waitForElementByElement(boardDescriptionField);
    boardDescriptionField.sendKeys(description);
    PageObjectLogging
        .log("typeBoardDescription", "board description: '" + description + "' typed in", true);
  }

  private void submitNewBoard() {
    waitForElementByElement(boardSubmitButton);
    scrollAndClick(boardSubmitButton);
    PageObjectLogging.log("submitNewBoard", "new board submitted", true);
  }

  public void createNewBoard(String title, String description) {
    openCreateNewBoardForm();
    typeBoardTitle(title);
    typeBoradDescription(description);
    submitNewBoard();
  }

  public void verifyBoardCreated(String title, String description) {
    waitForElementByXPath("//ul/li//a[contains(text(), '" + title.replaceAll("_", " ")
                          + "')]/../../../p[contains(text(), '" + description + "')]");
    PageObjectLogging.log("verifyBoardCreated", "recently created board verified", true);
  }

  private void clickDeleteForum(String name) {
    WebElement
        deleteButton =
        waitForElementByXPath(
            "//a[contains(text(), '" + name + "')]/../..//img[@class='sprite trash']");
    scrollAndClick(deleteButton);
    PageObjectLogging.log("clickDeleteForum", "delete forum button clicked", true);
  }

  private void confirmDeleteForum(String deletedName, String mergerdName) {
    waitForElementByElement(deleteBoardConfirmationField);
    deleteBoardConfirmationField.sendKeys(deletedName);
    Select select = new Select(mergeToBoard);
    select.selectByVisibleText(mergerdName);
    PageObjectLogging.log("confirmDeleteForum", "delete forum form populated", true);
  }

  private void clickDeleteAndMergeForum() {
    waitForElementByElement(deleteAndMergeButton);
    scrollAndClick(deleteAndMergeButton);
    PageObjectLogging.log("clickDeleteAndMergeForum", "delete and merge button clicked", true);
  }

  private void verifyForumDeletedText(String deletedName) {
    waitForElementByXPath("//div[@class='banner-notification confirm']" +
                          "/div[@class='msg' and contains(text(), '\"Board:" + deletedName
                          + "\" has been deleted.')]");
    PageObjectLogging.log("verifyForumDeletedText", "forum deleted text verified", true);
  }

  public void deleteForum(String sourceForumName, String destinationForumName) {
    clickDeleteForum(sourceForumName);
    confirmDeleteForum(sourceForumName, destinationForumName);
    clickDeleteAndMergeForum();
    verifyForumDeletedText(sourceForumName);
  }

  public String getFirstForumName() {
    waitForElementByElement(firstForumLink);
    return firstForumLink.getText();
  }

  public String getSecondForumName() {
    waitForElementByElement(secondForumLink);
    return secondForumLink.getText();
  }

  public void verifyForumExists(String forumName, String wikiURL) {
    String temp = driver.getCurrentUrl();
    try {

      getUrl(wikiURL + "wiki/Board:" + URLEncoder.encode(forumName, "UTF-8").replace("+", "_"));
    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("verifyForumExists", e.getMessage(), false);
    }
    waitForElementByXPath("//h1[contains(text(), '" + forumName.replace("_", " ") + "')]");
    getUrl(temp);
    PageObjectLogging.log("verifyForumExists", "verified forum exists", true);
  }

  public void verifyForumNotExists(String forumName, String wikiURL) {
    try {
      getUrl(wikiURL + "wiki/Board:" + URLEncoder.encode(forumName, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("verifyForumNotExists", e.getMessage(), false);
    }
    waitForElementByXPath(
        "//div[contains(text(), \"We couldn't find a board with that title.  Here's the list of forum boards.\")]");
    PageObjectLogging.log("verifyForumNotExists", "verified forum not exists", true);
  }

  private void clickModifyForum(String forumName) {
    WebElement
        editPecil =
        waitForElementByXPath(
            "//a[contains(text(), '" + forumName + "')]/../..//img[@class='sprite edit-pencil']");
    scrollAndClick(editPecil);
    PageObjectLogging.log("clickModifyForum", "modify forum button clicked", true);
  }

  private void clearEditBoardFields() {
    waitForElementByElement(boardTitleField);
    waitForElementByElement(boardDescriptionField);
    boardTitleField.clear();
    boardDescriptionField.clear();
    PageObjectLogging.log("clickEditBoardFields", "edit boards fields cleared", true);
  }

  public void editForum(String forumName, String newTitle, String newDescription) {
    clickModifyForum(forumName);
    clearEditBoardFields();
    typeBoardTitle(newTitle);
    typeBoradDescription(newDescription);
    submitNewBoard();
  }

  public void clickMoveDown(String forumName) {
    String temp = getFirstForumName();
    WebElement
        down =
        waitForElementByXPath(
            "//a[contains(text(), '" + forumName + "')]/../..//span[@class='movedown']");
    down.click();
    Assertion.assertEquals(temp, getSecondForumName());
    PageObjectLogging.log("clickMoveDown", "move down button clicked", true);
  }

  public void clickMoveUp(String forumName) {
    String temp = getSecondForumName();
    WebElement
        up =
        waitForElementByXPath(
            "//a[contains(text(), '" + forumName + "')]/../..//span[@class='moveup']");
    up.click();
    Assertion.assertEquals(temp, getFirstForumName());
    PageObjectLogging.log("clickMoveDown", "move up button clicked", true);
  }
}
