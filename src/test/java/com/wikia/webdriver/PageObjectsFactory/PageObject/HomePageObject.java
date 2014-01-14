package com.wikia.webdriver.PageObjectsFactory.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;

public class HomePageObject extends WikiBasePageObject {

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

	public HomePageObject(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public CreateNewWikiPageObjectStep1 startAWiki(String wikiURL)
	{
		startWikiButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("form[name='label-wiki-form']")));
		if (wikiURL.contains("preview"))
		{
			String currentUrl = driver.getCurrentUrl();
			String desiredUrl = currentUrl.replace("www.wikia.com", "preview.www.wikia.com");
			getUrl(desiredUrl);
		}
		return new CreateNewWikiPageObjectStep1(driver);
	}
}
