package com.wikia.webdriver.PageObjects.PageObject.WikiPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ibm.icu.util.CalendarAstronomer.Horizon;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.WikiBasePageObject;

public class WikiArticleSourceEditMode extends WikiBasePageObject{

	
	@FindBy(css="#mw-editbutton-bold")
	private WebElement bold;
	@FindBy(css="#mw-editbutton-italic")
	private WebElement italic;
	@FindBy(css="#mw-editbutton-link")
	private WebElement internalLink;
	@FindBy(css="#mw-editbutton-extlink")
	private WebElement externalLink;
	@FindBy(css="#mw-editbutton-headline")
	private WebElement lvl2headLine;
	@FindBy(css="#mw-editbutton-image")
	private WebElement embedFile;
	@FindBy(css="#mw-editbutton-media")
	private WebElement embedMedia;
	@FindBy(css="#mw-editbutton-math")
	private WebElement math;
	@FindBy(css="#mw-editbutton-nowiki")
	private WebElement nowiki;
	@FindBy(css="#mw-editbutton-signature")
	private WebElement signature;
	@FindBy(css="#mw-editbutton-hr")
	private WebElement hline;
	@FindBy(css="#mw-editbutton-wmu")
	private WebElement photo;
	@FindBy(css="#mw-editbutton-wpg")
	private WebElement gallery;
	@FindBy(css="#mw-editbutton-vet")
	private WebElement video;
	@FindBy(css=".cke_toolbar_source .cke_toolbar_expand")
	private WebElement more;
	
	@FindBy(css=".cke_source")
	private WebElement sourceModeTextArea;
	
	public WikiArticleSourceEditMode(WebDriver driver, String Domain) {
		super(driver, Domain);
		PageFactory.initElements(driver, this);
	}
	
	public WikiArticleSourceEditMode createNewArticleSource(String pageName, int layoutNum){
		getUrl(Global.DOMAIN+"index.php?title="+pageName+"&action=edit&useFormat="+layoutNum+"&useeditor=source");
		waitForElementByElement(more);
		PageObjectLogging.log("createNewArticleSource", "create article page in source mode opened", true, driver);
		return new WikiArticleSourceEditMode(driver, pageName);
	}
	public void focusTextArea()
	{
		jQueryFocus(".cke_source");
	}
	
	public String getSourceContent()
	{
		return executeScriptRet("$('.cke_source').attr('value')");
	}
	
	public void checkSourceContent(String desiredContent){
		CommonFunctions.assertString(desiredContent, getSourceContent());
	}

	
	public void clickBold(){
		focusTextArea();
		bold.click();
		PageObjectLogging.log("clickBold", "bold button was clicked", true, driver);
	}


	public void clickItalic(){
		focusTextArea();
		italic.click();
		PageObjectLogging.log("clickItalic", "italic button was clicked", true, driver);
	}

	public void clickInternalLink(){
		focusTextArea();
		internalLink.click();
		PageObjectLogging.log("clickInternalLink", "internal link button was clicked", true, driver);
	}
	
	public void clickExternalLink(){
		focusTextArea();
		externalLink.click();
		PageObjectLogging.log("clickExternalLink", "external link button was clicked", true, driver);
	}
	
	public void clickLvl2Headline(){
		focusTextArea();
		lvl2headLine.click();
		PageObjectLogging.log("clickExternalLink", "external link button was clicked", true, driver);
	}
	
	public void clickEmbedFile(){
		focusTextArea();
		embedFile.click();
		PageObjectLogging.log("clickEmbedFile", "embed file button was clicked", true, driver);
	}
	
	public void clickEmbedMedia(){
		focusTextArea();
		embedMedia.click();
		PageObjectLogging.log("clickEmbedMedia", "embed media button was clicked", true, driver);
	}
	
	public void clickMath(){
		focusTextArea();
		math.click();
		PageObjectLogging.log("clickMath", "math button was clicked", true, driver);
	}
	
	public void clickNowiki(){
		focusTextArea();
		nowiki.click();
		PageObjectLogging.log("clickNoWiki", "nowwiki button was clicked", true, driver);
	}
	
	public void clickSignature(){
		focusTextArea();
		signature.click();
		PageObjectLogging.log("clickSignature", "signature button was clicked", true, driver);
	}
	
	public void clickHorizontalLine(){
		focusTextArea();
		hline.click();
		PageObjectLogging.log("clickHorizontalLine", "horizontal line button was clicked", true, driver);
	}
	
	public void clickAddPhoto(){
		focusTextArea();
		photo.click();
		PageObjectLogging.log("clickAddPhot", "add photo button was clicked", true, driver);
	}
	
	public void clickAddGallery(){
		focusTextArea();
		gallery.click();
		PageObjectLogging.log("clickAddGallery", "add gallery button was clicked", true, driver);
	}
	
	public void clickAddVideo(){
		focusTextArea();
		video.click();
		PageObjectLogging.log("clickAddVideo", "add video button was clicked", true, driver);
	}
	
	public void clearSource()
	{
		sourceModeTextArea.clear();
	}
}
