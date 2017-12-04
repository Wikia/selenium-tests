package com.webdriver.testcases;

import com.webdriver.pageobject.MainPageObject;
import org.testng.annotations.Test;

public class MainPageTests {

    @Test(groups = {"MainPageTests"})
    public void verifyMainPageTitle() {
        MainPageObject MainPage = new MainPageObject();
        MainPage.openUrl("https://videopoint.pl");
        System.out.println(MainPage.getTitle());
    }

}
