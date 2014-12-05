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

public class MobileCategoryPageObject extends MobileBasePageObject {

	public MobileCategoryPageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#expAll:not(.exp)")
	private WebElement showAllButton;
	@FindBy(css = "#expAll.exp")
	private WebElement hideAllButton;
	@FindBy(css = "h2.collSec")
	private List<WebElement> chevronList;
	@FindBys(@FindBy(css = ".wkExhItm"))
	private List<WebElement> categoryExhibition;

	String articlesListSelector = "#%articlesFirstLetter% .wkLst li";
	String loadMoreButtonSelector = "#%articlesFirstLetter% .pagMore.visible";
	String loadPeviousButtonSelector = "#%articlesFirstLetter% .pagLess.visible";

	public MobileArticlePageObject openCategory(String wikiURL) {
		getUrl(wikiURL + URLsContent.CATEGORY_PMG);
		PageObjectLogging.log("openCategory", "category page: "+URLsContent.CATEGORY_PMG+", was opened", true, driver);
		return new MobileArticlePageObject(driver);
	}

	public void clickShowAllButton() {
		waitForElementByElement(showAllButton);
		showAllButton.click();
	}

	public void clickHideAllButton () {
		waitForElementByElement(hideAllButton);
		hideAllButton.click();
	}

	public void verifyChevronOpened() {
		for(WebElement elem:chevronList){
			Assertion.assertEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyChevronClosed() {
		for(WebElement elem:chevronList){
			Assertion.assertNotEquals("collSec open", elem.getAttribute("class"));
		}
	}

	public void verifyShowAll() {
		waitForElementByElement(showAllButton);
	}

	public void verifyHideAll() {
		waitForElementByElement(hideAllButton);
	}

	public void verifyCategoryExhibition() {
		waitForElementByElement(categoryExhibition.get(0));
		Assertion.assertTrue(categoryExhibition.size() == 4);
		PageObjectLogging.log("verifyCategoryExhibition", "Category exibition size (should equal 4) verified", true);
	}

	public void verifyArticlesCount(String articlesFirstLetter, int count) {
		List<WebElement> articlesList = getArticleList(articlesFirstLetter);
		Assertion.assertTrue(articlesList.size() == count);
	}

	private List<WebElement> getArticleList(String articlesFirstLetter) {
		List<WebElement> articlesList = driver.findElements(
				By.cssSelector(
						articlesListSelector.replace(
								"%articlesFirstLetter%", articlesFirstLetter
								)
						)
				);
		return articlesList;
	}

	private WebElement getLoadMoreButton(String articlesFirstLetter) {
		WebElement loadMoreButton = driver.findElement(
				By.cssSelector(
						loadMoreButtonSelector.replace(
								"%articlesFirstLetter%", articlesFirstLetter
								)
						)
				);
		return loadMoreButton;
	}

	private WebElement getLoadPreviousButton(String articlesFirstLetter) {
		WebElement loadPeviousButton = driver.findElement(
				By.cssSelector(
						loadPeviousButtonSelector.replace(
								"%articlesFirstLetter%", articlesFirstLetter
								)
						)
				);
		return loadPeviousButton;
	}

	public String getFirstArticleName(String articlesFirstLetter) {
		List<WebElement> articlesList = getArticleList(articlesFirstLetter);
		String firstArticle = articlesList.get(0).getText();
		return firstArticle;
	}

	public String getLastArticleName(String articlesFirstLetter) {
		List<WebElement> articlesList = getArticleList(articlesFirstLetter);
		String lastArticle = articlesList
				.get(articlesList.size()-1)
				.getText();
		return lastArticle;
	}

	public void verifyArticlesEquals(String article, String article2) {
		Assertion.assertEquals(article, article2);
	}

	public void verifyArticlesNotEquals(String article, String article2) {
		Assertion.assertNotEquals(article, article2);
	}

	public void showNextArticles(String articlesFirstLetter) {
		getLoadMoreButton(articlesFirstLetter).click();;
		waitForElementByElement(getLoadPreviousButton(articlesFirstLetter));
	}

	public void showPreviousArticles(String articlesFirstLetter) {
		getLoadPreviousButton(articlesFirstLetter).click();;
		waitForElementByElement(getLoadMoreButton(articlesFirstLetter));
	}

}
