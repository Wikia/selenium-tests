package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

public class BlogPageObject extends ArticlePageObject {

	@FindBy(css="div.author-details")
	private WebElement usernameField;
	@FindBy(css=".WikiaBlogPostHeader h1")
	private WebElement blogHeader;

	By image = By.cssSelector("img");
	By firstSpan = By.cssSelector("span:nth-child(2) a");
	By secondSpan = By.cssSelector("span:nth-child(3)");
	By thirdSpan = By.cssSelector("span:nth-child(4) a");

	public BlogPageObject(WebDriver driver) {
		super(driver);
	}

	public WatchPageObject unfollowBlogPage() {
		String url = urlBuilder.appendQueryStringToURL(getCurrentUrl(), "action=unwatch");
		getUrl(url);
		return new WatchPageObject(driver);
	}

	public void verifyBlogTitle(String title) {
		waitForElementByElement(blogHeader);
		Assertion.assertEquals(title, blogHeader.getText());
	}

	public String getBlogName() {
		return blogHeader.getText();
	}
}
