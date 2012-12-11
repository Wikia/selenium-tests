package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialCreateBlogPageObject extends WikiArticleEditMode {

	
	@FindBy(css="input[name='wpTitle']")
	private WebElement blogTitleInput;
	@FindBy(css="#ok")
	private WebElement okButton;
	@FindBy(css="input.control-button")
	private WebElement publishButtonGeneral;
	
	public SpecialCreateBlogPageObject(WebDriver driver, String Domain,
			String wikiArticle) {
		super(driver, Domain, wikiArticle);
		this.articlename = wikiArticle;
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
//		clickAndWait(publishButtonGeneral);
		jQueryClick("input.control-button");
		waitForElementByElement(editButton);
		PageObjectLogging.log("ClickOnPublishButton", "Click on 'Publish' button", true, driver);
	
		return new BlogPageObject(driver, Domain, articlename);
	}

}
