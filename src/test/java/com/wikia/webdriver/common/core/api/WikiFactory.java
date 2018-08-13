package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import java.util.ArrayList;
import org.apache.http.message.BasicNameValuePair;

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

  public void setIsTestWiki(String wikiId, boolean value) {
    String editToken = "";
    if (username != null) {
      editToken = new EditToken(username).getEditToken();
    } else {
      editToken = new EditToken(user).getEditToken();
    }
    params.add(new BasicNameValuePair("wiki_id", wikiId));
    params.add(new BasicNameValuePair("variable_id", "1856"));
    params.add(new BasicNameValuePair("variable_value", value ? "1" : "0"));
    params.add(new BasicNameValuePair("reason", "QA wikia"));
    params.add(new BasicNameValuePair("token", editToken));
    params.add(new BasicNameValuePair("action", "wfsavevariable"));
    params.add(new BasicNameValuePair("format", "json"));
    call();
  }
}
