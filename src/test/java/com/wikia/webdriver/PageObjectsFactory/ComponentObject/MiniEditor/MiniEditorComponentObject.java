package com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

public class MiniEditorComponentObject extends WikiBasePageObject{

	public MiniEditorComponentObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="body#bodyContent")
	private WebElement messageBodyField;
	@FindBy(css=".cke_contents iframe")
	public WebElement miniEditorIframe;
	@FindBy(css=".RTEImageButton .cke_icon")
	private WebElement addImageButton;
	@FindBy(css="img.image.thumb")
	private WebElement imageInMessageEditMode;
	@FindBy(css=".RTEVideoButton .cke_icon")
	private WebElement addVideoButton;
	@FindBy(css="img.video.thumb")
	private WebElement videoInMessageEditMode;
	@FindBy(css=".cke_toolbar_formatmini span.cke_button.cke_button_link a .cke_icon")
	private WebElement addLinkButton;
	@FindBy(css="input[value='ext']")
	private WebElement externalLinkOption;
	@FindBy(css="input.cke_dialog_ui_input_text")
	private WebElement targetPageOrURL;
	@FindBy(css="p.link-type-note span")
	private WebElement linkPageStatus;
	@FindBy(css="span.cke_dialog_ui_button")
	private WebElement linkModalOkButton;
	@FindBy (css=".loading-throbber")
	private WebElement loader;
	@FindBy (css="[id*='_uiElement'] .link-yes")
	private WebElement linkExistsIcon;
	@FindBy (css="[id*='_uiElement'] .external")
	private WebElement linkExternalIcon;

	public void writeMiniEditor(String text){
		waitForElementByElement(messageBodyField);
		messageBodyField.sendKeys(text);
	}
	
	public void writeMiniEditor(Keys key){
		waitForElementByElement(messageBodyField);
		messageBodyField.sendKeys(key);
	}
	 
	public void writeStylesMiniEditor(String message, String special){
		String specialKey = "Not Initialized";
		if (special.equals("Bold")) {
			specialKey = "b";
		}
		if (special.equals("Italic")) {
			specialKey = "i";
		}
		waitForElementByElement(messageBodyField);
		messageBodyField.sendKeys(message);
		messageBodyField.sendKeys(Keys.LEFT_CONTROL + "a" );
		messageBodyField.sendKeys(Keys.LEFT_CONTROL + specialKey );	
	}
	
	public void addVideoMiniEditor(String url){
		waitForElementByElement(addVideoButton);
		clickAndWait(addVideoButton);
		VetAddVideoComponentObject vetAddingVideo = new VetAddVideoComponentObject(driver);
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.submit();
		verifyVideoMiniEditor();
	}
	
	protected void clickPost(){}
	
	protected void clickPreview(){}
	
	
	protected void verifyImageMiniEditor(){
		waitForElementByElement(miniEditorIframe);
		driver.switchTo().frame(miniEditorIframe);
		waitForElementByElement(imageInMessageEditMode);
		driver.switchTo().defaultContent();
	}
	
	public void verifyVideoMiniEditor(){
		waitForElementByElement(miniEditorIframe);
		driver.switchTo().frame(miniEditorIframe);
		waitForElementByElement(videoInMessageEditMode);
		driver.switchTo().defaultContent();
	}

	public void addExternalLink(String externalLink){
		waitForElementByElement(addLinkButton);
		clickAndWait(addLinkButton);
		waitForElementByElement(externalLinkOption);
		clickAndWait(externalLinkOption);
		targetPageOrURL.sendKeys(externalLink);
		waitForElementByElement(linkExternalIcon);
		clickAndWait(linkModalOkButton);
	}

	public void addInternalLink(String internalLink){
		waitForElementByElement(addLinkButton);
		clickAndWait(addLinkButton);
		waitForElementByElement(targetPageOrURL);
		targetPageOrURL.sendKeys(internalLink);
		waitForElementByElement(linkExistsIcon);
		waitForElementByElement(linkModalOkButton);
		clickAndWait(linkModalOkButton);
	}

	public void clearContent(){
		messageBodyField.clear();
	}

	public VetAddVideoComponentObject clickAddVideo(){
		waitForElementByElement(addVideoButton);
		clickAndWait(addVideoButton);
		return new VetAddVideoComponentObject(driver);
	}

	public PhotoAddComponentObject clickAddImage() {
		waitForElementByElement(addImageButton);
		clickAndWait(addImageButton);
		return new PhotoAddComponentObject(driver);
	}

	public void waitForEditorReady() {
		waitForElementNotVisibleByElement(loader);
	}
}
