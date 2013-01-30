package com.wikia.webdriver.PageObjects.PageObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiCategoryPageObject;

public class WikiBasePageObject extends BasePageObject {

	@FindBy(css = "span.drop")
	private WebElement contributeButton;

	@FindBy(css = "a.createpage")
	private WebElement createArticleButton;

	@FindBy(css = "a[class='wikia-button createpage']")
	private WebElement addArticleButton;

	@FindBy(id = "wpCreatePageDialogTitle")
	private WebElement articleNameField;

	@FindBy(css = "article span.drop")
	private WebElement editDropDown;

	@FindBy(css = "a[data-id='delete']")
	private WebElement deleteButton;

	@FindBy(css = "input#wpConfirmB")
	private WebElement deleteConfirmationButton;

	@FindBy(xpath = "//div[@class='msg' and contains(text(), 'The comment has been deleted.')]")
	private WebElement deleteCommentConfirmationMessage;

	@FindBy(css = "div.msg a")
	private WebElement undeleteButton;

	@FindBy(css = "input#mw-undelete-submit")
	private WebElement restoreButton;

	@FindBy(css = "input#wpNewTitleMain")
	private WebElement renameArticleField;

	@FindBy(css = "input[name='wpMove']")
	private WebElement confirmRenamePageButton;

	@FindBy(css = "input#wpReason")
	private WebElement deleteCommentReasonField;

	@FindBy(css = "div.reset[id='ImageUpload']")
	private WebElement imageUploadModal;

	@FindBy(css = "div.details input")
	private WebElement addPhotoButton;

	@FindBy(css = "input[id='VideoEmbedUrl']")
	private WebElement videoModalInput;

	@FindBy(css = "a[id='VideoEmbedUrlSubmit']")
	private WebElement videoNextButton;

	@FindBy(css = "div.input-group.VideoEmbedNoBorder input")
	private WebElement videoAddVideoButton;

	@FindBy(css = "#VideoEmbed")
	private WebElement videoDialog;

	@FindBy(css = "input[value='Return to editing']")
	private WebElement videoReturnToEditing;

	@FindBy(css = "section[id='WikiaPhotoGalleryEditor']")
	private WebElement objectModal;

	@FindBy(css = "input[name='search'][placeholder='Search photos on this wiki']")
	private WebElement searchFieldImageInLightBox;

	@FindBy(css = "img.sprite.search")
	private WebElement searchButtonImageInLightBox;

	@FindBy(css = "a[id='WikiaPhotoGallerySearchResultsSelect']")
	private WebElement galleryDialogSelectButton;

	@FindBy(css = "a[id='WikiaPhotoGalleryEditorSave']")
	private WebElement galleryDialogFinishButton;
	
	@FindBy(css="input[id='VideoEmbedCaption']")
	private WebElement videoCaptionTextArea;
	
	@FindBy(css="input#ImageQuery")
	private WebElement imageQuery;
	
	@FindBy(css="div.permissions-errors")
	private WebElement premissionErrorMessage;
	
	@FindBy(css="[value='Find']")
	private WebElement imageFindButton;

	@FindBy(css="div.mw-warning-with-logexcerpt p")
	private WebElement pageDeletedInfo;
	
	@FindBy(css="#PREFOOTER_RIGHT_BOXAD")
	private WebElement ad_Prefooter_right_boxad;
	
	@FindBy(css="#PREFOOTER_LEFT_BOXAD")
	private WebElement ad_Prefooter_left_boxad;
	
	private By galleryDialogPhotosList = By
			.cssSelector("ul[class='WikiaPhotoGalleryResults'][type='results'] li input");
	private By galleryDialogPhotoOrientationsList = By
			.cssSelector("ul.clearfix[id='WikiaPhotoGalleryOrientation'] li");
	private String videoAddVideoButtonSelector = "div.input-group.VideoEmbedNoBorder input";
	private String videoReturnToEditingSelector = "input[value=\"Return to editing\"]";
	private By galleryDialogSlideshowOrientationsList = By
			.cssSelector("ul.clearfix[id='WikiaPhotoGallerySliderType'] li");
	private By layoutList = By.cssSelector("ul#CreatePageDialogChoices li");
	private By captionTextArea = By.cssSelector("textarea[id='ImageUploadCaption']");
	private By addThisPhotoLink = By.cssSelector("tr.ImageUploadFindLinks td a");

	public WikiBasePageObject(WebDriver driver, String Domain) {
		super(driver);
		this.Domain = Domain;
		PageFactory.initElements(driver, this);
	}

	public String getWikiName() {
		return Domain;
	}

	public void searchForImage(String name){
//		waitForElementByElement(imageFindButton);
		imageQuery.sendKeys(name);
//		waitForElementByElement(imageQuery);
		imageFindButton.click();
		PageObjectLogging.log("searchForImage", "search for image: "+name, true);
	}
	
	/**
	 * Left Click on add Object button.
	 *  
	 * @author Michal Nowierski
	 * @param Object Object = {Image, Gallery, Slideshow, Slider, Video}
	 */
	public void clickOnAddObjectButton(String Object) {
		String ObjectCss = "span.cke_button.RTE"+Object+"Button a";
		WebElement ObjectButton;
		waitForElementByCss(ObjectCss);
		waitForElementClickableByCss(ObjectCss);
		ObjectButton = driver.findElement(By.cssSelector(ObjectCss));
		clickAndWait(ObjectButton);
		PageObjectLogging.log("ClickOnAddObjectButton", "Edit Article: "+articlename+", on wiki: "+Domain+"", true, driver);		
	}
	
	/**
	 * Type given caption for the video
	 *  
	 * @author Michal Nowierski
	 */
	public void typeVideoCaption(String caption) {
		waitForElementByElement(videoCaptionTextArea);
		videoCaptionTextArea.clear();
		videoCaptionTextArea.sendKeys(caption);
		PageObjectLogging.log("TypeAcaption", "Type any caption for the photo", true, driver);
	}
	
	/**
	 * Set photo orientation option number n
	 * 
	 * @author Michal Nowierski
	 * @param n
	 *            = {1, 2}
	 *            <p>
	 *            1 - Horizontaal.
	 *            <p>
	 *            2 - Vertical
	 * */
	public void gallerySetSliderPosition(int n) {
		List<WebElement> List = driver
				.findElements(galleryDialogSlideshowOrientationsList);
		waitForElementByElement(List.get(n - 1));
		clickAndWait(List.get(n - 1));
		PageObjectLogging.log("GallerySetSliderPosition",
				"Set photo orientation option number " + n, true, driver);

	}

	/**
	 * Set photo orientation option number n
	 * 
	 * @author Michal Nowierski
	 * @param n
	 *            = {1,2,3,4}
	 *            <p>
	 *            1 - Original.
	 *            <p>
	 *            2 - Square.
	 *            <p>
	 *            3 - Landscape.
	 *            <p>
	 *            4 - Portrait
	 * */
	public void gallerySetPhotoOrientation(int n) {
		List<WebElement> List = driver
				.findElements(galleryDialogPhotoOrientationsList);
		waitForElementByElement(List.get(n - 1));
		clickAndWait(List.get(n - 1));
		PageObjectLogging.log("GallerySetPhotoOrientation",
				"Set photo orientation option number " + n, true, driver);

	}

	/**
	 * Set Object position to the wanted one
	 * 
	 * @author Michal Nowierski
	 * @param Object
	 *            {Gallery, Slideshow}
	 * @param WantedPosition
	 *            = {Left, Center, Right} !CASE SENSITIVITY! *
	 */
	public void gallerySetPositionGallery(String WantedPosition) {

		Select select = new Select(
				driver.findElement(By
						.cssSelector("select[id='WikiaPhotoGalleryEditorGalleryPosition']")));
		select.selectByVisibleText(WantedPosition);
		// below code will make sure that proper position is selected
		String category_name = select.getAllSelectedOptions().get(0).getText();
		while (!category_name.equalsIgnoreCase(WantedPosition)) {
			select.selectByVisibleText(WantedPosition);
			category_name = select.getAllSelectedOptions().get(0).getText();

		}
		PageObjectLogging.log("GallerySetPosition", "Set gallery position to "
				+ WantedPosition, true, driver);
	}

	/**
	 * Gallery dialog: Left click 'Finish' button
	 * 
	 * @author Michal Nowierski
	 * */
	public void galleryClickOnFinishButton() {
		waitForElementByElement(galleryDialogFinishButton);
		waitForElementClickableByElement(galleryDialogFinishButton);
		clickAndWait(galleryDialogFinishButton);
		PageObjectLogging.log("GalleryClickOnFinishButton",
				"Gallery dialog: Left click 'Finish' button ", true, driver);

	}

	public void gallerySetPositionSlideshow(String WantedPosition) {

		Select select = new Select(
				driver.findElement(By
						.cssSelector("select[id='WikiaPhotoGalleryEditorSlideshowAlign']")));
		select.selectByVisibleText(WantedPosition);
		// below code will make sure that proper position is selected
		String category_name = select.getAllSelectedOptions().get(0).getText();
		while (!category_name.equalsIgnoreCase(WantedPosition)) {
			select.selectByVisibleText(WantedPosition);
			category_name = select.getAllSelectedOptions().get(0).getText();
		}
		PageObjectLogging.log("GallerySetPosition",
				"Set slideshow position to " + WantedPosition, true, driver);
	}

	/**
	 * Gallery dialog: Left click 'Select' button
	 * 
	 * @author Michal Nowierski
	 * */
	public void galleryClickOnSelectButton() {
		waitForElementByElement(galleryDialogSelectButton);
		waitForElementClickableByElement(galleryDialogSelectButton);
		clickAndWait(galleryDialogSelectButton);
		PageObjectLogging.log("GalleryClickOnSelectButton",
				"Gallery dialog: Left click 'Select' button", true, driver);

	}

	/**
	 * Wait for Object and click on 'add this photo' under the first seen
	 * 
	 * @author Michal Nowierski
	 * @param n
	 *            n = parameter determining how many inputs the method should
	 *            check
	 * */
	public void galleryCheckImageInputs(int n) {
		driver.findElement(galleryDialogPhotosList);
		List<WebElement> List = driver.findElements(galleryDialogPhotosList);
		for (int i = 0; i < n; i++) {
			clickAndWait(List.get(i));
		}
		PageObjectLogging.log("CheckGalleryImageInputs", "Check first " + n
				+ " image inputs", true, driver);
	}

	public void searchImageInLightBox(String imageName) {
		waitForElementByElement(searchFieldImageInLightBox);
		searchFieldImageInLightBox.sendKeys(imageName);
		clickAndWait(searchButtonImageInLightBox);
		waitForElementByElement(searchButtonImageInLightBox);
	}

	/**
	 * Wait for Object and click on 'add this photo' under the first seen
	 * 
	 * @author Michal Nowierski
	 * @param Object
	 *            Object = {Gallery, GallerySlideshow, GallerySlider}
	 * */
	public void waitForObjectModalAndClickAddAphoto(String Object) {
		waitForElementClickableByBy(By.cssSelector("button[id='WikiaPhoto"
				+ Object + "AddImage']"));
		clickAndWait(driver.findElement(By.cssSelector("button[id='WikiaPhoto"
				+ Object + "AddImage']")));
		PageObjectLogging.log("WaitForObjectModalAndClickAddAphoto",
				"Wait for " + Object + " modal and click on 'add a photo'",
				true, driver);
		waitForElementByElement(objectModal);
	}

	/**
	 * Wait For Succes dialog and click on 'return to editing'
	 * 
	 * @author Michal Nowierski
	 * */
	public void waitForSuccesDialogAndReturnToEditing() {
		waitForElementByElement(videoReturnToEditing);
		waitForElementClickableByElement(videoReturnToEditing);
		jQueryClick(videoReturnToEditingSelector);
		// clickAndWait(videoReturnToEditing);
		waitForElementNotVisibleByCss(videoReturnToEditingSelector);
		PageObjectLogging.log("WaitForSuccesDialogAndReturnToEditing",
				"Wait For Succes dialog and click on 'return to editing'",
				true, driver);

	}

	/**
	 * Wait for video dialog
	 * 
	 * @author Michal Nowierski
	 * */
	public void waitForVideoDialog() {
		waitForElementByElement(videoDialog);
		PageObjectLogging.log("WaitForVideoDialog", "Wait for video dialog",
				true, driver);

	}

	/**
	 * Click 'Add a video'
	 * 
	 * @author Michal Nowierski
	 * */
	public void clickAddAvideo() {

		waitForElementByElement(videoAddVideoButton);
		// waitForElementClickableByElement(videoAddVideoButton);
		jQueryClick(videoAddVideoButtonSelector);
		// clickAndWait(videoAddVideoButton);
		PageObjectLogging.log("ClickAddAvideo", "Click 'Add a video'", true,
				driver);

	}

	/**
	 * Video Click Next button
	 * 
	 * @author Michal Nowierski
	 * */
	public void clickVideoNextButton() {
		waitForElementByElement(videoNextButton);
		waitForElementClickableByElement(videoNextButton);
		clickAndWait(videoNextButton);
		PageObjectLogging.log("ClickVideoNextButton", "Left Click Next button",
				true, driver);

	}

	/**
	 * Wait for Video modal and type in the video URL
	 * 
	 * @author Michal Nowierski
	 * */
	public void waitForVideoModalAndTypeVideoURL(String videoURL) {
		waitForElementByElement(videoModalInput);
		waitForElementClickableByElement(videoModalInput);
		videoModalInput.clear();
		videoModalInput.sendKeys(videoURL);
		PageObjectLogging.log("WaitForVideoModalAndTypeVideoURL",
				"Wait for Video modal and type in the video URL: " + videoURL,
				true, driver);
	}

	/**
	 * Left Click on add 'Photo' button.
	 * 
	 * @author Michal Nowierski
	 */
	public void clickOnAddPhotoButton2() {
		waitForElementByElement(addPhotoButton);
		waitForElementClickableByElement(addPhotoButton);
		clickAndWait(addPhotoButton);
		PageObjectLogging.log("ClickOnAddPhotoButton2",
				"Left Click on add 'Photo' button.", true, driver);
	}

	
	
	/**
	 * Wait for modal and click on 'add this photo' under the first seen photo
	 * 
	 * @author Michal Nowierski
	 */
	public void waitForModalAndClickAddThisPhoto() {
		waitForElementByElement(imageUploadModal);
		WebElement addPhoto = waitForElementByBy(addThisPhotoLink);
		waitForElementClickableByElement(addPhoto);
		clickAndWait(addPhoto);
		PageObjectLogging
				.log("WaitForModalAndClickAddThisPhoto",
						"Wait for modal and click on 'add this photo' under the first seen photo",
						true, driver);
	}

	/**
	 * Type given caption for the photo
	 * 
	 * @author Michal Nowierski
	 */
	public void typePhotoCaption(String caption) {
		WebElement captionText = waitForElementByBy(captionTextArea);
		captionText.clear();
		captionText.sendKeys(caption);
		PageObjectLogging.log("TypeAcaption", "Type any caption for the photo",
				true, driver);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles() {
		getUrl(Domain + "wiki/Special:NewFiles");
		return new SpecialNewFilesPageObject(driver, Domain);
	}

	public SpecialUploadPageObject openSpecialUpload() {
		getUrl(Domain + "wiki/Special:Upload");
		return new SpecialUploadPageObject(driver, Domain);
	}

	public SpecialMultipleUploadPageObject openSpecialMultipleUpload() {
		getUrl(Domain + "wiki/Special:MultipleUpload");
		return new SpecialMultipleUploadPageObject(driver, Domain);
	}

	public WikiArticlePageObject OpenArticle(String wikiArticle) {
		try {
			getUrl(Domain + "wiki/" + wikiArticle);
		} catch (TimeoutException e) {
			PageObjectLogging.log("OpenArticle",
					"page loads for more than 30 seconds", true);
		}
		return new WikiArticlePageObject(driver, Domain, wikiArticle);
	}

	public void verifyEditDropDownAnonymous() {
		List<WebElement> list = driver.findElements(By
				.cssSelector("header#WikiaPageHeader ul.WikiaMenuElement li"));
		Assertion.assertNumber(1, list.size(),
				"Edit drop-down number of items for anonymous user");
		Assertion.assertEquals(
				"history",
				list.get(0).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
	}

	public void verifyEditDropDownLoggedInUser() {
		List<WebElement> list = driver.findElements(By
				.cssSelector("header#WikiaPageHeader ul.WikiaMenuElement li"));
		Assertion.assertNumber(2, list.size(),
				"Edit drop-down number of items for admin user");
		Assertion.assertEquals(
				"history",
				list.get(0).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
		Assertion.assertEquals(
				"move",
				list.get(1).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
	}

	public void verifyEditDropDownAdmin() {
		List<WebElement> list = driver.findElements(By
				.cssSelector("header#WikiaPageHeader ul.WikiaMenuElement li"));
		Assertion.assertNumber(4, list.size(),
				"Edit drop-down number of items for admin user");
		Assertion.assertEquals(
				"history",
				list.get(0).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
		Assertion.assertEquals(
				"move",
				list.get(1).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
		Assertion.assertEquals(
				"protect",
				list.get(2).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
		Assertion.assertEquals(
				"delete",
				list.get(3).findElement(By.cssSelector("a"))
						.getAttribute("data-id"));
	}

	private void clickContributeButton() {
		executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
		executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
		waitForElementByElement(createArticleButton);
		PageObjectLogging.log("clickOnContributeButton",
				"contribute button clicked", true);
	}

	private void clickCreateArticleButton() {
		waitForElementByElement(createArticleButton);
		waitForElementClickableByElement(createArticleButton);
		// jQueryClick(".createpage");
		executeScript("document.querySelectorAll('.createpage')[0].click()");
		waitForElementByElement(driver.findElement(layoutList));
		PageObjectLogging.log("clickCreateArticleButton",
				"create article button clicked", true);
	}

	private void selectPageLayout(int number) {
		List<WebElement> list = driver.findElements(layoutList);
		clickAndWait(list.get(number));
		PageObjectLogging.log("selectPageLayout", "wiki layout selected", true,
				driver);
	}

	private void typeInArticleName(String name) {
		waitForElementByElement(articleNameField);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		articleNameField.sendKeys(name);
	}

	private void clickAddPageButton() {
		clickAndWait(addArticleButton);
		PageObjectLogging.log("clickAddPageButton", "add button clicked", true,
				driver);
	}

	public void verifyDeletedArticlePage(String pageName) {
		pageName = pageName.replace("_", " ");
		waitForElementByXPath("//h1[contains(text(), '" + pageName + "')]");
		// waitForElementByXPath("//p[contains(text(), 'This page has been deleted.')]");
		// waitForElementByXPath("//a[@class='wikia-button' and contains(text(), 'Create')]");
		waitForElementByXPath("//b[contains(text(), 'This page needs content. You can help by adding a sentence or a photo!')]");
		PageObjectLogging.log("verifyDeletedArticlePage",
				"deleted article page verified", true);
	}

	public void clickEditDropDown() {
		waitForElementByElement(editDropDown);
		clickAndWait(editDropDown);
		PageObjectLogging.log("clickEditDropDown", "edit drop-down clicked",
				true, driver);
	}

	public WikiArticleEditMode clickEditButton(String pageName) {
		//two lines below prevent hubs drop-down on IE9
		mouseOver("#GlobalNavigation li:nth(1)");
		mouseRelease("#GlobalNavigation li:nth(1)");
		waitForElementByElement(editButton);
		waitForElementClickableByElement(editButton);
		clickAndWait(editButton);
		PageObjectLogging.log("clickEditButton", "edit button clicked", true, driver);
		return new WikiArticleEditMode(driver, Domain, pageName);
	}

	public WikiArticleEditMode navigateToEditPage() {
		String URL = getCurrentUrl();
		String targetURL = URL+"?action=edit";
		driver.navigate().to(targetURL);
		PageObjectLogging.log("navigateToEditPage()", "navigating to edit page via URL", true, driver);
		return new WikiArticleEditMode(driver, Domain, articlename);
	}
	
	protected void clickDeleteButtonInDropDown() {
		waitForElementByElement(deleteButton);
		clickActions(deleteButton);
		PageObjectLogging.log("clickDeleteButtonInDropDown",
				"delete button in drop-down clicked", true);
	}

	protected void clickCommentDeleteConfirmationButton() {
		waitForElementByElement(deleteConfirmationButton);
		waitForElementByElement(deleteCommentReasonField);
		deleteCommentReasonField.clear();
		deleteCommentReasonField.sendKeys("QAReason");
		clickAndWait(deleteConfirmationButton);
		waitForElementByElement(deleteCommentConfirmationMessage);

	}

	protected void clickArticleDeleteConfirmationButton(String atricleName) {
		waitForElementByElement(deleteConfirmationButton);
		waitForElementByElement(deleteCommentReasonField);
		deleteCommentReasonField.clear();
		deleteCommentReasonField.sendKeys("QAReason");
		clickAndWait(deleteConfirmationButton);
		String temp = atricleName.replace("_", " ");
		waitForElementByXPath("//div[@class='msg' and contains(text(), '"
				+ temp + "\" has been deleted.')]");
	}

	public void deleteArticle(String atricleName) {
		getUrl(driver.getCurrentUrl() + "?action=delete");
		// clickDeleteButtonInDropDown();
		clickArticleDeleteConfirmationButton(atricleName);
		waitForElementByXPath("//div[@class='msg' and contains(text(), 'has been deleted.')]");
		PageObjectLogging.log("deleteArticle", "article "+atricleName+" has been deleted",
				true, driver);
	}
	
	public void deleteTop10List(String top10listName) {
		String top10listURL = driver.getCurrentUrl();
		getUrl(top10listURL + "?action=delete");
		clickArticleDeleteConfirmationButton(top10listName);
		getUrl(top10listURL);
		waitForTextToBePresentInElementByElement(pageDeletedInfo, "has been deleted.");
		PageObjectLogging.log("deleteArticle", "top 10 list: "+top10listName+" has been deleted",
				true, driver);
	}
	
	public void clickOnDeleteButton() {
		getUrl(driver.getCurrentUrl() + "?action=delete");
		PageObjectLogging.log("deleteArticle", "article deletion invoked",
				true, driver);
	}

	public void renameArticle(String articleName, String articleNewName) {
		getUrl(Global.DOMAIN + "wiki/Special:MovePage/" + articleName);
		waitForElementByElement(renameArticleField);
		waitForElementByElement(confirmRenamePageButton);
		renameArticleField.clear();
		renameArticleField.sendKeys(articleNewName);
		clickAndWait(confirmRenamePageButton);
		waitForElementByXPath("//b[contains(text(), '\"" + articleName
				+ "\" has been renamed \"" + articleNewName + "\"')]");
	}

	private void clickUndeleteArticle() {
		waitForElementByElement(undeleteButton);
		// jQuery didn't work here. The below workaround stimulates clicking on
		// 'undelete' button
		String href = undeleteButton.getAttribute("href");
		driver.navigate().to(href);
		// clickAndWait(undeleteButton);
		waitForElementByElement(restoreButton);
		PageObjectLogging.log("clickUndeleteArticle",
				"undelete article button clicked", true, driver);
	}

	private void clickRestoreArticleButton() {
		waitForElementByElement(restoreButton);
		clickAndWait(restoreButton);
		waitForElementByXPath("//div[@class='msg' and contains(text(), 'This page has been restored.')]");
		PageObjectLogging.log("clickUndeleteArticle",
				"undelete article button clicked", true, driver);
	}

	public void undeleteArticle() {
		clickUndeleteArticle();
		clickRestoreArticleButton();
	}

	public WikiArticleEditMode createNewArticle(String pageName,
			int layoutNumber) {
		// clickContributeButton();
		// clickCreateArticleButton();
		// selectPageLayout(layoutNumber);
		// typeInArticleName(pageName);
		// clickAddPageButton();
		getUrl(Global.DOMAIN + "index.php?title=" + pageName
				+ "&action=edit&useFormat=" + layoutNumber);
		String pageNameEnc = pageName.replace("_", " ");
		waitForElementByElement(driver.findElement(By.cssSelector("a[title='"
				+ pageNameEnc + "']")));
		return new WikiArticleEditMode(driver, Domain, pageName);
	}
	
	public SpecialCreateTopListPageObject createNewTop_10_list(String top_10_list_Name) {
		getUrl(Global.DOMAIN + "wiki/Special:CreateTopList/" + top_10_list_Name);
		PageObjectLogging.log("SpecialCreateTopListPageObject",
				"create top 10 list with name: "+top_10_list_Name, true, driver);
		return new SpecialCreateTopListPageObject(driver, Domain, top_10_list_Name);
		
	}

	public WikiArticlePageObject openArticle(String articleName) {
		URI uri;
		try {
			uri = new URI(Global.DOMAIN + "wiki/" + articleName);
			String url = uri.toASCIIString();
			getUrl(url);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		catch (TimeoutException e) {
			PageObjectLogging.log("OpenArticle",
					"page loads for more than 30 seconds", true);
		}
		PageObjectLogging.log("openArticle", "article " + articleName
				+ " opened", true);
		return new WikiArticlePageObject(driver, Domain, articleName);
	}
	
	public Top_10_list openTop10List(String topTenListName) {
		URI uri;
		try {
			uri = new URI(Global.DOMAIN + "wiki/" + topTenListName);
			String url = uri.toASCIIString();
			getUrl(url);
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}
		catch (TimeoutException e) {
			PageObjectLogging.log("openTop10List",
					"page loads for more than 30 seconds", true, driver);
		}
		PageObjectLogging.log("openTop10List", topTenListName
				+ " opened", true);
		return new Top_10_list(driver, Domain, topTenListName);
	}

	public WikiCategoryPageObject clickOnCategory(String categoryName) {
		List<WebElement> lista = driver.findElements(By
				.cssSelector("#catlinks li a"));
		Boolean result = false;
		// there might be more than one category on a random page. Thus - loop
		// over all of them.
		if (lista.size() > 0) {

			for (WebElement webElement : lista) {
				waitForElementByElement(webElement);
				if (webElement.getText().equalsIgnoreCase(categoryName)) {
					waitForElementClickableByElement(webElement);
					clickAndWait(webElement);
					result = true;
				}
			}
		}
		if (result) {
			PageObjectLogging.log("clickOnCategory", "clicked on "
					+ categoryName, true, driver);
		} else {
			PageObjectLogging.log("clickOnCategory", "category " + categoryName
					+ " not found", false, driver);
		}
		return new WikiCategoryPageObject(driver, Domain);
	}

	public WikiCategoryPageObject openCategoryPage(String category) {
		getUrl(Global.DOMAIN + "wiki/" + "Category:" + category);
		PageObjectLogging.log("openCategoryPage", category + " page opened",
				true, driver);
		return new WikiCategoryPageObject(driver, Domain);
	}

	public CreateNewWikiPageObjectStep1 startAWiki() {
		return null;

	}
	
	public void verifyPermissionsErrorsPresent() {
		waitForElementByElement(premissionErrorMessage);
		PageObjectLogging.log("verifyPermissionsErrors", "premission error found, as expected",
				true, driver);
	}
	
	public void verifyAdsVisible_PrefooterAds()
	{
		waitForElementByElement(ad_Prefooter_left_boxad);
		waitForElementByElement(ad_Prefooter_right_boxad);
		PageObjectLogging.log("verifyPrefooterAdsVisible", "left and right prefooter ads are visible", true, driver);
	}
	
	public void verifyAdsInvisible_PrefooterAds()
	{
		waitForElementNotVisibleByElement(ad_Prefooter_left_boxad);
		waitForElementNotVisibleByElement(ad_Prefooter_right_boxad);
		PageObjectLogging.log("verifyPrefooterAdsInvisible", "left and right prefooter ads are invisible", true, driver);
	}
}
