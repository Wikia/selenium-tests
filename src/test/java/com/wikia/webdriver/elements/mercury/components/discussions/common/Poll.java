package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Poll extends BasePageObject {

    @Getter
    @FindBy(css = ".poll-title__input")
    private WebElement pollTitleInput;

    @FindBy(css = ".poll-answer__input")
    private List<WebElement> pollAnswersInputsList;

    public Poll addTitle(String title) {
        wait.forElementClickable(pollTitleInput);
        pollTitleInput.sendKeys(title);

        return this;
    }

    public Poll addNthAnswer(String answer, int number) {
        wait.forElementClickable(pollAnswersInputsList.get(number));
        pollAnswersInputsList.get(number).sendKeys(answer);

        return this;
    }

}
