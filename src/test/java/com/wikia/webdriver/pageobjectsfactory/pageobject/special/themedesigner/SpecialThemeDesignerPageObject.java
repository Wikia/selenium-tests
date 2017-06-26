package com.wikia.webdriver.pageobjectsfactory.pageobject.special.themedesigner;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonUtils;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialThemeDesignerPageObject extends WikiBasePageObject {

  String tabSelector = "a[rel='%tabName%Tab']";
  String selectedTabSelector = "li.selected a[rel='%tabName%Tab']";
  @FindBy(css = ".save:not([disabled=true])")
  private WebElement saveButton;
  @FindBy(css = ".save[disabled]")
  private WebElement saveButtonDisabled;
  // theme tab
  @FindBys(@FindBy(css = "li[data-theme]"))
  private List<WebElement> themes;
  @FindBy(css = ".next.chevron")
  private WebElement nextButton;
  // customize tab
  @FindBy(css = ".color-body")
  private WebElement bgColor;
  @FindBy(css = ".background-image")
  private WebElement bgImage;
  @FindBy(css = "#tile-background")
  private WebElement bgTileOption;
  @FindBy(css = "#fix-background")
  private WebElement bgFixOption;
  @FindBy(css = "#not-split-background")
  private WebElement bgNoSplitOption;
  @FindBy(css = ".ThemeDesignerPicker.image")
  private WebElement bgImagePicker;
  @FindBy(css = ".color-buttons")
  private WebElement pgButtons;
  @FindBy(css = ".color-links")
  private WebElement pgLinks;
  @FindBy(css = ".color-header")
  private WebElement pgHeader;
  @FindBy(css = ".color-page")
  private WebElement pgColor;
  @FindBy(css = ".page h1")
  private WebElement pgSectionTitle;
  // wordmark tab
  @FindBy(css = "#WordMarkUploadForm [value=Upload]")
  private WebElement wordmarkSubmit;
  @FindBy(css = "#WordMarkUploadFile")
  private WebElement wordmarkUpload;
  @FindBy(css = "#FaviconUploadForm [value=Upload]")
  private WebElement faviconSubmit;
  @FindBy(css = "#FaviconUploadFile")
  private WebElement faviconUpload;
  @FindBy(css = "ul[style='margin-left: -760px;']")
  private WebElement secondThemesSet;
  @FindBy(css = "ul[style='margin-left: -1520px;']")
  private WebElement thirdThemesSet;
  @FindBy(id = "backgroundImageUploadFile")
  private WebElement fileUploadInput;
  @FindBy(css = "#BackgroundImageForm [type='submit']")
  private WebElement imageSubmit;

  public SpecialThemeDesignerPageObject open() {
    getUrl(urlBuilder.getUrlForPage(URLsContent.SPECIAL_THEME_DESIGNER));

    return this;
  }

  /**
   * select theme on Special:ThemeDesigner page
   */
  public String selectTheme(int number) {
    wait.forElementClickable(themes.get(0));
    if (number < 5) {
      scrollAndClick(themes.get(number));
    }
    if (number >= 5 && number < 10) {
      scrollAndClick(nextButton);
      wait.forElementClickable(secondThemesSet);
      scrollAndClick(themes, number);
    }
    if (number == 10) {
      scrollAndClick(nextButton);
      wait.forElementClickable(secondThemesSet);
      scrollAndClick(nextButton);
      wait.forElementClickable(thirdThemesSet);
      scrollAndClick(themes, number);
    }
    String themeName =
        themes.get(number).getAttribute("data-theme").toLowerCase();
    PageObjectLogging.log("selectTheme", "theme " + themeName + " selected", true);
    return themeName;
  }

  public void verifyThemeSelected(String themeName) {
    wait.forElementVisible(By.cssSelector("li.selected[data-theme='" + themeName + "']"));
    Assertion.assertEquals((String) jsActions.execute("ThemeDesigner.settings.theme"), themeName);
    PageObjectLogging.log("verifyThemeSelected", "theme " + themeName + " selection verified",
        true);
  }

  public void submitTheme() {
    wait.forElementClickable(saveButton);
    scrollAndClick(saveButton);
    wait.forElementVisible(saveButtonDisabled);
    PageObjectLogging.log("submitSelection", "selection of new skin saved", true);
  }

  public void uploadLargeImage() {
    fileUploadInput.sendKeys(CommonUtils.getAbsolutePathForFile(
        ClassLoader.getSystemResource("ImagesForUploadTests/2000x150.png").getPath()));
    imageSubmit.click();
  }

  public void selectTab(Tab tabName) {
    WebElement tab = wait
        .forElementVisible(By.cssSelector(tabSelector.replace("%tabName%", tabName.toString())));
    scrollAndClick(tab);
    wait.forElementVisible(
        By.cssSelector(selectedTabSelector.replace("%tabName%", tabName.toString())));
    PageObjectLogging.log("selectTab", tabName.toString() + " tab has been selected", true);
  }

  /**
   * no split option appears only for backgrounds that are over 2000px wide
   */
  public void verifyCustomizeTab() {
    wait.forElementVisible(bgColor);
    wait.forElementVisible(bgImage);
    wait.forElementVisible(bgImage);
    wait.forElementVisible(bgTileOption);
    wait.forElementVisible(bgFixOption);
    wait.forElementVisible(bgNoSplitOption);
    wait.forElementVisible(pgButtons);
    wait.forElementVisible(pgLinks);
    wait.forElementVisible(pgHeader);
    wait.forElementVisible(pgColor);
  }

  public void verifyWordmarkTab() {
    wait.forElementVisible(wordmarkSubmit);
    wait.forElementVisible(wordmarkUpload);
    wait.forElementVisible(faviconSubmit);
    wait.forElementVisible(faviconUpload);
  }

  public void openImagePicker() {
    wait.forElementVisible(bgImage);
    bgImage.click();
    wait.forElementVisible(bgImagePicker);
    PageObjectLogging.log("openImagePicker", "image picker opened", true, driver);
  }

  public void clickOutsideImagePicker() {
    wait.forElementVisible(pgSectionTitle);
    pgSectionTitle.click();
    PageObjectLogging.log("clickOutsideImageSelectionDialog", "clicked outside Image Picker", true);
  }

  public void verifyImagePickerDisappeared() {
    waitForElementNotVisibleByElement(bgImagePicker);
    PageObjectLogging.log("verifyImagePickerDisappeared", "Image Picker is invisible", true);
  }

  public enum Tab {
    THEME, CUSTOMIZE, WORDMARK
  }

}
