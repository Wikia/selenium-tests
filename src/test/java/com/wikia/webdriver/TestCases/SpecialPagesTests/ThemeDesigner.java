package com.wikia.webdriver.TestCases.SpecialPagesTests;

import java.util.Random;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.Special.SpecialFollowPageObject;
import com.wikia.webdriver.PageObjects.PageObject.Special.ThemeDesigner.SpecialThemeDesignerPageObject;

public class ThemeDesigner extends TestTemplate{
	
	private String themeName;
	
	@Test(groups={"themeDesigner001", "themeDesigner"})
	public void themeDesigner001_selectThemeFromFirstPage(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(3);
		designer.verifyThemeSelected(themeName);
	}
	
	@Test(groups={"themeDesigner002", "themeDesigner"})
	public void themeDesigner002_selectThemeFromMiddlePage(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(6);
		designer.verifyThemeSelected(themeName);
	}
	
	@Test(groups={"themeDesigner003", "themeDesigner"})
	public void themeDesigner003_selectThemeFromLastPage(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(10);
		designer.verifyThemeSelected(themeName);
	}
	
	@Test(groups={"themeDesigner004", "themeDesigner"})
	public void themeDesigner004_selectThemeSubmit(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Theme");
		themeName = designer.selectTheme(1);
		designer.verifyThemeSelected(themeName);
		designer.submitThemeSelection();
	}
	
	@Test(groups={"themeDesigner005", "themeDesigner"})
	public void themeDesigner005_customizeTab(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Customize");
		designer.verifyCustomizeTab();
	}
	
	@Test(groups={"themeDesigner005", "themeDesigner"})
	public void themeDesigner006_wordmarkTab(){
		CommonFunctions.logOut(driver);
		SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
		designer.openWikiPage();
		CommonFunctions.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		designer.openSpecialDesignerPage();
		designer.selectTab("Wordmark");
		designer.verifyWordmarkTab();
	}
}
