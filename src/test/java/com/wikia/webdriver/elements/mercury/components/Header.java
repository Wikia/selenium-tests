package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

  @FindBy(css = ".wiki-page-header__title")
  private WebElement pageTitle;

  @FindBy(css = ".wiki-page-header .has-hero-image")
  private WebElement headerWithHeroImage;

  @FindBy(css = ".wiki-page-header:not(.has-hero-image)")
  private WebElement headerWithoutHeroImage;

  private Wait wait;

  public Header(WebDriver driver) {
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public Header isPageTitleVisible() {
    wait.forElementVisible(pageTitle);
    PageObjectLogging.log("Page header title", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public Header isHeroImageVisible() {
    wait.forElementVisible(headerWithHeroImage);
    PageObjectLogging.log("Hero image", MercuryMessages.VISIBLE_MSG, true);

    return this;
  }

  public Header isHeroImageNotVisible() {
    wait.forElementVisible(headerWithoutHeroImage);
    PageObjectLogging.log("Hero image", MercuryMessages.INVISIBLE_MSG, true);

    return this;
  }
  
  public Header isHeroImageProperlyStyled() {
    PageObjectLogging.logInfo("checking 'background-color'…");
    Assertion.assertEquals(
        headerWithHeroImage.getCssValue("background-color"), "rgba(255, 255, 255, 1)");
    PageObjectLogging.logInfo("checking 'background-position'…");
    Assertion.assertEquals(headerWithHeroImage.getCssValue("background-position"), "50% 50%");
    PageObjectLogging.logInfo("checking 'background-repeat'…");
    Assertion.assertEquals(headerWithHeroImage.getCssValue("background-repeat"), "no-repeat");

    return this;
  }

  public Header isHeroImageSquare() {
    wait.forElementVisible(headerWithHeroImage);
    Assertion.assertEquals(headerWithHeroImage.getSize().getHeight(),
                           headerWithHeroImage.getSize().getWidth());
    PageObjectLogging.log("Hero image", "is square", true);

    return this;
  }

  public Header isNotHeroImageSquare() {
    wait.forElementVisible(headerWithHeroImage);
    Assertion.assertNotEquals(headerWithHeroImage.getSize().getHeight(),
                              headerWithHeroImage.getSize().getWidth());
    PageObjectLogging.log("Hero image", "is not square", true);

    return this;
  }
}
