package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject.Tab;

/**
 * @ownership Content X-Wing
 */
public class ThemeDesigner extends NewTestTemplate {

  private String themeName;

  @BeforeMethod(alwaysRun = true)
  public void setWiki() {
    wikiURL = urlBuilder.getUrlForWiki("themedesignerautomation");
  }

  @Test(groups = {"ThemeDesigner001", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void selectThemeFromFirstSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(3);
    designer.verifyThemeSelected(themeName);
  }

  @Test(groups = {"ThemeDesigner002", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void selectThemeFromSecondSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(6);
    designer.verifyThemeSelected(themeName);
  }

  @Test(groups = {"ThemeDesigner003", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void selectThemeFromThirdSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(10);
    designer.verifyThemeSelected(themeName);
  }

  @Test(groups = {"ThemeDesigner004", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void submitSelectedTheme() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(1);
    designer.verifyThemeSelected(themeName);
    designer.submitTheme();
  }

  @Test(groups = {"ThemeDesigner005", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void verifyCustomizeTabElements() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.CUSTOMIZE);
    designer.openImagePicker();
    designer.uploadLargeImage();
    designer.verifyCustomizeTab();
  }

  @Test(groups = {"ThemeDesigner006", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void verifyWordmarkTabElements() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.WORDMARK);
    designer.verifyWordmarkTab();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1194 check that color choice dialog closes on click
   * outside of the dialog
   */
  @Test(groups = {"ThemeDesigner007", "ThemeDesigner"})
  @Execute(asUser = User.STAFF)
  public void closeColorDialogWithOutsideClick() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject(driver);
    designer.openSpecialDesignerPage(wikiURL);
    designer.selectTab(Tab.CUSTOMIZE);
    designer.openImagePicker();
    designer.clickOutsideImagePicker();
    designer.verifyImagePickerDisappeared();
  }
}
