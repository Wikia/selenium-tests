package com.wikia.webdriver.TestCases.EventTrackingTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.EventsContent;
import com.wikia.webdriver.Common.ContentPatterns.PathsContent;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.EventTrackingVerifier;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;

/**
 * Those tests cover testing events from below wikia spreadsheet
 * https://docs.google.com/a/wikia-inc.com/spreadsheet/ccc?key=0
 * Ahq3QVBqbE1xdElQRmRvbk4tMUc3VXVmeWl6WlFWSEE#gid=4
 *
 * @author Michal 'justnpT' Nowierski
 */
public class EditorEventTrackingTests extends TestTemplate {

	/*
	 * TestCase001
	 * Verify if clicking on 'edit' button is tracked as edit-page event
	 * browser property must contain "eventTracking" String. eg. FFeventTracking
	 */
	@Test(groups={"EditorEventTrackingTests_001", "EditorEventTrackingTests"})
	public void EditorEventTrackingTests_001_verifyEditPageEvent()
	{
		CommonUtils.deleteDirectory(PathsContent.harDirPath);
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		WikiArticlePageObject article = new WikiArticlePageObject(driver);
		article.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		article.openRandomArticle();
		WikiArticleEditMode edit = article.edit();
		edit.enableWikiaTracker();
		edit.clickGalleryButton();
		try {
			Thread.sleep(10000); // this is required to make sure that netExport
									// has time to export the wanted har files
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		EventTrackingVerifier verifier = new EventTrackingVerifier(PathsContent.harDirPath);
		verifier.verifyEvent(EventsContent.editPageEvent);
		verifier.verifyEvent(EventsContent.openGalleryEvent);
	}
}
