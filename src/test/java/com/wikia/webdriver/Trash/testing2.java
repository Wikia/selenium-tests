package com.wikia.webdriver.Trash;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;

public class testing2 extends TestTemplate{
	
	@Test
	public void CreateNewWiki()
	{
//		driver.get(Global.LIVE_DOMAIN);
////		JavascriptExecutor js = (JavascriptExecutor) driver;
////		
//////		while(js.executeScript("return WikiBuilder.cityId").toString().equals("false"))
//////		{
//////			System.out.println("jest git");
//////		}
//////		
//		
//		
//		for (int i=0; i<100; i++)
//		{
//			System.out.println(i);
//			CommonFunctions.logInCookie(Properties.userName, Properties.password);
//		}
		driver.get(Global.DOMAIN);
		String event = "function fire(event, element){ var evt = document.createEvent(\"HTMLEvents\"); evt.initEvent(event, true, true); }";
		//fire('click', document.body)
	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(event);
//		document.getElementById('wkShrPag')
		js.executeScript("fire('click', document.getElementById('wkShrPag'))");
	}

	

}
