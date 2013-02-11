package com.wikia.webdriver.TestCases.VideoTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class VetTests extends TestTemplate {

	@Test(groups = { "VetTests001", "VetTests" })
	public void Vet_Tests_001_createTop10list() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userName, Properties.password, driver);	
		WikiArticlePageObject article = wiki.openRandomArticle();
//		WikiArticleEditMode articleEdit = article.edit();
//		articleEdit.clickAddAvideo();
		VetComponentObject vet = new VetComponentObject(driver);
		vet.addVideoByUrl(VideoContent.youtubeVideoURL);
		VetOptionsComponentObject vetO = new VetOptionsComponentObject(driver);
		vetO.adjustPosition(1);
		vetO.adjustPosition(2);
		vetO.adjustPosition(3);
		vetO.adjustStyle(1);
		vetO.adjustStyle(2);
		vetO.adjustWith(200);
		vetO.adjustWith(400);
		vetO.setTitle("my video");
	}
}
