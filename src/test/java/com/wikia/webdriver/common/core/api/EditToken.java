package com.wikia.webdriver.common.core.api;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.core.Helios;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import java.util.Iterator;

public class EditToken {
  private String baseURL = new UrlBuilder().getUrlForWiki(Configuration.getWikiName())
      + "/api.php";

  private static String EDIT_TOKEN_ERROR_MESSAGE = "Problem with edit token API call";

  private User user;

  public EditToken(User user) {
    this.user = user;
  }

  public String getEditToken() {
    try {
      String apiURL = new URIBuilder(baseURL)
          .setParameter("action", "query")
          .setParameter("prop", "info")
          .setParameter("format", "json")
          .setParameter("intoken", "edit")
          .setParameter("titles", "Main Page")
          .build().toASCIIString();

      CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
      HttpGet httpGet = new HttpGet(apiURL);
      // set header
      if (user != null) {
        httpGet.addHeader("X-Wikia-AccessToken", Helios.getAccessToken(user));
      }

      PageObjectLogging.logInfo("QUERY EDIT TOKEN: ", httpGet.toString());
      String editToken = httpClient.execute(httpGet, extractEditToken());

      PageObjectLogging.logInfo("QUERY EDIT TOKEN: ", "Token fetched: " + editToken);

      return editToken;
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    } catch (IOException e) {
      PageObjectLogging.log("IO EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    } catch (URISyntaxException e) {
      PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
      throw new WebDriverException(EDIT_TOKEN_ERROR_MESSAGE);
    }
  }

  private static ResponseHandler<String> extractEditToken() {
    return res -> {
      HttpEntity entity = res.getEntity();
      PageObjectLogging.logInfo("EDIT TOKEN HEADERS: ", res.toString());
      String source = EntityUtils.toString(entity);
      PageObjectLogging.logInfo("EDIT TOKEN RESPONSE RAW: ", source);
      try {
        JSONObject pagesValue = new JSONObject(source).getJSONObject("query").getJSONObject("pages");
        Iterator pageIterator = pagesValue.keys();
        if (pageIterator.hasNext()) {
          String key = (String)pageIterator.next();
          JSONObject pageInfo = pagesValue.getJSONObject(key);
          return pageInfo.getString("edittoken");
        } else {
          throw new WebDriverException("Could not find page in edit token response");
        }
      } catch (JSONException e) {
        PageObjectLogging.log("JSON EXCEPTION", ExceptionUtils.getStackTrace(e), false);
        throw new WebDriverException(e);
      }
    };
  }
}
