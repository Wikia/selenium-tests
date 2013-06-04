package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBaseMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.WikiArticleMonoBookPageObject;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class ArticleUserActionsMonoBookTests extends TestTemplate {

    private String articleContent;
    private String articleTitle;
    private String articleComment;
    private HashMap discussionContent;
    private String postReply;

    @DataProvider
    private static final Object[][] getArticleName() {
        return new Object[][] {
            {"QAarticle"},
            {"QAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleNameQAVeryLongArticleName"},
            {"QA/article"},
            {"QA_article"},
            {"123123123123"}
        };
    }

    /*
     * TestCase001
     * Edit random article as user
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_001_edit", ""})
    public void ArticleUserActionsMonoBook_001_edit() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleTitle = article.enterEdition();
        articleContent = article.editContent();
        article.openArticle(articleTitle);
        article.verifyNewContent(articleContent);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase002
     * Add article as user
     */
    @Test(dataProvider="getArticleName", groups={"monobook", "ArticleUserActionsMonoBook_002_add", ""})
    public void ArticleUserActionsMonoBook_002_add(String articleName) {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();

        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        String articleNew= wiki.addArticleByUrl(articleName);
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);


        articleContent = article.editContent();
        article.openArticle(articleNew);
        article.verifyNewArticle(articleNew);
        article.verifyNewContent(articleContent);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase003
     * Delete article as user
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_003_delete", ""})
    public void ArticleUserActionsMonoBook_003_delete() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleTitle = article.deleteArticle();
        article.verifyDeleted(articleTitle);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase004
     * Rename article as user
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_004_rename", ""})
    public void ArticleUserActionsMonoBook_004_rename() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleTitle = article.renameArticle();
        article.verifyRenamed(articleTitle);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase005
     * post comment
     * under random article as user
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_005_add_comment", ""})
    public void ArticleUserActionsMonoBook_005_add_comment()
    {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );
        
        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleComment = article.postComment();
        article.verifyCommentPresent(articleComment);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase006
     * Start a discussion
     * under random article
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_006_start_disc", ""})
    public void ArticleUserActionsMonoBook_006_start_discusion() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        article.startDiscussion();
        discussionContent = article.fillDiscussForm();
        article.verifyDiscussion(discussionContent);

        CommonFunctions.logoutCookie(cookie);
    }

    /*
     * TestCase007
     * Reply comment under random article
     * In order to do that first create a comment
     */
    @Test(groups={"monobook", "ArticleUserActionsMonoBook_007_reply_comment", ""})
    public void ArticleUserActionsMonoBook_005_reply_comment() {
        CommonFunctions.logOut(driver);
        String cookie = CommonFunctions.logInCookie(
            Properties.userNameStaff, Properties.passwordStaff
        );

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver, Global.DOMAIN);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);

        if(!article.checkCommentsPresent()) {
            articleComment = article.postComment();
        }

        postReply = article.replyComment();
        article.verifyReplyPresent(postReply);

        CommonFunctions.logoutCookie(cookie);
    }
}
