package com.wikia.webdriver.TestCases.EventTrackingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.PathsContent;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.EventTrackingVerifier;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * Those tests cover testing events from below wikia spreadsheet
 * https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0Ahq3QVBqbE1xdElQRmRvbk4tMUc3VXVmeWl6WlFWSEE#gid=4
 * @author Michal 'justnpT' Nowierski
 */
public class EditorEventTrackingTests extends TestTemplate {

	/*
	 * TestCase001
	 * Verify if clicking on 'edit' button is tracked as edit-page event
	 */
	@Test(groups={"EditorEventTrackingTests_001", "EditorEventTrackingTests"})
	public void EditorEventTrackingTests_001_verifyEditPageEvent()
	{
	
		//
		EventTrackingVerifier verifier = new EventTrackingVerifier(PathsContent.harFilePath);
		verifier.verifyEvent("editor-ck*view*edit-page");
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			verifier.verifyEvent("editor-ck*view*edit-page");
		CommonFunctions.logOut(driver);
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		WikiArticlePageObject article = wiki.openRandomArticle();
		WikiArticleEditMode edit = article.edit();
		edit.enableWikiaTracker();
		edit.clickGalleryButton();
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
