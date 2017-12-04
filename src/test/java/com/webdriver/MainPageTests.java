package com.webdriver;

import org.testng.annotations.Test;

public class MainPageTests {

    @Test(groups = {"MainPageTests"})
    public void verifyMainPageTitle() {
        MainPageObject MainPage = new MainPageObject();
        System.out.println(MainPage.getTitle());
    }

}
