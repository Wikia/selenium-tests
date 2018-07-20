package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.helpers.SignUpUser;
import com.wikia.webdriver.common.logging.Log;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserRegistration {

  private UserRegistration() {}

  public static void registerUserEmailConfirmed(SignUpUser user) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
    URL url = null;
    String env = Configuration.getEnvType().getKey();
    String baseURL = XMLReader.getValue("services_internal." + env + ".base_url");
    try {
      url = new URL(baseURL + "user-registration/users/emailconfirmed");
    } catch (MalformedURLException e) {
      Log.logError("Wrong internal services URL", e);
    }
    try {
      HttpPost httpPost = new HttpPost(new URI(
          url.getProtocol(),
          url.getUserInfo(),
          url.getHost(),
          url.getPort(),
          url.getPath(),
          url.getQuery(),
          url.getRef()
      ));

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

      CloseableHttpResponse resp = httpClient.execute(httpPost);

      Log.info("REGISTER USER: ", httpPost.toString());
      Log.info("REGISTER USER: ", "Response: " + EntityUtils.toString(resp.getEntity(), "UTF-8"));
    } catch (URISyntaxException | IOException e) {
      Log.logError("Error during registering user", e);
    }
  }
}
