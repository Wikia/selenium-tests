package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import static java.util.stream.Collectors.toList;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

@AllArgsConstructor
public class ShareDialog {

  private final WebElement webElement;

  public List<SocialIcon> getSocialIcons() {
    return webElement.findElements(By.className("social"))
        .stream()
        .map(SocialIcon::new)
        .collect(toList());
  }

  @AllArgsConstructor
  public static class SocialIcon {

    private final WebElement webElement;

    public String getSocialNetworkName() {
      return StringUtils.remove(webElement.getAttribute("class"), "social ");
    }
  }
}
