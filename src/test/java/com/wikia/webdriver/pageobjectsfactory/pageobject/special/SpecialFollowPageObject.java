package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

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

	public void verifyFollowedImageVideo(String imageVideo) {
		boolean isPresent = false;
		for (WebElement elem:imagesList) {
			String title = elem.getAttribute("title");
			if (title.contains(imageVideo)){
				isPresent = true;
			}
		}
		Assertion.assertTrue(isPresent, "image " + imageVideo + " is not present on the following list");
		PageObjectLogging.log("verifyFollowedImageVideo", imageVideo + "is visible on followed list", true);
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
		PageObjectLogging.log("verifyFollowedBlog", userName + " blog is visible on followed list", true);
	}

	public void verifyFollowedBlogPost(String blogPostName) {
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-blogs']//a[contains(text(), '" + blogPostName + "')]");
		PageObjectLogging.log("verifyFollowedBlogPost", blogPostName + " blog post is visible on followed list", true);
	}
}
