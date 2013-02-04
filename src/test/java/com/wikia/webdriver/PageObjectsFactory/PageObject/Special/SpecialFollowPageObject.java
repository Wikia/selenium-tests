package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class SpecialFollowPageObject extends BasePageObject{

	public SpecialFollowPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public SpecialFollowPageObject openFollowingPage(){
		getUrl(Global.DOMAIN+"wiki/Special:Following");
		PageObjectLogging.log("openFollowingPage", "following page opened", true, driver);
		return new SpecialFollowPageObject(driver);
	}
	
	public void verifyFollowedArticle(String articleName){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-article']//a[contains(text(), '"+articleName+"')]");
		PageObjectLogging.log("verifyFollowedArticle", articleName + "is visible on followed list", true);
	}
	
	public void verifyFollowedImageVideo(String image){
		waitForElementByXPath("//ul[@id='wikiafollowedpages-special-heading-media']//a[contains(text(), '"+image+"')]");
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
