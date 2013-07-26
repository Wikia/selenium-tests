package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;

public class SpecialCreateBlogPageObject extends VisualEditModePageObject {


	@FindBy(css="input[name='wpTitle']")
	private WebElement blogTitleInput;
	@FindBy(css="#ok")
	private WebElement okButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;

	private String blogName;

	public SpecialCreateBlogPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		blogName = PageContent.blogPostNamePrefix + getRandomDigits(5) + getRandomDigits(5);
	}

	public String getBlogName() {
		return this.blogName;
	}


	/**
	 * @author Michal Nowierski
	 */
	public void typeBlogPostTitle(String blogPostTitle) {
		waitForElementByElement(blogTitleInput);
		waitForElementClickableByElement(blogTitleInput);
		blogTitleInput.sendKeys(blogPostTitle);
		PageObjectLogging.log(
			"typeBlogPostTitle",
			"type title to blog post: <b>"+blogPostTitle+"</b>", true
		);
	}

	/**
	 * @author Michal Nowierski
	 */
	public void clickOk() {
		waitForElementByElement(okButton);
		waitForElementClickableByElement(okButton);
		okButton.click();
		PageObjectLogging.log("clickOk", "click OK button", true);
	}

	public BlogPageObject clickOnPublishBlogPostButton() {
		waitForElementByElement(publishButtonGeneral);
		waitForElementClickableByElement(publishButtonGeneral);
		publishButtonGeneral.click();
		waitForElementByBy(editButtonBy);
		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true);
		return new BlogPageObject(driver);
	}
}
