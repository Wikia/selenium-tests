package com.wikia.webdriver.Trash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.swing.RepaintManager;

import org.browsermob.core.har.Har;
import org.browsermob.core.har.HarEntry;
import org.browsermob.core.har.HarNameValuePair;
import org.browsermob.core.har.HarTimings;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonUtils;
import com.wikia.webdriver.Common.Core.ImageComparisonHelper;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Templates.TestTemplate;

public class DiffTest extends TestTemplate {

	@Test
	public void diffTest() {
		FileInputStream input;
		try {
			for (int i = 0; i < 100; i++) {
				driver.manage().window().maximize();
				driver.get("http://harrypotter.wikia.com/wiki/Special:Random");
				String url = driver.getCurrentUrl();
				url = url.substring(url.indexOf("/")+2);
				Thread.sleep(1000);
				hideDynamicContent();
				CommonUtils.captureScreenshot("./logs/diff/" + i + "live.png",
						driver);
				getHarFile("./temp/traffic_live"+i+".har");
				driver.get("http://preview."+url);
				Thread.sleep(1000);
				getHarFile("./temp/traffic_preview"+i+".har");
				hideDynamicContent();
				CommonUtils.captureScreenshot("./logs/diff/" + i
						+ "preview.png", driver);
				File preview = new File("./logs/diff/" + i + "preview.png");
				File live = new File("./logs/diff/" + i + "live.png");
				if (!(preview.length()==live.length())){
					ImageComparisonHelper.compareImages(preview.getCanonicalPath(),
							live.getCanonicalPath(),
							"c:/WebdriverTestsGit/logs/diff/" + i + "output.png", "compare_x64.exe");
					PageObjectLogging.log("images are different", "<a href='diff/"+i+"preview.png'>preview</a><br/>" +
							"<a href='diff/"+i+"live.png'>live</a><br/>" +
							"<a class='diff' href='diff/"+i+"output.png'>diff</a>", false);
					PageObjectLogging.log("live traffic", "<a href='../temp/traffic_live"+i+".har'>traffic</a>", false);
					PageObjectLogging.log("live traffic", "<a href='../temp/traffic_preview"+i+".har'>traffic</a>", false);
				}
				else{
					PageObjectLogging.log("images are the same", "<a href='diff/"+i+"preview.png'>preview</a><br/>" +
							"<a href='diff/"+i+"live.png'>live</a>", true);
					PageObjectLogging.log("live traffic", "<a href='../temp/traffic_live"+i+".har'>traffic</a>", true);
					PageObjectLogging.log("live traffic", "<a href='../temp/traffic_preview"+i+".har'>traffic</a>", true);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void getHarFile(String path){
//		try {
//			Thread.sleep(2500);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		try{
			server.start();
			Har har = server.getHar();
			parseUrlParams(har);
			addTimings(har);
			har.writeTo(new File(path));
			server.stop();
			server.cleanup();
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void browserMobTest(){
		driver.get("http://wikia.com");
		getHarFile("./temp/traffic.har");
	}

	private void addTimings(Har har)
	{
		HarTimings timings;
		for (HarEntry harEntry : har.getLog().getEntries())
		{
			if (harEntry != null)
			{
				timings = harEntry.getTimings();
				if (timings != null)
				{
					if (timings.getDns() == null) timings.setDns((long) 0);
					if (timings.getConnect() == null) timings.setConnect((long) 0);
					if (timings.getBlocked() == null) timings.setBlocked((long) 0);
				}
				else
				{
					System.out.println("WARN Timings are null");
				}
			}
			else
			{
				System.out.println("WARN Timings are null");				
				//harEntry = new HarEntry();
				//System.out.println("WARNING, MAGIC AHEAD: HarEntry was manually created to avoid NullPointers in further tests!");
			}
		}
	}

	private void parseUrlParams(Har har) throws UnsupportedEncodingException
	{
		String url;
		String params;
		String name;
		String value;
		int pos;
		java.util.List<HarNameValuePair> queryString = new ArrayList<HarNameValuePair>();

		int index;

		for (HarEntry harEntry : har.getLog().getEntries())
		{    			
			url = harEntry.getRequest().getUrl();
			index = url.indexOf('?');

			if (index>0)
			{				
				params = URLDecoder.decode(url.substring(index+1), "UTF-8");
				queryString = new ArrayList<HarNameValuePair>();

				for (String param : params.split("&"))
				{
					pos = param.indexOf('=');
					if (pos != -1)
					{
						name = param.substring(0, pos);
						value = param.substring(pos+1, param.length());
					}
					else {
						name = param;
						value = "";

					}
					queryString.add(new HarNameValuePair(name, value));
				}
				harEntry.getRequest().setQueryString(queryString);
			}			
		}
	}
	
	
	private void executeScript(String script, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}
	
	private void hideDynamicContent()
	{
		executeScript("$('.description').hide()", driver);
		executeScript("$('.video-thumbnail').hide()", driver);
		executeScript("$('#video-survey').hide()", driver);
		executeScript("$('.edited-by').hide()", driver);
		executeScript("$('#TOP_BUTTON').hide()", driver);
		executeScript("$('#TOP_LEADERBOARD').hide()", driver);
		executeScript("$('#TOP_RIGHT_BOXAD').hide()", driver);
		executeScript("$('#WIKIA_BAR_BOXAD_1').hide()", driver);
		executeScript("$('#PREFOOTER_LEFT_BOXAD').hide()", driver);
		executeScript("$('#PREFOOTER_RIGHT_BOXAD').hide()", driver);
		executeScript("$('#LEFT_SKYSCRAPER_2').hide()", driver);
		executeScript("$('#LEFT_SKYSCRAPER_3').hide()", driver);
		executeScript("$('#INCONTENT_BOXAD_1').hide()", driver);
		executeScript("$('.tally em').hide()", driver);
		executeScript("$('.carousel .thumbs').hide()", driver);
		executeScript("$('.WikiaSpotlight ').hide()", driver);
		executeScript("$('.RelatedPagesModule a.more').hide()", driver);
		executeScript("$('.RelatedPagesModule img').hide()", driver);
		executeScript("$('.articleSnippet').hide()", driver);
	}
}
