package com.wikia.webdriver.PageObjects.PageObject.ForumPageObject;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;

public class ForumManageBoardsPageObject extends BasePageObject{

	public ForumManageBoardsPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#CreateNewBoardButton")
	private WebElement createBoardButton;
	@FindBy(xpath="//section[@id='modalWrapper' and contains(text(), 'Create a new board')]")
	private WebElement createBoardModalHeader;
	@FindBy(css="[name='boardTitle']")
	private WebElement boardTitleField;
	@FindBy(css="[name='boardDescription']")
	private WebElement boardDescriptionField;
	@FindBy(css=".submit")
	private WebElement boardSubmitButton;
	@FindBy(css="[name='boardTitle']")
	private WebElement deleteBoardConfirmationField;
	@FindBy(css="[name='destinationBoardId']")
	private WebElement mergeToBoard;
	@FindBy(css=".modalToolbar .submit")
	private WebElement deleteAndMergeButton;
	@FindBy(xpath="//ul[@class='boards']/li//a")
	private WebElement firstForumLink;
	@FindBy(xpath="//ul[@class='boards']//li[2]//a")
	private WebElement secondForumLink;

	private void openCreateNewBoardForm(){
		waitForElementByElement(createBoardButton);
		createBoardButton.click();
		PageObjectLogging.log("openCreateNewBoardForm", "create new board form opened", true, driver);
	}
	
	private void typeBoardTitle(String title){
		waitForElementByElement(boardTitleField);
		boardTitleField.sendKeys(title);
		PageObjectLogging.log("typeBoardTitle", "board title typed in", true);
	}
	private void typeBoradDescription(String description){
		waitForElementByElement(boardDescriptionField);
		boardDescriptionField.sendKeys(description);
		PageObjectLogging.log("typeBoardDescription", "board description typed in", true);
	}
	private void submitNewBoard(){
		waitForElementByElement(boardSubmitButton);
		clickAndWait(boardSubmitButton);
		PageObjectLogging.log("submitNewBoard", "new board submitted", true, driver);
	}
	
	public void createNewBoard(String title, String description){
		openCreateNewBoardForm();
		typeBoardTitle(title);
		typeBoradDescription(description);
		submitNewBoard();
	}
	
	public void verifyBoardCreated(String title, String description){
		waitForElementByXPath("//ul/li//a[contains(text(), '"+title.replaceAll("_", " ")+"')]/../../../p[contains(text(), '"+description+"')]");
		PageObjectLogging.log("verifyBoardCreated", "recently created board verified", true);		
	}

	private void clickDeleteForum(String name){
		WebElement deleteButton = waitForElementByXPath("//a[contains(text(), '"+name+"')]/../..//img[@class='sprite trash']");
		clickAndWait(deleteButton);
		PageObjectLogging.log("clickDeleteForum", "delete forum button clicked", true, driver);				
	}
	
	private void confirmDeleteForum(String deletedName, String mergerdName){
		waitForElementByElement(deleteBoardConfirmationField);
		deleteBoardConfirmationField.sendKeys(deletedName);
		Select select = new Select(mergeToBoard);
		select.selectByVisibleText(mergerdName);
		PageObjectLogging.log("confirmDeleteForum", "delete forum form populated", true, driver);
	}
	
	private void clickDeleteAndMergeForum(){
		waitForElementByElement(deleteAndMergeButton);
		clickAndWait(deleteAndMergeButton);
		PageObjectLogging.log("confirmDeleteForum", "delete forum form populated", true, driver);
	}
	
	private void verifyForumDeletedText(String deletedName){
		waitForElementByXPath("//div[@class='global-notification confirm']" +
				"/div[@class='msg' and contains(text(), '\"Board:"+deletedName+"\" has been deleted.')]");
		PageObjectLogging.log("verifyForumDeletedText", "forum deleted text verified", true);
	}
	
	public void deleteForum(String sourceForumName, String destinationForumName){
		clickDeleteForum(sourceForumName);
		confirmDeleteForum(sourceForumName, destinationForumName);
		clickDeleteAndMergeForum();
		verifyForumDeletedText(sourceForumName);
	}
	
	public String getFirstForumName(){
		waitForElementByElement(firstForumLink);
		return firstForumLink.getText();
	}
	
	public String getSecondForumName(){
		waitForElementByElement(secondForumLink);
		return secondForumLink.getText();
	}
	
	public void verifyForumExists(String forumName){
		String temp = driver.getCurrentUrl();
		try {
			
			getUrl(Global.DOMAIN+"wiki/Board:"+URLEncoder.encode(forumName, "UTF-8").replace("+", "_"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementByXPath("//h1[contains(text(), '"+forumName.replace("_", " ")+"')]");
		getUrl(temp);
		PageObjectLogging.log("verifyForumExists", "verified forum exists", true, driver);
	}
	
	public void verifyForumNotExists(String forumName){
		try {
			getUrl(Global.DOMAIN+"wiki/Board:"+URLEncoder.encode(forumName, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForElementByXPath("//div[@class='msg' and contains(text(), \"The board you're looking for was not found\")]");
		PageObjectLogging.log("verifyForumNotExists", "verified forum not exists", true, driver);
	}
	
	private void clickModifyForum(String forumName){
		WebElement editPecil = waitForElementByXPath("//a[contains(text(), '"+forumName+"')]/../..//img[@class='sprite edit-pencil']");
		clickAndWait(editPecil);
		PageObjectLogging.log("clickModifyForum", "modify forum button clicked", true, driver);
	}
	
	private void clearEditBoardFields(){
		waitForElementByElement(boardTitleField);
		waitForElementByElement(boardDescriptionField);
		boardTitleField.clear();
		boardDescriptionField.clear();
		PageObjectLogging.log("clickEditBoardFields", "edit boards fields cleared", true, driver);
	}
	
	public void editForum(String forumName, String newTitle, String newDescription){
		clickModifyForum(forumName);
		clearEditBoardFields();
		typeBoardTitle(newTitle);
		typeBoradDescription(newDescription);
		submitNewBoard();
	}
	
	public void clickMoveDown(String forumName){
		String temp = getFirstForumName();
		WebElement down = waitForElementByXPath("//a[contains(text(), '"+forumName+"')]/../..//span[@class='movedown']");
		down.click();
		Assertion.assertEquals(temp, getSecondForumName());
		PageObjectLogging.log("clickMoveDown", "move down button clicked", true, driver);
	}
	
	public void clickMoveUp(String forumName){
		String temp = getSecondForumName();
		WebElement up = waitForElementByXPath("//a[contains(text(), '"+forumName+"')]/../..//span[@class='moveup']");
		up.click();
		Assertion.assertEquals(temp, getFirstForumName());
		PageObjectLogging.log("clickMoveDown", "move up button clicked", true, driver);
	}
}
