package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.components.login.JoinButtons;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JoinPage extends WikiBasePageObject {

    @Getter(lazy = true)
    private final JoinButtons joinButtons = new JoinButtons(driver);


}
