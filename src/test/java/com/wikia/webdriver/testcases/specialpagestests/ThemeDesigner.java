package com.wikia.webdriver.testcases.specialpagestests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner.SpecialThemeDesignerPageObject.Tab;

@Test(groups = "ThemeDesigner")
@Execute(onWikia = "themedesignerautomation", asUser = User.THEME_DESIGNER)
public class ThemeDesigner extends NewTestTemplate {

  private String themeName;

  @Test
  public void selectThemeFromFirstSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.THEME);

    designer.verifyThemeSelected(designer.selectTheme(3));
  }

  @Test
  public void selectThemeFromSecondSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.THEME);

    designer.verifyThemeSelected(designer.selectTheme(6));
  }

  @Test
  public void selectThemeFromThirdSet() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(10);

    designer.verifyThemeSelected(themeName);
  }

  @Test
  public void submitSelectedTheme() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.THEME);
    themeName = designer.selectTheme(1);
    designer.verifyThemeSelected(themeName);

    designer.submitTheme();
  }

  @Test
  public void verifyCustomizeTabElements() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.CUSTOMIZE);
    designer.openImagePicker();
    designer.uploadLargeImage();

    designer.verifyCustomizeTab();
  }

  @Test
  public void verifyWordmarkTabElements() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.WORDMARK);

    designer.verifyWordmarkTab();
  }

  /**
   * https://wikia-inc.atlassian.net/browse/DAR-1194 color choice dialog closes on click outside of
   * the dialog
   */
  @Test
  public void closeColorDialogWithOutsideClick() {
    SpecialThemeDesignerPageObject designer = new SpecialThemeDesignerPageObject().open();
    designer.selectTab(Tab.CUSTOMIZE);
    designer.openImagePicker();
    designer.clickOutsideImagePicker();

    designer.verifyImagePickerDisappeared();
  }
}
