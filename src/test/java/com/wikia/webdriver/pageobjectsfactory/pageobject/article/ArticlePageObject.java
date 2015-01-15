package com.wikia.webdriver.pageobjectsfactory.pageobject.article;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Style;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Alignment;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.interactivemaps.EmbedMapComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.lightbox.LightboxComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.CreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows.VECreateArticleModalComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.actions.DeletePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;


/**
 * @author Bogna 'bognix' Knychała
 */
public class ArticlePageObject extends WikiBasePageObject {

	@FindBy(css = "article header h1")
	protected WebElement articleHeader;
	@FindBy(css = "#mw-content-text")
	protected WebElement articleContentContainer;
	@FindBy(css = "#WikiaMainContentContainer")
	protected WebElement pageContentContainer;
	@FindBy(css = "#mw-content-text p")
	protected WebElement articleContent;
	@FindBy(css = ".wikia-menu-button.contribute, .contribute-button")
	protected WebElement contributeDropdown;
	@FindBy(css = "#ca-history")
	protected WebElement historyDropdown;
	@FindBy(css = ".WikiaMenuElement .createpage, ul .createpage")
	protected WebElement addArticleInDropdown;
	@FindBy(css = "#wpCreatePageDialogTitle")
	protected WebElement articleTitleInputModal;
	@FindBy(css = "#CreatePageModalDialog .primary")
	protected WebElement submitModal;
	@FindBy(css = "#ca-edit")
	protected WebElement editUsingClassicEditor;
	@FindBy(css = "#article-comm")
	protected WebElement commentArea;
	@FindBy(css = "#article-comm-form .loading-indicator")
	protected WebElement commentAreaLoadingIndicator;
	@FindBy(css = "#article-comm-submit")
	protected WebElement commentSubmitButton;
	@FindBys(@FindBy(css = ".speech-bubble-message"))
	protected List<WebElement> articleComments;
	@FindBys(@FindBy(css = ".sub-comments .speech-bubble-message"))
	protected List<WebElement> commentReplies;
	@FindBys(@FindBy(css = "#WikiaPageHeader .WikiaMenuElement li"))
	protected List<WebElement> editDropdownElements;
	@FindBy(css = "#WikiaArticleComments")
	protected WebElement allCommentsArea;
	@FindBy(css = "blockquote .article-comments .actionButton[name='wpArticleSubmit']")
	protected WebElement editCommentSubmitButton;
	@FindBy(css = ".article-comm-edit-box .loading-indicator")
	protected WebElement replyCommentLoadingIndicator;
	@FindBy(css = "blockquote .article-comm-edit-box .actionButton[name='wpArticleSubmit']")
	protected WebElement replyCommentSubmitButton;
	@FindBy(css = ".WikiaArticle.article-comm-text")
	protected List<WebElement> commentTextList;
	@FindBy(css = "#mw-content-text .thumbimage")
	protected WebElement imageArticle;
	@FindBy(css = "#mw-content-text .wikia-gallery")
	protected WebElement galleryArticle;
	@FindBy(css = "#mw-content-text .wikia-slideshow")
	protected WebElement slideshowArticle;
	@FindBy(css = "#mw-content-text .wikiaPhotoGallery-slider-body")
	protected WebElement sliderArticle;
	@FindBy(css = "#toc")
	protected WebElement tableOfContents;
	@FindBy(css = "#toc ol")
	protected WebElement tableOfContentsOrderedList;
	@FindBys(@FindBy(css = "#toc ol a"))
	protected List<WebElement> tableOfContentsSectionsList;
	@FindBy(css = "#togglelink")
	protected WebElement tableOfContentsShowHideButton;
	@FindBy(css = "#mw-content-text .video-thumbnail")
	protected WebElement videoThumbnail;
	@FindBys(@FindBy(css = "#mw-content-text .video-thumbnail"))
	protected List<WebElement> videoThumbnailList;
	@FindBy(css = "#mw-content-text .article-thumb")
	protected WebElement videoThumbnailWrapper;
	@FindBy(css = "#mw-content-text .inline-video")
	protected WebElement videoInline;
	@FindBy(css = ".wikiaVideoPlaceholder #WikiaImagePlaceholderInner0")
	private WebElement videoAddPlaceholder;
	@FindBy(css = ".wikiaImagePlaceholder #WikiaImagePlaceholderInner0")
	private WebElement imageAddPlaceholder;
	@FindBy(css = "figcaption p")
	private WebElement videoTitle;
	@FindBy(css = "a.details.sprite")
	private WebElement videoDetailsButton;
	@FindBy(css = "#CategorySelectAdd")
	private WebElement addCategory;
	@FindBy(css = "#CategorySelectInput")
	private WebElement addCategoryInput;
	@FindBy(css = "#CategorySelectSave")
	private WebElement saveCategory;
	@FindBy(css = "#articleCategories .category.normal .name a")
	private List<WebElement> categoryList;
	@FindBy(css = ".ui-autocomplete")
	private WebElement categorySuggestionsList;
	@FindBy(css = ".category.new")
	private WebElement categoryNew;
	@FindBy(css = "button.save:not([disabled])")
	private WebElement categorySaveButtonEnabled;
	@FindBy(css = "button.save[disabled]")
	private WebElement categorySaveButtonDisabled;
	@FindBy(css = ".WikiaPageHeader h1")
	private WebElement articleTitle;
	@FindBy(css = "#WikiWelcomeModal .close")
	private WebElement welcomeLightBoxCloseButton;
	@FindBy(css = "#WikiWelcomeModal h3")
	private WebElement welcomeLightBoxTitle;
	@FindBy(css = "li.ui-menu-item > a")
	private List<WebElement> categorySuggestionsListItems;
	@FindBy(css = ".article-table")
	private WebElement table;
	@FindBy(css = ".WikiHeader > h1 > a")
	private WebElement wikiNameHeader;
	@FindBy(css = "#mw-content-text img.thumbimage")
	private WebElement thumbnailImageArticle;
	@FindBy(css = ".wikia-menu-button")
	private WebElement articleEditButton;
	@FindBy(css = "#WikiaPageHeader .chevron")
	private WebElement openEditDropdown;
	@FindBy(css = ".view")
	private WebElement viewEmbedMapButton;

	private static final String EDIT_BUTTON_SELECTOR = ".article-comm-edit";
	private static final String DELETE_BUTTON_SELECTOR = ".article-comm-delete";
	private static final String COMMENT_AUTHOR_LINK = ".edited-by";
	private static final String REPLY_COMMENT_SELECTOR = ".article-comm-reply";

	final Integer minInlineVideoSize = 400;

	String editCategorySelector =
		"li[data-name='%categoryName%'] .toolbar .editCategory";
	String removeCategorySelector =
		"li[data-name='%categoryName%'] .toolbar .removeCategory";
	String videoInCommentsSelector =
		".speech-bubble-message img[data-video-name*='%videoName%']";

	public ArticlePageObject(WebDriver driver) {
		super(driver);
	}

	public String getAtricleTextRaw() {
		return pageContentContainer.getText();

	}

	public void verifyArticleTitle(String title) {
		waitForElementVisibleByElement(articleHeader);
		Assertion.assertEquals(articleHeader.getText(), title);
	}

	public void verifyContent(String content) {
		waitForElementVisibleByElement(articleContent);
		Assertion.assertStringContains(content, articleContent.getText());
	}


	public void verifyFormattingFromVE(Formatting format, String content) {
		waitForElementNotVisibleByElement(veMode);
		List<WebElement> elements = articleContentContainer.findElements(format.getTag());
		boolean isPresent = false;
		for (WebElement elem : elements) {
			if (elem.getText().contains(content)) {
				isPresent = true;
				break;
			}
		}
		waitForElementByElement(articleEditButton);
		Assertion.assertTrue(isPresent, "text is not present in the article");
	}

	public void verifyStyleFromVE(Style style, String content) {
		waitForElementNotVisibleByElement(veMode);
		List<WebElement> elements = articleContentContainer.findElements(style.getTag());
		boolean isPresent = false;
		for (WebElement elem : elements) {
			if (elem.getText().equals(content)) {
				isPresent = true;
				break;
			}
		}
		waitForElementByElement(articleEditButton);
		Assertion.assertTrue(isPresent, "text is not present in the article");
	}

	public VisualEditModePageObject createArticleInCKUsingDropdown(String articleTitle) {
		actionsClick(contributeDropdown);
		waitForElementVisibleByElement(addArticleInDropdown);
		CreateArticleModalComponentObject articleModal = clickArticleInDropDown(addArticleInDropdown);
		articleModal.createPageWithBlankLayout(articleTitle);
		return new VisualEditModePageObject(driver);
	}

	public SourceEditModePageObject createArticleInSrcUsingDropdown(String articleTitle) {
		actionsClick(contributeDropdown);
		waitForElementVisibleByElement(addArticleInDropdown);
		CreateArticleModalComponentObject articleModal = clickArticleInDropDown(addArticleInDropdown);
		articleModal.createPageWithBlankLayout(articleTitle);
		return new SourceEditModePageObject(driver);
	}

	private CreateArticleModalComponentObject clickArticleInDropDown(WebElement articleDropDown) {
		waitForElementClickableByElement(addArticleInDropdown);
		addArticleInDropdown.click();
		return new CreateArticleModalComponentObject(driver);
	}

	public VisualEditorPageObject createArticleInVEUsingDropdown(String articleTitle) {
		actionsClick(contributeDropdown);
		waitForElementVisibleByElement(addArticleInDropdown);
		addArticleInDropdown.click();
		articleTitleInputModal.sendKeys(articleTitle);
		submitModal.click();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditModePageObject editArticleInRTEUsingDropdown() {
		openEditDropdown.click();
		waitForElementVisibleByElement(editUsingClassicEditor);
		editUsingClassicEditor.click();
		return new VisualEditModePageObject(driver);
	}

	public SourceEditModePageObject editArticleInSrcUsingDropdown() {
		actionsClick(editUsingClassicEditor);
		return new SourceEditModePageObject(driver);
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
		scrollAndClick(commentSubmitButton);
		waitForElementNotVisibleByElement(commentSubmitButton);
		PageObjectLogging.log("submitComment", "comment has been submitted", true);
	}

	public void submitEditComment() {
		driver.switchTo().defaultContent();
		editCommentSubmitButton.click();
		waitForElementNotVisibleByElement(editCommentSubmitButton);
	}

	public void verifyCommentText(String comment) {
		WebElement mostRecentComment = articleComments.get(0);
		waitForTextToBePresentInElementByElement(mostRecentComment, comment);
		Assertion.assertStringContains(comment, mostRecentComment.getText());
	}

	public void verifyCommentVideo(String videoName) {
		driver.findElement(
			By.cssSelector(videoInCommentsSelector.replace("%videoName%", videoName))
		);
		PageObjectLogging.log("verifyCommentVideo", "video is visible in comments section", true);
	}

	public MiniEditorComponentObject triggerEditCommentArea() {
		scrollToElement(allCommentsArea);
		WebElement mostRecentComment = articleComments.get(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
			"arguments[0].querySelector(arguments[1]).click()",
			mostRecentComment, EDIT_BUTTON_SELECTOR
		);
		return new MiniEditorComponentObject(driver);
	}

	public DeletePageObject deleteFirstComment() {
		scrollToElement(allCommentsArea);
		WebElement mostRecentComment = articleComments.get(0);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
			"arguments[0].querySelector(arguments[1]).click()",
			mostRecentComment, DELETE_BUTTON_SELECTOR
		);
		return new DeletePageObject(driver);
	}

	public void verifyCommentDeleted(String comment) {
		if (!commentTextList.isEmpty()) {
			for (WebElement elem : commentTextList) {
				Assertion.assertTrue(!comment.equals(elem.getText()));
			}
		}
		PageObjectLogging.log("verifyCommentDeleted", "comment " + comment
			+ " was deleted", true);
	}

	public String getFirstCommentText() {
		return commentTextList.get(0).getText();
	}

	public void verifyCommentCreator(String userName) {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement editedByArea = mostRecentComment.findElement(By.cssSelector(COMMENT_AUTHOR_LINK));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(userName, editedByArea.getText());
	}

	public MiniEditorComponentObject triggerCommentReply() {
		WebElement mostRecentComment = articleComments.get(0);
		WebElement replyButton = mostRecentComment.findElement(By.cssSelector(REPLY_COMMENT_SELECTOR));
		scrollAndClick(replyButton);
		waitForElementNotVisibleByElement(replyCommentLoadingIndicator);
		return new MiniEditorComponentObject(driver);
	}

	public void submitReplyComment() {
		driver.switchTo().defaultContent();
		waitForElementClickableByElement(replyCommentSubmitButton);
		scrollAndClick(replyCommentSubmitButton);
		waitForElementNotVisibleByElement(replyCommentSubmitButton);
	}

	public void verifyCommentReply(String reply) {
		WebElement mostRecentReply = commentReplies.get(0);
		waitForElementVisibleByElement(mostRecentReply);
		Assertion.assertStringContains(reply, mostRecentReply.getText());
	}

	public void verifyReplyCreator(String userName) {
		WebElement mostRecentReply = commentReplies.get(0);
		WebElement editedByArea = mostRecentReply.findElement(By.cssSelector(COMMENT_AUTHOR_LINK));
		waitForElementVisibleByElement(editedByArea);
		Assertion.assertStringContains(userName, editedByArea.getText());
	}

	public String getArticleName() {
		String articleName = articleHeader.getText();
		PageObjectLogging.log("getArticleName", "the name of the article is: " + articleName, true);
		return articleName;
	}

	public void verifyDropdownForAdmin() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(renameDropdown);
		waitForElementVisibleByElement(deleteDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(protectDropdown);
		waitForElementVisibleByElement(veEditButton);
		Assertion.assertEquals(editDropdownElements.size(), 5);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for admin", true);
	}

	public void verifyDropdownForUser() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(renameDropdown);
		waitForElementVisibleByElement(veEditButton);
		Assertion.assertEquals(editDropdownElements.size(), 3);
		PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for user", true);
	}

	public void verifyDropdownForAnon() {
		actionsClick(articleEditDropdown);
		waitForElementVisibleByElement(historyDropdown);
		waitForElementVisibleByElement(veEditButton);
		Assertion.assertEquals(editDropdownElements.size(), 2);
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
		driver.findElement(By.cssSelector("#mw-content-text .video-thumbnail"));
		PageObjectLogging.log("verifyVideo", "video is visible", true);
	}

	public void verifyVideoInline() {
		waitForElementByElement(videoInline);
		PageObjectLogging.log("verifyVideoInline", "Video is visible", true);
	}

	public void verifyVideoAutoplay(String providerName) {
		VideoComponentObject video = new VideoComponentObject(driver, videoInline);
		video.verifyVideoAutoplay(providerName, true);
	}

	public VideoComponentObject getVideoPlayer() {
		return new VideoComponentObject(driver, videoInline);
	}

	private void verifyTableProperty(String propertyName, int propertyValue) {
		waitForElementByElement(table);
		Assertion.assertEquals(table.getAttribute(propertyName), Integer.toString(propertyValue));
		PageObjectLogging.log(
			"verifyTableProperty",
			"table has correct " + propertyName + " property",
			true
		);
	}

	public void verifyTableBorder(int propertyValue) {
		verifyTableProperty("border", propertyValue);
	}

	public void verifyTableCellpadding(int propertyValue) {
		verifyTableProperty("cellpadding", propertyValue);
	}

	public void verifyTableCellspacing(int propertyValue) {
		verifyTableProperty("cellspacing", propertyValue);
	}

	public void verifyTableAlignment(Alignment alignment) {
		waitForElementByElement(table);
		Assertion.assertEquals(
			table.getCssValue("float").toLowerCase(),
			alignment.toString().toLowerCase()
		);
		PageObjectLogging.log("verifyTableAlignment", "table has correct alignment", true);
	}

	public void verifyTableSize(int width, int height) {
		waitForElementByElement(table);
		Dimension size = table.getSize();
		Assertion.assertEquals(size.getHeight(), height);
		Assertion.assertEquals(size.getWidth(), width);
		PageObjectLogging.log("verifyTableSize", "table has correct size", true);
	}

	public void verifyVideoAlignment(PositionsVideo positions) {
		String videoClass = videoThumbnail.findElement(
			By.xpath("./..")
		).getAttribute("class");
		String position;
		switch (positions) {
			case LEFT:
				position = "left";
				break;
			case CENTER:
				position = "none";
				break;
			case RIGHT:
				position = "right";
				break;
			default:
				position = "position is not provided";
				break;
		}
		Assertion.assertStringContains(position, videoClass);
	}

	public Integer getVideoWidth(WebElement thumbnail) {
		int videoWidth = Integer.parseInt(thumbnail.findElement(
			By.tagName("img")
		).getAttribute("width"));
		PageObjectLogging.log("getVideoWidth", "Video width is " + videoWidth, true);
		return videoWidth;
	}

	public void verifyVideoWidth(int widthDesired) {
		int videoWidth = getVideoWidth(videoThumbnail);
		Assertion.assertNumber(
			widthDesired,
			videoWidth,
			"width should be " + widthDesired + " but is " + videoWidth
		);
	}

	public void verifyVideoCaption(String captionDesired) {
		String caption = videoThumbnailWrapper.findElement(
			By.className("caption")
		).getText();
		Assertion.assertStringContains(captionDesired, caption);
		PageObjectLogging.log("verifyVideoCaption", "video has expected caption", true);
	}

	public void verifyVideoName(String nameDesired) {
		String name = videoThumbnailWrapper.findElement(
			By.className("title")
		).getText();
		Assertion.assertStringContains(nameDesired, name);
		PageObjectLogging.log("verifyVideoName", "video has expected name", true);
	}

	public VetAddVideoComponentObject clickAddVideoPlaceholder() {
		waitForElementByElement(videoAddPlaceholder);
		scrollAndClick(videoAddPlaceholder);
		return new VetAddVideoComponentObject(driver);
	}

	public PhotoAddComponentObject clickAddImagePlaceholder() {
		waitForElementByElement(imageAddPlaceholder);
		scrollAndClick(imageAddPlaceholder);
		return new PhotoAddComponentObject(driver);
	}

	public FilePagePageObject clickVideoDetailsButton() {
		waitForElementByElement(videoTitle);
		executeScript("$('a.details.sprite').css('visibility', 'visible')");
		waitForElementByElement(videoDetailsButton);
		videoDetailsButton.click();
		PageObjectLogging.log("clickVideoDetailsButton", "Video Details link is clicked", true);
		return new FilePagePageObject(driver);
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
		WebElement desiredCategory = categorySuggestionsListItems.get(categoryNumber);
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

	public WatchPageObject unfollowArticle(String wikiURL) {
		String url = urlBuilder.appendQueryStringToURL(wikiURL, "title=" + articleTitle.getText());
		url = urlBuilder.appendQueryStringToURL(url, URLsContent.ACTION_UNFOLLOW);
		getUrl(url);
		return new WatchPageObject(driver);
	}

	public void verifyTOCpresent() {
		waitForElementByElement(tableOfContents);
		PageObjectLogging.log("verifyTOCpresent", "toc is present", true);
	}

	public void verifyTOCexpanded() {
		waitForElementByElement(tableOfContentsOrderedList);
		PageObjectLogging.log("verifyTOCexpanded", "toc is expanded", true);
	}

	public void verifyTOCcollapsed() {
		waitForElementNotVisibleByElement(tableOfContentsOrderedList);
		PageObjectLogging.log("verifyTOCcollapsed", "toc is collapsed", true);
	}

	/**
	 * the method clicks on button show or hide,
	 * depending on which one is currently visible
	 */
	public void clickTOCshowHideButton() {
		waitForElementByElement(tableOfContentsShowHideButton);
		scrollAndClick(tableOfContentsShowHideButton);
		PageObjectLogging.log("clickTOCshowHideButton", "table of contents 'show/hide' button clicked", true);
	}

	/**
	 * 1. remember the section that TOC link points to
	 * 2. click on the TOC link
	 * 3. verify that the section went up on the screen
	 * 4. verify that the wanted section is almost on the top of the screen
	 *
	 * @param numberOfTheSection - TOC link counting from the top
	 */
	public void verifyTOCsectionLinkWorks(int numberOfTheSection) {
		WebElement sectionTOClink = tableOfContentsSectionsList.get(numberOfTheSection - 1);
		String sectionID = sectionTOClink.getAttribute("href").substring(getCurrentUrl().length());
		WebElement sectionOnArticle = driver.findElement(By.cssSelector(sectionID));
		int sectionYbefore = sectionOnArticle.getLocation().getY();
		sectionTOClink.click();
		int sectionYafter = sectionOnArticle.getLocation().getY();
		Assertion.assertNotEquals(sectionYbefore, sectionYafter);
		// assume that if section is less than 5px from top, it is scrolled up properly
		Assertion.assertTrue(sectionYafter < 5);
		PageObjectLogging.log("verifyTOCsectionLinkWorks", "choosen section " + sectionID + " was scrolled up", true);
	}

	public void verifyWikiTitleOnCongratualtionsLightBox(String wikiName) {
		Assertion.assertStringContains(wikiName, welcomeLightBoxTitle.getText());
	}

	public void closeNewWikiCongratulationsLightBox() {
		waitForElementByElement(welcomeLightBoxCloseButton);
		scrollAndClick(welcomeLightBoxCloseButton);
		PageObjectLogging.log("closeNewWikiCongratulationsLightBox ", "congratulations lightbox closed", true);
	}

	public void verifyWikiTitleHeader(String wikiName) {
		Assertion.assertStringContains(wikiName, wikiNameHeader.getText());
	}

	public void verifyTableRemoved() {
		Assertion.assertTrue(!checkIfElementOnPage(table));
		PageObjectLogging.log("verifyTableRemoved", "table was removed", true);
	}

	public LightboxComponentObject clickThumbnailImage() {
		waitForElementClickableByElement(thumbnailImageArticle);
		thumbnailImageArticle.click();
		PageObjectLogging.log("clickThumbnailImage", "Thumbnail image is clicked", true);
		return new LightboxComponentObject(driver);
	}

	public LightboxComponentObject clickThumbnailVideoLightbox() {
		waitForElementClickableByElement(videoThumbnail);
		videoThumbnail.click();
		PageObjectLogging.log("clickThumbnailVideoLightbox", "Video thumbnail is clicked", true);
		return new LightboxComponentObject(driver);
	}

	public VideoComponentObject clickThumbnailVideoInline() {
		WebElement thumbnail = getThumbnailVideoInline();
		waitForElementClickableByElement(thumbnail);
		thumbnail.click();
		PageObjectLogging.log("clickThumbnailVideoInline", "Video thumbnail is clicked", true);
		verifyVideoInline();
		Integer videoWidth = getVideoWidth(thumbnail);
		return new VideoComponentObject(driver, videoInline, videoWidth);
	}

	public WebElement getThumbnailVideoInline() {
		for (WebElement thumbnail : videoThumbnailList) {
			Integer width = getVideoWidth(thumbnail);
			if (width > minInlineVideoSize) {
				PageObjectLogging.log("getThumbnailVideoInline", "Video thumbnail found", true);
				return thumbnail;
			}
		}
		PageObjectLogging.log("getThumbnailVideoInline", "Video thumbnail not found", true);
		return null;
	}

	public VisualEditorPageObject openVEModeWithRedLinks(int linkNumber) {
		WebElement redLinkToClick = redLinks.get(linkNumber);
		VECreateArticleModalComponentObject veArticleModal = clickVERedLink(redLinkToClick);
		veArticleModal.createPage();
		return new VisualEditorPageObject(driver);
	}

	public VisualEditModePageObject openCKModeWithRedLinks(int linkNumber) {
		WebElement redLinkToClick = redLinks.get(linkNumber);
		CreateArticleModalComponentObject articleModal = clickRedLink(redLinkToClick);
		articleModal.createPageWithBlankLayout("");
		return new VisualEditModePageObject(driver);
	}

	private VECreateArticleModalComponentObject clickVERedLink(WebElement redLink) {
		waitForElementClickableByElement(redLink);
		jQueryClick(redLink);
		return new VECreateArticleModalComponentObject(driver);
	}

	private CreateArticleModalComponentObject clickRedLink(WebElement redLink) {
		waitForElementClickableByElement(redLink);
		jQueryClick(redLink);
		return new CreateArticleModalComponentObject(driver);
	}

	public SourceEditModePageObject openSrcModeWithRedLinks(int linkNumber) {
		WebElement redLinkToClick = redLinks.get(linkNumber);
		CreateArticleModalComponentObject articleModal = clickRedLink(redLinkToClick);
		articleModal.createPageWithBlankLayout("");
		return new SourceEditModePageObject(driver);
	}

	public EmbedMapComponentObject clickViewEmbedMap() {
		waitForElementVisibleByElement(viewEmbedMapButton);
		scrollToElement(viewEmbedMapButton);
		viewEmbedMapButton.click();
		driver.switchTo().activeElement();
		return new EmbedMapComponentObject(driver);
	}
}
