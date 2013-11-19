package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;

public class SourceEditModePageObject extends EditMode{


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
	@FindBy(css=".close.wikia-chiclet-button")
	private WebElement moreClose;
	@FindBy(css="section#WikiaPhotoGalleryEditor")
	private WebElement componentSelector;
	@FindBy(css="a.wikia-button[type='2']")
	private WebElement createSlideshow;
	@FindBy(css="a.wikia-button[type='1']")
	private WebElement createGallery;
	@FindBy(css="a.wikia-button[type='3']")
	private WebElement createSlider;
	@FindBy(css="#wpSave")
	private WebElement submitButton;

	@FindBy(css=".cke_source")
	private WebElement sourceModeTextArea;

	public SourceEditModePageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public SourceEditModePageObject createNewArticleSource(String pageName, int layoutNum){
		getUrl(Global.DOMAIN+"index.php?title="+pageName+"&action=edit&useFormat="+layoutNum+"&useeditor=source");
		waitForElementByElement(more);
		PageObjectLogging.log("createNewArticleSource", "create article page in source mode opened", true, driver);
		return new SourceEditModePageObject(driver);
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
		waitForElementClickableByElement(sourceModeTextArea);
		Assertion.assertEquals(desiredContent, getSourceContent());
	}

	public void checkSourceVideoContent(String desiredContent){
		Assertion.assertEquals(desiredContent.substring(1, 38) + desiredContent.substring(48), getSourceContent().substring(1, 38) + getSourceContent().substring(48));
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

	public PhotoAddComponentObject clickAddPhoto(){
		focusTextArea();
		photo.click();
		PageObjectLogging.log("clickAddPhot", "add photo button was clicked", true, driver);
		return new PhotoAddComponentObject(driver);
	}

	public void clickAddGallery(){
		focusTextArea();
		gallery.click();
		PageObjectLogging.log("clickAddGallery", "add gallery button was clicked", true, driver);
	}

	public VetAddVideoComponentObject clickAddVideo(){
		focusTextArea();
		video.click();
		PageObjectLogging.log("clickAddVideo", "add video button was clicked", true, driver);
		return new VetAddVideoComponentObject(driver);
	}

	public void clickMore(){
		focusTextArea();
		more.click();
		PageObjectLogging.log("clickMore", "more button was clicked", true, driver);
	}

	public void clearSource(){
		sourceModeTextArea.clear();
		PageObjectLogging.log("clearSource", "source area erased", true, driver);
	}

	public void closeMore(){
		moreClose.click();
	}

	public void verifyComponentSelector()
	{
		waitForElementByElement(componentSelector);
		PageObjectLogging.log("verifyComponentSelector", "component selector is visible", true, driver);
	}

	public Object addComponent(String componentName){
		if (componentName.equals("slideshow")){
			waitForElementByElement(createSlideshow);
			createSlideshow.click();
			PageObjectLogging.log("addComponent", "selected "+componentName+" component", true);
			return new SlideshowBuilderComponentObject(driver);
		}
		else if (componentName.equals("gallery")){
			waitForElementByElement(createGallery);
			createGallery.click();
			PageObjectLogging.log("addComponent", "selected "+componentName+" component", true);
			return new GalleryBuilderComponentObject(driver);
		}
		else if (componentName.equals("slider")){
			waitForElementByElement(createSlider);
			createSlider.click();
			PageObjectLogging.log("addComponent", "selected "+componentName+" component", true);
			return new SliderBuilderComponentObject(driver);
		}
		else{
			PageObjectLogging.log("addComponent", "not supported component name: "+componentName, false);
			return null;
		}
	}

	public void checkMainTools()
	{
		for (int i=1; i<17; i++){
			clearSource();
			clickMore();
			String content = driver.findElement(By.xpath("//section[@class='modalContent']//span[@id='edittools_main']/a["+i+"]")).getText();
			driver.findElement(By.xpath("//section[@class='modalContent']//span[@id='edittools_main']/a["+i+"]")).click();
			checkSourceContent(content);
		}
	}

	public void checkWikiMarkupTools()
	{
		for (int i=1,j=i+1; i<21; i++){
			j=i+1;
			clearSource();
			clickMore();
			String content = executeScriptRet("$('.modalContent #edittools_wikimarkup a:nth-child("+j+")').text()");
			driver.findElement(By.xpath("//section[@class='modalContent']//span[@id='edittools_wikimarkup']/a["+i+"]")).click();
			checkSourceContent(content);
		}
	}

	public void checkSymbolsTools()
	{
		for (int i=1; i<65; i++){
			clearSource();
			clickMore();
			String content = driver.findElement(By.xpath("//section[@class='modalContent']//span[@id='edittools_symbols']/a["+i+"]")).getText();
			driver.findElement(By.xpath("//section[@class='modalContent']//span[@id='edittools_symbols']/a["+i+"]")).click();
			checkSourceContent(content);
		}
	}

	private String getContent() {
		waitForElementByElement(sourceModeTextArea);
		return sourceModeTextArea.getAttribute("value");
	}

	public void verifyVideoAlignment(PositionsVideo position) {
		Assertion.assertStringContains(getContent(),
				position.toString().toLowerCase());
	}

	public void verifyVideoWidth(int widthDesired) {
		String content = getContent();
		int width = Integer.parseInt(
				content.substring(content.indexOf("px")-6, content.indexOf("px")-3)
		);
		Assertion.assertNumber(
				width,
				widthDesired,
				"width is " + width + " should be " + widthDesired
		);
	}

	public void verifyVideoCaption(String desiredCaption) {
		Assertion.assertStringContains(getContent(), desiredCaption);
	}

	/**
	 * adds source code that will create Table of Contents on the article
	 */
	public void addTOC() {
		clearContent();
		appendContent(PageContent.articleWithTOClines);
	}

	private void appendContent(String content) {
		waitForElementByElement(sourceModeTextArea);
		sourceModeTextArea.sendKeys(content);
		PageObjectLogging.log("appendContent", "text: '"+content+"', added to the source mode", true);
	}

	private void appendNewLine(String content) {
		waitForElementByElement(sourceModeTextArea);
		sourceModeTextArea.sendKeys(Keys.ENTER);
		sourceModeTextArea.sendKeys(content);
		PageObjectLogging.log("appendNewLine", "text "+content+" added to the source mode in new line", true);
	}

	public void clearContent() {
		waitForElementByElement(sourceModeTextArea);
		sourceModeTextArea.clear();
		PageObjectLogging.log("clearContent", "source mode cleared", true);
	}
}
