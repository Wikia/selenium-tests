package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class SpecialCreatePagePageObject extends SpecialPageObject {

    @FindBy (css = "#HiddenFieldsDialog input[name='wpTitle']")
    private WebElement titleInput;
    @FindBy (css = "#HiddenFieldsDialog #ok")
    private WebElement submitTitleInput;
    @FindBy (css = "#bodyContent")
    private WebElement contentInput;

    public SpecialCreatePagePageObject (WebDriver driver, String Domain) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addPageWithGivenTitleAndDefaultContent(String title) {
	String defaultContent = PageContent.articleText;
	createArticle(title, defaultContent);
	PageObjectLogging.log(
	    "ArticleCreated", "Article with given title created", true
	);
    }

    public void addPageWithDefaultTitleAndGivenContent(String content) {
	String defaultTitle = PageContent.articleNamePrefix + getTimeStamp();
	createArticle(defaultTitle, content);
	PageObjectLogging.log(
	    "ArticleCreated", "Article with given content created", true
	);
    }

    private void createArticle(String title, String content) {
        waitForElementByElement(titleInput);
        titleInput.sendKeys(title);
        waitForElementByElement(submitTitleInput);
        clickAndWait(submitTitleInput);
	WikiArticleEditMode article = new WikiArticleEditMode(
            driver, Global.DOMAIN, title
        );
	article.typeInContent(content);
	article.clickOnPublish();
    }
}
