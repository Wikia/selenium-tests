package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Poll extends BasePageObject {

  @Getter
  @FindBy(css = ".poll-title__input")
  private WebElement titleInput;

  @Getter
  @FindBy(css = ".poll-title")
  private WebElement title;

  @Getter
  @FindBy(css = ".poll-footer > button")
  private WebElement voteButton;

  @FindBy(css = ".poll-footer__share")
  private WebElement shareButton;

  @Getter
  @FindBy(css = ".poll-answer__input")
  private List<WebElement> answersInputsList;

  @Getter
  @FindBy(css = ".poll-answer")
  private List<WebElement> answersList;

  @Getter
  @FindBy(css = ".poll-answer__label > .poll-answer__radio")
  private List<WebElement> answersRadioButtonsList;

  @Getter
  @FindBy(css = ".is-selected  > .poll-answer__result > .poll-answer__result-bar")
  private WebElement activeBarResult;

  @Getter
  @FindBy(css = ".poll-answer__result-bar")
  private List<WebElement> barResultsList;

  @FindBy(css = ".poll-title__remove-icon")
  private WebElement deletePollIcon;

  @FindBy(css = ".poll-answer__remove")
  private WebElement deleteAnswerIcon;

  @FindBy(css = ".poll-answers__add-answer-button > .wds-button")
  private WebElement addAnswerButton;

  @FindBy(css = ".voter-list")
  private WebElement votersListModal;

  @FindBy(css = ".voter-list__header-icon > .wds-icon")
  private WebElement closeVotersListButton;

  @FindBy(css = ".poll-vote-count")
  private WebElement pollVotesIcon;

  @FindBy(css = ".user-avatar")
  private List<WebElement> votersList;

  @FindBy(css = ".poll-answer")
  private List<WebElement> answerResultsList;

  public int getVotersNumber() {
    return votersList.size();
  }

  public int getRadioButtonsNumber() {
    wait.forElementVisible(answersRadioButtonsList.get(0));

    return answersRadioButtonsList.size();
  }

  public Poll addTitle(String title) {
    wait.forElementClickable(titleInput);
    titleInput.sendKeys(title);

    return this;
  }

  public Poll addNthAnswer(String answer, int number) {
    try {
      wait.forElementClickable(answersInputsList.get(number));
      answersInputsList.get(number).sendKeys(answer);
    } catch (Exception e) {
      Log.log("Could not add nth answer", e, false);
    }

    return this;
  }

  public Poll clickPollTitle() {
    wait.forElementClickable(title);
    title.click();

    return this;
  }

  public Poll clickVoteButton() {
    wait.forElementClickable(voteButton);
    voteButton.click();

    return this;
  }

  public Poll clickOnNthAnswerResult(int n) {
    wait.forElementClickable(answerResultsList.get(n));
    answerResultsList.get(n).click();

    return this;
  }

  public Poll clickOnPollVotesIcon() {
    wait.forElementClickable(pollVotesIcon);
    pollVotesIcon.click();

    return this;
  }

  public Poll clickNthAnswer(int number) {
    try {
      wait.forElementClickable(answersList.get(number));
      answersList.get(number).click();
    } catch (Exception e) {
      Log.log("Could not click nth answer", e, false);
    }

    return this;
  }

  public Poll clickAddAnswerButton() {
    wait.forElementClickable(addAnswerButton);
    scrollAndClick(addAnswerButton);

    return this;
  }

  public Poll closeVotersListModal() {
    wait.forElementClickable(closeVotersListButton);
    closeVotersListButton.click();

    return this;
  }

  public Poll deletePoll() {
    wait.forElementClickable(deletePollIcon);
    deletePollIcon.click();

    return this;
  }

  public Poll deleteNthAnswer(int number) {
    try {
      wait.forElementClickable(answersInputsList.get(number));
      answersInputsList.get(number).click();
    } catch (Exception e) {
      Log.log("Could not click nth answer", e, false);
    }

    wait.forElementClickable(deleteAnswerIcon);
    deleteAnswerIcon.click();

    return this;
  }

  public boolean isChosenResultBarDisplayed() {
    return isElementDisplayed(activeBarResult);
  }

  public boolean isShareButtonDisplayed() {
    return isElementDisplayed(shareButton);
  }

  public boolean isVotersListDisplayed() { return isElementDisplayed(votersListModal); }
}
