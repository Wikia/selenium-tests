package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleSourceEditMode;

public class ArticleSourceModeTests extends TestTemplate{
	
	@Test
	public void RTE_001_Bold(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickBold();
		source.checkSourceContent("'''Bold text'''");
	}

}
