package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class ForumPage extends WikiBasePageObject {

  @FindBy(css = ".policies-link")
  private WebElement faqButton;
  @FindBy(css = "#ForumPoliciesModal")
  private WebElement faqLightBox;
  @FindBy(css = "#ForumPoliciesModal .secondary")
  private WebElement closeFaqLightBoxButton;
  @FindBy(css = ".button.admin-link")
  private WebElement manageBoardsButton;
  @FindBy(css = "div.wikiaThrobber")
  private WebElement faqModalLoadingState;

  private By forumBoardsList = By.cssSelector("ul.boards h4 a");

  private void openFaqLightBox() {
    wait.forElementVisible(faqButton);
    scrollAndClick(faqButton);
    PageObjectLogging.log("openFaqLightBox", "faq lightbox opened", true);
  }

  private void closeFaqLightBox() {
    waitForElementNotVisibleByElement(faqModalLoadingState);
    wait.forElementVisible(closeFaqLightBoxButton);
    closeFaqLightBoxButton.click();
    PageObjectLogging.log("closeFaqLightBox", "faq lightbox closed", true);
  }

  private void checkFaqLightBoxOpened() {
    wait.forElementVisible(faqLightBox);
    PageObjectLogging.log("checkFaqLightBoxOpened", "faq lightbox verified", true);
  }

  public void verifyFaqLightBox() {
    openFaqLightBox();
    checkFaqLightBoxOpened();
    closeFaqLightBox();
  }

  public ForumManageBoardsPageObject clickManageBoardsButton() {
    scrollAndClick(manageBoardsButton);
    PageObjectLogging.log("clickManageBoardsButton", "manage boards button clicked", true);
    return new ForumManageBoardsPageObject();
  }

  /*
   * this method choose first link on the board which doesn't contain chinese signs
   */
  public ForumBoardPage openForumBoard() {
    WebElement forumBoardLink = getForumElementsList().stream()
        .filter(webElement -> webElement.toString().contains("%"))
        .findFirst()
        .orElse(null);

    wait.forElementVisible(forumBoardLink);
    wait.forElementClickable(forumBoardLink);
    scrollAndClick(forumBoardLink);
    PageObjectLogging.log("openForumBoard", "click on the forum Board", true, driver);
    return new ForumBoardPage();
  }

  private List<WebElement> getForumElementsList() {
    return driver.findElements(forumBoardsList);
  }
}
