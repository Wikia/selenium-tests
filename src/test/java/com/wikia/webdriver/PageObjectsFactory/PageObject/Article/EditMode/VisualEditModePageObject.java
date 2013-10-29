package com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditCategory.EditCategoryComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import org.openqa.selenium.interactions.Actions;

/**
 * @author: Bogna 'bognix' Knychała
 */
public class VisualEditModePageObject extends EditMode {

	@FindBy(css="#bodyContent")
	private WebElement contentInput;
	@FindBy(css="div.cke_wrapper.cke_ltr div.cke_contents iframe")
	private WebElement iframe;
	@FindBy(css="img.image")
	private WebElement image;
	@FindBy(css="img.image-gallery")
	private WebElement gallery;
	@FindBy(css="img.image-slideshow")
	private WebElement slideshow;
	@FindBy(css="img.image-gallery-slider")
	private WebElement slider;
	@FindBy(css="img.video")
	private WebElement video;
	@FindBy(css="img.video-placeholder")
	private WebElement videoPlaceholder;
	@FindBy(css=".RTEMediaOverlayEdit")
	private WebElement modifyComponentButton;
	@FindBy(css="[style*=\"block\"] .RTEMediaOverlayDelete")
	private WebElement removeComponentButton;
	@FindBy(css="#RTEConfirmOk > span")
	private WebElement removeConfirmationButton;
	@FindBy(css="#wpTextbox1")
	private WebElement messageSourceModeTextArea;
	@FindBy(css="input#CategorySelectInput")
	private WebElement categoryInput;
	@FindBy(css="#CategorySelect .ui-autocomplete")
	private WebElement categorySuggestionsContainer;
	@FindBy(css="li.category")
	private List<WebElement> categoryList;
	@FindBy(css=".RTEMediaCaption")
	private WebElement caption;
	@FindBy(css=".cke_button_tabledelete > span.cke_label")
	private WebElement deleteItem;
	@FindBy(css=".cke_button_table")
	private WebElement propertiesItem;
	@FindBy(css=".article-table")
	private WebElement VisualModeTable;
	@FindBy(css=".cke_contextmenu iframe")
	private WebElement contextFrame;
	@FindBy(css=".cke_dialog_body")
	private WebElement addTableLightbox;

	private By imageBy = By.cssSelector("img.image");
	private By galleryBy = By.cssSelector("img.image-gallery");
	private By slideshowBy = By.cssSelector("img.image-slideshow");
	private By sliderBy = By.cssSelector("img.image-gallery-slider");
	private By videoBy = By.cssSelector("img.video");
	private By categorySuggestionsList = By.cssSelector("li > a");
	private By articleTableBy = By.cssSelector(".article-table");

	private String categoryEditSelector = "li.category[data-name='%categoryName%'] li.editCategory";
	private String categoryRemoveSelector = "li.category[data-name='%categoryName%'] li.removeCategory";
	private String categoryRemovedSelector = "li.category[data-name='%categoryName%']";

	public enum Components {
		Photo, Gallery, Slideshow, Slider, Video, VideoPlaceholder
	}

	public VisualEditModePageObject(WebDriver driver) {
		super(driver);
	}

	public void clearContent() {
		driver.switchTo().frame(iframe);
		contentInput.clear();
		driver.switchTo().defaultContent();
	}

	public void addContent(String content) {
		driver.switchTo().frame(iframe);
		contentInput.clear();
		contentInput.sendKeys(content);
		driver.switchTo().defaultContent();
		PageObjectLogging.log("addContent", "content " + content + " added to the article", true);
	}

	private void verifyComponent(WebElement component){
		driver.switchTo().frame(iframe);
		waitForElementByElement(component);
		driver.switchTo().defaultContent();
	}

	public void verifyPhoto() {
		verifyComponent(image);
	}

	public void verifyGallery() {
		verifyComponent(gallery);
	}

	public void verifySlideshow() {
		verifyComponent(slideshow);
	}

	public void verifySlider() {
		verifyComponent(slider);
	}

	public void verifyVideo() {
		verifyComponent(video);
	}

	public void verifyVideoPosition(PositionsVideo position) {
		verifyComponent(video);
		driver.switchTo().frame(iframe);
		String positionClass = video.getAttribute("class");
		driver.switchTo().defaultContent();
		switch (position) {
			case left:
				Assertion.assertStringContains(positionClass, "alignLeft");
				break;
			case center:
				Assertion.assertStringContains(positionClass, "alignCenter");
				break;
			case right:
				Assertion.assertStringContains(positionClass, "alignRight");
				break;
		}
	}

	public void verifyVideoWidth(int widthDesired) {
		verifyComponent(video);
		driver.switchTo().frame(iframe);
		int widthCurrent = Integer.parseInt(video.getAttribute("width"));
		driver.switchTo().defaultContent();
		Assertion.assertNumber(
				widthDesired,
				widthCurrent,
				"width should be " + widthDesired + " but is " + widthCurrent
		);
	}

	public void verifyVideoCaption(String captionDesired) {
		mouseOverComponent(Components.Video);
		Assertion.assertEquals(captionDesired, caption.getText());
	}

	public void verifyVideoNoCaption() {
		driver.switchTo().frame(iframe);
		String videoClass = video.getAttribute("class");
		Assertion.assertTrue(!videoClass.contains("thumb"), "video with thumbnail is displayed");
	}

	private void mouseOverComponent (Components component) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		switch (component) {
		case Gallery:
			verifyComponent(gallery);
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-gallery').mouseenter()");
			break;
		case Slideshow:
			verifyComponent(slideshow);
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-slideshow').mouseenter()");
			break;
		case Slider:
			verifyComponent(slider);
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.image-gallery-slider').mouseenter()");
			break;
		case Video:
			verifyComponent(video);
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.video').mouseenter()");
			break;
		case Photo:
			verifyComponent(image);
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.image').mouseenter()");
			break;
		case VideoPlaceholder:
			js.executeScript("$('div.cke_contents>iframe').contents().find('img.video-placeholder').mouseenter()");
			break;
		default:
			break;
		}
	}

	public Object modifyComponent(Components component) {
		mouseOverComponent(component);
		modifyComponentButton.click();
		PageObjectLogging.log("modifyGallery", "Click on 'modify button' on gallery", true, driver);
		switch (component) {
		case Gallery:
			return new GalleryBuilderComponentObject(driver);
		case Photo:
			return new PhotoAddComponentObject(driver);
		case Slider:
			return new SliderBuilderComponentObject(driver);
		case Slideshow:
			return new SlideshowBuilderComponentObject(driver);
		case Video:
			return new VetOptionsComponentObject(driver);
		case VideoPlaceholder:
			return new VetAddVideoComponentObject(driver);
		default:
			return null;
		}
	}

	public void removeComponent(Components component) {
		mouseOverComponent(component);
		removeComponentButton.click();
		removeConfirmationButton.click();
		PageObjectLogging.log("removeGallery", "Click on 'remove button' on gallery", true);
	}

	public void verifyComponentRemoved(Components component) {
		driver.switchTo().frame(iframe);
		switch (component) {
		case Photo:
			waitForElementNotPresent(imageBy);
		case Gallery:
			waitForElementNotPresent(galleryBy);
		case Slideshow:
			waitForElementNotPresent(slideshowBy);
		case Slider:
			waitForElementNotPresent(sliderBy);
		case Video:
			waitForElementNotPresent(videoBy);
		default:
			break;
		}
		driver.switchTo().defaultContent();
		PageObjectLogging.log("verifyGalleryRemoved", "Click on 'remove button' on gallery", true);
	}

	/**
	 * Delete unwanted video by its name.
	 * Message article page is e.g http://mediawiki119.wikia.com/wiki/MediaWiki:RelatedVideosGlobalList
	 * This method destination is exactly related videos message article
	 *
	 * @author Michal Nowierski
	 * @param unwantedVideoName e.g "What is love (?) - on piano (Haddway)"
	 */
	public void deleteUnwantedVideoFromMessage(String unwantedVideoName) {
		ArrayList<String> videos = new ArrayList<String>();
		waitForElementByElement(messageSourceModeTextArea);
		String sourceText = messageSourceModeTextArea.getText();
		int index = 0;
		while (true) {
			int previousStarIndex = sourceText.indexOf("*", index);
			int nextStarIndex = sourceText.indexOf("*", previousStarIndex+1);
			if (nextStarIndex<0) {
				break;
			}
			String video = sourceText.substring(previousStarIndex, nextStarIndex);
			if (!video.contains(unwantedVideoName)) {
				videos.add(video);
			}
			index = previousStarIndex+1;
		}
		waitForElementByElement(messageSourceModeTextArea);
		messageSourceModeTextArea.clear();
		messageSourceModeTextArea.sendKeys("WHITELIST");
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		messageSourceModeTextArea.sendKeys(Keys.ENTER);
		String builder = "";
		for (int i = 0; i<videos.size(); i++) {
			builder+=videos.get(i);
			builder+="\n";
		}
		CommonUtils.setClipboardContents(builder);
		messageSourceModeTextArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		PageObjectLogging.log("deleteUnwantedVideoFromMessage", "Delete all source code on the article", true, driver);
	}

	public void typeCategoryName(String categoryName) {
		waitForElementByElement(categoryInput);
		CommonUtils.setClipboardContents(categoryName);
		categoryInput.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		PageObjectLogging.log("typeCategoryName", categoryName + " typed", true);
	}

	public void triggerCategorySuggestions() {
		int timeout = 0;
		pressDownArrow(categoryInput);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String returned = (String) js.executeScript("return $('ul.ui-autocomplete li').text()");
		while(returned.isEmpty()&&timeout<=5000) {
			try {
				Thread.sleep(500);
				timeout += 500;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pressDownArrow(categoryInput);
			returned = (String) js.executeScript("return $('ul.ui-autocomplete li').text()");
		}
	}

	public void submitCategory() {
		pressEnter(categoryInput);
		PageObjectLogging.log("submitCategory", "category submitted", true);
	}

	public void verifyCategoryPresent(String category) {
		boolean categoryVisible = false;
		for (WebElement elem : categoryList) {
			if (elem.getText().equals(category)) {
				categoryVisible = true;
			}
		}
		Assertion.assertTrue(categoryVisible, "category "+category+" not present");
	}

	public void verifyCategoryNotPresent(String category) {
		waitForElementNotPresent(categoryRemovedSelector.replace("%categoryName%", category));
		boolean categoryVisible = true;
		for (WebElement elem : categoryList) {
			if (elem.getText().equals(category)) {
				categoryVisible = false;
			}
		}
		Assertion.assertTrue(categoryVisible, "category "+category+" present");
	}

	public String selectCategorySuggestions(int categoryNumber) {
		waitForElementByElement(categorySuggestionsContainer);
		WebElement categoryItem = categorySuggestionsContainer
				.findElements(categorySuggestionsList)
				.get(categoryNumber);
		String categoryName = categoryItem.getText();
		categoryItem.click();
		waitForElementNotVisibleByElement(categorySuggestionsContainer);
		PageObjectLogging.log("selectCategorySuggestions", categoryNumber + " category selected from suggestions", true);
		return categoryName;
	}

	public EditCategoryComponentObject editCategory(String categoryName) {
		WebElement category = driver.findElement(
				By.cssSelector(
						categoryEditSelector.replace("%categoryName%", categoryName)
				)
		);
		jQueryClick(category);
		PageObjectLogging.log("editCategory", "edit category button clicked on category " + categoryName, true);
		return new EditCategoryComponentObject(driver);
	}

	public void removeCategory(String categoryName) {
		WebElement category = driver.findElement(
				By.cssSelector(
						categoryRemoveSelector.replace("%categoryName%", categoryName)
				)
		);
		jQueryClick(category);
		PageObjectLogging.log("removeCategory", "remove category button clicked on category " + categoryName, true);
	}

	private void clickContextMenuOption(WebElement option) {
		waitForElementByElement(iframe);
		driver.switchTo().frame(iframe);
		Actions actions = new Actions(driver);
		actions.contextClick(VisualModeTable).build().perform();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(contextFrame);
		option.click();
		driver.switchTo().defaultContent();
		waitForElementNotPresent(articleTableBy);
	}

	public void clickDeleteTableButton() {
		clickContextMenuOption(deleteItem);
	}

	public void clickPropertiesTableButton() {
		clickContextMenuOption(propertiesItem);
	}

}
