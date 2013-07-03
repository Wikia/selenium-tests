package com.wikia.webdriver.PageObjectsFactory.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Hubs.EntertainmentHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Hubs.LifestyleHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Hubs.VideoGamesHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Search.CrossWikiSearch.CrossWikiSearchPageObject;

public class HomePageObject extends BasePageObject{

	@FindBy(css="header.wikiahomepage-header a.button") 
	private WebElement startWikiButton;
	@FindBy(css="section.grid-2.videogames a img") 
	private WebElement OpenVideoGamesHub;
	@FindBy(css="section.grid-2.entertainment a img") 
	private WebElement OpenEntertainmentHub;
	@FindBy(css="section.grid-2.lifestyle a img") 
	private WebElement OpenLifestyleHub;
	@FindBy(css="a.ajaxLogin")
	private WebElement LoginOverlay;
	@FindBy(css="div#UserLoginDropdown input[name='username']")
	private WebElement UserNameField;
	@FindBy(css="div#UserLoginDropdown a.forgot-password")
	private WebElement ForgotYourPassword;
    @FindBy(css="#WikiaSearch button.wikia-button")
    private WebElement searchButton;
    @FindBy(css="#WikiaSearch input[name='search']")
    private WebElement searchInput;
//	@FindBy(css=".wikia-mosaic-slider-panorama")
//	private WebElement hubsHeroCarousel;
	
	private By hubsHeroCarousel = By.cssSelector(".wikia-mosaic-slider-panorama");
	
	public HomePageObject(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void openHomePage()
	{
		try{
			getUrl(Global.LIVE_DOMAIN);
		}
		catch (TimeoutException e)
		{
			PageObjectLogging.log("openHomePage", "timeouted when opening homepage", true);
		}
		try{
			driver.getCurrentUrl();
		}
		catch (TimeoutException e)
		{
			PageObjectLogging.log("openHomePage", "timeouted when opening homepage", true);
		}
	}
	
	public void triggerLoginOverlay()
	{
		clickAndWait(LoginOverlay);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='username']")));
	}
	
	public void typeInUserName(String userName)
	{	
		waitForElementByElement(UserNameField);
		UserNameField.sendKeys(userName);
	}
	
	public void forgotYourPasswordClick()
	{
		waitForElementByElement(ForgotYourPassword);
		clickAndWait(ForgotYourPassword);
		waitForElementByCss("div#UserLoginDropdown div.error-msg");
	}
	
	public CreateNewWikiPageObjectStep1 startAWiki()
	{
		clickAndWait(startWikiButton);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name='label-wiki-form']")));
		if (Global.LIVE_DOMAIN.contains("preview"))
		{
			String currentUrl = driver.getCurrentUrl();
			String desiredUrl = currentUrl.replace("www.wikia.com", "preview.www.wikia.com");
			getUrl(desiredUrl);
		}
		return new CreateNewWikiPageObjectStep1(driver);
	}
	
	public HubBasePageObject OpenHub(String Hub){
		if (Hub.equals("VideoGamesHub")) {
			PageObjectLogging.log("open hub", "before hub page opened", true, driver);
			getUrl(URLsContent.VideoGamesHub);
			WebElement hubsHero = driver.findElement(hubsHeroCarousel);
			PageObjectLogging.log("open hub", "after hub page opened", true, driver);
			waitForElementByElement(hubsHero);
			if (Global.LIVE_DOMAIN.contains("preview"))
			{
				String currentUrl = driver.getCurrentUrl();
				String temp = currentUrl.replace("http://www.wikia.com", "http://preview.www.wikia.com");
				getUrl(temp);
//				hubsHero = driver.findElement(hubsHeroCarousel);
				waitForElementByElement(driver.findElement(hubsHeroCarousel));
			}
			PageObjectLogging.log("OpenHub", "Open "+Hub, true, driver);
			return new VideoGamesHubPageObject(driver);
		}
		if (Hub.equals("EntertainmentHub")) {
			PageObjectLogging.log("open hub", "before hub page opened", true, driver);
			getUrl(URLsContent.EntertainmentHub);
			WebElement hubsHero = driver.findElement(hubsHeroCarousel);
			PageObjectLogging.log("open hub", "after hub page opened", true, driver);
			waitForElementByElement(hubsHero);
			if (Global.LIVE_DOMAIN.contains("preview"))
			{
				String currentUrl = driver.getCurrentUrl();
				String temp = currentUrl.replace("http://www.wikia.com", "http://preview.www.wikia.com");
				getUrl(temp);
//				hubsHero = driver.findElement(hubsHeroCarousel);
				waitForElementByElement(driver.findElement(hubsHeroCarousel));
			}
			PageObjectLogging.log("OpenHub", "Open "+Hub, true, driver);
			return new EntertainmentHubPageObject(driver);	
		}
		if (Hub.equals("LifestyleHub")) {
			PageObjectLogging.log("open hub", "before hub page opened", true, driver);
			getUrl(URLsContent.LifestyleHub);
			WebElement hubsHero = driver.findElement(hubsHeroCarousel);
			PageObjectLogging.log("open hub", "after hub page opened", true, driver);
			waitForElementByElement(hubsHero);
			if (Global.LIVE_DOMAIN.contains("preview"))
			{
				String currentUrl = driver.getCurrentUrl();
				String temp = currentUrl.replace("http://www.wikia.com", "http://preview.www.wikia.com");
				getUrl(temp);
//				hubsHero = driver.findElement(hubsHeroCarousel);
				waitForElementByElement(driver.findElement(hubsHeroCarousel));
			}
			PageObjectLogging.log("OpenHub", "Open "+Hub, true, driver);
			return new LifestyleHubPageObject(driver);	
		}
		else {
			PageObjectLogging.log("OpenHub", "Incorrect parameter. Hub name: '"+Hub+"' is wrong and won't open any hub", false, driver);
			return null;
		}
	}

    public CrossWikiSearchPageObject searchFor(String queryString) {
        searchInput.sendKeys(queryString);
        clickAndWait(searchButton);
        PageObjectLogging.log("searchFor", "Enter search string \"" + queryString + "\" and click ok.", true, driver);
        return new CrossWikiSearchPageObject(driver);
    }
}
