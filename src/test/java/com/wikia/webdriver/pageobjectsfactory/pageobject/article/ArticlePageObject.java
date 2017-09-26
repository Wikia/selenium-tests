package com.wikia.webdriver.pageobjectsfactory.pageobject.article;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.interactions.Typing;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Editor;
import com.wikia.webdriver.common.dataprovider.VisualEditorDataProvider.Formatting;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.oasis.components.comment.ArticleComment;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addtable.TableBuilderComponentObject.Alignment;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editcategory.EditCategoryComponentObject;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class ArticlePageObject extends WikiBasePageObject {

  @FindBy(css = ".page-header__title")
  protected WebElement articleHeader;
  @FindBy(css = "#mw-content-text")
  protected WebElement articleContentContainer;
  @FindBy(css = "#WikiaMainContentContainer")
  protected WebElement pageContentContainer;
  @FindBy(css = "#mw-content-text p")
  protected WebElement articleContent;
  @FindBy(css = "#ca-history")
  protected WebElement historyDropdown;
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
  @FindBys(@FindBy(css = ".page-header__contribution-buttons .wds-dropdown__content .wds-list li"))
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
  private WebElement categorySuggestionsDialog;
  @FindBy(css = ".category.new")
  private WebElement categoryNew;
  @FindBy(css = "button.save:not([disabled])")
  private WebElement categorySaveButtonEnabled;
  @FindBy(css = "button.save[disabled]")
  private WebElement categorySaveButtonDisabled;
  @FindBy(css = "#WikiWelcomeModal .close")
  private WebElement welcomeLightBoxCloseButton;
  @FindBy(css = "#WikiWelcomeModal h3")
  private WebElement welcomeLightBoxTitle;
  @FindBy(css = "li.ui-menu-item > a")
  private List<WebElement> categorySuggestionsListItems;
  @FindBy(css = ".article-table")
  private WebElement table;
  @FindBy(css = ".wds-community-header__sitename a")
  private WebElement wikiNameHeader;
  @FindBy(css = "#mw-content-text img.thumbimage")
  private WebElement thumbnailImageArticle;
  @FindBy(css = ".wds-button-group")
  private WebElement articleEditButton;
  @FindBy(css = "[href='#WikiaArticleComments']")
  private WebElement commentButton;

  @Getter(lazy = true)
  private final ArticleComment articleComment = new ArticleComment();

  private static final String EDIT_BUTTON_SELECTOR = ".article-comm-edit";
  private static final String DELETE_BUTTON_SELECTOR = ".article-comm-delete";
  private static final String COMMENT_AUTHOR_LINK = ".edited-by";
  private static final String REPLY_COMMENT_SELECTOR = ".article-comm-reply";

  final Integer minInlineVideoSize = 400;

  String editCategorySelector = "li[data-name='%categoryName%'] .toolbar .editCategory";
  String removeCategorySelector = "li[data-name='%categoryName%'] .toolbar .removeCategory";
  String videoInCommentsSelector = ".speech-bubble-message img[data-video-name*='%videoName%']";


  /**
   * Open article with name that is the following combination: TEST CLASS NAME + TEST METHOD NAME
   */
  public ArticlePageObject open() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
           + TestContext.getCurrentMethodName());

    return this;
  }

  public ArticlePageObject open(String articleTitle) {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
           + articleTitle);
    return this;
  }

  public ArticlePageObject openArticleByPath(String articlePath) {
    getUrl(String.format("%s%s", urlBuilder.getUrlForWiki(Configuration.getWikiName()), articlePath));
    return this;
  }

  public String getAtricleTextRaw() {
    return pageContentContainer.getText();

  }

  public String getArticleTitle(){
    wait.forElementVisible(articleTitle);
    return articleTitle.getText();
  }

  public void verifyArticleTitle(String title) {
    wait.forElementVisible(articleTitle);
    Assertion.assertEquals(articleTitle.getText(), title);
  }

  public void verifyContent(String content) {
    wait.forElementVisible(articleContent);
    Assertion.assertStringContains(articleContent.getText(), content);
  }

  public void verifyElementInContent(By element) {
    wait.forElementVisible(articleContent);
    Assertion.assertTrue(articleContentContainer.findElement(element).isDisplayed());
  }

  public String getContent() {
    wait.forElementVisible(articleContent);

    return articleContent.getText();
  }

  public void verifyFormattingFromVE(Formatting format, String content) {
    waitForElementNotVisibleByElement(veMode);

    List<WebElement> elements = articleContentContainer.findElements(format.getTag());
    boolean isPresent = elements.stream().anyMatch(elem -> elem.getText().contains(content));

    wait.forElementVisible(articleEditButton);
    Assertion.assertTrue(isPresent, "text is not present in the article");
  }

  public MiniEditorComponentObject triggerCommentArea() {
    jsActions.scrollToElement(allCommentsArea);
    wait.forElementVisible(commentArea);
    jsActions.focus(commentArea);
    waitForElementNotVisibleByElement(commentAreaLoadingIndicator);
    return new MiniEditorComponentObject(driver);
  }

  public void submitComment() {
    driver.switchTo().defaultContent();
    scrollAndClick(commentSubmitButton);
    waitForElementNotVisibleByElement(commentSubmitButton, 30);
    PageObjectLogging.log("submitComment", "comment has been submitted", true);
  }

  public void submitEditComment() {
    driver.switchTo().defaultContent();
    editCommentSubmitButton.click();
    waitForElementNotVisibleByElement(editCommentSubmitButton);
  }

  public void verifyCommentText(String comment) {
    WebElement mostRecentComment = articleComments.get(0);
    wait.forTextInElement(mostRecentComment, comment);
    Assertion.assertStringContains(mostRecentComment.getText(), comment);
  }

  public boolean isVideoCommentPresent(String videoName) {
    try {
      driver.findElement(By.cssSelector(videoInCommentsSelector.replace("%videoName%", videoName)));
      return true;
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Video is not visible in comments section", e);
      return false;
    }
  }

  public MiniEditorComponentObject triggerEditCommentArea() {
    jsActions.scrollToElement(allCommentsArea);
    WebElement mostRecentComment = articleComments.get(0);
    PageObjectLogging.log("First check",mostRecentComment.getText(), true);

    WebElement editButton = mostRecentComment.findElement(By.cssSelector(EDIT_BUTTON_SELECTOR));
    new Actions(driver).moveToElement(editButton).perform();
    driver.executeScript("arguments[0].querySelector(arguments[1]).click()", mostRecentComment,
                     EDIT_BUTTON_SELECTOR);
    return new MiniEditorComponentObject(driver);
  }

  public DeletePageObject deleteFirstComment() {
    jsActions.scrollToElement(allCommentsArea);
    WebElement mostRecentComment = articleComments.get(0);
    driver.executeScript("arguments[0].querySelector(arguments[1]).click()", mostRecentComment,
                     DELETE_BUTTON_SELECTOR);
    return new DeletePageObject(driver);
  }

  public void verifyCommentDeleted(String comment) {
    if (!commentTextList.isEmpty()) {
      for (WebElement elem : commentTextList) {
        Assertion.assertTrue(!comment.equals(elem.getText()));
      }
    }
    PageObjectLogging.log("verifyCommentDeleted", "comment " + comment + " was deleted", true);
  }

  public String getFirstCommentText() {
    return commentTextList.get(0).getText();
  }

  public void verifyCommentCreator(String userName) {
    WebElement mostRecentComment = articleComments.get(0);
    WebElement editedByArea = mostRecentComment.findElement(By.cssSelector(COMMENT_AUTHOR_LINK));
    wait.forElementVisible(editedByArea);
    Assertion.assertStringContains(editedByArea.getText(), userName);
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
    wait.forElementClickable(replyCommentSubmitButton);
    scrollAndClick(replyCommentSubmitButton);
    waitForElementNotVisibleByElement(replyCommentSubmitButton);
  }

  public void verifyCommentReply(String reply) {
    WebElement mostRecentReply = commentReplies.get(0);
    wait.forElementVisible(mostRecentReply);
    Assertion.assertStringContains(mostRecentReply.getText(), reply);
  }

  public void verifyReplyCreator(String userName) {
    WebElement mostRecentReply = commentReplies.get(0);
    WebElement editedByArea = mostRecentReply.findElement(By.cssSelector(COMMENT_AUTHOR_LINK));
    wait.forElementVisible(editedByArea);
    Assertion.assertStringContains(editedByArea.getText(), userName);
  }

  public String getArticleName() {
    String articleName = articleTitle.getText();
    PageObjectLogging.log("getArticleName", "the name of the article is: " + articleName, true);
    return articleName;
  }

  public void verifyDropdownForAdmin() {
    this.openArticleEditDropdown();
    wait.forElementVisible(renameDropdown);
    wait.forElementVisible(deleteDropdown);
    wait.forElementVisible(historyDropdown);
    wait.forElementVisible(protectDropdown);
    wait.forElementVisible(veEditButton);
    Assertion.assertEquals(editDropdownElements.size(), 5);
    PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for admin", true);
  }

  public void verifyDropdownForUser() {
    this.openArticleEditDropdown();
    wait.forElementVisible(historyDropdown);
    wait.forElementVisible(renameDropdown);
    wait.forElementVisible(veEditButton);
    Assertion.assertEquals(editDropdownElements.size(), 3);
    PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for user", true);
  }

  public void verifyDropdownForAnon() {
    this.openArticleEditDropdown();
    wait.forElementVisible(historyDropdown);
    wait.forElementVisible(veEditButton);
    Assertion.assertEquals(editDropdownElements.size(), 2);
    PageObjectLogging.log("DropdownVerified", "Edit dropdown verified for anon", true);
  }

  public void verifyPhoto() {
    wait.forElementVisible(imageArticle);
    PageObjectLogging.log("verifyPhoto", "photo is visible", true);
  }

  public void verifyGallery() {
    wait.forElementVisible(galleryArticle);
    PageObjectLogging.log("verifyGallery", "gallery is visible", true);
  }

  public void verifySlideshow() {
    wait.forElementVisible(slideshowArticle);
    PageObjectLogging.log("verifySlideshow", "slideshow is visible", true);
  }

  public void verifySlider() {
    wait.forElementVisible(sliderArticle);
    PageObjectLogging.log("verifySlider", "slider is visible", true);
  }

  public void verifyVideo() {
    driver.findElement(By.cssSelector("#mw-content-text .video-thumbnail"));
    PageObjectLogging.log("verifyVideo", "video is visible", true);
  }

  public void verifyVideoInline() {
    wait.forElementVisible(videoInline);
    PageObjectLogging.log("verifyVideoInline", "Video is visible", true);
  }

  private void verifyTableProperty(String propertyName, int propertyValue) {
    wait.forElementVisible(table);
    Assertion.assertEquals(table.getAttribute(propertyName), Integer.toString(propertyValue));
    PageObjectLogging.log("verifyTableProperty", "table has correct " + propertyName + " property",
                          true);
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
    wait.forElementVisible(table);
    Assertion.assertEquals(table.getCssValue("float").toLowerCase(), alignment.toString()
        .toLowerCase());
    PageObjectLogging.log("verifyTableAlignment", "table has correct alignment", true);
  }

  public void verifyTableSize(int width, int height) {
    wait.forElementVisible(table);
    Dimension size = table.getSize();
    Assertion.assertEquals(size.getHeight(), height);
    Assertion.assertEquals(size.getWidth(), width);
    PageObjectLogging.log("verifyTableSize", "table has correct size", true);
  }

  public void verifyVideoAlignment(PositionsVideo positions) {
    String videoClass = videoThumbnail.findElement(By.xpath("./..")).getAttribute("class");
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
    Assertion.assertStringContains(videoClass, position);
  }

  public Integer getVideoWidth(WebElement thumbnail) {
    return Integer.parseInt(thumbnail.findElement(By.tagName("img")).getAttribute("width"));
  }

  public int getVideoThumbnailWidth() {
    return getVideoWidth(videoThumbnail);
  }

  public String getVideoCaption() {
    return videoThumbnailWrapper.findElement(By.className("caption")).getText();
  }

  public void verifyVideoName(String nameDesired) {
    String name = videoThumbnailWrapper.findElement(By.className("title")).getText();
    Assertion.assertStringContains(name, nameDesired);
    PageObjectLogging.log("verifyVideoName", "video has expected name", true);
  }

  public VetAddVideoComponentObject clickAddVideoPlaceholder() {
    wait.forElementVisible(videoAddPlaceholder);
    scrollAndClick(videoAddPlaceholder);
    return new VetAddVideoComponentObject(driver);
  }

  public PhotoAddComponentObject clickAddImagePlaceholder() {
    wait.forElementVisible(imageAddPlaceholder);
    scrollAndClick(imageAddPlaceholder);
    return new PhotoAddComponentObject(driver);
  }

  private void clickAddCategoryButton() {
    scrollAndClick(addCategory);
    wait.forElementVisible(addCategoryInput);
  }

  private void typeCategoryName(String category) {
    Typing.sendKeysHumanSpeed(addCategoryInput, category);
  }

  public void verifySubmitCategoryEnabled() {
    wait.forElementVisible(categorySaveButtonEnabled);
  }

  public void verifySubmitCategoryDisabled() {
    wait.forElementVisible(categorySaveButtonDisabled);
  }

  public void submitCategory() {
    wait.forElementClickable(saveCategory);
    saveCategory.click();
    waitForElementNotVisibleByElement(addCategoryInput);
    PageObjectLogging.log("submitCategory", "submit category clicked", true);
  }

  public void addCategory(String category) {
    clickAddCategoryButton();
    typeCategoryName(category);
    new Actions(driver).sendKeys(addCategoryInput, Keys.ENTER).perform();
    wait.forElementVisible(categoryNew);
    PageObjectLogging.log("addCategory", category + " category added", true);
  }

  public EditCategoryComponentObject editCategory(String category) {
    WebElement editCategory =
        driver
            .findElement(By.cssSelector(editCategorySelector.replace("%categoryName%", category)));
    scrollAndClick(editCategory);
    PageObjectLogging.log("editCategory", "edit button on category " + category + " clicked", true);
    return new EditCategoryComponentObject(driver);
  }

  public void removeCategory(String category) {
    WebElement editCategory =
        driver.findElement(By.cssSelector(removeCategorySelector
                                              .replace("%categoryName%", category)));
    scrollAndClick(editCategory);
    PageObjectLogging.log("removeCategory", "remove button on category " + category + " clicked",
                          true);
  }

  public String addCategorySuggestions(String category, int categoryIndex) {
    clickAddCategoryButton();
    typeCategoryName(category);
    wait.forElementVisible(categorySuggestionsDialog);
    if (categoryIndex > categorySuggestionsListItems.size()-1){
      throw new WebDriverException("List of category suggestions doesn't contain sufficient number of elements");
    }
    WebElement desiredCategory = categorySuggestionsListItems.get(categoryIndex);
    String desiredCategoryText = desiredCategory.getText();
    scrollAndClick(categorySuggestionsListItems.get(categoryIndex));
    waitForElementNotVisibleByElement(categorySuggestionsDialog);
    PageObjectLogging.log("addCategorySuggestions", "category " + desiredCategoryText
                                                    + " added from suggestions", true);
    return desiredCategoryText;
  }

  public boolean isCategoryPresent(String category) {
    return categoryList.stream().anyMatch(elem -> elem.getText().equals(category));
  }

  public WatchPageObject unfollowArticle() {
    String
        url =
        urlBuilder
            .appendQueryStringToURL(urlBuilder.getUrlForWiki(), "title=" + articleTitle.getText());
    url = urlBuilder.appendQueryStringToURL(url, URLsContent.ACTION_UNFOLLOW);
    getUrl(url);
    return new WatchPageObject();
  }

  public void verifyTOCpresent() {
    wait.forElementVisible(tableOfContents);
    PageObjectLogging.log("verifyTOCpresent", "toc is present", true);
  }

  public void verifyTOCexpanded() {
    wait.forElementVisible(tableOfContentsOrderedList);
    PageObjectLogging.log("verifyTOCexpanded", "toc is expanded", true);
  }

  public void verifyTOCcollapsed() {
    waitForElementNotVisibleByElement(tableOfContentsOrderedList);
    PageObjectLogging.log("verifyTOCcollapsed", "toc is collapsed", true);
  }

  /**
   * the method clicks on button show or hide, depending on which one is currently visible
   */
  public void clickTOCshowHideButton() {
    wait.forElementVisible(tableOfContentsShowHideButton);
    scrollAndClick(tableOfContentsShowHideButton);
    PageObjectLogging.log("clickTOCshowHideButton", "table of contents 'show/hide' button clicked",
                          true);
  }

  /**
   * 1. remember the section that TOC link points to 2. click on the TOC link 3. verify that the
   * section went up on the screen 4. verify that the wanted section is almost on the top of the
   * screen
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
    PageObjectLogging.log("verifyTOCsectionLinkWorks", "choosen section " + sectionID
                                                       + " was scrolled up", true);
  }

  public void verifyWikiTitleOnCongratualtionsLightBox(String wikiName) {
    wait.forElementVisible(welcomeLightBoxTitle);
    Assertion.assertStringContains(welcomeLightBoxTitle.getText(), wikiName);
  }

  public void closeNewWikiCongratulationsLightBox() {
    wait.forElementClickable(welcomeLightBoxCloseButton);
    scrollAndClick(welcomeLightBoxCloseButton);
    PageObjectLogging.log("closeNewWikiCongratulationsLightBox ",
                          "congratulations lightbox closed", true);
  }

  public void verifyWikiTitleHeader(String wikiName) {
    Assertion.assertStringContains(wikiNameHeader.getText(), wikiName);
  }

  public void verifyTableRemoved() {
    Assertion.assertTrue(!isElementOnPage(table));
    PageObjectLogging.log("verifyTableRemoved", "table was removed", true);
  }

  public LightboxComponentObject clickThumbnailImage() {
    wait.forElementClickable(thumbnailImageArticle);
    thumbnailImageArticle.click();
    PageObjectLogging.log("clickThumbnailImage", "Thumbnail image is clicked", true);
    return new LightboxComponentObject(driver);
  }

  public LightboxComponentObject clickThumbnailVideoLightbox() {
    wait.forElementClickable(videoThumbnail);
    videoThumbnail.click();
    PageObjectLogging.log("clickThumbnailVideoLightbox", "Video thumbnail is clicked", true);
    return new LightboxComponentObject(driver);
  }

  public VideoComponentObject clickThumbnailVideoInline() {
    WebElement thumbnail = getThumbnailVideoInline();
    wait.forElementClickable(thumbnail);
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
    return new VisualEditorPageObject();
  }

  public VisualEditModePageObject openCKModeWithRedLinks(int linkNumber) {
    WebElement redLinkToClick = redLinks.get(linkNumber);
    CreateArticleModalComponentObject articleModal = clickRedLink(redLinkToClick);
    articleModal.createPageWithBlankLayout("");
    return new VisualEditModePageObject();
  }

  private VECreateArticleModalComponentObject clickVERedLink(WebElement redLink) {
    wait.forElementClickable(redLink);
    jsActions.click(redLink);
    return new VECreateArticleModalComponentObject(driver);
  }

  private CreateArticleModalComponentObject clickRedLink(WebElement redLink) {
    wait.forElementClickable(redLink);
    jsActions.click(redLink);
    return new CreateArticleModalComponentObject(driver);
  }

  public SourceEditModePageObject openSrcModeWithRedLinks(int linkNumber) {
    WebElement redLinkToClick = redLinks.get(linkNumber);
    CreateArticleModalComponentObject articleModal = clickRedLink(redLinkToClick);
    articleModal.createPageWithBlankLayout("");
    return new SourceEditModePageObject();
  }

  public ArticlePageObject clickCommentButton(){
    commentButton.click();

    return this;
  }

  public void verifyMainEditEditor(Editor expectedEditor) {
    switch (expectedEditor) {
      case VE:
        VisualEditorPageObject ve = openVEModeWithMainEditButton();
        ve.verifyVEToolBarPresent();
        ve.verifyEditorSurfacePresent();
        break;
      case CK:
        VisualEditModePageObject ck = openCKModeWithMainEditButton();
        Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
        ck.clickPublishButton();
        break;
      case SRC:
        SourceEditModePageObject src = openSrcModeWithMainEditButton();
        src.verifySourceOnlyMode();
        src.clickPublishButton();
        break;
    }
  }

  public void verifyCreateAPageEditor(Editor expectedEditor) {
    switch (expectedEditor) {
      case VE:
        VisualEditorPageObject ve = openVEModeWithMainEditButton();
        ve.verifyVEToolBarPresent();
        ve.verifyEditorSurfacePresent();
        break;
      case CK:
        VisualEditModePageObject ck = openCKModeWithMainEditButtonDropdown();
        Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
        ck.clickPublishButton();
        break;
      case SRC:
        SourceEditModePageObject src = openSrcModeWithMainEditButtonDropdown();
        src.verifySourceOnlyMode();
        src.clickPublishButton();
        break;
    }
  }

  public void verifySectionEditEditor(Editor expectedEditor) {
    switch (expectedEditor) {
      case VE:
        VisualEditorPageObject ve = openVEModeWithSectionEditButton(0);
        ve.verifyVEToolBarPresent();
        ve.verifyEditorSurfacePresent();
        break;
      case CK:
        VisualEditModePageObject ck = openCKModeWithSectionEditButton(0);
        Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
        ck.clickPublishButton();
        break;
      case SRC:
        SourceEditModePageObject src = openSrcModeWithSectionEditButton(0);
        src.verifySourceOnlyMode();
        src.clickPublishButton();
        break;
    }
  }

  public void verifyRedLinkEditor(Editor expectedEditor) {
    switch (expectedEditor) {
      case VE:
        VisualEditorPageObject ve = openVEModeWithRedLinks(0);
        ve.verifyVEToolBarPresent();
        ve.verifyEditorSurfacePresent();
        break;
      case CK:
        VisualEditModePageObject ck = openCKModeWithRedLinks(0);
        Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
        ck.clickPublishButton();
        break;
      case SRC:
        SourceEditModePageObject src = openSrcModeWithRedLinks(0);
        src.verifySourceOnlyMode();
        src.clickPublishButton();
        break;
    }
  }

  public void verifyURLActionEditEditor(Editor expectedEditor, String articleName, String wikiURL) {
    switch (expectedEditor) {
      case CK:
        VisualEditModePageObject ck = navigateToArticleEditPage(wikiURL, articleName);
        Assertion.assertTrue(ck.isContentLoaded(), "Content is not loaded");
        ck.clickPublishButton();
        break;
      case SRC:
        SourceEditModePageObject src = navigateToArticleEditPageSrc(wikiURL, articleName);
        src.verifySourceOnlyMode();
        src.clickPublishButton();
        break;
      default:
        throw new NoSuchElementException(
            "Invalid expected editor chosen: " + expectedEditor.name());
    }
  }

  public void verifyURLVEActionEditEditor(Editor expectedEditor, String wikiURL) {
    switch (expectedEditor) {
      case VE:
        VisualEditorPageObject ve = openNewArticleEditModeVisual(wikiURL);
        ve.verifyVEToolBarPresent();
        ve.verifyEditorSurfacePresent();
        break;
      default:
        throw new NoSuchElementException(
            "Invalid expected editor chosen: " + expectedEditor.name());
    }
  }
}
