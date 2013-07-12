package com.wikia.webdriver.TestCases.BlogTests;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBaseMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.UserProfileMonoBookPageObject;
import java.util.HashMap;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychala
 */
public class BlogMonoBookTests extends TestTemplate {

    private String formContent = PageContent.blogContent;
    /*
     * TestCase001
     * Open user profile
     * Create blog post using special page
     */
    //@Test(groups={"monobook", "BlogMonobook_001_add", "BlogTests"})
    public void BlogMonobook_001_add() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff, driver
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(
            driver, Properties.userNameStaff
        );
        userProfile.addBlogPostViaSpecial();
        HashMap content = userProfile.fillBlogPostForm();
        userProfile.openUserBlogViaUrl();
        userProfile.verifyBlogPost(content);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase002
     * Open user profile
     * Rename blog post
     */
    //@Test(groups={"monobook", "BlogMonobook_002_rename", "BlogTests"})
    public void BlogMonobook_002_rename() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff, driver
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(
            driver, Properties.userNameStaff
        );
        userProfile.addBlogPostViaSpecial();
        HashMap content = userProfile.fillBlogPostForm();
        userProfile.openUserBlogViaUrl();
        userProfile.verifyBlogPost(content);
        userProfile.openBlogPost((String) content.get("postTitle"));
        userProfile.renameBlogPost();
        String newName = userProfile.fillRenameForm();
        userProfile.verifyNewName(newName);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase003
     * Open user profile
     * Edit existing blog post
     */
    //@Test(groups={"monobook", "BlogMonobook_003_edit", "BlogTests"})
    public void BlogMonobook_003_edit() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff, driver
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(
            driver, Properties.userNameStaff
        );
        userProfile.addBlogPostViaSpecial();
        HashMap content = userProfile.fillBlogPostForm();
        userProfile.openUserBlogViaUrl();
        userProfile.verifyBlogPost(content);
        userProfile.openBlogPost((String) content.get("postTitle"));
        userProfile.editBlogPost();
        String newContent = userProfile.fillEditForm(formContent);
        userProfile.verifyNewContent(newContent);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase004
     * Open user profile
     * Delete blog post
     */
    //@Test(groups={"monobook", "BlogMonobook_004_delete", "BlogTests"})
    public void BlogMonobook_004_delete() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff, driver
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        UserProfileMonoBookPageObject userProfile = new UserProfileMonoBookPageObject(
            driver, Properties.userNameStaff
        );
        userProfile.addBlogPostViaSpecial();
        HashMap content = userProfile.fillBlogPostForm();
        userProfile.openUserBlogViaUrl();
        userProfile.verifyBlogPost(content);
        userProfile.openBlogPost((String) content.get("postTitle"));
        userProfile.deleteBlogPost();
        String deletionUrl = userProfile.fillDeleteForm();
        userProfile.verifyDeleted(deletionUrl);

        CommonFunctions.logoutCookie(cookie);
    }
}
