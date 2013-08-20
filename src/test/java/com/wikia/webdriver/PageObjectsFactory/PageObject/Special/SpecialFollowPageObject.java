package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialFollowPageObject extends SpecialPageObject {

	@FindBy(css="#wikiafollowedpages-special-heading-blogs span>a")
	private List<WebElement> blogList;
	@FindBy(css="#wikiafollowedpages-special-heading-media span>a")
	private List<WebElement> imagesList;

	public SpecialFollowPageObject(WebDriver driver, String wikiURL) {
		super(driver);
		getUrl(wikiURL + URLsContent.specialFollow);
	}

	public void verifyFollowedArticle(String articleName) {
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-article']//a[contains(text(), '" + articleName + "')]");
		PageObjectLogging.log("verifyFollowedArticle", articleName + "is visible on followed list", true);
	}

	public void verifyFollowedImageVideo(String image) {
		boolean isPresent = false;
		for (WebElement elem:imagesList) {
			String title = elem.getAttribute("title");
			if (title.contains(image)){
				isPresent = true;
			}
		}
		Assertion.assertTrue(isPresent, "image " + image + " is not present on the following list");
		PageObjectLogging.log("verifyFollowedArticle", image + "is visible on followed list", true);
	}

	public void verifyFollowedBlog(String userName, String blogTitle) {
		boolean isPresent = false;
		for (WebElement elem:blogList) {
			String title = elem.getAttribute("title");
			if (title.contains(userName) && title.contains(blogTitle)) {
				isPresent = true;
			}
		}
		Assertion.assertTrue(isPresent, "blog " + blogTitle + " is not present on the following list");
		PageObjectLogging.log("verifyFollowedArticle", userName + " blog is visible on followed list", true);
	}

	public void verifyFollowedBlogPost(String bloPostName) {
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-blogs']//a[contains(text(), '" + bloPostName + "')]");
		PageObjectLogging.log("verifyFollowedArticle", bloPostName + " blog post is visible on followed list", true);
	}
}
