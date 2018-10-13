package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebDriverException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public abstract class AttachedPageBase<T extends AttachedPageBase<T>> extends BasePageObject {

  private String siteSpecificPath;

  public AttachedPageBase(String siteSpecificPath) {
    this.siteSpecificPath = siteSpecificPath;
  }

  protected abstract T getThis();

  public T open() {
    open(getWikiUrl() + URLsContent.USER_LOGIN);
    return getThis();
  }

  public T open(String redirectUrl) {
    String signinUrl = rewriteDomainIfNeeded(urlBuilder.getWikiGlobalURL() + siteSpecificPath);
    try {
      getUrl(urlBuilder.appendQueryStringToURL(signinUrl, "redirect=" +
          URLEncoder.encode( redirectUrl, StandardCharsets.UTF_8.name()))
      );
    } catch (UnsupportedEncodingException e) {
      Log.log("Opening attached page", "Unable to encode redirect", false);
      throw new WebDriverException("Unable to encode redirect", e);
    }
    Log.log("Opening attached page", "Special:UserLogin page opened", true);

    return getThis();
  }

  //TODO: Remove it after fandom migration
  /**
   * We use www.wikia.com everywhere, mercury is an exception
   */
  private String rewriteDomainIfNeeded(String url) {
    return Configuration.getForceFandomDomain() ? url.replace("wikia.com","fandom.com") : url;
  }
}
