package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import org.openqa.selenium.support.FindBys;

public class MobileCategoryPageObject extends MobileBasePageObject {

	public MobileCategoryPageObject(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// UI Mapping
	private String categoryPmg = "wiki/Category:PMG";
	@FindBy(css = "#expAll:not(.exp)")
	private WebElement showAllButton;
	@FindBy(css = "#expAll.exp")
	private WebElement hideAllButton;
	@FindBy(css = "h2.collSec")
	private List<WebElement> chevronList;
	@FindBy(css = ".pagMore.visible")
	private WebElement loadMoreButton;
	@FindBy(css = ".pagLess.visible")
	private WebElement loadPreviousButton;
	@FindBys(@FindBy(css = ".wkExhItm"))
	private List<WebElement> categoryExhibition;
	@FindBys(@FindBy(css = ".collSec"))
	private List<WebElement> articleList;
	@FindBys(@FindBy(css = ".artSec.open .wkLst>li>a"))
	private List<WebElement> articleListWithPagination;

	public MobileArticlePageObject openCategory(String wikiURL){
		getUrl(wikiURL + categoryPmg);
		waitForElementByElement(showAllButton);
		PageObjectLogging.log("openCategory", "category page was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public void clickShowAllButton(){
		waitForElementByElement(showAllButton);
		showAllButton.click();
	}

	public void clickHideAllButton (){
		waitForElementByElement(hideAllButton);
		hideAllButton.click();
	}

	public void verifyChevronOpened(){
		for(WebElement elem:chevronList){
			Assertion.assertEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyChevronClosed(){
		for(WebElement elem:chevronList){
			Assertion.assertNotEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyShowAll (){
		waitForElementByElement(showAllButton);
	}

	public void verifyHideAll() {
		waitForElementByElement(hideAllButton);
	}

	public void verifyCategoryExhibition(){
		Assertion.assertTrue(categoryExhibition.size() == 4);
	}

	/*
	 *  this method open list which has more than 25 articles
	 */
	public void openArticlesWithPagination(){
		for(int i=0; i < articleList.size(); i++) {
			articleList.get(i).click();
			if(loadMoreButton.isDisplayed()) {
				PageObjectLogging.log(
					"openArticlesWithPagination",
					"article with pagination was opened",
					true, driver
				);
				break;
			}
			else if(i == articleList.size()-1) {
				PageObjectLogging.log(
					"openArticlesWithPagination",
					"article with pagination was not found and opened",
					false, driver
				);
			}
			else {
				articleList.get(i).click();
			}
		}
	}

	/*
	 *  this method check amount of results on page, more/previous buttons are displaying correctly
	 *  and articles are switching correctly
	 */
	public void verifyPagination(){
		Assertion.assertTrue(articleListWithPagination.size() == 25);
		String firstArticle = articleListWithPagination.get(0).getAttribute("href");
		String lastArticle = articleListWithPagination.get(articleListWithPagination.size()-1).getAttribute("href");
		loadMoreButton.click();
		waitForElementByElement(loadPreviousButton);
		Assertion.assertTrue(articleListWithPagination.size() <= 25);
		String firstArticle2 = articleListWithPagination.get(0).getAttribute("href");
		Assertion.assertNotEquals(firstArticle, firstArticle2);
		loadPreviousButton.click();
		waitForElementByElement(loadMoreButton);
		Assertion.assertTrue(articleListWithPagination.size() == 25);
		String firstArticle3 = articleListWithPagination.get(0).getAttribute("href");
		String lastArticle3 = articleListWithPagination.get(articleListWithPagination.size()-1).getAttribute("href");
		Assertion.assertEquals(firstArticle, firstArticle3);
		Assertion.assertEquals(lastArticle, lastArticle3);
	}

}
