package com.wikia.webdriver.PageObjectsFactory.PageObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateTopListPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialMultipleUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialNewFilesPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialUploadPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialVideosPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Top_10_list;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiCategoryPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import org.openqa.selenium.NoSuchElementException;

public class WikiBasePageObject extends BasePageObject {

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

	@FindBy(css = "input[name='search'][placeholder='Search photos on this wiki']")
	private WebElement searchFieldImageInLightBox;

	@FindBy(css = "img.sprite.search")
	private WebElement searchButtonImageInLightBox;
	
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
	
	@FindBy (css = "#WikiaPageHeader h1")
	private WebElement wikiFirstHeader;

	@FindBy (css = "#WikiaArticle a[href*='Special:UserLogin']")
	private WebElement specialUserLoginLink;

	@FindBy(css = ".UserLoginModal input[name='username']")
	protected WebElement modalUserNameInput;

	@FindBy(css = ".UserLoginModal input[name='password']")
	protected WebElement modalPasswordInput;

	@FindBy(css = ".UserLoginModal input[type='submit']")
	protected WebElement modalLoginSubmit;

        @FindBy(css = ".wikia-menu-button.contribute.secondary.combined > .drop")
        protected WebElement contributeButton;

        @FindBy(css = ".WikiaMenuElement a[data-id='createpage']")
        protected WebElement contributeAddPage;

        @FindBy(css = "#CreatePageDialog")
        protected WebElement addPageModal;

    //Selectors
    protected String loginModalSelector = ".UserLoginModal";
	
	private String pageName;

	public WikiBasePageObject(WebDriver driver, String Domain) {
		super(driver);
		this.Domain = Global.DOMAIN;
		PageFactory.initElements(driver, this);
	}

	public String getWikiName() {
		return Domain;
	}
	
	/**
	 * @author Michal Nowierski
	 * */
	public SpecialVideosPageObject openSpecialVideoPage(){
		getUrl(Domain+"wiki/Special:Videos");
		return new SpecialVideosPageObject(driver, Domain);
	}

	public SpecialNewFilesPageObject openSpecialNewFiles() {
		getUrl(Domain + "wiki/Special:NewFiles");
		return new SpecialNewFilesPageObject(driver);
	}

	public SpecialUploadPageObject openSpecialUpload() {
		getUrl(Domain + "wiki/Special:Upload");
		return new SpecialUploadPageObject(driver, Domain);
	}

	public SpecialMultipleUploadPageObject openSpecialMultipleUpload() {
		getUrl(Domain + "wiki/Special:MultipleUpload");
		return new SpecialMultipleUploadPageObject(driver, Domain);
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
	}

        public void renameArticleAndVerify(String articleName, String articleNewName) {
            renameArticle(articleName, articleNewName);
            verifyArticleRenamed(articleName, articleNewName);
        }

        public void verifyArticleRenamed(String articleName, String articleNewName) {
            waitForElementByXPath(
                "//b[contains(text(), '\"" + articleName
                + "\" has been renamed \"" + articleNewName + "\"')]"
            );
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
		getUrl(Global.DOMAIN + "index.php?title=" + pageName
				+ "&action=edit&useFormat=" + layoutNumber);
		String pageNameEnc = pageName.replace("_", " ");
		waitForElementByElement(driver.findElement(By.cssSelector("a[title='"
				+ pageNameEnc + "']")));
		return new WikiArticleEditMode(driver, Domain, pageName);
	}
	
	public WikiArticleEditMode createNewDefaultArticle(){
		this.pageName = PageContent.articleNamePrefix+getTimeStamp();
		return createNewArticle(this.pageName, 1);
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

        public void openSpecialPage(String specialPage) {
            getUrl(Domain + specialPage);
        }

        public void verifyLoginReguiredMessage() {
            waitForTextToBePresentInElementByElement(
                wikiFirstHeader, PageContent.loginRequired
            );
            PageObjectLogging.log(
                "LoginRequiredMessage",
                "Login required message in first header present",
                true, driver
            );
        }

        public void clickLoginOnSpecialPage() {
            waitForElementByElement(specialUserLoginLink);
            PageObjectLogging.log(
                "LoginLinkPresent",
                "Link to login special page present",
                true, driver
            );
            clickAndWait(specialUserLoginLink);
            PageObjectLogging.log(
                "LoginLinkClicked",
                "Link to login special page clicked",
                true, driver
            );
        }

        public void verifyNotLoggedInMessage() {
            waitForTextToBePresentInElementByElement(
                wikiFirstHeader, PageContent.notLoggedInMessage
            );
            PageObjectLogging.log(
                "NotLoggedInMessage",
                "Not logged in message present",
                true, driver
            );
        }

        public void clickContributeNewPage() {
            clickContributeButton();
            waitForElementVisibleByElement(contributeAddPage);
            clickAndWait(contributeAddPage);
        }

        public void logInViaModal(String userName, String password) {
            waitForElementByElement(modalUserNameInput);
            modalUserNameInput.sendKeys(userName);
            waitForElementByElement(modalPasswordInput);
            modalPasswordInput.sendKeys(password);
            PageObjectLogging.log(
                "FillLoginForm",
                "Login form in modal is filled",
                true, driver
            );

            clickAndWait(modalLoginSubmit);
            PageObjectLogging.log(
                "LoginFormSubmitted",
                "Login form is submitted",
                true
            );

            waitForElementNotVisibleByElement(logInModal);
            PageObjectLogging.log(
                "LoginModalDissapears",
                "Login modal is no longer visible",
                true
            );
        }

        public String receiveMailWithNewPassowrd() {
            MailFunctions.deleteAllMails(Properties.email, Properties.emailPassword);
            String newPassword = MailFunctions.getPasswordFromMailContent((
                MailFunctions.getFirstMailContent(
                    Properties.email, Properties.emailPassword)
                )
            );
            PageObjectLogging.log(
                "NewPasswordRecived",
                "New password recived from mail",
                true
            );

            return newPassword;
        }

    /**
     * Method checks if current wiki page is main page of this wiki
     *
     * @return Boolean
     */
    protected Boolean checkIfMainPage() {
        WebElement body = driver.findElement(By.cssSelector("body"));
	return (body.getAttribute("class").contains("mainpage"));
    }
}
