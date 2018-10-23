package com.wikia.webdriver.common.remote;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.Objects;

public final class Utils {

  public static final String ACCESS_TOKEN_HEADER = "X-Wikia-AccessToken";

  private Utils() {
    throw new AssertionError();
  }

  public static String extractSiteIdFromMediaWikiUsing(final WebDriver driver) {
    String text = driver.findElement(By.cssSelector(".wikitable tr:nth-child(5) td:nth-child(2)"))
        .getText();
    // Found text: "city_id: 1362702, cluster: c7, dc: sjc"
    return StringUtils.substringBetween(text, ": ", ",");
  }

  public static String excractSiteIdFromWikiName(String wikiName) {
    return new SiteId(wikiName).getSiteId();
  }

  //TODO: Get rid of it and add services' url for fandom in config
  public static String buildServicesUrl() {
    final String environment = Configuration.getEnvType().getKey();
    File configurationFile = new File(Configuration.getCredentialsFilePath());
    final String url = XMLReader.getValue(configurationFile, "services." + environment).replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX);
    final String properUrl = Configuration.getForceFandomDomain() ? url.replace("wikia", "fandom") : url;
    Objects.requireNonNull(url, "Please check if your configuration file contains url for service ");
    return properUrl;
  }
}
