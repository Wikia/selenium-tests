package com.wikia.webdriver.elements.mercury.pages.login;

import com.wikia.webdriver.elements.mercury.components.login.JoinButtons;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;

public class JoinPage extends WikiBasePageObject {

    @Getter(lazy = true)
    private final JoinButtons joinButtons = new JoinButtons(driver);

}
