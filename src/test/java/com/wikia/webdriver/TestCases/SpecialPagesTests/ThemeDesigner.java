package com.wikia.webdriver.TestCases.SpecialPagesTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.ThemeDesigner.SpecialThemeDesignerPageObject;

public class ThemeDesigner extends TestTemplate {

	private String themeName;

	@Test(groups={"ThemeDesigner001", "ThemeDesigner"})
	public void themeDesigner001_selectThemeFromFirstPage(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(3);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner002", "ThemeDesigner"})
	public void themeDesigner002_selectThemeFromMiddlePage(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(6);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner003", "ThemeDesigner"})
	public void themeDesigner003_selectThemeFromLastPage(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(10);
		designer.verifyThemeSelected(themeName);
	}

	@Test(groups={"ThemeDesigner004", "ThemeDesigner"})
	public void themeDesigner004_selectThemeSubmit(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(1);
		designer.verifyThemeSelected(themeName);
		designer.submitThemeSelection();
	}

	@Test(groups={"ThemeDesigner005", "ThemeDesigner"})
	public void themeDesigner005_customizeTab(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Customize");
		designer.verifyCustomizeTab();
	}

	@Test(groups={"ThemeDesigner006", "ThemeDesigner"})
	public void themeDesigner006_wordmarkTab(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Wordmark");
		designer.verifyWordmarkTab();
	}

	/**
	 * https://wikia-inc.atlassian.net/browse/DAR-1194
	 * check that color choosing dialog is closed by click outside of the dialog
	 */
	@Test(groups={"ThemeDesigner007", "ThemeDesigner"})
	public void themeDesigner007_closeColorDialogWithOutsideClick(){
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		login.loginAndVerifyOnWiki(Properties.userNameStaff, Properties.passwordStaff, Global.DOMAIN);
		designer.openSpecialDesignerPage();
		designer.selectTab("Customize");
		designer.verifyCustomizeTab();
		designer.openImagePicker();
		designer.clickOutsideImagePicker();
		designer.verifyImagePickerDisappeared();
	}
}
