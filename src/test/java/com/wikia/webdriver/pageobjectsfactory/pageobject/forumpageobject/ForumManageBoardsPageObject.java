package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ForumManageBoardsPageObject extends BasePageObject {

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

  public ForumManageBoardsPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  private void openCreateNewBoardForm() {
    wait.forElementVisible(createBoardButton);
    createBoardButton.click();
    LOG.log("openCreateNewBoardForm", "create new board form opened", LOG.Type.SUCCESS);
  }

  private void typeBoardTitle(String title) {
    wait.forElementVisible(boardTitleField);
    boardTitleField.sendKeys(title);
    LOG.log("typeBoardTitle", "board title: '" + title + "' typed in", LOG.Type.SUCCESS);
  }

  private void typeBoradDescription(String description) {
    wait.forElementVisible(boardDescriptionField);
    boardDescriptionField.sendKeys(description);
    LOG
        .logResult("typeBoardDescription", "board description: '" + description + "' typed in",
                   true);
  }

  private void submitNewBoard() {
    wait.forElementVisible(boardSubmitButton);
    scrollAndClick(boardSubmitButton);
    LOG.log("submitNewBoard", "new board submitted", LOG.Type.SUCCESS);
  }

  public void createNewBoard(String title, String description) {
    openCreateNewBoardForm();
    typeBoardTitle(title);
    typeBoradDescription(description);
    submitNewBoard();
  }

  public void verifyBoardCreated(String title, String description) {
    wait.forElementVisible(By.xpath("//ul/li//a[contains(text(), '" + title.replaceAll("_", " ")
                                    + "')]/../../../p[contains(text(), '" + description + "')]"));
    LOG.log("verifyBoardCreated", "recently created board verified", LOG.Type.SUCCESS);
  }

  private void clickDeleteForum(String name) {
    WebElement
        deleteButton =
        wait.forElementVisible(By.xpath(
            "//a[contains(text(), '" + name + "')]/../..//img[@class='sprite trash']"));
    scrollAndClick(deleteButton);
    LOG.log("clickDeleteForum", "delete forum button clicked", LOG.Type.SUCCESS);
  }

  private void confirmDeleteForum(String deletedName, String mergerdName) {
    wait.forElementVisible(deleteBoardConfirmationField);
    deleteBoardConfirmationField.sendKeys(deletedName);
    Select select = new Select(mergeToBoard);
    select.selectByVisibleText(mergerdName);
    LOG.log("confirmDeleteForum", "delete forum form populated", LOG.Type.SUCCESS);
  }

  private void clickDeleteAndMergeForum() {
    wait.forElementVisible(deleteAndMergeButton);
    scrollAndClick(deleteAndMergeButton);
    LOG.log("clickDeleteAndMergeForum", "delete and merge button clicked", LOG.Type.SUCCESS);
  }

  private void verifyForumDeletedText(String deletedName) {
    wait.forElementVisible(By.xpath("//div[@class='banner-notification confirm']" +
                                    "/div[@class='msg' and contains(text(), '\"Board:" + deletedName
                                    + "\" has been deleted.')]"));
    LOG.log("verifyForumDeletedText", "forum deleted text verified", LOG.Type.SUCCESS);
  }

  public void deleteForum(String sourceForumName, String destinationForumName) {
    clickDeleteForum(sourceForumName);
    confirmDeleteForum(sourceForumName, destinationForumName);
    clickDeleteAndMergeForum();
    verifyForumDeletedText(sourceForumName);
  }

  public String getFirstForumName() {
    wait.forElementVisible(firstForumLink);
    return firstForumLink.getText();
  }

  public String getSecondForumName() {
    wait.forElementVisible(secondForumLink);
    return secondForumLink.getText();
  }

  public void verifyForumExists(String forumName, String wikiURL) {
    String temp = driver.getCurrentUrl();
    try {

      getUrl(wikiURL + "wiki/Board:" + URLEncoder.encode(forumName, "UTF-8").replace("+", "_"));
    } catch (UnsupportedEncodingException e) {
      LOG.log("verifyForumExists", e, LOG.Type.ERROR);
    }
    wait.forElementVisible(
        By.xpath("//h1[contains(text(), '" + forumName.replace("_", " ") + "')]"));
    getUrl(temp);
    LOG.log("verifyForumExists", "verified forum exists", LOG.Type.SUCCESS);
  }

  public void verifyForumNotExists(String forumName, String wikiURL) {
    try {
      getUrl(wikiURL + "wiki/Board:" + URLEncoder.encode(forumName, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      LOG.log("verifyForumNotExists", e, LOG.Type.ERROR);
    }
    wait.forElementVisible(By.xpath(
        "//div[contains(text(), \"We couldn't find a board with that title.  Here's the list of forum boards.\")]"));
    LOG.log("verifyForumNotExists", "verified forum not exists", LOG.Type.SUCCESS);
  }

  private void clickModifyForum(String forumName) {
    WebElement
        editPecil =
        wait.forElementVisible(By.xpath(
            "//a[contains(text(), '" + forumName + "')]/../..//img[@class='sprite edit-pencil']"));
    scrollAndClick(editPecil);
    LOG.log("clickModifyForum", "modify forum button clicked", LOG.Type.SUCCESS);
  }

  private void clearEditBoardFields() {
    wait.forElementVisible(boardTitleField);
    wait.forElementVisible(boardDescriptionField);
    boardTitleField.clear();
    boardDescriptionField.clear();
    LOG.log("clickEditBoardFields", "edit boards fields cleared", LOG.Type.SUCCESS);
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
        wait.forElementVisible(By.xpath(
            "//a[contains(text(), '" + forumName + "')]/../..//span[@class='movedown']"));
    down.click();
    Assertion.assertEquals(getSecondForumName(), temp);
    LOG.log("clickMoveDown", "move down button clicked", LOG.Type.SUCCESS);
  }

  public void clickMoveUp(String forumName) {
    String temp = getSecondForumName();
    WebElement
        up =
        wait.forElementVisible(By.xpath(
            "//a[contains(text(), '" + forumName + "')]/../..//span[@class='moveup']"));
    up.click();
    Assertion.assertEquals(getFirstForumName(), temp);
    LOG.log("clickMoveDown", "move up button clicked", LOG.Type.SUCCESS);
  }
}
