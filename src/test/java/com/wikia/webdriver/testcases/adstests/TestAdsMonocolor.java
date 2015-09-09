package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * @ownership AdEng
 */
public class TestAdsMonocolor extends TemplateNoFirstLoad {

  @Test(groups = "AdsMonocolorOasis")
  public void adsMonocolorOasis() {
    driver.get(urlBuilder.getUrlForPath("adtest", "Monocolor_Ad"));
    String topLeaderboardSelector = AdsContent.getSlotSelector(AdsContent.TOP_LB);
    WebElement topLeaderboard = driver.findElement(By.cssSelector(topLeaderboardSelector));
    AdsComparison comparison = new AdsComparison();
    Assertion.assertFalse(comparison.isAdVisible(topLeaderboard, topLeaderboardSelector, driver));
  }

}
