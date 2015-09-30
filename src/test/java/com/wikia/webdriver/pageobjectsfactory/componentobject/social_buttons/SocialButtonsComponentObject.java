package com.wikia.webdriver.pageobjectsfactory.componentobject.social_buttons;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Michal 'justnpT' Nowierski
 */
public class SocialButtonsComponentObject extends WikiBasePageObject {

  @FindBys(@FindBy(css = "#PageShareToolbar svg"))
  protected List<WebElement> shareButtonImages;
  @FindBys(@FindBy(css = "#PageShareToolbar a"))
  protected List<WebElement> shareButtonLinks;

  public SocialButtonsComponentObject(WebDriver driver) {
    super(driver);
  }

  /**
   * Check if every share button is visible, then take a screenshot
   */
  public void verifyShareButtonsPresent() {
    for (int i = 0; i < shareButtonImages.size(); i++) {
      WebElement image = shareButtonImages.get(i);
      wait.forElementVisible(image);
    }
    LOG.success("verifyShareButtonsPresent", "social buttons present", true);
  }

  /**
   * Get titles of every share button. The values are eg. "Facebook", "Twitter", (...)
   *
   * @return array of the titles
   */
  public String[] getShareButtonTitles() {
    int numberOfShareButtons = shareButtonLinks.size();
    String[] titles = new String[numberOfShareButtons];
    for (int i = 0; i < numberOfShareButtons; i++) {
      WebElement link = shareButtonLinks.get(i);
      wait.forElementVisible(link);
      titles[i] = link.getAttribute("title");
    }
    return titles;
  }
}
