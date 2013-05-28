package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBaseMonoBookPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPageMonoBook.WikiArticleMonoBookPageObject;
import java.util.HashMap;
import org.testng.annotations.DataProvider;

public class ArticleAnonymousActionsMonoBookTests extends TestTemplate {

    private String articleTitle;
    private String articleContent;
    private String articleComment;
    private HashMap discussionContent;
    private String replyComment;

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
     * Open random wiki page as anonymous user
     * Verify editing article by anonymous user
     */
    @Test(groups={"monobook", "ArticleAnonymousActionsMonoBook_001_edit", ""})
    public void ArticleAnonymousActionsMonoBook_001_edit() {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleTitle = article.enterEdition();
        articleContent = article.editContent();
        article.openArticle(articleTitle);
        article.verifyNewContent(articleContent);
    }

    /*
     * TestCase002
     * Add article as anonymous user
     */
    @Test(dataProvider="getArticleName", groups={"monobook", "ArticleAnonymousActionsMonoBook_002_add", ""})
    public void ArticleAnonymousActionsMonoBook_002_add(String articleName) {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.addArticleByUrl(articleName);
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleContent = article.editContent();
        article.verifyNewArticle(articleName);
    }

    /*
     * TestCase003
     * Open random wiki page as anonymous user
     * post comment
     */
    @Test(groups={"monobook", "ArticleAnonymousActionsMonoBook_003_add_comment", ""})
    public void ArticleAnonymousActionsMonoBook_003_add_comment()
    {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        articleComment = article.postComment();
        article.verifyCommentPresent(articleComment);
        article.verifyRestrictedPermissions();
    }

    /*
     * TestCase004
     * Start a discussion
     * To random article
     */
    @Test(groups={"monobook", "ArticleAnonymousActionsMonoBook_004_start_disc", ""})
    public void ArticleAnonymousActionsMonoBook_004_start_discusion() {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);
        article.startDiscussion();
        discussionContent = article.fillDiscussForm();
        article.verifyDiscussion(discussionContent);
    }

    /*
     * TestCase005
     * Reply comment under random article
     * In order to do that first create a comment
     */
    @Test(groups={"monobook", "ArticleAnonymousActionsMonoBook_005_reply_comment", ""})
    public void ArticleAnonymousActionsMonoBook_005_reply_comment() {
        CommonFunctions.logOut(driver);

        WikiBaseMonoBookPageObject wiki = new WikiBaseMonoBookPageObject(driver);
        wiki.openWikiWithMonobook();
        wiki.openRandomArticleMonobook();
        WikiArticleMonoBookPageObject article = new WikiArticleMonoBookPageObject(driver);

        if (!article.checkCommentsPresent()) {
            articleComment = article.postComment();
        }

        replyComment = article.replyComment();
        article.verifyReplyPresent(replyComment);
    }
}
