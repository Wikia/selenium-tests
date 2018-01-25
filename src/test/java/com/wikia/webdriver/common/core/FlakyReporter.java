package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class FlakyReporter {

  private static final String SERVICE_URL = "http://services.wikia.com/my-awesome-service";
  private HttpClient httpclient = HttpClients.createDefault();
  private HttpPost httppost = new HttpPost(SERVICE_URL);

  // Request parameters and other properties.
  private List<NameValuePair> params = new ArrayList<>();

  public void sendFlaky(String name, Integer status, Boolean flaky) throws IOException {
    params.add(new BasicNameValuePair("name", name));
    params.add(new BasicNameValuePair("status", mapStatus(status)));
    params.add(new BasicNameValuePair("flaky", Boolean.toString(flaky)));
    httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    //Execute and get the response.
    HttpResponse response = httpclient.execute(httppost);
    HttpEntity entity = response.getEntity();

    if (entity != null) {
      try (InputStream instream = entity.getContent()) {
        System.out.print(instream.toString());
      }
    }
  }

  private String mapStatus(Integer status) {
    if (status == 1) {
      return "success";
    } else if (status == 2) {
      return "failure";
    } else return "unknown";
  }

}
