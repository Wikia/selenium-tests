package com.wikia.webdriver.common.core;

import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class CommonUtils {

  private CommonUtils() {

  }

  /**
   * appends given text to specified file
   */
  public static void setClipboardContents(String content) {
    StringSelection ss = new StringSelection(content);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
  }

  public static void appendTextToFile(String filePath, String textToWrite) {
    try {
      FileWriter newFile = new FileWriter(filePath, true);
      BufferedWriter out = new BufferedWriter(newFile);
      out.write(textToWrite);
      out.newLine();
      out.flush();
      out.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * delete directory by path
   */
  public static void deleteDirectory(String dirName) {
    try {
      FileUtils.deleteDirectory(new File(dirName));
    } catch (IOException e) {
      PageObjectLogging.log("deleteDirectory", e.getMessage(), false);
    }
  }

  /**
   * creates directory based on given path
   */
  public static void createDirectory(String fileName) {
    try {
      new File(fileName).mkdir();
      System.out.println("directory " + fileName + " created");
    } catch (SecurityException e) {
      throw new RuntimeException(e);
    }
  }

  public static String sendPost(String apiUrl, String[][] param) {
    try {
      DefaultHttpClient httpclient = new DefaultHttpClient();
      HttpPost httpPost = new HttpPost(apiUrl);
      List<NameValuePair> paramPairs = new ArrayList<NameValuePair>();

      for (int i = 0; i < param.length; i++) {
        paramPairs.add(new BasicNameValuePair(param[i][0], param[i][1]));
      }
      httpPost.setEntity(new UrlEncodedFormEntity(paramPairs));

      HttpResponse response = null;
      response = httpclient.execute(httpPost);

      HttpEntity entity = response.getEntity();
      return EntityUtils.toString(entity);
    } catch (UnsupportedEncodingException e) {
      PageObjectLogging.log("sendPost", e.getMessage(), false);
      return null;
    } catch (ClientProtocolException e) {
      PageObjectLogging.log("sendPost", e.getMessage(), false);
      return null;
    } catch (IOException e) {
      PageObjectLogging.log("sendPost", e.getMessage(), false);
      return null;
    }
  }
}
