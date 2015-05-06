package com.wikia.webdriver.pageobjectsfactory.componentobject.social_buttons;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.componentobject.media.VideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.filepage.FilePagePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class SocialButtonsComponentObject extends WikiBasePageObject {

  public SocialButtonsComponentObject(WebDriver driver) {
      super(driver);
  }

    @FindBys(@FindBy(css = "#PageShareToolbar svg"))
    protected List<WebElement> shareButtonImages;

    @FindBys(@FindBy(css = "#PageShareToolbar a"))
    protected List<WebElement> shareButtonLinks;

    /**
     * Check if every share button is visible, then take a screenshot
     */
    public void verifyShareButtonsPresent() {
        for (int i = 0; i < shareButtonImages.size(); i++) {
            WebElement image = shareButtonImages.get(i);
            waitForElementByElement(image);
        }
        PageObjectLogging.log("verifyShareButtonsPresent", "social buttons present", true, driver);
    }

    /**
     * Get titles of every share button. The values are eg. "Facebook", "Twitter", (...)
     * @return array of the titles
     */
    public String[] getShareButtonTitles() {
        int numberOfShareButtons = shareButtonLinks.size();
        String[] titles = new String[numberOfShareButtons];
        for (int i = 0; i < numberOfShareButtons; i++) {
            WebElement link = shareButtonLinks.get(i);
            waitForElementByElement(link);
            titles[i] = link.getAttribute("title");
        }
        return titles;
    }

}
