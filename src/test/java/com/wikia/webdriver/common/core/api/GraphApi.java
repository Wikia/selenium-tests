package com.wikia.webdriver.common.core.api;

import com.wikia.webdriver.common.core.XMLReader;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriverException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphApi {

    private static final String ERROR_MESSAGE = "Problem with Graph API call used to create new facebook test user";
    private static ArrayList<BasicNameValuePair> PARAMS;
    private static final String wikia_production_app_access_token = XMLReader
        .getValue("facebook.prod.accessToken");

    public HashMap<String, String> createFacebookTestUser(String appId) {
        try {
            HttpResponse response = createTestUser(appId);
            String entity = EntityUtils.toString(response.getEntity());
            return new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
        } catch (IOException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        } catch (URISyntaxException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }

    }

    public HashMap<String, String> deleteFacebookTestUser(String userId) {
        try {
            HttpResponse response = deleteTestUser(userId);
            String entity = EntityUtils.toString(response.getEntity());
            return new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
        } catch (IOException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        } catch (URISyntaxException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }

    }

    private String getURLcreateUser(String appId) {
        String host = "https://graph.facebook.com/v2.6/";
        String path = appId+"/accounts/test-users";
        return host + path;
    }

    private String getURLdeleteUser(String userId) {
        String host = "https://graph.facebook.com/v2.7/";
        String path = userId;
        return host + path;
    }

    private ArrayList<BasicNameValuePair> getParams() {
        PARAMS = new ArrayList<>();
        PARAMS.add(new BasicNameValuePair("access_token", wikia_production_app_access_token));
        return PARAMS;
    }

    private HttpResponse createTestUser(String appId) throws IOException, URISyntaxException {
            URL url = new URL(getURLcreateUser(appId));
            CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
            HttpPost httpPost = getHttpPost(url);

            if (getParams() != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
            }
            return httpClient.execute(httpPost);
    }

    private HttpResponse deleteTestUser(String userId) throws IOException, URISyntaxException {
        URL url = new URL(getURLdeleteUser(userId));
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
        HttpDelete httpDelete = getHttpDelete(url);
        return httpClient.execute(httpDelete);
    }

    public static HttpDelete getHttpDelete(URL url) throws URISyntaxException {
        return new HttpDelete(
            new URIBuilder((
                new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
                        url.getPath(), url.getQuery(), url.getRef())
            )).addParameter("access_token", wikia_production_app_access_token).build());
    }

    public static HttpPost getHttpPost(URL url) throws URISyntaxException {
        return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
            url.getPath(), url.getQuery(), url.getRef()));
    }
}

