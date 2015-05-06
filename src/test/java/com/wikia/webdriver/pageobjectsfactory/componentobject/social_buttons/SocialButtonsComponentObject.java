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
 * @author Karol 'kkarolk' Kujawiak
 * @author Saipetch Kongkatong
 */
public class SocialButtonsComponentObject extends WikiBasePageObject {

  public SocialButtonsComponentObject(WebDriver driver) {
      super(driver);
  }

    @FindBys(@FindBy(css = "#PageShareToolbar svg"))
    protected List<WebElement> shareButtonImages;

    @FindBys(@FindBy(css = "#PageShareToolbar a"))
    protected List<WebElement> shareButtonLinks;

    public void verifyShareButtonsPresent() {
        for (int i = 0; i < shareButtonImages.size(); i++) {
            WebElement image = shareButtonImages.get(i);
            waitForElementByElement(image);
        }
        PageObjectLogging.log("verifyShareButtonsPresent", "social buttons present", true, driver);
    }

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
