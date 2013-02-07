/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BaseMonoBookPageObject;
import java.util.HashMap;
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
public class UserProfileMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy (css="#pt-userpage a")
    private WebElement userNameLink;
    @FindBy (css="#firstHeading")
    private WebElement header;
    @FindBy (css="#blogPostTitle")
    private WebElement blogPostTitleInput;
    @FindBy (css="#wpTextbox1")
    private WebElement contentInput;
    @FindBy (css="#wpSave")
    private WebElement submitBlogPost;
    @FindBy (css="section.WikiaBlogListing")
    private WebElement blogPostsSection;
    @FindBy (css="#ca-move")
    private WebElement renameLink;
    @FindBy (css="#wpNewTitleMain")
    private WebElement renameTitleInput;
    @FindBy (css="#wpReason")
    private WebElement renameReason;
    @FindBy (css=".mw-submit > input")
    private WebElement renameSubmit;
    @FindBy (css = ".noarticletext")
    private WebElement noArticleArea;
    @FindBy (css = "#ca-edit > a")
    private WebElement editLink;
    @FindBy (css = "#mw-content-text")
    private WebElement postContent;
    @FindBy (css = "#ca-delete > a")
    private WebElement deleteLink;
    @FindBy (css = "#wpDeleteReasonList")
    private WebElement deleteReasonSelect;
    @FindBy (css = "#wpConfirmB")
    private WebElement deleteSubmit;
    @FindBy (css = ".error.mw-error-cannotdelete")
    private WebElement cannotDeleteMessageContainer;


    private String userName;

    public UserProfileMonoBookPageObject(WebDriver driver, String user) {
        super(driver);
        PageFactory.initElements(driver, this);
        changeToMonoBook();
        userName = user;
    }

    public void verifyUserProfilePage(String username) {
        verifyUserNameLink(username);
    }

    public void verifyUserProfileWelcomePage(String username) {
        verifyUserNameLink(username);
        verifyUserNameInHeader(username);
    }

    public void verifyWelcomeEmail () {
        PageObjectLogging.log("verifyWelcomeEmail ", "start of email verification", true);
        String[] mailContent = MailFunctions.getWelcomeMailContent(MailFunctions.getFirstMailContent(Properties.email, Properties.emailPassword));
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        System.out.println("*****************************************************************************");
        for (int i=0; i<mailContent.length; i++){
            System.out.println(i+".  "+mailContent[i]);
            System.out.println("\n");
        }
        Assertion.assertEquals("Edit your profile.", mailContent[4]);
        Assertion.assertEquals("Learn the basics.", mailContent[10]);
        Assertion.assertEquals("Get a quick tutorial on the basics of Wikia: how to edit a page, your user =profile, change your preferences, and more.", mailContent[12]);
        Assertion.assertEquals("Check it out (http://community.wikia.com/wiki/Help:Wikia_Basics)", mailContent[14]);
        Assertion.assertEquals("Explore more wikis.", mailContent[16]);
        Assertion.assertEquals("There are thousands of wikis on Wikia, find more wikis that interest you by= heading to one of our hubs: Video Games (http://www.wikia.com/Video_Games)=, Entertainment (http://www.wikia.com/Entertainment), or Lifestyle (http://=www.wikia.com/Lifestyle).", mailContent[18]);
        Assertion.assertEquals("Go to http://www.wikia.com", mailContent[20]);
        Assertion.assertEquals("Want more information? Find advice, answers, and the Wikia community at Com=munity Central (http://www.community.wikia.com). Happy editing!", mailContent[22]);
        Assertion.assertEquals("The Wikia Team", mailContent[24]);
        PageObjectLogging.log("verifyWelcomeEmail ", "end of email verification", true);
    }

    private void verifyUserNameLink(String username) {
        waitForElementByElement(userNameLink);
        username = username.replace("_", " ");
        Assertion.assertEquals(username, userNameLink.getText());
        PageObjectLogging.log(
            "verifyUserNameLink",
            "username link is present in top right corner",
            true, driver
        );
    }

    public void addBlogPostViaSpecial() {
        getUrl(Domain + URLsContent.specialAddBlogPost + "?useskin=monobook");
        waitForTextToBePresentInElementByElement(
            header, PageContent.createNewBlogPostMessage
        );
        PageObjectLogging.log(
            "addBlogPostPageOpened",
            "Add blog post page is opened with monobook skin",
            true, driver
        );
    }

    public HashMap fillBlogPostForm() {
        String postTitle = PageContent.blogPostNamePrefix + getTimeStamp();
        String blogPostContent = PageContent.blogContent + getTimeStamp();
        waitForElementByElement(blogPostTitleInput);
        blogPostTitleInput.sendKeys(postTitle);
        waitForElementByElement(contentInput);
        contentInput.sendKeys(blogPostContent);
        clickAndWait(submitBlogPost);

        PageObjectLogging.log(
            "SubmitBlogPost",
            "New blog post is submitted",
            true, driver
        );

        HashMap content = new HashMap();
        content.put("postTitle", postTitle);
        content.put("postContent", blogPostContent);
        return content;
    }

    public void openUserBlogViaUrl() {
        String userBlog = URLsContent.userBlog.replace("%user%", userName);
        getUrl(Domain + userBlog);
        changeToMonoBook();

        PageObjectLogging.log(
            "OpenBlogPage",
            "Blog page is opened with monobook skin",
            true, driver
        );
    }

    public void verifyBlogPost(HashMap content) {
        waitForElementByElement(blogPostsSection);
        waitForTextToBePresentInElementByElement(
            blogPostsSection, (String) content.get("postTitle")
        );
        PageObjectLogging.log(
            "BlogPostTitlePresent",
            "New blog post title is present",
            true, driver
        );

        waitForTextToBePresentInElementByElement(
            blogPostsSection, (String) content.get("postContent")
        );
        PageObjectLogging.log(
            "BlogPostContentPresent",
            "New blog post content is present",
            true, driver
        );
    }

    public void openBlogPost(String postTitle) {
        WebElement blogPostLink = driver.findElement(By.partialLinkText(postTitle));
        clickAndWait(blogPostLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "EnterBlogPost",
            "Enter blog post with monobook skin",
            true, driver
        );
    }

    public void renameBlogPost() {
        waitForElementByElement(renameLink);
        clickAndWait(renameLink);
        PageObjectLogging.log(
            "EnterRenamingForm",
            "Enter renaming blog post form for currently selected post",
            true, driver
        );
    }

    public String fillRenameForm() {
        String newName = userName + "/" + getTimeStamp();
        changeToMonoBook();
        renameTitleInput.clear();
        renameTitleInput.sendKeys(newName);
        renameReason.sendKeys(getTimeStamp());
        clickAndWait(renameSubmit);

        PageObjectLogging.log(
            "renameFormSubmitted",
            "Rename form is filled and submitted",
            true, driver
        );

        return newName;
    }

    public void verifyNewName (String newName) {
        openUserBlogViaUrl();
        String currentUrl = driver.getCurrentUrl();
        getUrl(currentUrl + "/" + newName);
        waitForTextToBePresentInElementByElement(header, newName);
        PageObjectLogging.log(
            "verifyNewName",
            "New blog post title is present",
            true, driver
        );

        waitForElementNotVisibleByElement(noArticleArea);
        PageObjectLogging.log(
            "verifyOldContent",
            "Old blog post content is present",
            true, driver
        );
    }

    public void editBlogPost() {
        waitForElementByElement(editLink);
        clickAndWait(editLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "enterEditionForm",
            "Enter edition form and change skin to monobook",
            true, driver
        );
    }

    public String fillEditForm(String blogPostContent) {
        String content = blogPostContent + getTimeStamp();
        waitForElementByElement(contentInput);
        contentInput.clear();
        contentInput.sendKeys(content);
        clickAndWait(submitBlogPost);
        PageObjectLogging.log(
            "EditionFormSubmited",
            "Edition form is submited and skin is changed to monobook",
            true, driver
        );
        return content;
    }

    public void verifyNewContent(String content) {
        waitForElementByElement(postContent);
        waitForTextToBePresentInElementByElement(postContent, content);
        PageObjectLogging.log(
            "newContentPresent",
            "new blog post content is present",
            true, driver
        );
    }

    public void deleteBlogPost() {
        waitForElementByElement(deleteLink);
        clickAndWait(deleteLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "enterDeletionForm",
            "Enter deletion form and change skin to monobook",
            true, driver
        );
    }

    public String fillDeleteForm() {
        Select deleteSelect = new Select(deleteReasonSelect);
        deleteSelect.selectByIndex(1);
        clickAndWait(deleteSubmit);

        String deletionUrl = getCurrentUrl();
        PageObjectLogging.log(
            "submitDeletionForm",
            "blog post deletion form is submitted and skin is changed to monobook",
            true, driver
        );
        return deletionUrl;
    }

    public void verifyDeleted(String deletionUrl) {
        getUrl(deletionUrl);
        changeToMonoBook();
        waitForElementNotVisibleByElement(cannotDeleteMessageContainer);
        PageObjectLogging.log(
            "messagePresent",
            "Message about deletion blog post is present",
            true, driver
        );
    }

    private void verifyUserNameInHeader(String username) {
        waitForElementByElement(header);
        username = username.replace("_", " ");
        String prefixedUserName = URLsContent.userPrefix.replace("%user%", username);
        Assertion.assertEquals(prefixedUserName, header.getText());
        PageObjectLogging.log(
            "verifyUserNameInHeader",
            "username is present in header",
            true, driver
        );
    }
    
}
