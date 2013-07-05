package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import org.openqa.selenium.WebDriver;

public class SpecialFollowPageObject extends SpecialPageObject {

	public SpecialFollowPageObject(WebDriver driver) {
		super(driver);
	}

	public SpecialFollowPageObject openFollowingPage(){
		getUrl(Global.DOMAIN + URLsContent.specialFollow);
		PageObjectLogging.log("openFollowingPage", "following page opened", true);
		return new SpecialFollowPageObject(driver);
	}

	public void verifyFollowedArticle(String articleName){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-article']//a[contains(text(), '"+articleName+"')]");
		PageObjectLogging.log("verifyFollowedArticle", articleName + "is visible on followed list", true);
	}

	public void verifyFollowedImageVideo(String image){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-media']//a[@href='"+image.replace(Global.DOMAIN, "/")+"']");
		PageObjectLogging.log("verifyFollowedArticle", image + "is visible on followed list", true);
	}

	public void verifyFollowedBlog(String userName){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-blogs']//a[contains(text(), '"+userName+"')]");
		PageObjectLogging.log("verifyFollowedArticle", userName + " blog is visible on followed list", true);
	}

	public void verifyFollowedBlogPost(String bloPostName){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-blogs']//a[contains(text(), '"+bloPostName+"')]");
		PageObjectLogging.log("verifyFollowedArticle", bloPostName + " blog post is visible on followed list", true);
	}
}
