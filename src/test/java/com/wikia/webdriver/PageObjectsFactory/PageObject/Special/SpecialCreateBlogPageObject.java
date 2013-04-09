package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class SpecialCreateBlogPageObject extends WikiArticleEditMode {

	
	@FindBy(css="input[name='wpTitle']")
	private WebElement blogTitleInput;
	@FindBy(css="#ok")
	private WebElement okButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;
	
	public SpecialCreateBlogPageObject(WebDriver driver, String Domain,
			String blogPostTitle) {
		super(driver, Domain, blogPostTitle);
		this.articlename = blogPostTitle;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Michal Nowierski
	 */
	public void typeBlogPostTitle(String blogPostTitle) {
		waitForElementByElement(blogTitleInput);
		waitForElementClickableByElement(blogTitleInput);
		blogTitleInput.sendKeys(blogPostTitle);
		PageObjectLogging.log("typeBlogPostTitle", "type title to blog post: <b>"+blogPostTitle+"</b>", true, driver);
	}

	/**
	 * @author Michal Nowierski
	 */
	public void clickOk() {
		waitForElementByElement(okButton);
		waitForElementClickableByElement(okButton);
		clickAndWait(okButton);
		PageObjectLogging.log("clickOk", "click OK button", true, driver);
	}

	public BlogPageObject clickOnPublishButton() {
		mouseOver("#GlobalNavigation li:nth(1)");
		mouseRelease("#GlobalNavigation li:nth(1)");
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		jQueryClick("input.control-button");
		waitForElementByElement(editButton);
		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true, driver);
		return new BlogPageObject(driver, Domain, articlename);
	}

	public SpecialCreateBlogPageObject createBlogFormUrl(String blogPostTitle){
		getUrl(Global.DOMAIN+"wiki/Special:CreateBlogPage");
		typeBlogPostTitle(blogPostTitle);
		clickOk();
		return new SpecialCreateBlogPageObject(driver, this.Domain, blogPostTitle);
	}

	public void createBlogPost(String title, String content) {
	    typeBlogPostTitle(title);
	    clickOk();
	    typeInContent(content);
	    clickOnPublishButton();
	}

	public void createBlogPostWithDefaultContent(String title) {
	    createBlogPost(title, PageContent.blogContent);
	}

	public void createBlogPostWithDefaultTitle(String content) {
	    String defaultTitle = PageContent.blogPostNamePrefix + getTimeStamp();
	    createBlogPost(defaultTitle, content);
	}
}
