package com.wikia.webdriver.common.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class UserRegistration {
  private UserRegistration() {}

  public static void registerUserEmailConfirmed(SignUpUser user) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
    URL url = null;
    String env = Configuration.getEnvType().getKey();
    String baseURL = XMLReader.getValue("services_internal." + env + ".base_url");
    try {
      url = new URL(baseURL + "/user-registration/users/emailconfirmed");
    } catch (MalformedURLException e) {
      PageObjectLogging.logError("Wrong internal services URL", e);
    }
    try {
      HttpPost httpPost = new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(),
          url.getPort(), url.getPath(), url.getQuery(), url.getRef()));

      httpPost.setHeader("X-Client-Ip", "8.8.8.8");
      httpPost.setHeader("X-Wikia-Internal-Request", "1");

      List<BasicNameValuePair> params = new ArrayList<>();
      params.add(new BasicNameValuePair("username", user.getUsername()));
      params.add(new BasicNameValuePair("password", user.getPassword()));
      params.add(new BasicNameValuePair("email", user.getEmail()));
      params.add(new BasicNameValuePair("birthdate", user.getBirthday().toString()));
      params.add(new BasicNameValuePair("marketingAllowed", "on"));
      params.add(new BasicNameValuePair("registrationWikiaId", "80433"));

      httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));
      httpClient.execute(httpPost);
    } catch (URISyntaxException | IOException e) {
      PageObjectLogging.logError("Error during registering user", e);
    }
  }
}
