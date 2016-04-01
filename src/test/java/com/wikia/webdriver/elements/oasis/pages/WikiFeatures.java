package com.wikia.webdriver.elements.oasis.pages;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class WikiFeatures extends BasePageObject {

  @FindBy(css = "#WikiFeatures > ul:nth-child(3) > li:nth-child(1) > div.actions > span > span.button")
  private WebElement europaInfoboxThemeSlider;

  @FindBy(css = "#WikiFeatures > ul:nth-child(3) > li:nth-child(1) > div.actions > span > span.texton")
  private WebElement europaInfoboxThemeSliderText;

  @FindBy(css = "#DeactivateDialog")
  private WebElement deactivateDialog;

  @FindBy(css = "#DeactivateDialog > footer > div > button.button.normal.primary")
  private WebElement confirmDeactivation;

  @FindBy(css = ".feature")
  private List<WebElement> features;

  public WikiFeatures() { super(); }

  public WikiFeatures openWikiFeatures(String wikiURL) {
    getUrl(wikiURL + URLsContent.SPECIAL_WIKI_FEATURES);
    PageObjectLogging.log("openWikiFeaturesPage", "special wiki features page opened", true, driver);

    return this;
  }

  public boolean isEuropaInfoboxThemeEnabled() {
    if (europaInfoboxThemeSliderText.getText().equalsIgnoreCase("Enabled")) {
      return true;
    } else {
      return false;
    }
  }

  public WikiFeatures enableEuropaInfoboxTheme() {
    wait.forElementClickable(europaInfoboxThemeSlider);
    europaInfoboxThemeSlider.click();

    return this;
  }

  public WikiFeatures disableEuropaInfoboxTheme() {
    if (this.isEuropaInfoboxThemeEnabled() == true) {
      wait.forElementClickable(europaInfoboxThemeSlider);
      europaInfoboxThemeSlider.click();

      wait.forElementClickable(confirmDeactivation);
      confirmDeactivation.click();
    }

    return this;
  }

}
