package com.wikia.webdriver.PageObjects.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileArticlePageObject extends MobileBasePageObject{

	
	
	public MobileArticlePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#wkArtCom .collSec.addChev")
	private WebElement commentsSectionShowButton;	
	@FindBy(css=".commSbt.wkBtn.main")
	private WebElement postCommentButton;
	@FindBy(css=".wkInp[name='wpArticleComment']")
	private WebElement commentInputArea;
	@FindBy(css=".cmnRpl")
	private WebElement replyCommentButton;
	@FindBy(css="[placeholder='Post a reply']")
	private WebElement replyInputArea;
	@FindBy(css="#wkMdlCnt [name='wpArticleSubmit']")
	private WebElement replySubmitButton;
	@FindBy(css="#wkMdlClo")
	private WebElement closeModalButton;		
	@FindBy(css=".collSec.addChev.open")
	private WebElement commentsSectionHideButton;
	@FindBy(css="#commMore")
	private WebElement loadMoreCommentsButton;
	@FindBy(css="#commPrev")
	private WebElement loadPreviousCommentsButton;
	
	private void showCommentsSection(){
		waitForElementByElement(commentsSectionShowButton);
		clickAndWait(commentsSectionShowButton);
		waitForElementByElement(postCommentButton);
		waitForElementByElement(commentInputArea);
		PageObjectLogging.log("showCommentsSection", "comments sections is visible", true, driver);
	}
	
	private void verifyAddedComment(String comment){
		waitForElementByXPath("//li[@class='comment']/blockquote/div[@class='txt']/p[contains(text(), '"+comment+"')]");
		PageObjectLogging.log("verifyAddedComment", "comment "+comment+" is visible", true, driver);
	}
	
	public void addComment(String comment){
		showCommentsSection();
		commentInputArea.sendKeys(comment);
		postCommentButton.click();
		verifyAddedComment(comment);
	}
	
	private void verifyAddedReplyOnCommentPage(String reply){
		waitForElementByXPath("//div[@id='wkMdlWrp']//ul[@class='sub-comments']//p[contains(text(), '"+reply+"')]");
		PageObjectLogging.log("verifyAddedReply", "reply "+reply+" is visible", true, driver);
	}
	
	public void addReply(String reply){
		waitForElementByElement(replyCommentButton);
		String url = driver.getCurrentUrl();
		clickAndWait(replyCommentButton);
		verifyURL(url+"#Modal");
		replyInputArea.sendKeys(reply);
		replyInputArea.submit();
		verifyURL(url+"#Modal");
		verifyAddedReplyOnCommentPage(reply);
		PageObjectLogging.log("addReply", "reply "+reply+" added", true, driver);
	}
	
	public void verifyPagination()
	{
		showCommentsSection();
		waitForElementByElement(loadMoreCommentsButton);
		loadMoreCommentsButton.click();
		waitForElementByElement(loadPreviousCommentsButton);
		loadPreviousCommentsButton.click();
		waitForElementByElement(loadMoreCommentsButton);
	}

}
