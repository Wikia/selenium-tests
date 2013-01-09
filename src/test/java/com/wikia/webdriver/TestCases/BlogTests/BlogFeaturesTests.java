package com.wikia.webdriver.TestCases.BlogTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.BlogPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateBlogPageObject;

public class BlogFeaturesTests extends TestTemplate{

	@Test(groups={"Blog_001", "BlogFeatures"})
	public void Blog_001_AddingGallery(){
		SpecialCreateBlogPageObject blog = new SpecialCreateBlogPageObject(driver, Global.DOMAIN, "");
		blog.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password);
//		blog = blog.createBlogFormUrl(blogPostTitle)
	}
	
	@Test(groups={"Blog_002", "BlogFeatures"})
	public void Blog_002_AddingSlideshow(){}
	
	@Test(groups={"Blog_003", "BlogFeatures"})
	public void Blog_003_AddingSlider(){}
	
	@Test(groups={"Blog_004", "BlogFeatures"})
	public void Blog_004_AddingVideo(){}
	
	@Test(groups={"Blog_005", "BlogFeatures"})
	public void Blog_005_AddingImage(){}
	
}
