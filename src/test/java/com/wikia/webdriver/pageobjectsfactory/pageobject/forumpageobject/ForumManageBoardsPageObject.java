package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

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
    LOG.success("openCreateNewBoardForm", "create new board form opened");
  }

  private void typeBoardTitle(String title) {
    wait.forElementVisible(boardTitleField);
    boardTitleField.sendKeys(title);
    LOG.success("typeBoardTitle", "board title: '" + title + "' typed in");
  }

  private void typeBoradDescription(String description) {
    wait.forElementVisible(boardDescriptionField);
    boardDescriptionField.sendKeys(description);
    LOG.result("typeBoardDescription", "board description: '" + description + "' typed in", true);
  }

  private void submitNewBoard() {
    wait.forElementVisible(boardSubmitButton);
    scrollAndClick(boardSubmitButton);
    LOG.success("submitNewBoard", "new board submitted");
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
    LOG.success("verifyBoardCreated", "recently created board verified");
  }

  private void clickDeleteForum(String name) {
    WebElement deleteButton =
        wait.forElementVisible(By.xpath("//a[contains(text(), '" + name
            + "')]/../..//img[@class='sprite trash']"));
    scrollAndClick(deleteButton);
    LOG.success("clickDeleteForum", "delete forum button clicked");
  }

  private void confirmDeleteForum(String deletedName, String mergerdName) {
    wait.forElementVisible(deleteBoardConfirmationField);
    deleteBoardConfirmationField.sendKeys(deletedName);
    Select select = new Select(mergeToBoard);
    select.selectByVisibleText(mergerdName);
    LOG.success("confirmDeleteForum", "delete forum form populated");
  }

  private void clickDeleteAndMergeForum() {
    wait.forElementVisible(deleteAndMergeButton);
    scrollAndClick(deleteAndMergeButton);
    LOG.success("clickDeleteAndMergeForum", "delete and merge button clicked");
  }

  private void verifyForumDeletedText(String deletedName) {
    wait.forElementVisible(By.xpath("//div[@class='banner-notification confirm']"
        + "/div[@class='msg' and contains(text(), '\"Board:" + deletedName
        + "\" has been deleted.')]"));
    LOG.success("verifyForumDeletedText", "forum deleted text verified");
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
      LOG.error("verifyForumExists", e);
    }
    wait.forElementVisible(By.xpath("//h1[contains(text(), '" + forumName.replace("_", " ") + "')]"));
    getUrl(temp);
    LOG.success("verifyForumExists", "verified forum exists");
  }

  public void verifyForumNotExists(String forumName, String wikiURL) {
    try {
      getUrl(wikiURL + "wiki/Board:" + URLEncoder.encode(forumName, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      LOG.error("verifyForumNotExists", e);
    }
    wait.forElementVisible(By
        .xpath("//div[contains(text(), \"We couldn't find a board with that title.  Here's the list of forum boards.\")]"));
    LOG.success("verifyForumNotExists", "verified forum not exists");
  }

  private void clickModifyForum(String forumName) {
    WebElement editPecil =
        wait.forElementVisible(By.xpath("//a[contains(text(), '" + forumName
            + "')]/../..//img[@class='sprite edit-pencil']"));
    scrollAndClick(editPecil);
    LOG.success("clickModifyForum", "modify forum button clicked");
  }

  private void clearEditBoardFields() {
    wait.forElementVisible(boardTitleField);
    wait.forElementVisible(boardDescriptionField);
    boardTitleField.clear();
    boardDescriptionField.clear();
    LOG.success("clickEditBoardFields", "edit boards fields cleared");
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
    WebElement down =
        wait.forElementVisible(By.xpath("//a[contains(text(), '" + forumName
            + "')]/../..//span[@class='movedown']"));
    down.click();
    Assertion.assertEquals(getSecondForumName(), temp);
    LOG.success("clickMoveDown", "move down button clicked");
  }

  public void clickMoveUp(String forumName) {
    String temp = getSecondForumName();
    WebElement up =
        wait.forElementVisible(By.xpath("//a[contains(text(), '" + forumName
            + "')]/../..//span[@class='moveup']"));
    up.click();
    Assertion.assertEquals(getFirstForumName(), temp);
    LOG.success("clickMoveDown", "move up button clicked");
  }
}
