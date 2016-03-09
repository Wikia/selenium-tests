package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdsFliteObject extends AdsBaseObject {
  private String[] fliteSyntheticSuperheroesIframes;

  public AdsFliteObject(WebDriver driver,
                        String testedPage,
                        Dimension resolution,
                        String[] fliteSyntheticSuperheroesIframes ) {
    super(driver, testedPage, resolution);
    this.fliteSyntheticSuperheroesIframes = fliteSyntheticSuperheroesIframes;
  }

  private void switchToFliteSyntheticSuperheroesAd(){
    for (String cssIframe : fliteSyntheticSuperheroesIframes ){
      WebElement iframe = driver.findElement(By.cssSelector(cssIframe));
      driver.switchTo().frame(iframe);
    }
  }

  public void verifyFliteSkin(String left, String right, String cssButton){
    if (StringUtils.isNotEmpty(cssButton)) {
      switchToFliteSyntheticSuperheroesAd();
      WebElement button = driver.findElement(By.cssSelector(cssButton));
      new Actions(driver).moveToElement(button).perform();
      driver.switchTo().defaultContent();
    }

    verifySkin(left, right, null, null);
  }
}
