package com.wikia.webdriver.TestCases.VisualEditor;

import java.util.ArrayList;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.DataProvider.VisualEditorDataProvider.InsertDialog;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorOptionsDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorReviewChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.VisualEditorDialogs.VisualEditorSaveChangesDialog;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.VisualEditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 */

public class VECategoryTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String articleName;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
		articleName = PageContent.articleNamePrefix + base.getTimeStamp();
	}

	@Test(
		groups = {"VECategoryTests", "VECategoryTests_001", "VEAddCategory"}
	)
	public void VECategoryTests_001_AddNewCategory() {
		String testCategory = "ca";
		ArrayList<String> categoryWikiTexts = new ArrayList<>();
		categoryWikiTexts.add("[[Category:Ca]]");

		VisualEditorPageObject ve = base.launchVisualEditorWithMainEdit(articleName, wikiURL);
		VisualEditorOptionsDialog optionsDialog =
			(VisualEditorOptionsDialog) ve.openDialogFromMenu(InsertDialog.CATEGORIES);
		optionsDialog.addCategory(testCategory);
		ve = optionsDialog.clickApplyChangesButton();
		VisualEditorSaveChangesDialog saveDialog = ve.clickPublishButton();
		VisualEditorReviewChangesDialog reviewDialog = saveDialog.clickReviewYourChanges();
		reviewDialog.verifyAddedDiffs(categoryWikiTexts);
		saveDialog = reviewDialog.clickReturnToSaveFormButton();
		saveDialog.savePage();
	}
}
