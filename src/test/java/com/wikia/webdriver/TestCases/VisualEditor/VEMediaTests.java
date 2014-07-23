package com.wikia.webdriver.TestCases.VisualEditor;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorAddMediaDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * VE-1134 Adding Youtube Video
 * VE-1134 Adding Premium Video with full URL
 *
 */

public class VEMediaTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEMediaTests", "VEAddExternalVideoTests_001", "VEPreviewVideo"}
	)
	public void VEMediaTests_001_previewVideo() {
		String title = "Short film directed by Guy Ritchie starring David Beckham - H&M Spring 2013";
		String providerName = "youtube";

		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorAddMediaDialog mediaDialog =
			(VisualEditorAddMediaDialog) ve.openDialogFromMenu(InsertDialog.MEDIA);
		mediaDialog = mediaDialog.searchMedia("h");
		ve = mediaDialog.previewExistingMediaByTitle(title);
		ve.verifyPreviewVideoPlay(providerName);
	}
}
