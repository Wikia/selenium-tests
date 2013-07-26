package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.Blog;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Blog.SpecialCreateBlogPageObject;

public class BlogPageObject extends ArticlePageObject {

	@FindBy(css="div.author-details")
	private WebElement usernameField;
	@FindBy(css=".WikiaBlogPostHeader h1")
	private WebElement blogHeader;

	By image = By.cssSelector("img");
	By firstSpan = By.cssSelector("span:nth-child(2) a");
	By secondSpan = By.cssSelector("span:nth-child(3)");
	By thirdSpan = By.cssSelector("span:nth-child(4) a");

	public BlogPageObject(WebDriver driver) {
		super(driver);
	}

	public void verifyUsernameFieldPresent(String userName) {
		waitForElementByElement(usernameField);
		waitForElementByElement(usernameField.findElement(image));
		waitForValueToBePresentInElementsAttributeByElement(usernameField.findElement(image), "alt", userName);
		waitForTextToBePresentInElementByElement(usernameField.findElement(firstSpan), userName);
		waitForElementByElement(usernameField.findElement(secondSpan));
		waitForTextToBePresentInElementByElement(usernameField.findElement(thirdSpan), "User blog:"+userName);
		PageObjectLogging.log("verifyUsernameFieldPresent ", "verify that Username Field and all its element are presesnt", true);
	}

	public SpecialCreateBlogPageObject editBlog(){
		getUrl(driver.getCurrentUrl()+"?action=edit");
		PageObjectLogging.log("editBlog", "blog is in edit mode now", true, driver);
		return new SpecialCreateBlogPageObject(driver);
	}

	public void deleteBlogPost(String postName){
		getUrl(driver.getCurrentUrl() + "?action=delete");
		clickArticleDeleteConfirmationButton(postName);
		waitForElementVisibleByElement(userMessage);
		waitForElementByElement(userMessage.findElement(By.cssSelector("a[href*='Special:Undelete']")));
		PageObjectLogging.log(
			"deleteArticle", "article has been deleted",
			true, driver
		);
	}

	public BlogPageObject openBlogPage(String userName){
		getUrl(Global.DOMAIN+"/User_blog:"+userName);
		PageObjectLogging.log("openBlogPage", "blog page opened", true);
		return new BlogPageObject(driver);
	}

	public void followBlogPage(String userName){
		unfollowBlogPage(userName);
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"&action=watch");
		scrollAndClick(followSubmit);
		waitForElementByElement(followedButton);
	}

	public void followBlogPostPage(String userName, String postName){
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"/"+postName+"&action=watch");
		scrollAndClick(followSubmit);
		waitForElementByElement(followedButton);
	}

	public void unfollowBlogPage(String userName){
		getUrl(Global.DOMAIN+"index.php?title=User_blog:"+userName+"&action=unwatch");
		scrollAndClick(followSubmit);
		waitForElementByElement(unfollowedButton);
	}

	public void verifyBlogTitle(String title) {
		waitForElementByElement(blogHeader);
		Assertion.assertEquals(title, blogHeader.getText());
	}


	public String getBlogName() {
		return blogHeader.getText();
	}
}
