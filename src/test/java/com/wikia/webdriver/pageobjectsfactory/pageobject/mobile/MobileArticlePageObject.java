package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mobile.TableOfContentsComponentObject;

public class MobileArticlePageObject extends MobileBasePageObject{

	public MobileArticlePageObject(WebDriver driver) {
		super(driver);
	}

	private String wikiTOC = "wiki/TOC#";
	private String modal = "#Modal";
	private String mainPageTitle = "Mobileregressiontesting Wiki";
	private String curtainNotOpened = ".ads";
	private String backCssSelector = ".goBck";

	@FindBy(css="#wkArtComHeader")
	private WebElement commentsSectionHeader;
	@FindBy(css=".commSbt.wkBtn.main")
	private WebElement postCommentButton;
	@FindBy(css=".commText[name='wpArticleComment']")
	private WebElement commentInputArea;
	@FindBy(css=".cmnRpl")
	private WebElement replyCommentButton;
	@FindBy(css="[placeholder='Post a reply']")
	private WebElement replyInputArea;
	@FindBy(css="#commMore")
	private WebElement loadMoreCommentsButton;
	@FindBy(css="#commPrev")
	private WebElement loadPreviousCommentsButton;
	@FindBy(css=".lazy.media.load.loaded")
	private WebElement modalWrapper;
	@FindBy(css=".swiperPage.current")
	private WebElement currentImageModal;
	@FindBy(css="#wkTOC.active")
	private WebElement tocSideMenu;
	@FindBy(css="#wkTOCHandle")
	private WebElement openTOCbutton;
	@FindBy(css="#wkMainCntHdr>h1")
	private WebElement wikiHeader;
	@FindBys(@FindBy(css=".txt > p"))
	private List<WebElement> listOfComments;
	@FindBy(css="section.artSec.open")
	private WebElement sectionOpened;
	@FindBy(css="section.artSec.open")
	private List<WebElement> sectionOpenedList;
	@FindBy(css="div#mw-content-text h2.collSec.open")
	private WebElement sectionVisibilityElement;
	@FindBy(css="#wkArtCnt")
	private WebElement numberOfComments;
	@FindBy(css="#wkMdlImages > .current > img")
	private WebElement openedImage;
	@FindBy(css = "#wkCurtain")
	private WebElement curtain;
	@FindBy(css = "#wkNavBack")
	private WebElement menuBackButton;
	@FindBys(@FindBy(css=".thumb > img"))
	private List<WebElement> listOfMediaElements;
	@FindBys(@FindBy(css="#lvl1>li"))
	private List<WebElement> menuTabs;
	@FindBys(@FindBy(css=".lvl2.cur>li"))
	private List<WebElement> level2;
	@FindBy(css=".lvl3")
	private WebElement level3;
	@FindBy(css=".lvl2.cur.anim-done")
	private WebElement level2Visible;
	@FindBy(css=".lvl3.cur.anim-done")
	private WebElement level3Visible;
	@FindBy(css="#wkCurtain:not(.active)")
	private WebElement curtainClosed;
	@FindBy(css=".editsection > a")
	private List<WebElement> editSectionList;

	public void showCommentsSection() {
		waitForElementByElement(commentsSectionHeader);
		scrollToElement(commentsSectionHeader);
		waitForElementByElement(postCommentButton);
		waitForElementByElement(commentInputArea);
		PageObjectLogging.log("showCommentsSection", "comments sections is visible", true, driver);
	}

	private void verifyAddedCommentOnTop(String comment) {
		Assertion.assertEquals(
				listOfComments.get(0).getAttribute("innerText"), comment, "comment is not added on top"
		);
	}

	private void verifyNumberOfComments(int number) {
		waitForElementByElement(commentsSectionHeader);
		Assertion.assertEquals(Integer.parseInt(commentsSectionHeader.getAttribute("data-count")), number + 1,
			"number of comments was not increased"
		);
	}

	public void addComment(String comment) {
		showCommentsSection();
		int firstNumberOfComments = listOfComments.size();
		commentInputArea.sendKeys(comment);
		postCommentButton.click();
		waitForElementByXPath(
			"//li[@class='comment']/blockquote/div[@class='txt']/p[contains(text(), '"
			+ comment + "')]"
		);
		Assertion.assertTrue(listOfComments.size() == (firstNumberOfComments + 1), "size of list was not increased");
		verifyAddedCommentOnTop(comment);
		PageObjectLogging.log("addComment", "comment " + comment + " added", true);
	}

	public void verifyInputExpand(String comment) {
		showCommentsSection();
		int inputHeight = Integer.parseInt(commentInputArea.getCssValue("height").substring(0, 1));
		commentInputArea.sendKeys(comment);
		int inputExpandedHeight = Integer.parseInt(commentInputArea.getCssValue("height").substring(0, 1));
		Assertion.assertTrue(inputExpandedHeight > inputHeight, "input is not bigger");
	}

	public void verifyNumberOfComments(String comment) {
		showCommentsSection();
		int number = Integer.parseInt(commentsSectionHeader.getAttribute("data-count"));
		commentInputArea.sendKeys(comment);
		postCommentButton.click();
		waitForElementByXPath(
			"//li[@class='comment']/blockquote/div[@class='txt']/p[contains(text(), '"
			+ comment + "')]"
		);
		verifyNumberOfComments(number);
	}

	private void verifyAddedReplyOnCommentPage(String reply) {
		waitForElementByXPath(
				"//div[@id='wkMdlWrp']//ul[@class='sub-comments']//p[contains(text(), '" + reply + "')]"
		);
		PageObjectLogging.log("verifyAddedReply", "reply " + reply + " is visible", true, driver);
	}

	public void addReply(String reply) {
		waitForElementByElement(replyCommentButton);
		String url = driver.getCurrentUrl();
		replyCommentButton.click();
		waitForElementByElement(replyInputArea);
		verifyURL(url);
		replyInputArea.sendKeys(reply);
		replyInputArea.submit();
		verifyURL(url);
		verifyAddedReplyOnCommentPage(reply);
		PageObjectLogging.log("addReply", "reply "+reply+" added", true, driver);
	}

	public void clickLoadMoreButton() {
		waitForElementByElement(loadMoreCommentsButton);
		scrollAndClick(loadMoreCommentsButton);
		waitForElementByElement(loadPreviousCommentsButton);
	}

	public void clickLoadPreviousButton() {
		waitForElementByElement(loadPreviousCommentsButton);
		scrollAndClick(loadPreviousCommentsButton);
		waitForElementByElement(loadMoreCommentsButton);
		waitForElementNotVisibleByElement(loadPreviousCommentsButton);
	}

	public void verifyFirstCommentsNotEquals(String firstComment) {
		Assertion.assertNotEquals(firstComment, listOfComments.get(0).getAttribute("innerText"),
			"comments are the same"
		);
	}

	public void verifyFirstCommentsEquals(String firstComment) {
		Assertion.assertEquals(firstComment, listOfComments.get(0).getAttribute("innerText"),
			"comments are not the same"
		);
	}

	public String getCommentInnerText() {
		 return listOfComments.get(0).getAttribute("innerText");
	}

	public MobileArticlePageObject openSections(String wikiURL) {
		getUrl(wikiURL + URLsContent.articleSections);
		PageObjectLogging.log("openSections", "sections page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public MobileArticlePageObject openTOCPage(String wikiURL) {
		getUrl(wikiURL + wikiTOC);
		PageObjectLogging.log("openTOCPage", "TOC page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public MobileArticlePageObject openTopbarPage(String wikiURL) {
		getUrl(wikiURL + URLsContent.articleTopbar);
		waitForElementByElement(wikiHeader);
		PageObjectLogging.log("openTopbarPage", "Topbar page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public void clickSection(int sectionNumber) {
		WebElement chev = waitForElementByXPath("//div[@id='mw-content-text']/h2["+sectionNumber+"]");
		scrollAndClick(chev);
		PageObjectLogging.log("clickSection", "section " + chev.getText() + " clicked", true, driver);
	}

	public MobileEditModePageObject editSection(int sectionNumber) {
		scrollAndClick(editSectionList.get(sectionNumber));
		return new MobileEditModePageObject(driver);
	}

	public String getSectionText(int sectionNumber) {
		return sectionOpenedList.get(sectionNumber).getText();
	}

	public void verifySectionVisibility() {
		waitForElementByElement(sectionVisibilityElement);
		PageObjectLogging.log("verifySectionVisibility", "section is opened and visible", true);
	}

	public void verifySectionInvisibility() {
		waitForElementNotVisibleByElement(sectionVisibilityElement);
		PageObjectLogging.log("verifySectionInvisibility", "section is not visible", true, driver);
	}

	public void clickHideButton() {
		WebElement hideSectionButton = waitForElementByCss(backCssSelector);
		hideSectionButton.click();
		PageObjectLogging.log("clickHideButton", "hide section button clicked", true, driver);
	}

	public MobileArticlePageObject openModals(String wikiURL) {
		getUrl(wikiURL + URLsContent.articleModal);
		waitForElementByElement(modalWrapper);
		PageObjectLogging.log("openModals", "modals page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public MobileModalComponentObject clickModal() {
		waitForElementByElement(modalWrapper);
		modalWrapper.click();
		waitForElementByElement(currentImageModal);
		PageObjectLogging.log("clickModal", "modal url verified", true, driver);
		return new MobileModalComponentObject(driver);
	}

	public void scrollToImage(int imageNumber) {
		waitForElementByElement(listOfMediaElements.get(imageNumber));
		scrollToElement(listOfMediaElements.get(imageNumber));
	}

	public MobileModalComponentObject clickOpenedImage(int n) {
		waitForElementByElement(listOfMediaElements.get(n));
		scrollAndClick(listOfMediaElements.get(n));
		waitForElementByElement(openedImage);
		openedImage.click();
		PageObjectLogging.log("clickModal", "modal url verified", true, driver);
		return new MobileModalComponentObject(driver);
	}

	public void verifyTocClosed() {
		waitForElementNotVisibleByElement(tocSideMenu);
		PageObjectLogging.log("verifyTocClosed", "toc is closed", true);
	}

	public TableOfContentsComponentObject openToc() {
		waitForElementByElement(openTOCbutton);
		openTOCbutton.click();
		PageObjectLogging.log("openToc", "toc opened", true);
		return new TableOfContentsComponentObject(driver);
	}

	public void verifyPositionsNotEquals(Long positionBeforeClick) {
		Assertion.assertNotEquals(positionBeforeClick, getPosition());
	}

	public void verifySectionHeaderOpened(String desiredId) {
		waitForElementByElement(sectionHeaderOpened);
		String currentId = sectionHeaderOpened.getAttribute("id");
		Assertion.assertEquals(desiredId, currentId, "id's are not equals");
		PageObjectLogging.log("verifySectionHeaderOpened", "header section opened", true);
	}

	public void verifySectionOpened(String desiredId, int level) {
		waitForElementByElement(sectionOpened);
		sectionOpened.findElement(By.cssSelector("h" + level + "#" + desiredId));
		PageObjectLogging.log("verifySectionLevel" + level + "Opened", "section opened", true);
	}

	public void clickOpenedImage() {
		waitForElementByElement(openedImage);
		openedImage.click();
	}

	private void verifyTopbarButton(WebElement element) {
		waitForElementByElement(topbarLoginButton);
		element.click();
		waitForElementNotPresent(curtainNotOpened);
		Assertion.assertEquals("block", curtain.getCssValue("display"), "menu is not opened");
		element.click();
		waitForElementNotVisibleByElement(curtainClosed);
		Assertion.assertEquals("none", curtain.getCssValue("display"), "menu is not closed");
	}

	public void verifyAllTopbarButtons() {
		verifyTopbarButton(topbarLoginButton);
		verifyTopbarButton(topbarMenuButton);
		verifyTopbarButton(topbarSearchButton);
	}

	public void clickWordmark() {
		waitForElementByElement(wikiaTopPageLogo);
		wikiaTopPageLogo.click();
	}

	public void verifyMainPageOpened(String wikiURL) {
		waitForValueToBePresentInElementsAttributeByElement(wikiHeader, "innerText", mainPageTitle);
		Assertion.assertEquals(wikiURL + URLsContent.mobileTestMainPage, getCurrentUrl(), "URLs are not equals");
	}

	public void openMenu() {
		waitForElementByElement(topbarMenuButton);
		topbarMenuButton.click();
	}

	public void verifyMenuPagination() {
		openMenu();
		String firstTab = menuTabs.get(0).getAttribute("innerText");
		for(WebElement elem : menuTabs) {
			if(elem.getAttribute("class").contains("cld")) {
				waitForElementNotVisibleByElement(level2Visible);
				waitForElementClickableByElement(elem);
				elem.click();
				for(WebElement elem2 : level2) {
					if(elem2.getAttribute("class").contains("cld")) {
						waitForElementByElement(level2Visible);
						waitForElementNotVisibleByElement(level3);
						elem2.click();
						waitForElementVisibleByElement(level3Visible);
						waitForElementClickableByElement(menuBackButton);
						menuBackButton.click();
						break;
					}
				}
				waitForElementNotVisibleByElement(level3Visible);
				menuBackButton.click();
				break;
			}
		}
		Assertion.assertEquals(firstTab, menuTabs.get(0).getAttribute("innerText"),
			"firts tabs are not the same"
		);
		PageObjectLogging.log("verifyMenu", "menu was veryfied", true);
	}

	public MobileHistoryPageObject goToCurrentArticleHistoryPage() {
		getUrl(
			urlBuilder.appendQueryStringToURL(
				driver.getCurrentUrl(),
				URLsContent.historyAction
			)
		);
		return new MobileHistoryPageObject(driver);
	}

}
