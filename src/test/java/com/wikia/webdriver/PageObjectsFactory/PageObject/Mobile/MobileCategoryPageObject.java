package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

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

	public MobileArticlePageObject openCategory(String wikiURL) {
		getUrl(wikiURL + URLsContent.categoryPmg);
		PageObjectLogging.log("openCategory", "category page: "+URLsContent.categoryPmg+", was opened", true, driver);
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

	public void openArticle(int i) {
		articleList.get(i).click();
		waitForElementByElement(loadMoreButton);
	}

	public void showNextArticles() {
		loadMoreButton.click();
		waitForElementByElement(loadPreviousButton);
	}

	public void showPreviousArticles() {
		loadPreviousButton.click();
		waitForElementByElement(loadMoreButton);
	}

	public void verifyArticlesCount(int count) {
		Assertion.assertTrue(articleListWithPagination.size() == count);
	}

	public String getFirstArticleName() {
		String firstArticle = articleListWithPagination.get(0).getAttribute("href");
		return firstArticle;
	}

	public String getLastArticleName() {
		String lastArticle = articleListWithPagination.get(articleListWithPagination.size()-1).getAttribute("href");
		return lastArticle;
	}

	public void verifyArticlesEquals(String article, String article2) {
		Assertion.assertEquals(article, article2);
	}

	public void verifyArticlesNotEquals(String article, String article2) {
		Assertion.assertNotEquals(article, article2);
	}

}
