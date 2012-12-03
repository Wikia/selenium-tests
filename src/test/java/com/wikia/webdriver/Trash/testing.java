package com.wikia.webdriver.Trash;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjects.PageObject.BasePageObject;
import com.wikia.webdriver.PageObjects.PageObject.WikiPage.WikiArticleEditMode;

public class testing extends TestTemplate
{
	
////	@Test(groups = {"trash"})
////	public void CreateNewWiki()
////	{
//////		driver.get(Global.LIVE_DOMAIN);
//////		Har har1 = server.getHar();
//////		har1.writeTo(new File("c:/b.har"));
////		BasePageObject b = new BasePageObject(driver);
////		driver.get("http://preview.mediawiki119.wikia.com/");
////		CommonFunctions.logIn(Properties.userName, Properties.password);
////		driver.get("http://preview.mediawiki119.wikia.com/wiki/QAarticle1346923033271?action=edit");
////		b.mouseOverInArticleIframe("img");
////		b.waitForElementByCss("span.RTEMediaOverlayEdit");
////		b.click(driver.findElement(By.cssSelector("span.RTEMediaOverlayEdit")));
//////		driver.get("http://wikia.com");
//////		js.executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
//////		js.executeScript("document.querySelectorAll(\".wikia-menu-button\")[0].click()");
//////		driver.findElement(By.cssSelector("#article-comm")).click();
//////		WebElement a = driver.findElement(By.cssSelector("nav[class='wikia-menu-button contribute secondary combined']"));
//////		
//////		for (int i=0; i<3; i++)
//////		{
//////			((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////			((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////			((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////			((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////			try {
//////				Thread.sleep(1000);
//////			} catch (InterruptedException e) {
//////				// TODO Auto-generated catch block
//////				e.printStackTrace();
//////			}
//////		}
//////		((JavascriptExecutor)driver).executeScript("$($($('iframe')[1].contentDocument.body).find('img')).mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$($($('iframe[title*=\"Rich\"]')[0].contentDocument.body).find('img')).mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseenter()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
//////		((JavascriptExecutor)driver).executeScript("$('.Video_Games').mouseleave()");
////
//////		b.executeScript("$('.Video_Games').mouseenter()");
//////		b.executeScript("$('.Video_Games').mouseenter()");
//////		a.click();
//////		b.aaa();
////		
////	}
////	
//	
//////aaaa
//	
////	
////	
////	
////	
////	
////	public void dragdrop(By ByFrom, By ByTo) {
////		WebElement LocatorFrom = driver.findElement(ByFrom);
////		WebElement LocatorTo = driver.findElement(ByTo);
////		String xto=Integer.toString(LocatorTo.getLocation().x);
////		String yto=Integer.toString(LocatorTo.getLocation().y);
////		((JavascriptExecutor)driver).executeScript("function simulate(f,c,d,e){var b,a=null;for(b in eventMatchers)if(eventMatchers[b].test(c)){a=b;break}if(!a)return!1;document.createEvent?(b=document.createEvent(a),a==\"HTMLEvents\"?b.initEvent(c,!0,!0):b.initMouseEvent(c,!0,!0,document.defaultView,0,d,e,d,e,!1,!1,!1,!1,0,null),f.dispatchEvent(b)):(a=document.createEventObject(),a.detail=0,a.screenX=d,a.screenY=e,a.clientX=d,a.clientY=e,a.ctrlKey=!1,a.altKey=!1,a.shiftKey=!1,a.metaKey=!1,a.button=1,f.fireEvent(\"on\"+c,a));return!0} var eventMatchers={HTMLEvents:/^(?:load|unload|abort|error|select|change|submit|reset|focus|blur|resize|scroll)$/,MouseEvents:/^(?:click|dblclick|mouse(?:down|up|over|move|out))$/}; " +
////		"simulate(arguments[0],\"mousedown\",0,0); simulate(arguments[0],\"mousemove\",arguments[1],arguments[2]); simulate(arguments[0],\"mouseup\",arguments[1],arguments[2]); ",
////		LocatorFrom,xto,yto);
////	}
////	
////	
////	@Test
////	
////	public void aaa()
////	{
////		driver.get("http://devfiles.myopera.com/articles/735/example.html");
////		By locA = By.xpath("//li[contains(text(), 'Art Brut')]");
////		By locB = By.xpath("//li[contains(text(), 'Babyshambles')]");
////		dragdrop(locA, locB);
////	}
//	
//	
//	@Test
//	public void aaa()
//	{
//		BasePageObject b = new BasePageObject(driver);
//		driver.get("http://www.facebook.com/");
//		if (b.waitForElementByCss("div#userNavigationLabel"));
//		{
//			WebElement fbdd = driver.findElement(By.cssSelector("div#userNavigationLabel"));
//			b.waitForElementByElement(fbdd);
//			fbdd.click();
//			WebElement fblo = driver.findElement(By.cssSelector("label input[value='Log Out']"));
//			b.waitForElementByElement(fblo);
//			fblo.click();
//			WebElement lonot = driver.findElement(By.cssSelector("input#email"));
//			b.waitForElementByElement(lonot);
//		}
//		driver.get(Global.DOMAIN);
//		Object[] bbb = driver.getWindowHandles().toArray();
//		b.executeScript("$('a.ajaxLogin').mouseenter()");
//		b.waitForElementByCss("a.wikia-button-facebook");
//		b.executeScript("$('a.wikia-button-facebook').click()");
////		b.click(driver.findElement(By.cssSelector("a.wikia-button-facebook")));
//		Object[] aaa = driver.getWindowHandles().toArray();
//		driver.switchTo().window(aaa[1].toString());
//		WebElement em = driver.findElement(By.cssSelector("#email"));
//		WebElement pa = driver.findElement(By.cssSelector("#pass"));
//		WebElement sm = driver.findElement(By.cssSelector("input[name='login']"));
//		b.waitForElementByElement(em);
//		b.waitForElementByElement(pa);
//		b.waitForElementByElement(sm);
//		em.clear();
//		em.sendKeys(Properties.userNameFB);
//		pa.clear();
//		pa.sendKeys(Properties.passwordFB);
//		sm.click();
//		driver.switchTo().window(aaa[0].toString());
//		b.waitForElementByCss("a img.avatar");
//	}
	
	@Test 
	public void numbers()
	{
		WikiArticleEditMode e = new WikiArticleEditMode(driver, Global.DOMAIN, "");
		e.editArticleByName("QAarticle1346923033271");
		e.clickOnSourceButton();
		WebElement el = driver.findElement(By.cssSelector(".cke_source"));
		el.clear();
		el.sendKeys("aaa");
	}
		
//		BasePageObject b = new BasePageObject(driver);
//		driver.get("http://mediawiki119.wikia.com/wiki/Formatting");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		Object aaa = js.executeScript("return document.getElementById('WikiaPageHeader')");
	}
	
	