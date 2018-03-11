package com.wikia.webdriver.elements.mercury.components.discussions.common;

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
    @FindBy(css = ".poll-footer__vote")
    private WebElement voteButton;

    @FindBy(css = ".poll-footer__voted")
    private WebElement votedMessage;

    @FindBy(css = ".poll-answer__input")
    private List<WebElement> answersInputsList;

    @FindBy(css = ".poll-answer")
    private List<WebElement> answersList;

    @Getter
    @FindBy(css = ".poll-answer__radio")
    private List<WebElement> answersRadioButtonsList;

    @Getter
    @FindBy(css = ".poll-answer__result-bar.is-active")
    private WebElement activeBarResult;

    @Getter
    @FindBy(css = ".poll-answer__result-bar")
    private List<WebElement> BarResultsList;

    public Poll addTitle(String title) {
        wait.forElementClickable(titleInput);
        titleInput.sendKeys(title);

        return this;
    }

    public Poll addNthAnswer(String answer, int number) {
        wait.forElementClickable(answersInputsList.get(number));
        answersInputsList.get(number).sendKeys(answer);

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

    public Poll clickNthAnswer(int number) {
        wait.forElementClickable(answersList.get(number));
        answersList.get(number).click();

        return this;
    }

    public boolean isChosenResultBarDisplayed() {
        wait.forElementVisible(activeBarResult);

        return activeBarResult.isDisplayed();
    }

    public boolean isAlreadyVotedMessageVisible() {
        wait.forElementVisible(votedMessage);

        return votedMessage.isDisplayed();
    }

}
