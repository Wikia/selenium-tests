package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.ThemeDesigner;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class SpecialThemeDesignerPageObject extends WikiBasePageObject{

	@FindBy(css=".save")
	private WebElement saveButton;
	//theme tab
	@FindBys(@FindBy(css="li[data-theme]"))
	private List<WebElement> themes;
	@FindBy(css=".next.chevron")
	private WebElement nextButton;
	@FindBy(css=".previous.chevron")
	private WebElement previousButton;
	@FindBy(css=".PreviewFrame")
	private WebElement previewFrame;
	//customize tab
	@FindBy(css=".color-body")
	private WebElement bgColor;
	@FindBy(css=".background-image")
	private WebElement bgImage;
	@FindBy(css=".ThemeDesignerPicker.image")
	private WebElement bgImagePicker;
	@FindBy(css=".color-buttons")
	private WebElement pgButtons;
	@FindBy(css=".color-links")
	private WebElement pgLinks;
	@FindBy(css=".color-header")
	private WebElement pgHeader;
	@FindBy(css=".color-page")
	private WebElement pgColor;
	@FindBy(css=".page h1")
	private WebElement pgSectionTitle;
	//wordmark tab
	@FindBy(css="#WordMarkUploadForm [value=Upload]")
	private WebElement wordmarkSubmit;
	@FindBy(css="#WordMarkUploadFile")
	private WebElement wordmarkUpload;
	@FindBy(css="#FaviconUploadForm [value=Upload]")
	private WebElement faviconSubmit;
	@FindBy(css="#FaviconUploadFile")
	private WebElement faviconUpload;
	@FindBy(css="ul[style='margin-left: -760px;']")
	private WebElement secondThemesSet;
	@FindBy(css="ul[style='margin-left: -1520px;']")
	private WebElement thirdThemesSet;

	public SpecialThemeDesignerPageObject(WebDriver driver) {
		super(driver);
	}

	public void openSpecialDesignerPage(){
		getUrl(Global.DOMAIN+"wiki/Special:ThemeDesigner");
		PageObjectLogging.log("openSpecialDesignerPage", "special designer page opened", true, driver);
	}

	/**
	 * select theme on Special:ThemeDesigner page
	 * @param number
	 */
	public String selectTheme(int number){
		waitForElementByElement(themes.get(0));
		if (number <5){
			waitForElementByElement(themes.get(number));
			scrollAndClick(themes.get(number));
		}
		if(number >=5 && number <10){
			scrollAndClick(nextButton);
			waitForElementByElement(secondThemesSet);
			waitForElementByElement(themes.get(number));
			scrollAndClick(themes.get(number));
		}
		if(number==10){
			scrollAndClick(nextButton);
			waitForElementByElement(secondThemesSet);
			waitForElementByElement(themes.get(7));
			scrollAndClick(nextButton);
			waitForElementByElement(thirdThemesSet);
			waitForElementByElement(themes.get(number));
			scrollAndClick(themes.get(number));
		}
		String themeName = themes.get(number).findElement(By.cssSelector("label")).getText().toLowerCase();
		PageObjectLogging.log("selectTheme", "theme "+themeName+" selected", true);
		return themeName;
	}

	public void verifyThemeSelected(String themeName){
		waitForElementByCss("li.selected[data-theme='"+themeName+"']");
		Assertion.assertEquals(themeName, executeScriptRet("ThemeDesigner.settings.theme"));
		PageObjectLogging.log("verifyThemeSelected", "theme "+themeName+" selection verified", true);
	}

	public void submitThemeSelection(){
		scrollAndClick(saveButton);
		waitForElementByElement(editButton);
		PageObjectLogging.log("submitSelection", "selection of new skin saved", true);
	}

	public void selectTab(String tabName){
		WebElement tab = waitForElementByCss("a[rel='"+tabName+"Tab']");
		scrollAndClick(tab);
		waitForElementByCss("li.selected a[rel='"+tabName+"Tab']");
		PageObjectLogging.log("submitSelection", "selection of new skin saved", true);
	}

	public void verifyCustomizeTab(){
		waitForElementByElement(bgColor);
		waitForElementByElement(bgImage);
		waitForElementByElement(pgButtons);
		waitForElementByElement(pgLinks);
		waitForElementByElement(pgHeader);
		waitForElementByElement(pgColor);
	}

	public void verifyWordmarkTab(){
		waitForElementByElement(wordmarkSubmit);
		waitForElementByElement(wordmarkUpload);
		waitForElementByElement(faviconSubmit);
		waitForElementByElement(faviconUpload);
	}

	public void openImagePicker() {
		waitForElementByElement(bgImage);
		bgImage.click();
		waitForElementByElement(bgImagePicker);
		PageObjectLogging.log("openImagePicker", "image picker opened", true, driver);
	}

	public void clickOutsideImagePicker() {
		waitForElementByElement(pgSectionTitle);
		pgSectionTitle.click();
		PageObjectLogging.log("clickOutsideImageSelectionDialog", "clicked outside Image Picker", true);
	}

	public void verifyImagePickerDisappeared() {
		waitForElementNotVisibleByElement(bgImagePicker);
		PageObjectLogging.log("verifyImagePickerDisappeared", "Image Picker is invisible", true);
	}
}
