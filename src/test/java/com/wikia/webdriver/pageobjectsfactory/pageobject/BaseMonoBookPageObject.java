package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Bogna 'bognix' Knychala
 */
public class BaseMonoBookPageObject extends WikiBasePageObject {

  @FindBy(css = ".skin-monobook")
  protected WebElement monobookSkinClass;
  @FindBy(css = "body.oasis-oasis")
  protected WebElement oasisSkinClass;

  public BaseMonoBookPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void openWikiPageWithMonobook() {
    getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.NOEXTERNALS);
    LOG.success("OpenWikiPage", "Wiki Page opened with success");
    changeToMonoBook();
    LOG.logResult("logOut", "skin is changing for more than 30 seconds", true, driver);
  }

  public void changeToMonoBook() {
    String currentUrl = driver.getCurrentUrl();
    if (currentUrl.contains("?")) {
      getUrl(currentUrl + "&useskin=monobook");
    } else {
      getUrl(currentUrl + "?useskin=monobook");
    }
    verifySkinChanged();
  }

  public void verifySkinChanged() {
    wait.forElementVisible(monobookSkinClass);
    LOG.success("skinChangedToMonoBook", "skin is changed to monobook");
  }
}
