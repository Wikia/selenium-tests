package com.wikia.webdriver.Trash;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;



public class main extends TestTemplate{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		Car mercedes = new Car();
////		Car bmw = new Car();
////		
////		mercedes.setNumberOfDoors(5);
////		mercedes.getNumberOfDoors();
////		mercedes.setNumberOfWheels(4);
////		mercedes.getNumberOfWheels();
////		mercedes.setNumberOfWindows(4);
////		mercedes.getNumberOfWindows();
////		mercedes.driveForward();
////		mercedes.driveBackward();
////		
////		
////		bmw.setNumberOfDoors(3);
////		bmw.getNumberOfDoors();
//
//		mercedes.ifelse(0);
		
		Properties.setProperties();
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpPost httpPost = new HttpPost("http://mediawiki119.wikia.com/api.php");
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
		
		nvps.add(new BasicNameValuePair("action", "login"));
		nvps.add(new BasicNameValuePair("format", "xml"));
		nvps.add(new BasicNameValuePair("lgname", Properties.userName));
		nvps.add(new BasicNameValuePair("lgpassword", Properties.password));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		
		HttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String xmlResponse = EntityUtils.toString(entity);
        String[] xmlResponseArr = xmlResponse.split("\"");
        String token = xmlResponseArr[5];
        
   
        List <NameValuePair> nvps2 = new ArrayList<NameValuePair>();
        
        nvps2.add(new BasicNameValuePair("action", "login"));
		nvps2.add(new BasicNameValuePair("format", "xml"));
		nvps2.add(new BasicNameValuePair("lgname", Properties.userName));
		nvps2.add(new BasicNameValuePair("lgpassword", Properties.password));
		nvps2.add(new BasicNameValuePair("lgtoken", token));
		
		httpPost.setEntity(new UrlEncodedFormEntity(nvps2, HTTP.UTF_8));
		
		response = httpclient.execute(httpPost);
        entity = response.getEntity();
        xmlResponse = EntityUtils.toString(entity);
        xmlResponseArr = xmlResponse.split("\"");
        
        File file = new File("."+File.separator+
				"src"+File.separator+
				"test"+File.separator+
				"resources"+File.separator+
				"ChromeDriver"+File.separator+
				"chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		WebDriver d = new ChromeDriver();
        d.get("http://mediawiki119.wikia.com/wiki/");
        
        System.out.println(xmlResponseArr[13]);
        System.out.println(xmlResponseArr[7]);
        System.out.println(xmlResponseArr[5]);
        System.out.println(xmlResponseArr[9]);
        
        Cookie c1 = new Cookie(xmlResponseArr[11]+"_session", xmlResponseArr[13]);
        Cookie c2 = new Cookie(xmlResponseArr[11]+"UserName", xmlResponseArr[7]);
        Cookie c3 = new Cookie(xmlResponseArr[11]+"UserID", xmlResponseArr[5]);
        Cookie c4 = new Cookie(xmlResponseArr[11]+"Token", xmlResponseArr[9]);
//        
        
        d.manage().addCookie(c1);
        d.manage().addCookie(c2);
        d.manage().addCookie(c3);
        d.manage().addCookie(c4);
        
     
        

		
//		api.php ? action=login & lgname=Bob & lgpassword=secret

	}

}
