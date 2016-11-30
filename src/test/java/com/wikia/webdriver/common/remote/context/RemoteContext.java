package com.wikia.webdriver.common.remote.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebDriver;

@AllArgsConstructor
@Getter
public class RemoteContext {

  private final String siteId;
}
