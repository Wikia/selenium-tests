package com.wikia.webdriver.common.core.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wikia.webdriver.common.logging.PageObjectLogging;
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
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by neptunooo on 04/08/16.
 */
public class GraphApi {

    private static String ERROR_MESSAGE = "Problem with Graph API call used to create new facebook test user";
    private static ArrayList<BasicNameValuePair> PARAMS;
    private static String wikia_production_app_access_token = "112328095453510|uncl945c48bixtm47AWZM64doDQ";
    private static String wikia_preview_app_access_token = "983287518357559|RE3IANMJOSmv04gSFO7hThMWVBI";

    public HashMap<String, String> createFacebookTestUser(String app_id) {
        try {
            HttpResponse response = createTestUser(app_id);
            InputStream body = response.getEntity().getContent();
            String entity = EntityUtils.toString(response.getEntity());
            HashMap<String, String> result =
                new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
            return result;
        }
        catch (IOException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }
        catch (URISyntaxException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }

    }

    public HashMap<String, String> deleteFacebookTestUser(String app_id, String user_id) {
        try {
            HttpResponse response = deleteTestUser(user_id);
            InputStream body = response.getEntity().getContent();
            String entity = EntityUtils.toString(response.getEntity());
            HashMap<String, String> result =
                new Gson().fromJson(entity, new TypeToken<HashMap<String, String>>(){}.getType());
            return result;
        }
        catch (IOException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }
        catch (URISyntaxException e) {
            PageObjectLogging.log("URI_SYNTAX EXCEPTION", ExceptionUtils.getStackTrace(e), false);
            throw new WebDriverException(ERROR_MESSAGE);
        }

    }

    private String getURLcreateUser(String app_id) {
        String host = "https://graph.facebook.com/v2.6/";
        String path = app_id+"/accounts/test-users";
        return host + path;
    }

    private String getURLdeleteUser(String user_id) {
        String host = "https://graph.facebook.com/v2.7/";
        String path = user_id;
        return host + path;
    }

    protected ArrayList<BasicNameValuePair> getParams() {

        PARAMS = new ArrayList<BasicNameValuePair>();
        PARAMS.add(new BasicNameValuePair("access_token", wikia_production_app_access_token));
        return PARAMS;
    }

    private HttpResponse createTestUser(String app_id) throws IOException, URISyntaxException {
            URL url = new URL(getURLcreateUser(app_id));
            CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
            HttpPost httpPost = getHtppPost(url);

            if (getParams() != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
            }
            return httpClient.execute(httpPost);
    }

    private HttpResponse deleteTestUser(String user_id) throws IOException, URISyntaxException {
        URL url = new URL(getURLdeleteUser(user_id));
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
        HttpDelete httpDelete = getHtppDelete(url);
        return httpClient.execute(httpDelete);
    }

    public static HttpDelete getHtppDelete(URL url) throws URISyntaxException {
        return new HttpDelete(
            new URIBuilder((
                new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
                        url.getPath(), url.getQuery(), url.getRef())
            )).addParameter("access_token", wikia_production_app_access_token).build());
    }

    public static HttpPost getHtppPost(URL url) throws URISyntaxException {
        return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
            url.getPath(), url.getQuery(), url.getRef()));
    }
}

