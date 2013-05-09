package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;

import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class SpecialSearchPageObject extends SpecialPageObject{

	@FindBy(css = "#search-v2-input")
	private WebElement searchBox;
	@FindBys(@FindBy(css = "li.result"))
	private List<WebElement> results;
	@FindBy(css = "a[data-event=\"search_click_match\"]")
	private WebElement match;
	@FindBy(css = "#search-v2-button")
	private WebElement searchButton;
	
	public SpecialSearchPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void goToSearchPage(String searchUrl) {
		try{
			getUrl(searchUrl+"wiki/Special:Search");			
		}
		catch (TimeoutException e)
		{
			PageObjectLogging.log("goToSearchPage", "timeouted when opening search page", false);
		}
	}
	
	public SpecialSearchPageObject searchFor( String term ) {
		searchBox.clear();
		searchBox.sendKeys( term );
		PageObjectLogging.log("searchFor", "Typed search term" +term, true, driver);
		clickAndWait(searchButton);
		PageObjectLogging.log("searchFor", "Search button clicked", true, driver);
		return new SpecialSearchPageObject(driver);
	}
	
	public void verifyMatchResultUrl( String url ) {
		String href = match.getAttribute("href");
		if ( href.contains(url) ) {
			PageObjectLogging.log("verifyMatchResultUrl", "match result page matches url "+url+ ": "+href, true, driver);
		} else {
			PageObjectLogging.log("verifyMatchResultUrl", "match result page does not match url "+url+ ": "+href, false, driver);
		}
	}
	
}