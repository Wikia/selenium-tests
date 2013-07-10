package com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Multiwikifinder;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

/**
 *
 * @author łukasz
 */
public class SpecialMultiwikifinderPageObject extends WikiBasePageObject{

    @FindBy(css="#mw-content-text input[type=submit]")
    private WebElement findButton;
    @FindBy(css="input[name=target]")
    private WebElement enterPagenameField;
    @FindBy(css=".AdminDashboardGeneralHeader.AdminDashboardArticleHeader>h1")
    private WebElement multiwikifinderPageHeader;
    @FindBy(css=".mw-spcontent>p")
    private WebElement noWikisMessage;
    @FindBy(css="a[href*=\"/wiki/Special:Multiwikifinder?limit=20&offset=0&target=A\"]")
    private WebElement maxAmountOfLinksOnPage20;
    @FindBy(css="a[href*=\"/wiki/Special:Multiwikifinder?limit=50&offset=0&target=A\"]")
    private WebElement maxAmountOfLinksOnPage50;
    @FindBy(css="a[href*=\"/wiki/Special:Multiwikifinder?limit=100&offset=0&target=A\"]")
    private WebElement maxAmountOfLinksOnPage100;
    @FindBy(css="a[href*=\"/wiki/Special:Multiwikifinder?limit=250&offset=0&target=A\"]")
    private WebElement maxAmountOfLinksOnPage250;
    @FindBy(css="a[href*=\"/wiki/Special:Multiwikifinder?limit=500&offset=0&target=A\"]")
    private WebElement maxAmountOfLinksOnPage500;
    @FindBy(css = "a[href*=\"http://lyrics.wikia.com/A\"]")
    private WebElement firstLink;
    @FindBy(css = ".mw-nextlink")
    private WebElement nextResultsButton;
    @FindBy(css = ".mw-prevlink")
    private WebElement previousResultsButton;
    @FindBys(@FindBy(css=".special>li>a"))
    private List <WebElement> listOfLinks;

    public SpecialMultiwikifinderPageObject(WebDriver driver) {
	super(driver);
    }

    public SpecialMultiwikifinderPageObject openSpecialMultiwikifinderPage(){
	getUrl(Global.DOMAIN+"wiki/Special:Multiwikifinder");
	waitForElementByElement(multiwikifinderPageHeader);
	PageObjectLogging.log(
	    "openSpecialMultiwikifinderPage",
	    "Special Multiwikifinder page was opened",
	    true, driver
	);
	return new SpecialMultiwikifinderPageObject(driver);
    }

    public void enterPagename(String pagename){
	waitForElementByElement(enterPagenameField);
	enterPagenameField.sendKeys(pagename);
    }

    public void clickFindButton(){
	waitForElementByElement(findButton);
	clickAndWait(findButton);
    }

    public void clickNextResultsButton(){
	waitForElementByElement(nextResultsButton);
	clickAndWait(nextResultsButton);
    }

    public void clickPreviousResultsButton(){
	waitForElementByElement(previousResultsButton);
	clickAndWait(previousResultsButton);
    }

    public void findPagename(String pagename){
	enterPagename(pagename);
	clickFindButton();
    }

    public void verifyFoundNotExistingPagename(){
	waitForElementByElement(noWikisMessage);
	PageObjectLogging.log(
	    "verifyNoPagenameFounded",
	    "Not existing pagename is not founded",
	    true, driver
	);
    }

    public void verifyMaxAmountOfLinksOnPage(int maxAmountOfLinks){
	waitForElementByElement(firstLink);
	int amountOfLinks = listOfLinks.size();
	if(amountOfLinks <= maxAmountOfLinks){
	    PageObjectLogging.log(
		"verifyMaxAmountOfLinksOnPage",
		"Max amount of links is not exceeded",
		true, driver
	    );
	}
	else{
	    PageObjectLogging.log(
		"verifyMaxAmountOfLinksOnPage",
		"Max amount of links is exceeded",
		false, driver
	    );
	    assert amountOfLinks <= maxAmountOfLinks;
	}
    }

    public void clickAndVerifyMaxAmountOfLinksOnPage(){
	clickAndWait(maxAmountOfLinksOnPage20);
	verifyMaxAmountOfLinksOnPage(20);
	clickAndWait(maxAmountOfLinksOnPage50);
	verifyMaxAmountOfLinksOnPage(50);
	clickAndWait(maxAmountOfLinksOnPage100);
	verifyMaxAmountOfLinksOnPage(100);
	clickAndWait(maxAmountOfLinksOnPage250);
	verifyMaxAmountOfLinksOnPage(250);
	clickAndWait(maxAmountOfLinksOnPage500);
	verifyMaxAmountOfLinksOnPage(500);
    }

    public void verifyPagination(){
	String firstLinkOnFirstPage = listOfLinks.get(0).getAttribute("href");
	String lastLinkOnFirstPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
	clickNextResultsButton();
	String firstLinkOnSecondPage = listOfLinks.get(0).getAttribute("href");
	String lastLinkOnSecondPage = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
	if(!(firstLinkOnFirstPage.equals(firstLinkOnSecondPage)) &
	    (!(lastLinkOnFirstPage.equals(lastLinkOnSecondPage)))){
		PageObjectLogging.log(
		    "verifyPagination",
		    "Links on next pages are different",
		    true, driver
		);
	    }
	else{
		PageObjectLogging.log(
		    "verifyPagination",
		    "Links on next pages are the same",
		    false, driver
		);
		assert !(firstLinkOnFirstPage.equals(firstLinkOnSecondPage)) &
		    (!(lastLinkOnFirstPage.equals(lastLinkOnSecondPage)));
	}
	clickPreviousResultsButton();
	String firstLinkAfterBack = listOfLinks.get(0).getAttribute("href");
	String lastLinkAfterBack = listOfLinks.get(listOfLinks.size()-1).getAttribute("href");
	if((firstLinkOnFirstPage.equals(firstLinkAfterBack)) &
	    (lastLinkOnFirstPage.equals(lastLinkAfterBack))){
		PageObjectLogging.log(
		    "verifyPagination",
		    "Links on the same pages are the same",
		    true, driver
		    );
		}
	else{
		PageObjectLogging.log(
		    "verifyPagination",
		    "Links on the same pages are different",
		    false, driver
		);
		assert (firstLinkOnFirstPage.equals(firstLinkAfterBack)) &
		    (lastLinkOnFirstPage.equals(lastLinkAfterBack));
	}
    }

    public void verifyAllLinksHavePagenameInPath(String popularPagename){
	for(int i=0; i<listOfLinks.size(); i++){
	    if(listOfLinks.get(i).getAttribute("href").endsWith(popularPagename)){
		PageObjectLogging.log(
		    "verifyThisLinkHavePagenameInPath",
		    "This link has searched pagename in path",
		    true
		);
	    }
	    else{
		PageObjectLogging.log(
		    "verifyAllLinksHavePagenameInPath",
		    "Not all links have searched pagename in path",
		    false
		);
		assert listOfLinks.get(i).getAttribute("href").endsWith(popularPagename);
	    }
	}
    }
}