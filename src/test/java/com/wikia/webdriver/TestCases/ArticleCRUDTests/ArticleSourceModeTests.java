package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleSourceEditMode;

public class ArticleSourceModeTests extends TestTemplate{
	
	@Test(groups={"RTE_extended"})
	public void RTE_001_Bold(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickBold();
		source.checkSourceContent("'''Bold text'''");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_002_Italic(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickItalic();
		source.checkSourceContent("''Italic text''");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_003_InternalLink(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickInternalLink();
		source.checkSourceContent("[[Link title]]");
		source.clickOnPublishButton();
	}

	@Test(groups={"RTE_extended"})
	public void RTE_004_ExternalLink(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickExternalLink();
		source.checkSourceContent("[http://www.example.com link title]");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_005_HeadLine(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickLvl2Headline();
		source.checkSourceContent("\n== Headline text ==\n");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_006_EmbedFile(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickEmbedFile();
		source.checkSourceContent("[[File:Example.jpg]]");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_007_EmbedMedia(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickEmbedMedia();
		source.checkSourceContent("[[Media:Example.ogg]]");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_008_Math(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickMath();
		source.checkSourceContent("<math>Insert formula here</math>");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_009_Nowiki(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickNowiki();
		source.checkSourceContent("<nowiki>Insert non-formatted text here</nowiki>");
		source.clickOnPublishButton();
	}
	
	@Test(groups={"RTE_extended"})
	public void RTE_010_Signature(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickSignature();
		source.checkSourceContent("--~~~~");
		source.clickOnPublishButton();
	}	
	
	@Test(groups={"RTE_extended"})
	public void RTE_011_HLine(){
		WikiArticleSourceEditMode source = new WikiArticleSourceEditMode(driver, Global.DOMAIN);
		String pageName = "QAarticle"+source.getTimeStamp();
		source.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickHorizontalLine();
		source.checkSourceContent("\n----\n");
		source.clickOnPublishButton();
	}	
}
