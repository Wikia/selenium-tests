package com.wikia.webdriver.common.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;

public class FlakyReporter {

  private static final String SERVICE_URL = XMLReader.getValue("cornflaky.address");
  private HttpClient httpclient = HttpClients.createDefault();
  private HttpPost httppost = new HttpPost(String.format("%s/%s", SERVICE_URL, "status"));

  // Request parameters and other properties.
  private List<NameValuePair> params = new ArrayList<>();

  public void sendFlaky(String name, Integer status, Boolean flaky) throws IOException {
    httppost.setEntity(new StringEntity(getData(name, status, flaky)));
    httppost.setHeader("Content-Type", "application/json");
    // Execute and get the response.
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
    } else
      return "unknown";
  }

  private String getData(String name, Integer status, boolean flaky) {
    String env = Configuration.getEnv();
    String app = Configuration.getApp();
    String version = Configuration.getVersion();

    params.add(new BasicNameValuePair("name", name));
    params.add(new BasicNameValuePair("status", mapStatus(status)));
    params.add(new BasicNameValuePair("flaky", Boolean.toString(flaky)));

    String data = "";
    try {
      JSONObject metadata =
          new JSONObject().put("env", env).put("app", app).put("version", version);
      data = new JSONObject().put("name", name).put("status", mapStatus(status))
          .put("flaky", Boolean.toString(flaky)).put("meta", metadata).toString();
    } catch (JSONException e) {
      PageObjectLogging.logError("Error building metadata for CornFlaky", e);
    }

    return data;
  }

}
