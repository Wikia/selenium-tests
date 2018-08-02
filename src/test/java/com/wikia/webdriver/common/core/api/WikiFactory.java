package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.Log;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.WebDriverException;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class WikiFactory extends ApiCall {

  private String baseURL = UrlBuilder.createUrlBuilderForWiki("community")
                               .getUrl()
                               .replace(UrlBuilder.HTTPS_PREFIX, UrlBuilder.HTTP_PREFIX)
                           + "/api.php";
  private ArrayList<BasicNameValuePair> params = new ArrayList<>();
  private User user = User.STAFF;
  private String username = user.getUserName();

  @Override
  protected String getURL() {
    return baseURL;
  }

  @Override
  protected User getUser() {
    return user;
  }

  @Override
  protected ArrayList<BasicNameValuePair> getParams() {
    return params;
  }

  @Override
  protected String getUserName() {
    return username;
  }

  public void setIsTestWiki(String wikiId, int value) {
    String editToken = "";
    if (username != null) {
      editToken = new EditToken(username).getEditToken();
    } else {
      editToken = new EditToken(user).getEditToken();
    }
    try {
      params.add(new BasicNameValuePair("wiki_id", wikiId));
      params.add(new BasicNameValuePair("variable_id", "1856"));
      params.add(new BasicNameValuePair("variable_value", Integer.toString(value)));
      params.add(new BasicNameValuePair("reason", "QA wikia"));
      params.add(new BasicNameValuePair("token", editToken));
      params.add(new BasicNameValuePair("action", "wfsavevariable"));
      params.add(new BasicNameValuePair("format", "json"));

      URL_STRING = new URIBuilder(baseURL).build().toASCIIString();
    } catch (URISyntaxException e) {
      Log.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException("Failed to build edit API URL");
    }
    call();
  }
}
