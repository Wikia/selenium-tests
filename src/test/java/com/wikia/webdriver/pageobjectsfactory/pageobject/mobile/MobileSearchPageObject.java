package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class MobileSearchPageObject extends MobileBasePageObject {

	public MobileSearchPageObject(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "ul#wkResultUl li.result p a")
	WebElement searchResultList;
	@FindBy(css = "#wkResultNext")
	WebElement searchNextPageButton;
	@FindBy(css = "#wkResultPrev")
	WebElement searchPreviousPageButton;
	@FindBys(@FindBy(css = ".result"))
	List<WebElement> resultList;

	public void verifySearchResultsList() {
		waitForElementByElement(searchResultList);
		PageObjectLogging.log("verifySearchResultsList", "search results list verified", true, driver);
	}

	public void verifyNextPageButtonIsVisible() {
		waitForElementByElement(searchNextPageButton);
	}

	public void verifyPreviousPageButtonIsVisible() {
		waitForElementByElement(searchPreviousPageButton);
	}

	public void clickOnSearchNextPageButton() {
		searchNextPageButton.click();
	}

	public void clickOnSearchPreviousPageButton() {
		searchPreviousPageButton.click();
	}

	public List<String> getResult() {
		List<String> listTitle = new ArrayList<String>();
		for (WebElement elem : resultList) {
			listTitle.add(elem.findElement(By.cssSelector("a")).getText());
		}
		return listTitle;
	}

	public void compareResultsEquals(List<String> beforePagination, List<String> afterPagination) {
		Assertion.assertNumber(beforePagination.size(), afterPagination.size(), "checking length");
		for (int i = 0; i < beforePagination.size(); i++) {
			Assertion.assertEquals(beforePagination.get(i), afterPagination.get(i), "list's elements are not equals");
		}
	}

	public void compareResultsNotEquals(List<String> beforePagination, List<String> afterPagination) {
		Assertion.assertTrue(beforePagination.size() != afterPagination.size(), "sizes of lists are the same");
		for (int i = 0; i < beforePagination.size(); i++) {
			Assertion.assertNotEquals(beforePagination.get(i), afterPagination.get(i), "list's elements are equals");
		}
	}
}
