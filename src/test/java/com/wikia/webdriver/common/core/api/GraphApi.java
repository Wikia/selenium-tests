package com.wikia.webdriver.common.core.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
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

    private String getURL(String app_id) {
        String host = "https://graph.facebook.com/v2.6/";
        String post = app_id+"/accounts/test-users";
        return host + post;
    }

    protected ArrayList<BasicNameValuePair> getParams() {

        PARAMS = new ArrayList<BasicNameValuePair>();
        PARAMS.add(new BasicNameValuePair("access_token", wikia_production_app_access_token));
        return PARAMS;
    }

    public HashMap<String, String> createFacebookTestUser(String app_id) {
        try {
            URL url = new URL(getURL(app_id));
            CloseableHttpClient httpClient = HttpClientBuilder.create().disableAutomaticRetries().build();
            HttpPost httpPost = getHtppPost(url);

            if (getParams() != null) {
                httpPost.setEntity(new UrlEncodedFormEntity(getParams(), StandardCharsets.UTF_8));
            }

            HttpResponse response = httpClient.execute(httpPost);
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

    public static HttpPost getHtppPost(URL url) throws URISyntaxException {
        return new HttpPost(new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
            url.getPath(), url.getQuery(), url.getRef()));
    }
}

