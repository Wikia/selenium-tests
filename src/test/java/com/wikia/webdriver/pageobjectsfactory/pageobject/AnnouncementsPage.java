package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.elements.mercury.pages.discussions.BasePage;

public class AnnouncementsPage extends BasePage {

    private static final String URL_PATH = "/announcements";

    public AnnouncementsPage open() {
        driver.get(getUrlWithCacheBuster(String.format("%s%s", urlBuilder.getUrl(), URL_PATH)));
        return this;
    }

}
