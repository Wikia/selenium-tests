package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog.BlogListPageObject;

public class SpecialCreateBlogListingPageObject extends BasePageObject{

	public SpecialCreateBlogListingPageObject(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="blogPostTitle")
	private WebElement blogListingPageTitle;
	
	@FindBys(@FindBy(id="#categoryCloudSection1 span"))
	private List<WebElement> categoryList;

	@FindBy(id="wpCategoryInput1")
	private WebElement categoryField;
	
	@FindBy(id="wpCategoryButton")
	private WebElement categorySubmit;
	
	@FindBy(id="blogListingCalculateMatches")
	private WebElement calculateButton;
	
	@FindBy(css="select[name='blogListingSortBy']")
	private WebElement sortDropDown;
	
	@FindBy(id="wpSave")
	private WebElement savePageButton;
//	
//	@FindBy(css="")
//	private WebElement blogListingPageTitle;
	
	public SpecialCreateBlogListingPageObject typeTitle(String title){
		waitForElementByElement(blogListingPageTitle);
		blogListingPageTitle.sendKeys(title);
		PageObjectLogging.log("typeTitle", title+" title typed", true);
		return this;
	}
	
	public SpecialCreateBlogListingPageObject openCreateBlogListingPage(){
		getUrl(Global.DOMAIN+URLsContent.specialAddBlogListingPage);
		PageObjectLogging.log("openCreateBlogListingPage", "create blog listing page opened", true);
		return this;
	}
	
	public BlogListPageObject clickSavePageButton(){
		waitForElementByElement(savePageButton);
		PageObjectLogging.log("clickSavePageButton", "save page button clicked", true);
		return new BlogListPageObject(driver);
	}

}
