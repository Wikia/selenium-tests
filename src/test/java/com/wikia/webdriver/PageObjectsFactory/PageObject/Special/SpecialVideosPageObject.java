package com.wikia.webdriver.PageObjectsFactory.PageObject.Special;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;

public class SpecialVideosPageObject extends SpecialPageObject{

	@FindBy(css = "a.addVideo")
	private WebElement addVideo;
	@FindBy(css = "div.WikiaGrid div:nth-child(1).grid-2")
	private WebElement newestVideo;
	
	public SpecialVideosPageObject(WebDriver driver, String Domain) {
            super(driver);
            PageFactory.initElements(driver, this);
	}

	
	@FindBys(@FindBy(css=".image.video"))
	private List<WebElement> videos;
	
	
	public SpecialVideosPageObject openSpecialVideoPage(){
		getUrl(Domain+"wiki/Special:Videos");
		return new SpecialVideosPageObject(driver, Domain);
	}
	
	public String[] followRandomVideo(){
		
		List<String> hrefs = new ArrayList();
		List<String> names = new ArrayList();
		for (WebElement elem:videos)
		{
			hrefs.add(elem.getAttribute("href"));
			names.add(elem.getAttribute("data-video-name"));
		}
		Random r = new Random();
		int rnd = r.nextInt(hrefs.size()-1);
		String href = hrefs.get((rnd)+1);
		String name = names.get((rnd)+1);
//		getUrl(Global.DOMAIN+href+"?action=watch");
		getUrl(href+"?action=watch");
		clickAndWait(followSubmit);
		waitForElementByElement(followedButton);
		String[] retArr = {href, name};
		return retArr;
	}
	
	public void unfollowVideo(String videoName){
		getUrl(videoName+"?action=unwatch");
		clickAndWait(followSubmit);
		waitForElementByElement(unfollowedButton);
	}

	public VetAddVideoComponentObject clickAddAVideo() {
		waitForElementByElement(addVideo);
		waitForElementClickableByElement(addVideo);
		clickAndWait(addVideo);
		PageObjectLogging.log("clickAddAVideo", "click on 'add a video' button", true, driver);
		return new VetAddVideoComponentObject(driver);
	}

	public void verifyVideoAdded(String videoDescription) {
		waitForElementByElement(newestVideo);
		WebElement videoDescriptonElem = newestVideo.findElement(By.cssSelector("a.image.video"));
		waitForValueToBePresentInElementsAttributeByElement(videoDescriptonElem, "data-video-name", videoDescription);
		PageObjectLogging.log("verifyVideoAdded", "verify that video with following descriotion was added: "+videoDescription, true);	
	}	
}