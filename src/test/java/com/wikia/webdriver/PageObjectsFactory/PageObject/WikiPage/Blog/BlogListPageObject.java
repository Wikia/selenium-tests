package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

public class BlogListPageObject extends BasePageObject{

	@FindBy(xpath="//h2[contains(text(), 'Blog posts')]")
	private WebElement blogListHeader;
	
	
	public BlogListPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyBlogListPage(String listName){
		verifyURL(Global.DOMAIN+
				URLsContent.blogList.replace("%listName%", listName));
		waitForElementByElement(blogListHeader);
		
	}
}
