package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators.ActivityPageCreator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import javax.swing.text.html.HTML;
import java.util.List;


public class ActivityPageFactory {
  private List<ActivityPageCreator> creators;
  private WebElement activity;
  private WebDriver driver;

  public ActivityPageFactory(WebDriver driver, WebElement activity, List<ActivityPageCreator> creators) {
    this.creators = creators;
    this.activity = activity;
    this.driver = driver;
  }

  public Activity createActivityPage() {
    String activityClass = activity.getAttribute(HTML.Attribute.CLASS.toString());
    if (!creators.stream().filter(c -> c.match(activityClass)).findAny().isPresent()) {
      throw new IllegalArgumentException("Type of activity was not recognized");
    }
    ActivityPageCreator creator = creators.stream().filter(c -> c.match(activityClass)).findAny().get();
    return creator.createPage(driver, activity);
  }
}
