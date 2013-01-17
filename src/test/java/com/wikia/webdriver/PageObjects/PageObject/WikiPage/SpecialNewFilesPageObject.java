package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class SpecialNewFilesPageObject extends WikiBasePageObject{


	@FindBy(css="header[id='WikiaPageHeader'] a.wikia-button") 
	private WebElement AddAphotoButton;
	@FindBy(css="input[name='wpUploadFile']") 
	private WebElement BrowseForFileInput;
	@FindBy(css="div.step-1 input[value*='Upload']") 
	private WebElement UploadFileInput;
	@FindBy(css="div.advanced a") 
	private WebElement MoreOrFewerOptions;
	@FindBy(css="div.toggles input[name='wpIgnoreWarning']") 
	private WebElement IgnoreAnyWarnings;
	@FindBy(css="section[id='UploadPhotosWrapper']") 
	private WebElement UploadPhotoDialog;
	
	@FindBys(@FindBy(css=".wikia-gallery .image"))
	private List<WebElement> imagesNewFiles;
	
	private String WikiaPreviewImgCssSelector = "div.wikia-gallery span.wikia-gallery-item img";

	public SpecialNewFilesPageObject(WebDriver driver, String Domain) {
		
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}

	public void clickOnAddaPhoto() {
		waitForElementByElement(AddAphotoButton);
		clickAndWait(AddAphotoButton);
		PageObjectLogging.log("ClickOnAddaPhoto", "Click on add a photo button", true, driver);
	}
	
	public void clickOnUploadaPhoto() {
		waitForElementByElement(UploadFileInput);
		clickAndWait(UploadFileInput);
		PageObjectLogging.log("ClickOnUploadaPhoto", "Click on upload a photo button", true, driver);
	}
	
	public void clickOnMoreOrFewerOptions() {
		waitForElementByElement(MoreOrFewerOptions);
		clickAndWait(MoreOrFewerOptions);
		PageObjectLogging.log("ClickOnMoreOrFewerOptions", "Click on More or Fewer options (depends on which of those two is currently visible)", true, driver);
		
	}
	public void checkIgnoreAnyWarnings() {
		waitForElementByElement(IgnoreAnyWarnings);
		clickAndWait(IgnoreAnyWarnings);
		PageObjectLogging.log("CheckIgnoreAnyWarnings", "Check 'Ignore Any Warnings' option", true, driver);
		
	}

	/**
	 * Selects given image in upload browser. 
	 * 
	 * @author Michal Nowierski
	 * ** @param file file to Be uploaded
	 * <p> Look at folder acceptancesrc/src/test/resources/ImagesForUploadTests - this is where those files are stored
	 *  */ 
	public void typeInFileToUploadPath(String file){
	sendKeys(BrowseForFileInput, System.getProperty("user.dir")+"\\src\\test\\resources\\ImagesForUploadTests\\"+file);
	}

	public void waitForFile(String FileName) {
		driver.navigate().refresh();
		waitForValueToBePresentInElementsAttributeByCss(WikiaPreviewImgCssSelector, "src", FileName);
		PageObjectLogging.log("waitForFile", "Verify if "+FileName+" has been succesfully uploaded", true, driver);
	}
	
	public String followRandomImage(){
		List<String> hrefs = new ArrayList<String>();
		for (WebElement elem:imagesNewFiles)		{
			hrefs.add(elem.getAttribute("data-image-name"));
		}
		Random r = new Random();
		String imageName = hrefs.get((r.nextInt(hrefs.size()-1))+1);
		getUrl(Global.DOMAIN+"wiki/File:"+imageName+"?action=watch");
		clickAndWait(followSubmit);
		waitForElementByElement(followedButton);
		return imageName;
	}
	
	public void unfollowImage(String imageName){
		getUrl(Global.DOMAIN+"wiki/File:"+imageName+"?action=unwatch");
		clickAndWait(followSubmit);
		waitForElementByElement(unfollowedButton);
	}













	
}
