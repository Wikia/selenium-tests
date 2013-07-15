package com.wikia.webdriver.PageObjectsFactory.PageObject.Article;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.RenameArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class ArticlePageObject extends WikiBasePageObject {

	private final String editButtonSelector = ".article-comm-edit";
	private final String commentAuthorLink = ".edited-by";
	private final String replyCommentSelector = ".article-comm-reply";

	@FindBy(css="#WikiaPageHeader h1")
	protected WebElement articleHeader;
	@FindBy(css="#WikiaPageHeader .drop")
	protected WebElement articleEditDropdown;
	@FindBy(css="#mw-content-text p")
	protected WebElement articleContent;
	@FindBy(css="#WikiHeader .drop")
	protected WebElement contributeDropdown;
	@FindBy(css="#ca-delete")
	protected WebElement deleteDropdown;
	@FindBy(css="#ca-protect")
	protected WebElement protectDropdown;
	@FindBy(css="#ca-move")
	protected WebElement renameDropdown;
	@FindBy(css="#ca-history")
	protected WebElement historyDropdown;
	@FindBy(css=".WikiaMenuElement .createpage")
	protected WebElement addArticleInDropdown;
	@FindBy(css="#wpCreatePageDialogTitle")
	protected WebElement articleTitleInputModal;
	@FindBy(css="#CreatePageDialogButton .createpage")
	protected WebElement submitModal;
	@FindBy(css="#ca-edit")
	protected WebElement editDropdown;
	@FindBy(css="#article-comm")
	protected WebElement commentArea;
	@FindBy(css="#article-comm-form .loading-indicator")
	protected WebElement commentAreaLoadingIndicator;
	@FindBy(css="#article-comm-submit")
	protected WebElement commentSubmitButton;
	@FindBys(@FindBy(css=".speech-bubble-message"))
	protected List<WebElement> articleComments;
	@FindBys(@FindBy(css=".sub-comments .speech-bubble-message"))
	protected List<WebElement> commentReplies;
	@FindBys(@FindBy(css="#WikiaPageHeader .WikiaMenuElement li"))
	protected List<WebElement> editDropdownElements;
	@FindBy(css="#WikiaArticleComments")
	protected WebElement allCommentsArea;
	@FindBy(css="blockquote .article-comments .actionButton[name='wpArticleSubmit']")
	protected WebElement editCommentSubmitButton;
	@FindBy(css=".article-comm-edit-box .loading-indicator")
	protected WebElement replyCommentLoadingIndicator;
	@FindBy(css="blockquote .article-comm-edit-box .actionButton[name='wpArticleSubmit']")
	protected WebElement replyCommentSubmitButton;


	public ArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public void compareTitle(String title) {
		waitForElementVisibleByElement(articleHeader);
		Assertion.assertEquals(articleHeader.getText(), title);
	}

	public void compareContent(String content) {
		waitForElementVisibleByElement(articleContent);
		Assertion.assertStringContains(articleContent.getText(), content);
	}

	public VisualEditModePageObject createArticleUsingDropdown(String articleTitle) {
		contributeDropdown.click();
		waitForElementVisibleByElement(addArticleInDropdown);
		addArticleInDropdown.click();
		articleTitleInputModal.sendKeys(articleTitle);
		submitModal.click();
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject editArticleUsingDropdown() {
		editDropdown.click();
		return new VisualEditModePageObject(driver);
	}

	public MiniEditorComponentObject triggerCommentArea() {
		scrollAndClick(commentArea);
		waitForElementNotVisibleByElement(commentAreaLoadingIndicator);
		return new MiniEditorComponentObject(driver);
	}

	public void submitComment() {
		driver.switchTo().defaultContent();
		commentSubmitButton.click();
		waitForElementNotVisibleByElement(commentSubmitButton);
	}

	public void submitEditComment() {
		driver.switchTo().defaultContent();
		editCommentSubmitButton.click();
		waitForElementNotVisibleByElement(editCommentSubmitButton);
	}

	public void verifyArticleComment(String comment) {
		WebElement mostRecentComment = articleComments.get(0);
		waitForTextToBePresentInElementByElement(mostRecentComment, comment);
		Assertion.assertStringContains(mostRecentComment.getText(), comment);
	}

	public MiniEditorComponentObject triggerEditCommentArea() {
		scrollToElement(allCommentsArea);
		WebElement mostRecentComment = articleComments.get(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//TODO Action chains not working on Firefox for some reason
		js.executeScript(
			"arguments[0].querySelector(arguments[1]).click()",
			mostRecentComment, editButtonSelector
		);
		return new MiniEditorComponentObject(driver);
	}

	public void verifyCommentCreator(String userName) {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement editedByArea = mostRecentComment.findElement(By.cssSelector(commentAuthorLink));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(editedByArea.getText(), userName);
	}

	public MiniEditorComponentObject triggerCommentReply() {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement replyButton = mostRecentComment.findElement(By.cssSelector(replyCommentSelector));
		replyButton.click();
		waitForElementNotVisibleByElement(replyCommentLoadingIndicator);
		return new MiniEditorComponentObject(driver);
	}

	public void submitReplyComment() {
		driver.switchTo().defaultContent();
		replyCommentSubmitButton.click();
		waitForElementNotVisibleByElement(replyCommentSubmitButton);
	}

	public void verifyCommentReply(String reply) {
		WebElement mostRecentReply = commentReplies.get(0);
		waitForElementVisibleByElement(mostRecentReply);
		Assertion.assertStringContains(mostRecentReply.getText(), reply);
	}

	public void verifyReplyCreator(String userName) {
		WebElement mostRecentReply = commentReplies.get(0);
		WebElement editedByArea = mostRecentReply.findElement(By.cssSelector(commentAuthorLink));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(editedByArea.getText(), userName);
	}

	public DeleteArticlePageObject deleteArticleUsingDropdown() {
		articleEditDropdown.click();
		waitForElementVisibleByElement(deleteDropdown);
		deleteDropdown.click();
		return new DeleteArticlePageObject(driver);
	}

	public String getArticleName() {
		return articleHeader.getText();
	}

	public RenameArticlePageObject renameArticleUsingDropdown() {
		articleEditDropdown.click();
		waitForElementVisibleByElement(renameDropdown);
		renameDropdown.click();
		return new RenameArticlePageObject(driver);
	}

	public void verifyDropdownForAdmin() {
		articleEditDropdown.click();
		waitForElementVisibleByElement(renameDropdown);
		waitForElementVisibleByElement(deleteDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(protectDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 4);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for admin", true);
	}

	public void verifyDropdownForUser() {
		articleEditDropdown.click();
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(renameDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 2);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for user", true);
	}

	public void verifyDropdownForAnon() {
		articleEditDropdown.click();
		waitForElementVisibleByElement(historyDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 1);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for anon", true);
	}
}
