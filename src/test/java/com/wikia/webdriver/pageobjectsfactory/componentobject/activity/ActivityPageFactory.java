package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import com.wikia.webdriver.pageobjectsfactory.componentobject.activity.creators.ActivityPageCreator;
import org.openqa.selenium.WebElement;
import javax.swing.text.html.HTML;
import java.util.List;


public class ActivityPageFactory {
  private List<ActivityPageCreator> creators;
  private WebElement activity;

  public ActivityPageFactory(WebElement activity, List<ActivityPageCreator> creators) {
    this.creators = creators;
    this.activity = activity;
  }

  public Activity createActivityPage() {
    String activityClass = activity.getAttribute(HTML.Attribute.CLASS.toString());
    if (!creators.stream().filter(c -> c.match(activityClass)).findAny().isPresent()) {
      throw new IllegalArgumentException("Type of activity was not recognized");
    }
    ActivityPageCreator creator = creators.stream().filter(c -> c.match(activityClass)).findAny().get();
    return creator.createPage(activity);
  }
}
