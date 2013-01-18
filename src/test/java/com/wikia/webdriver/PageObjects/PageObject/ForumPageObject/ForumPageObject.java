package com.wikia.webdriver.PageObjects.PageObject.ForumPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;

public class ForumPageObject extends BasePageObject{

	@FindBy(css=".button.policies-link")
	private WebElement faqButton;
	@FindBy(css=".modalWrapper.policies")
	private WebElement faqLightBox;
	@FindBy(css=".close.wikia-chiclet-button")
	private WebElement closeFaqLightBoxButton;
	@FindBy(css=".button.admin-link")
	private WebElement manageBoardsButton;	
	
	
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
		getUrl(Global.DOMAIN+"Special:Forum");
		waitForElementByElement(faqButton);
		PageObjectLogging.log("openForumPage", "forum page opened", true);
		return new ForumPageObject(driver);
	}
	
	
	public ForumManageBoardsPageObject clickManageBoardsButton(){
		clickAndWait(manageBoardsButton);
		PageObjectLogging.log("clickManageBoardsButton", "manage boards button clicked", true);
		return new ForumManageBoardsPageObject(driver);
	}
	
}
