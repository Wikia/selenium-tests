package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook;


import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class WikiArticleMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(css = "#firstHeading")
    private WebElement titleLocator;
    @FindBy(css = "#ca-edit a")
    private WebElement editLink;
    @FindBy(css = "#wpTextbox1")
    private WebElement editionArea;
    @FindBy(css = "#wpSave")
    private WebElement submitEditButton;
    @FindBy(css = "#ca-delete > a")
    private WebElement deleteArticleLink;
    @FindBy(css = "#wpDeleteReasonList")
    private WebElement deleteReasonSelect;
    @FindBy(css = "#wpConfirmB")
    private WebElement submitDelete;
    @FindBy(css = "#mw-content-text")
    private WebElement articleContentLocator;
    @FindBy(css = "#article-comm")
    private WebElement postCommentLocator;
    @FindBy(css = "#article-comm-submit")
    private WebElement postCommentSubmit;
    @FindBy(css = ".throbber")
    private WebElement commentSubmitionIndicator;
    @FindBy(css = ".article-comm-delete")
    private WebElement deleteComment;
    @FindBy(css = ".mw-warning-with-logexcerpt > p")
    private WebElement warningMessage;
    @FindBy(css = "#ca-talk a")
    private WebElement discussionLink;
    @FindBy(css = "#RelatedForumDiscussion > .forum-content")
    private WebElement discussionUnderArticle;
    @FindBy(css = "#article-comments-ul")
    private WebElement comments;
    @FindBy(css = "#RelatedForumDiscussion .forum-new-post")
    private WebElement startDiscussion;
    @FindBy(css = "#ForumNewMessage > .message")
    private WebElement disussionArea;
    @FindBy(css = "#BoardList")
    private WebElement selectBoard;
    @FindBy(css = ".title-container textarea:nth-of-type(2)")
    private WebElement discussionTitleElement;
    @FindBy(css = ".editarea textarea:nth-of-type(2)")
    private WebElement discussionContentElement;
    @FindBy (css= ".buttons > .submit")
    private WebElement submitDiscussion;
    @FindBy (css = ".article-comm-reply")
    private WebElement replyButton;
    @FindBy (css = "textarea[name='wpArticleReply']")
    private WebElement replyArea;
    @FindBy (css = ".article-comm-input-text > .buttons > input[type='submit']")
    private WebElement submitReply;
    @FindBy (css = "#ca-move > a")
    private WebElement renameLink;
    @FindBy (css = "#wpNewTitleMain")
    private WebElement newTitleLocator;
    @FindBy (css = ".mw-submit>input")
    private WebElement submitRename;
    @FindBy (css = ".noarticletext")
    private WebElement noArticleInfo;
    @FindBy (css = "#bodyContent")
    private WebElement bodyContent;


    private String articleTitle;
    private final String commentsSelector = ".comments > li > .speech-bubble-message";

    public WikiArticleMonoBookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        changeToMonoBook();
        articleTitle = getArticleTitle();
    }

    public String enterEdition() {
        waitForElementByElement(editLink);
        clickAndWait(editLink);
        waitForElementByElement(submitEditButton);
        changeToMonoBook();
        PageObjectLogging.log(
            "editArticlePageMonobook",
            "edit article page with monobook skin opened",
            true, driver
        );

        waitForElementByElement(titleLocator);
        Assertion.assertStringContains(titleLocator.getText(), articleTitle);
        PageObjectLogging.log(
            "editTitleContainsArticleTitle",
            "title on edit article page contains article title",
            true, driver
        );
        return articleTitle;
    }

    public String editContent() {
        String articleContent = enterDefaultContent();
        waitForElementByElement(submitEditButton);
        clickAndWait(submitEditButton);
        PageObjectLogging.log(
            "submitEditForm",
            "new content is added to form",
            true, driver
        );

        //BodyContent element is unique for edition form with oasis skin
        //until it is present new article is not saved to database
        waitForElementNotVisibleByElement(bodyContent);
        changeToMonoBook();
        return articleContent;
    }

    public void deleteContent() {
        waitForElementByElement(editionArea);
        editionArea.clear();
        PageObjectLogging.log(
            "clearEditForm",
            "edition area is cleared",
            true, driver
        );
    }

    public String enterDefaultContent() {
        String content = PageContent.articleText + getTimeStamp();
        editionArea.sendKeys(content);
        PageObjectLogging.log(
            "addContentToArticle",
            "add content to article",
            true, driver
        );
        return content;
    }

    public void verifyNewContent(String content) {
        waitForElementByElement(articleContentLocator);

        waitForTextToBePresentInElementByElement(
            articleContentLocator, content
        );

        PageObjectLogging.log(
            "ContentFound",
            "new content found in the article",
            true, driver
        );
    }

    public String renameArticle() {
        clickAndWait(renameLink);
        changeToMonoBook();
        String newName = PageContent.articleNamePrefix + getTimeStamp();
        newTitleLocator.clear();
        newTitleLocator.sendKeys(newName);

        clickAndWait(submitRename);
        return newName;
    }

    public void verifyRenamed(String articleName) {
        openArticle(articleName);
        changeToMonoBook();
        Assertion.assertEquals(titleLocator.getText(), articleName);
        PageObjectLogging.log(
            "NewArticleNamePresent",
            "Article has new name",
            true, driver
        );
    }

    public void verifyNewArticle(String articleName) {
        waitForElementByElement(titleLocator);

        articleName = articleName.replace("_", " ");
        waitForTextToBePresentInElementByElement(titleLocator, articleName);
        PageObjectLogging.log(
            "NewArticleTitle",
            "New article title is present",
            true, driver
        );
    }

    public String postComment() {
        String comment = PageContent.commentText + getTimeStamp();
        postCommentLocator.sendKeys(comment);
        clickAndWait(postCommentSubmit);
        return comment;
    }

    public void verifyCommentPresent(String comment) {

        waitForTextToBePresentInElementByElement(
            comments,comment
        );

        PageObjectLogging.log(
            "CommentFound",
            "new comment found under the article",
            true, driver
        );
    }

    public void startDiscussion() {
        waitForElementByElement(startDiscussion);
        startDiscussion.click();
        changeToMonoBook();
        PageObjectLogging.log(
            "discussAboutArticle",
            "discuss about article page with monobook skin opened",
            true, driver
        );

        waitForElementByElement(titleLocator);
        Assertion.assertStringContains(titleLocator.getText(), articleTitle);
        PageObjectLogging.log(
            "discussionTitleContainsArticleTitle",
            "title on discuss about article page contains article title",
            true, driver
        );
    }

    public HashMap fillDiscussForm() {
        waitForElementByElement(disussionArea);
        Select selectElement = new Select(selectBoard);
        selectElement.selectByIndex(1);
        String discussionTitle = "QA Title" + getTimeStamp();
        String discussionContent = "QA Content" + getTimeStamp();
        discussionTitleElement.sendKeys(discussionTitle);
        discussionContentElement.sendKeys(discussionContent);

        PageObjectLogging.log(
            "fillDiscussionForm",
            "mandatory fields in discussion form are filled",
            true, driver
        );

        waitForElementClickableByElement(submitDiscussion);
        clickAndWait(submitDiscussion);

        HashMap content = new HashMap();
        content.put("discussionTitle", discussionTitle);
        content.put("discussionContent", discussionContent);
        return content;
    }

    public void verifyDiscussion(HashMap content) {
        openArticle(articleTitle);
        waitForTextToBePresentInElementByElement(
            discussionUnderArticle, (String) content.get("discussionTitle")
        );
        PageObjectLogging.log(
            "DiscussionTitleFound",
            "new discussion title found under the article",
            true, driver
        );

        waitForTextToBePresentInElementByElement(
            discussionUnderArticle, (String) content.get("discussionContent")
        );
        PageObjectLogging.log(
            "DiscussionContentFound",
            "new discussion content found under the article",
            true, driver
        );
    }

    public Boolean checkCommentsPresent() {

        List commentsElements = driver.findElements(By.cssSelector(commentsSelector));
        if (commentsElements.size() > 0) {
            return true;
        }
        return false;
    }

    public String replyComment() {
        waitForElementByElement(replyButton);
        clickAndWait(replyButton);
        waitForElementByElement(replyArea);
        String reply =  getTimeStamp();
        replyArea.sendKeys(reply);
        clickAndWait(submitReply);
        return reply;
    }

    public void verifyReplyPresent(String reply) {
        waitForTextToBePresentInElementByElement(
            comments, reply
        );
        PageObjectLogging.log(
            "ReplyFound",
            "reply found under the comment",
            true, driver
        );
    }

    public String deleteArticle() {
        clickAndWait(deleteArticleLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "DeleteForm",
            "Eneter article delete form",
            true, driver
        );
        Select select = new Select(deleteReasonSelect);
        select.selectByIndex(1);
        PageObjectLogging.log(
            "ReasonSelected",
            "deletion reason selected",
            true, driver
        );
        clickAndWait(submitDelete);
        return articleTitle;
    }

    public void verifyRestrictedPermissions() {
        //TODO Assert raises
    }

    public void verifyDeleted(String articleName) {
        openArticle(articleName);
        changeToMonoBook();
        waitForElementByElement(warningMessage);
        waitForTextToBePresentInElementByElement(
            warningMessage, PageContent.articleDeletedMessage
        );
        PageObjectLogging.log(
            "MessageAboutDeletion",
            "Message about deletion is present",
            true, driver
        );
    }

    public void openArticle(String articleName) {
        String articleTitleWithUnderscore = articleName.replace(" ", "_");
        String url =  Global.DOMAIN + "wiki/" + articleTitleWithUnderscore;
        getUrl(url);
        changeToMonoBook();
    }

    private String getArticleTitle() {
        waitForElementByElement(titleLocator);
        String title = titleLocator.getText();
        return title;
    }
}
