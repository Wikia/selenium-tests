package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

public class SpecialCreateBlogPageObject extends WikiArticleEditMode {

	
	@FindBy(css="input[name='wpTitle']")
	private WebElement blogTitleInput;
	@FindBy(css="#ok")
	private WebElement okButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;
	
	public SpecialCreateBlogPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	
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
	
	public SpecialCreateBlogPageObject createBlogFormUrl(String blogPostTitle){
		getUrl(Global.DOMAIN+"wiki/Special:CreateBlogPage");
		typeBlogPostTitle(blogPostTitle);
		clickOk();
		return new SpecialCreateBlogPageObject(driver);
	}
}
