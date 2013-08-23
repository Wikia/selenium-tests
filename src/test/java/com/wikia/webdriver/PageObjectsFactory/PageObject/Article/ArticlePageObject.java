package com.wikia.webdriver.PageObjectsFactory.PageObject.Article;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.EditCategory.EditCategoryComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.MiniEditor.MiniEditorComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.DeleteArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticleActions.RenameArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.VisualEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Watch.WatchPageObject;

/**
 *
 * @author Bogna 'bognix' Knychała
 */
public class ArticlePageObject extends WikiBasePageObject {

	private final String editButtonSelector = ".article-comm-edit";
	private final String deleteButtonSelector = ".article-comm-delete";
	private final String commentAuthorLink = ".edited-by";
	private final String replyCommentSelector = ".article-comm-reply";

	@FindBy(css="#WikiaPageHeader h1")
	protected WebElement articleHeader;
	@FindBy(css="#WikiaMainContent .drop img")
	protected WebElement articleEditDropdown;
	@FindBy(css="#mw-content-text p")
	protected WebElement articleContent;
	@FindBy(css="#WikiHeader .drop")
	protected WebElement contributeDropdown;
	@FindBy(css="#ca-delete")
	protected WebElement deleteDropdown;
	@FindBy(css="#ca-protect")
	protected WebElement protectDropdown;
	@FindBy(css="#ca-move")
	protected WebElement renameDropdown;
	@FindBy(css="#ca-history")
	protected WebElement historyDropdown;
	@FindBy(css=".WikiaMenuElement .createpage")
	protected WebElement addArticleInDropdown;
	@FindBy(css="#wpCreatePageDialogTitle")
	protected WebElement articleTitleInputModal;
	@FindBy(css="#CreatePageDialogButton .createpage")
	protected WebElement submitModal;
	@FindBy(css="#ca-edit")
	protected WebElement editDropdown;
	@FindBy(css="#article-comm")
	protected WebElement commentArea;
	@FindBy(css="#article-comm-form .loading-indicator")
	protected WebElement commentAreaLoadingIndicator;
	@FindBy(css="#article-comm-submit")
	protected WebElement commentSubmitButton;
	@FindBys(@FindBy(css=".speech-bubble-message"))
	protected List<WebElement> articleComments;
	@FindBys(@FindBy(css=".sub-comments .speech-bubble-message"))
	protected List<WebElement> commentReplies;
	@FindBys(@FindBy(css="#WikiaPageHeader .WikiaMenuElement li"))
	protected List<WebElement> editDropdownElements;
	@FindBy(css="#WikiaArticleComments")
	protected WebElement allCommentsArea;
	@FindBy(css="blockquote .article-comments .actionButton[name='wpArticleSubmit']")
	protected WebElement editCommentSubmitButton;
	@FindBy(css=".article-comm-edit-box .loading-indicator")
	protected WebElement replyCommentLoadingIndicator;
	@FindBy(css="blockquote .article-comm-edit-box .actionButton[name='wpArticleSubmit']")
	protected WebElement replyCommentSubmitButton;
	@FindBy(css=".WikiaArticle.article-comm-text")
	protected List<WebElement> commentTextList;
	@FindBy(css="#mw-content-text .thumbimage")
	protected WebElement imageArticle;
	@FindBy(css="#mw-content-text .wikia-gallery")
	protected WebElement galleryArticle;
	@FindBy(css="#mw-content-text .wikia-slideshow")
	protected WebElement slideshowArticle;
	@FindBy(css="#mw-content-text .wikiaPhotoGallery-slider-body")
	protected WebElement sliderArticle;
	@FindBy(css="#mw-content-text .Wikia-video-play-button")
	protected WebElement videoArticle;
	@FindBy(css="section.RelatedVideosModule")
	protected WebElement rVModule;
	@FindBy(css=".button.addVideo")
	protected WebElement rVAddVideo;
	@FindBy(css="#WikiaImagePlaceholderInner0")
	private WebElement videoAddPlaceholder;
	@FindBy(css=".RVBody .item:nth-child(1) .lightbox[data-video-name]")
	private WebElement rvFirstVideo;
	@FindBy(css="#CategorySelectAdd")
	private WebElement addCategory;
	@FindBy(css="#CategorySelectInput")
	private WebElement addCategoryInput;
	@FindBy(css="#CategorySelectSave")
	private WebElement saveCategory;
	@FindBy(css=".WikiaArticleCategories li > span a")
	private List<WebElement> categoryList;
	@FindBy(css=".ui-autocomplete")
	private WebElement categorySuggestionsList;
	@FindBy(css=".categories")
	private WebElement categoriesContainer;
	@FindBy(css=".category.new")
	private WebElement categoryNew;
	@FindBy(css="button.save:not([disabled])")
	private WebElement categorySaveButtonEnabled;
	@FindBy(css="button.save[disabled]")
	private WebElement categorySaveButtonDisabled;
	@FindBy(css = ".WikiaPageHeader h1")
	private WebElement articleTitle;

	By categorySuggestionsListItems = By.cssSelector("li.ui-menu-item > a");

	String editCategorySelector = "li[data-name='%categoryName%'] li.editCategory > img";
	String removeCategorySelector = "li[data-name='%categoryName%'] li.removeCategory > img";

	public ArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyArticleTitle(String title) {
		waitForElementVisibleByElement(articleHeader);
		Assertion.assertEquals(articleHeader.getText(), title);
	}

	public void verifyContent(String content) {
		waitForElementVisibleByElement(articleContent);
		Assertion.assertStringContains(articleContent.getText(), content);
	}

	public VisualEditModePageObject createArticleUsingDropdown(String articleTitle) {
		actionsClick(contributeDropdown);
		waitForElementVisibleByElement(addArticleInDropdown);
		addArticleInDropdown.click();
		articleTitleInputModal.sendKeys(articleTitle);
		submitModal.click();
		return new VisualEditModePageObject(driver);
	}

	public VisualEditModePageObject editArticleUsingDropdown() {
		actionsClick(editDropdown);
		return new VisualEditModePageObject(driver);
	}

	public MiniEditorComponentObject triggerCommentArea() {
		scrollToElement(allCommentsArea);
		waitForElementVisibleByElement(commentArea);
		jQueryFocus(commentArea);
		waitForElementNotVisibleByElement(commentAreaLoadingIndicator);
		return new MiniEditorComponentObject(driver);
	}

	public void submitComment() {
		driver.switchTo().defaultContent();
		commentSubmitButton.click();
		waitForElementNotVisibleByElement(commentSubmitButton);
	}

	public void submitEditComment() {
		driver.switchTo().defaultContent();
		editCommentSubmitButton.click();
		waitForElementNotVisibleByElement(editCommentSubmitButton);
	}

	public void verifyArticleComment(String comment) {
		WebElement mostRecentComment = articleComments.get(0);
		waitForTextToBePresentInElementByElement(mostRecentComment, comment);
		Assertion.assertStringContains(mostRecentComment.getText(), comment);
	}

	public MiniEditorComponentObject triggerEditCommentArea() {
		scrollToElement(allCommentsArea);
		WebElement mostRecentComment = articleComments.get(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//TODO Action chains not working on Firefox for some reason
		js.executeScript(
			"arguments[0].querySelector(arguments[1]).click()",
			mostRecentComment, editButtonSelector
		);
		return new MiniEditorComponentObject(driver);
	}

	public DeleteArticlePageObject deleteComment() {
		scrollToElement(allCommentsArea);
		WebElement mostRecentComment = articleComments.get(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"arguments[0].querySelector(arguments[1]).click()",
				mostRecentComment, deleteButtonSelector
				);
		return new DeleteArticlePageObject(driver);
	}

	public void verifyCommentDeleted(String comment) {
		for(WebElement elem:commentTextList) {
			Assertion.assertTrue(
					!comment.equals(elem.getText())
					);
		}
	}

	public String getFirstCommentText() {
		return commentTextList.get(0).getText();
	}

	public void verifyCommentCreator(String userName) {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement editedByArea = mostRecentComment.findElement(By.cssSelector(commentAuthorLink));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(editedByArea.getText(), userName);
	}

	public MiniEditorComponentObject triggerCommentReply() {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement replyButton = mostRecentComment.findElement(By.cssSelector(replyCommentSelector));
		replyButton.click();
		waitForElementNotVisibleByElement(replyCommentLoadingIndicator);
		return new MiniEditorComponentObject(driver);
	}

	public void submitReplyComment() {
		driver.switchTo().defaultContent();
		waitForElementClickableByElement(replyCommentSubmitButton);
		replyCommentSubmitButton.click();
		waitForElementNotVisibleByElement(replyCommentSubmitButton);
	}

	public void verifyCommentReply(String reply) {
		WebElement mostRecentReply = commentReplies.get(0);
		waitForElementVisibleByElement(mostRecentReply);
		Assertion.assertStringContains(mostRecentReply.getText(), reply);
	}

	public void verifyReplyCreator(String userName) {
		WebElement mostRecentReply = commentReplies.get(0);
		WebElement editedByArea = mostRecentReply.findElement(By.cssSelector(commentAuthorLink));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(editedByArea.getText(), userName);
	}

	public DeleteArticlePageObject deleteArticleUsingDropdown() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(deleteDropdown);
		deleteDropdown.click();
		return new DeleteArticlePageObject(driver);
	}

	public String getArticleName() {
		String articleName = articleHeader.getText();
		PageObjectLogging.log("getArticleName", "the name of the article is: "+articleName, true);
		return articleName;
	}

	public RenameArticlePageObject renameArticleUsingDropdown() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(renameDropdown);
		renameDropdown.click();
		return new RenameArticlePageObject(driver);
	}

	public void verifyDropdownForAdmin() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(renameDropdown);
		waitForElementVisibleByElement(deleteDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(protectDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 4);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for admin", true);
	}

	public void verifyDropdownForUser() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(renameDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 2);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for user", true);
	}

	public void verifyDropdownForAnon() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(historyDropdown);
		Assertion.assertEquals(editDropdownElements.size(), 1);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for anon", true);
	}

	public void verifyPhoto() {
		waitForElementByElement(imageArticle);
		PageObjectLogging.log("verifyPhoto", "photo is visible", true);
	}

	public void verifyGallery() {
		waitForElementByElement(galleryArticle);
		PageObjectLogging.log("verifyGallery", "gallery is visible", true);
	}

	public void verifySlideshow() {
		waitForElementByElement(slideshowArticle);
		PageObjectLogging.log("verifySlideshow", "slideshow is visible", true);
	}

	public void verifySlider() {
		waitForElementByElement(sliderArticle);
		PageObjectLogging.log("verifySlider", "slider is visible", true);
	}

	public void verifyVideo() {
		waitForElementByElement(videoArticle);
		PageObjectLogging.log("verifyVideo", "video is visible", true);
	}

	public void verifyVideoAlignment(PositionsVideo positions) {
		String videoClass = videoArticle.findElement(
				By.xpath("./../..")
				).getAttribute("class");
		String position;
		switch(positions) {
		case left:
			position = "left";
			break;
		case center:
			position = "none";
			break;
		case right:
			position = "right";
			break;
		default:
			position = "position is not provided";
			break;
		}
		Assertion.assertStringContains(videoClass, position);
	}

	public void verifyVideoWidth(int widthDesired) {
		int videoWidth = Integer.parseInt(videoArticle.findElement(
				By.xpath("./../img")
				).getAttribute("width"));
		Assertion.assertNumber(
				widthDesired,
				videoWidth,
				"width should be " + widthDesired + " but is " + videoWidth);
	}

	public void verifyVideoCaption(String captionDesired) {
		String caption = videoArticle.findElement(
				By.xpath("./../../figcaption")
				).getText();
		Assertion.assertStringContains(caption,captionDesired);
		PageObjectLogging.log("verifyVideoCaption", "video has caption", true);
	}

	public void verifyVideoNoCaption() {
		String videoClass = videoArticle.findElement(
				By.xpath("./../img")
				).getAttribute("class");
		Assertion.assertTrue(!videoClass.contains("thumbimage"));
		PageObjectLogging.log("verifyVideoNoCaption", "video has no caption", true);
	}

	public void verifyRelatedVideosModule() {
		waitForElementByElement(rVModule);
		PageObjectLogging.log("verifyRelatedVideosModule", "related videos module is visible", true);
	}

	public VetAddVideoComponentObject clickAddRelatedVideo() {
		waitForElementByElement(rVAddVideo);
		scrollAndClick(rVAddVideo);
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyRelatedVideoAdded(String videoName) {
		waitForTextToBePresentInElementByElement(rvFirstVideo, videoName);
	}

	public VetAddVideoComponentObject clickAddVideoPlaceholder(){
		waitForElementByElement(videoAddPlaceholder);
		scrollAndClick(videoAddPlaceholder);
		return new VetAddVideoComponentObject(driver);
	}

	private void clickAddCategoryButton() {
		scrollAndClick(addCategory);
		waitForElementByElement(addCategoryInput);
	}

	private void typeCategoryName(String category) {
		addCategoryInput.sendKeys(category);
	}


	public void verifySubmitCategoryEnabled() {
		waitForElementByElement(categorySaveButtonEnabled);
	}

	public void verifySubmitCategoryDisabled() {
		waitForElementByElement(categorySaveButtonDisabled);
	}

	public void submitCategory() {
		waitForElementClickableByElement(saveCategory);
		saveCategory.click();
		waitForElementNotVisibleByElement(addCategoryInput);
		PageObjectLogging.log("submitCategory", "submit category clicked", true);
	}

	public void addCategory(String category) {
		clickAddCategoryButton();
		typeCategoryName(category);
		pressEnter(addCategoryInput);
		waitForElementByElement(categoryNew);
		PageObjectLogging.log("addCategory", category + " category added", true);
	}

	public EditCategoryComponentObject editCategory(String category) {
		WebElement editCategory = driver.findElement(
				By.cssSelector(
						editCategorySelector.replace("%categoryName%", category)
				)
		);
		scrollAndClick(editCategory);
		PageObjectLogging.log("editCategory", "edit button on category " + category + " clicked", true);
		return new EditCategoryComponentObject(driver);
	}

	public void removeCategory(String category) {
		WebElement editCategory = driver.findElement(
				By.cssSelector(
						removeCategorySelector.replace("%categoryName%", category)
						)
				);
		scrollAndClick(editCategory);
		PageObjectLogging.log("removeCategory", "remove button on category " + category + " clicked", true);
	}

	public String addCategorySuggestions(String category, int categoryNumber) {
		clickAddCategoryButton();
		typeCategoryName(category);
		waitForElementByElement(categorySuggestionsList);
		List<WebElement> suggestionsList = categorySuggestionsList.findElements(categorySuggestionsListItems);
		WebElement desiredCategory = suggestionsList.get(categoryNumber);
		String desiredCategoryText = desiredCategory.getText();
		desiredCategory.click();
		waitForElementNotVisibleByElement(categorySuggestionsList);
		PageObjectLogging.log("addCategorySuggestions", "category " + category + " added from suggestions", true);
		return desiredCategoryText;
	}

	public void verifyCategoryPresent(String category) {
		boolean categoryVisible = false;
		for (WebElement elem : categoryList) {
			if (elem.getText().equals(category)) {
				categoryVisible = true;
			}
		}
		Assertion.assertTrue(categoryVisible, "category " + category + " not present");
	}

	public void verifyCategoryNotPresent(String category) {
		boolean categoryVisible = true;
		for (WebElement elem : categoryList) {
			if (elem.getText().equals(category)) {
				categoryVisible = false;
			}
		}
		Assertion.assertTrue(categoryVisible, "category " + category + " present");
	}

	public WatchPageObject unfollowArticle(String wikiURL) {
		String url = URLsContent.buildUrl(wikiURL, "title=" + articleTitle.getText());
		url = URLsContent.buildUrl(url, URLsContent.unfollowParameter);
		getUrl(url);
		return new WatchPageObject(driver);
	}
}
