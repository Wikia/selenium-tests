package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class BlogListPageObject extends BasePageObject{

	@FindBy(xpath="//h2[contains(text(), 'Blog posts')]")
	private WebElement blogListHeader;
	@FindBy(css="a[data-id='createblogpost']")
	private WebElement createBlogPostButton;
	@FindBy(css="#WikiaBlogListingPost")
	private List<WebElement> blogList;
	
	
	public BlogListPageObject(WebDriver driver) {
		super(driver);
	}

	public BlogListPageObject verifyBlogListPage(String listName){
		verifyURL(Global.DOMAIN+
				URLsContent.blogList.replace("%listName%", listName));
		waitForElementByElement(blogListHeader);
		waitForElementByElement(createBlogPostButton);
		return this;
	}
	
	public BlogListPageObject verifyBlogList(){
		for (WebElement elem:blogList){
			//header with link to blog post
			elem.findElement(By.cssSelector("h1 a[href*='/wiki/User_blog:']"));
			//comments bubble
			elem.findElement(By.cssSelector(".comments"));
		}
		return this;
	}
	
	public BlogListPageObject followBlogListingPage(String name){
		getUrl(Global.DOMAIN+"wiki/Blog:"+name+"?action=watch");
		scrollAndClick(followSubmit);
		waitForElementByElement(followedButton);
		return this;
	}
}
