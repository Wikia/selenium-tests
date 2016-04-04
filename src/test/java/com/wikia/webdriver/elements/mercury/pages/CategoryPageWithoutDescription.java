package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPageWithoutDescription extends WikiBasePageObject {

  @FindBy(css = ".category-sections .category-section li")
  private WebElement firstSectionMembers;

  public CategoryPageWithoutDescription() {
    super();
  }

  private CategoryPageWithoutDescription checkDescriptionAbsence() {
    wait.forElementNotPresent(By.cssSelector(".article-content"));
    PageObjectLogging.logInfo("There's no article container on the page, which is nice.");

    return this;
  }

  private CategoryPageWithoutDescription checkFirstSectionHasMembers() {
    Assertion.assertTrue(firstSectionMembers.isDisplayed(), "First section is empty.");
    PageObjectLogging.logInfo("First section contains members: " + firstSectionMembers);

    return this;
  }

  public CategoryPageWithoutDescription check() {
    return this
        .checkDescriptionAbsence()
        .checkFirstSectionHasMembers();
  }

  public CategoryPageWithoutDescription openFromUrl() {
    new Navigate(driver).toPage(MercurySubpages.CATEGORY_WITHOUT_DESCRIPTION);

    return this.check();
  }
}
