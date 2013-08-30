package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.ThemeDesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.ThemeDesigner.SpecialThemeDesignerPageObject.Tab;

public class ThemeDesigner extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	private String themeName;

	@Test(groups={"ThemeDesigner001", "ThemeDesigner"})
	public void themeDesigner001_selectThemeFromFirstPage(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Theme);
		themeName = designer.selectTheme(3);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner002", "ThemeDesigner"})
	public void themeDesigner002_selectThemeFromMiddlePage(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Theme);
		themeName = designer.selectTheme(6);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner003", "ThemeDesigner"})
	public void themeDesigner003_selectThemeFromLastPage(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Theme);
		themeName = designer.selectTheme(10);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner004", "ThemeDesigner"})
	public void themeDesigner004_selectThemeSubmit(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Theme);
		themeName = designer.selectTheme(1);
		designer.verifyThemeSelected(themeName);
		designer.submitThemeSelection();
	}

	@Test(groups={"ThemeDesigner005", "ThemeDesigner"})
	public void themeDesigner005_customizeTab(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Customize);
		designer.verifyCustomizeTab();
	}

	@Test(groups={"ThemeDesigner006", "ThemeDesigner"})
	public void themeDesigner006_wordmarkTab(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Wordmark);
		designer.verifyWordmarkTab();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1194
	 * check that color choosing dialog is closed by click outside of the dialog
	 */
	@Test(groups={"ThemeDesigner007", "ThemeDesigner"})
	public void themeDesigner007_closeColorDialogWithOutsideClick(){
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openSpecialDesignerPage(wikiURL);
		designer.selectTab(Tab.Customize);
		designer.verifyCustomizeTab();
		designer.openImagePicker();
		designer.clickOutsideImagePicker();
		designer.verifyImagePickerDisappeared();
	}
}
