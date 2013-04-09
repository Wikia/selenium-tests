package com.wikia.webdriver.PageObjectsFactory.PageObject.SignUp;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Core.MailFunctions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.BlogPageObject;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserProfilePageObject extends WikiBasePageObject{

	private final String editUserIdenSelector = "#userIdentityBoxEdit a";
	private final String editUserIdenSpan = "#userIdentityBoxEdit";
	private final String editUserIdenModalSelector = "#UPPLightboxWrapper";
	private final String userDetails = ".details > li";
	private final String detailsSectionSelector = ".details";

	@FindBy(css="header#WikiaHeader a.ajaxLogin")
	private WebElement logInLink;
	@FindBy(css="li[data-id='blog'] a")
	private WebElement blogTab;
	@FindBy(css="a[data-id='createblogpost']")
	private WebElement createBlogPostButton;
	@FindBy (css=editUserIdenSelector)
	private WebElement editUserIdentityLink;
	@FindBy (css=editUserIdenModalSelector)
	private WebElement editUserIdentityModal;
	@FindBy (css="#UPPLightboxWrapper input[name='name']")
	private WebElement editUserNameInput;
	@FindBy (css="#UPPLightboxWrapper input[name='location']")
	private WebElement editLocationInput;
	@FindBy (css="#UPPLightboxWrapper input[name='occupation']")
	private WebElement editOccupationInput;
	@FindBy (css="#UPPLightboxWrapper input[name='gender']")
	private WebElement editGenderInput;
	@FindBy (css="#UPPLightboxWrapper input[name='website']")
	private WebElement editWebsiteInput;
	@FindBy (css="#UPPLightboxWrapper .save")
	private WebElement submitUserIdentityModalSubmit;
	@FindBy (css=".masthead-info > hgroup > h2")
	private WebElement userIdentityAkaHeader;
	@FindBy (css="h1[itemprop='name']")
	private WebElement userIdentityHeader;

	public UserProfilePageObject(WebDriver driver) {
		super(driver, Global.DOMAIN);
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author Karol Kujawiak
	 */
	private void verifyLogInInvisiblity() {
	    wait.until(ExpectedConditions.stalenessOf(logInLink));
	    PageObjectLogging.log(
		"verifyLogInInvisiblity ", "Log in is not visible", true
	    );
	}

	/**
	 * @author Karol Kujawiak
	 */
	private void verifyRegisterInvisiblity() {
	    try {
		wait.until(
		    ExpectedConditions.invisibilityOfElementLocated(
			By.cssSelector("header#WikiaHeader a.ajaxRegister")
		    )
		);
	    } catch (NoSuchElementException e) {
		PageObjectLogging.log(
		    "verifyLogInInvisiblity ", "Register in is not visible", true
		);
	    }
	}

	/**
	 * @author Karol Kujawiak
	 */
	public void verifyWelcomeEmail(String userName, String mailUserName, String mailPassword) {
	    PageObjectLogging.log("verifyWelcomeEmail ", "start of email verification", true);
	    String[] mailContent = MailFunctions.getWelcomeMailContent(MailFunctions.getFirstMailContent(mailUserName, mailPassword));
//	    Assertion.assertEquals("We're happy to welcome you to Wikia and Wikia! Here are some things you can= do to get started.", mailContent[2]);
	    Assertion.assertEquals("Edit your profile.", mailContent[4]);
//	    Assertion.assertEquals("Add a profile photo and a few quick facts about yourself on your Wikia prof=ile.", mailContent[6]);
//	    Assertion.assertEquals("Go to http://www.wikia.com/User:"+userName, mailContent[8].replace("=", ""));
	    Assertion.assertEquals("Learn the basics.", mailContent[10]);
	    Assertion.assertEquals("Get a quick tutorial on the basics of Wikia: how to edit a page, your user =profile, change your preferences, and more.", mailContent[12]);
	    Assertion.assertEquals("Check it out (http://community.wikia.com/wiki/Help:Wikia_Basics)", mailContent[14]);
	    Assertion.assertEquals("Explore more wikis.", mailContent[16]);
	    Assertion.assertEquals("There are thousands of wikis on Wikia, find more wikis that interest you by= heading to one of our hubs: Video Games (http://www.wikia.com/Video_Games)=, Entertainment (http://www.wikia.com/Entertainment), or Lifestyle (http://=www.wikia.com/Lifestyle).", mailContent[18]);
	    Assertion.assertEquals("Go to http://www.wikia.com", mailContent[20]);
	    Assertion.assertEquals("Want more information? Find advice, answers, and the Wikia community at Com=munity Central (http://www.community.wikia.com). Happy editing!", mailContent[22]);
	    Assertion.assertEquals("The Wikia Team", mailContent[24]);
	    PageObjectLogging.log("verifyWelcomeEmail ", "end of email verification", true);
	}

	/**
	 * @author Michal Nowierski
	 */
	public BlogPageObject clickOnBlogTab() {
		waitForElementByElement(blogTab);
		waitForElementClickableByElement(blogTab);
		clickAndWait(blogTab);
		PageObjectLogging.log(
		    "clickOnBlogTab", "Click on blog tab", true
		);
		return new BlogPageObject(
		    driver, this.Domain, this.articlename
		);
	}

	public void openUserIdentityModal() {
	    //TODO wait for 2.32.0 version of webdriver and modify this method
	    //Actions are poorly supported in FF19 with webdriver 2.31.0 and 2.30.0
	    showElementHackyWay(editUserIdenSpan);
	    waitForElementVisibleByElement(editUserIdentityLink);
	    click(editUserIdentityLink);
	    waitForElementVisibleByElement(editUserIdentityModal);
	    PageObjectLogging.log(
		"modalOpened", "Edit user identity modal opened", true
	    );
	}

	private void editUserName(String userName) {
	    waitForElementByElement(editUserNameInput);
	    editUserNameInput.clear();
	    editUserNameInput.sendKeys(userName);
	    PageObjectLogging.log(
		"userNameInputFilled", "User name input is filled", true
	    );
	}

	private void editLocation(String location) {
	    waitForElementByElement(editLocationInput);
	    editLocationInput.clear();
	    editLocationInput.sendKeys(location);
	    PageObjectLogging.log(
		"locationInputFilled", "Location input is filled", true
	    );
	}

	private void editOccupation(String occupation) {
	    waitForElementByElement(editOccupationInput);
	    editOccupationInput.clear();
	    editOccupationInput.sendKeys(occupation);
	    PageObjectLogging.log(
		"occupationInputFilled", "Occupation input is filled", true
	    );
	}

	private void editGender(String gender) {
	    waitForElementByElement(editGenderInput);
	    editGenderInput.clear();
	    editGenderInput.sendKeys(gender);
	    PageObjectLogging.log(
		"genderInputFilled", "Gender input is filled", true
	    );
	}

	public void populateBlockedContent(String blockedContent) {
	    editLocation(blockedContent);
	    editOccupation(blockedContent);
	    editGender(blockedContent);
	}

	public void submitEdition() {
	    clickAndWait(submitUserIdentityModalSubmit);
	    PageObjectLogging.log(
		"modalNotPresent", "Edition is saved and modal is closed", true
	    );
	}

	public void verifyContentBlocked(String blockedContent) {

	    List <WebElement> details = driver.findElements(By.cssSelector(userDetails));

	    if (details.isEmpty()) {
		PageObjectLogging.log(
		    "blockedContentNotPresent",
		    "Blocked Content was not saved in input field",
		    true
		);
		return;
	    }

	    for (WebElement detail : details) {
		if (detail.getText().contains(blockedContent)) {
		    PageObjectLogging.log(
			"blockedContentPresent",
			"Blocked Content was saved in input field",
			false
		    );
		    return;
		}
	    }
	}
}
