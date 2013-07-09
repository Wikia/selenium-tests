package com.wikia.webdriver.PageObjectsFactory.PageObject.ForumPageObject;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import java.util.ArrayList;
import java.util.List;

import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.EditMode.WikiArticleEditMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class ForumPageObject extends WikiArticlePageObject{

	@FindBy(css=".button.policies-link")
	private WebElement faqButton;
	@FindBy(css=".modalWrapper.policies")
	private WebElement faqLightBox;
	@FindBy(css=".close.wikia-chiclet-button")
	private WebElement closeFaqLightBoxButton;
	@FindBy(css=".button.admin-link")
	private WebElement manageBoardsButton;	
	
	private By forumBoardsList = By.cssSelector("ul.boards h4 a");
	
	public ForumPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private void openFaqLightBox(){
		clickAndWait(faqButton);
		PageObjectLogging.log("openFaqLightBox", "faq lightbox opened", true);
	}

	private void closeFaqLightBox(){
		clickAndWait(closeFaqLightBoxButton);
		PageObjectLogging.log("closeFaqLightBox", "faq lightbox closed", true);
	}

	private void checkFaqLightBoxOpened(){
		waitForElementByElement(faqLightBox);
		PageObjectLogging.log("checkFaqLightBoxOpened", "faq lightbox verified", true);
	}

	public void verifyFaqLightBox(){
		openFaqLightBox();
		checkFaqLightBoxOpened();
		closeFaqLightBox();
	}

	public ForumPageObject openForumMainPage(){
		getUrl(Global.DOMAIN + URLsContent.specialForum);
		waitForElementByElement(faqButton);
		PageObjectLogging.log("openForumPage", "forum page opened", true);
		return new ForumPageObject(driver);
	}

	public ForumManageBoardsPageObject clickManageBoardsButton(){
		clickAndWait(manageBoardsButton);
		PageObjectLogging.log("clickManageBoardsButton", "manage boards button clicked", true);
		return new ForumManageBoardsPageObject(driver);
	}

	public ForumBoardPageObject openForumBoard(int forumBoardNumber) {
		WebElement forumBoardLink = getForumElementsList().get(forumBoardNumber-1);
		waitForElementByElement(forumBoardLink);
		waitForElementClickableByElement(forumBoardLink);
		click(forumBoardLink);
		PageObjectLogging.log("openForumBoard", "click on the forum Board number "+forumBoardNumber, true, driver);
		return new ForumBoardPageObject(driver);
	}

	public List<String> getForumNamesList(){
		List<WebElement> listWebElements = getForumElementsList();
		List<String> forumNames = new ArrayList<String>();
		for (WebElement elem:listWebElements){
			forumNames.add(elem.getText());
		}
		return forumNames;
	}
	
	private List<WebElement> getForumElementsList(){
		List<WebElement> listWebElements = driver.findElements(forumBoardsList);		
		return listWebElements;
	}

    public WikiArticleEditMode createNewTemplate( String templateName, String templateContent ) {
        WikiArticleEditMode edit = super.createNewTemplate( templateName );

        edit.typeInTemplateContent( templateContent );
        edit.clickOnPublish();
        this.waitForElementByCss("#WikiaArticle");

        PageObjectLogging.log("createNewTemplate", "new template created", true);

        return edit;
    }
	
}
