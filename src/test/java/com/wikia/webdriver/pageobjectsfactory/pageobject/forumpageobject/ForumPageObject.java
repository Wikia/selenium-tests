package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class ForumPageObject extends WikiBasePageObject {

  @FindBy(css = ".button.policies-link")
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

  public ForumPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

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
    return new ForumManageBoardsPageObject(driver);
  }

  /*
   * this method choose first link on the board which doesn't contain chinese signs
   */
  public ForumBoardPageObject openForumBoard() {
    WebElement forumBoardLink = null;
    for (int i = 0; i < getForumElementsList().size(); i++) {
      if (!getForumElementsList().get(i).toString().contains("%")) {
        forumBoardLink = getForumElementsList().get(i);
        break;
      }
    }
    wait.forElementVisible(forumBoardLink);
    wait.forElementClickable(forumBoardLink);
    scrollAndClick(forumBoardLink);
    PageObjectLogging.log("openForumBoard", "click on the forum Board", true, driver);
    return new ForumBoardPageObject(driver);
  }

  public ForumBoardPageObject openForumBoard(String forumBoardTitle) {
    int forumNumber = 0;
    List<String> forumNames = getForumNamesList();
    String formattedForumBoardTitle = forumBoardTitle.replace("_", " ");
    for (int i = 0; i < forumNames.size(); i++) {
      if (forumNames.get(i).contains(formattedForumBoardTitle)) {
        forumNumber = i + 1;
      }
    }
    if (forumNumber == 0) {
      PageObjectLogging.log("openForumBoard",
          "didn't find forum Board with title " + formattedForumBoardTitle, true, driver);
      return null;
    } else {
      PageObjectLogging.log("openForumBoard",
          "click on the forum Board with title " + formattedForumBoardTitle, true, driver);
      return openForumBoard();
    }
  }

  public List<String> getForumNamesList() {
    List<WebElement> listWebElements = getForumElementsList();
    List<String> forumNames = new ArrayList<String>();
    for (WebElement elem : listWebElements) {
      forumNames.add(elem.getText());
    }
    return forumNames;
  }

  private List<WebElement> getForumElementsList() {
    List<WebElement> listWebElements = driver.findElements(forumBoardsList);
    return listWebElements;
  }
}
