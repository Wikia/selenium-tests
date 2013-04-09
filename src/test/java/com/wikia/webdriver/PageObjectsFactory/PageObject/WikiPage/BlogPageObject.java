package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialCreateBlogPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BlogPageObject extends WikiArticlePageObject{

	@FindBy(css="div.author-details")
	private WebElement usernameField;
	@FindBy(css="a[data-id='createblogpost']")
	private WebElement createBlogPostButton;

	By image = By.cssSelector("img");
	By firstSpan = By.cssSelector("span:nth-child(2) a");
	By secondSpan = By.cssSelector("span:nth-child(3)");
	By thirdSpan = By.cssSelector("span:nth-child(4) a");

	public BlogPageObject(WebDriver driver, String Domain, String wikiArticle) {
		super(driver, Domain, wikiArticle);
		PageFactory.initElements(driver, this);
	}

	public void verifyUsernameFieldPresent(String userName) {
		waitForElementByElement(usernameField);
		waitForElementByElement(usernameField.findElement(image));
		waitForValueToBePresentInElementsAttributeByElement(usernameField.findElement(image), "alt", userName);
		waitForTextToBePresentInElementByElement(usernameField.findElement(firstSpan), userName);
		
		waitForElementByElement(usernameField.findElement(secondSpan));
		waitForTextToBePresentInElementByElement(usernameField.findElement(thirdSpan), "User blog:"+userName);
		PageObjectLogging.log("verifyUsernameFieldPresent ", "verify that Username Field and all its element are presesnt", true, driver);			
	}

	public SpecialCreateBlogPageObject editBlog(){
		getUrl(driver.getCurrentUrl()+"?action=edit");
		PageObjectLogging.log("editBlog", "blog is in edit mode now", true, driver);
		return new SpecialCreateBlogPageObject(driver, this.Domain, this.articlename);
	}
	
	public void deleteBlogPost(String postName){
		getUrl(driver.getCurrentUrl() + "?action=delete");
		clickArticleDeleteConfirmationButton(postName);
		waitForElementByXPath("//div[@class='msg' and contains(text(), 'has been deleted.')]");
		PageObjectLogging.log("deleteArticle", "article has been deleted",
				true, driver);
	}

	public void followBlogPage(String userName){
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"&action=watch");
		clickAndWait(followSubmit);
		waitForElementByElement(followedButton);
	}
	
	public void followBlogPostPage(String userName, String postName){
		//  /wiki/User_blog:QATestsUser/blogPost1358842569583?action=unwatch&token=bc863c8bb8a5b9c46d19ea102919bb84%2B%5C
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"/"+postName+"&action=watch");
		clickAndWait(followSubmit);
		waitForElementByElement(followedButton);
	}
	
	public void unfollowBlogPage(String userName){
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"&action=unwatch");
		clickAndWait(followSubmit);
		waitForElementByElement(unfollowedButton);
	}

	public SpecialCreateBlogPageObject clickOnCreateBlogPost() {
	    waitForElementByElement(createBlogPostButton);
	    waitForElementClickableByElement(createBlogPostButton);
	    clickAndWait(createBlogPostButton);
	    PageObjectLogging.log(
		"clickOnCreateBlogPost",
		"Click on create blog post button",
		true
	    );
	    return new SpecialCreateBlogPageObject(driver, Domain, articlename);
	}

}
