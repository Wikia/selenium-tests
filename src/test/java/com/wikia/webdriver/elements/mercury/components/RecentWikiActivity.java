package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RecentWikiActivity extends WikiBasePageObject {

  @FindBy(css = ".recent-change__see-diff")
  private WebElement seeDiffArrow;
  @FindBy(css = ".diff-page__undo")
  private WebElement undoButton;
  @FindBy(css = ".sub-head--cancel")
  private WebElement backArrow;
  @FindBy(css = ".sub-head--done")
  private WebElement undoButtonOnSummaryPage;
  @FindBy(css = ".confirmation-message__textarea")
  private WebElement summaryTestbox;

  private By undoButtonBy = By.cssSelector(".diff-page_undo");
  private By undoButtonOnSummaryPageBy = By.cssSelector(".sub-head--done");

  private Loading loading;
  private Actions actions;

  public RecentWikiActivity() {
    super();

    this.loading = new Loading(driver);
    this.actions = new Actions(driver);
  }

  public RecentWikiActivity openDiffPage() {
    wait.forElementClickable(seeDiffArrow);

    String urlToBeRedirected = seeDiffArrow.getAttribute("href");
    seeDiffArrow.click();

    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("The arrow redirecting to the diff page was clicked");

    Assertion.assertTrue(driver.getCurrentUrl().contains(urlToBeRedirected),
                         "You were not redirected to the diff page");
    PageObjectLogging.logInfo("You were redirected to the diff page");

    return this;
  }

  private void submitOnDiffPage() {
    wait.forElementClickable(undoButton);
    PageObjectLogging.logInfo("Undo button is clickable");

    undoButton.click();
    wait.forElementNotVisible(undoButtonBy);
    PageObjectLogging.logInfo("Undo button was clicked");
  }

  private void summary(String searchQuery) {
    wait.forElementClickable(summaryTestbox);
    summaryTestbox.click();
    actions.sendKeys(searchQuery).perform();
    PageObjectLogging.logInfo("Typed in summary box: " + searchQuery);
  }

  private void submitOnSummaryPage() {
    wait.forElementClickable(undoButtonOnSummaryPage);
    undoButtonOnSummaryPage.click();
    PageObjectLogging.logInfo("Undo button was clicked");

    loading.handleAsyncPageReload();

    Assertion.assertTrue(driver.getCurrentUrl().contains(URLsContent.RECENT_WIKI_ACTIVITY),
                         "You were not redirected to the recent wiki activity");
    PageObjectLogging.logInfo("You were redirected to the recent wiki activity");
  }

  public void submitWithoutSummary() {
    submitOnDiffPage();
    submitOnSummaryPage();
  }

  public RecentWikiActivity submitWithSummary() {
    submitOnDiffPage();
    summary("For testing");
    submitOnSummaryPage();

    PageObjectLogging.logInfo("Undo was submitted with a short summary");

    return this;
  }


  public RecentWikiActivity goBackToRWA() {
    wait.forElementClickable(backArrow);
    backArrow.click();

    loading.handleAsyncPageReload();

    PageObjectLogging.logInfo("The arrow that redirect user back to RWA was clicked");

    Assertion.assertTrue(driver.getCurrentUrl().contains(URLsContent.RECENT_WIKI_ACTIVITY),
                         "You were not redirected to the recent wiki activity");
    PageObjectLogging.logInfo("You were redirected to the recent wiki activity");

    return this;
  }
}
