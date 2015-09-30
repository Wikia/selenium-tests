package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.AlertHandler;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.componentobject.editprofile.AvatarComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePageObject extends WikiBasePageObject {

  @FindBy(css = "li[data-id='blog'] a")
  private WebElement blogTab;
  @FindBy(css = "a[data-id='createblogpost']")
  private WebElement createBlogPostButton;
  @FindBy(css = ".WikiaBlogListingPost h2>a")
  private List<WebElement> blogPostList;
  @FindBy(css = ".masthead-avatar")
  private WebElement avatarWrapper;
  @FindBy(css = "#userAvatarEdit")
  private WebElement avatarEditButton;
  @FindBy(css = "#UserAvatarRemove")
  private WebElement avatarRemoveButton;

  private By image = By.cssSelector("img");

  private String avatarSelector = ".masthead-avatar > img[src*='/%imageName%']";

  public UserProfilePageObject(WebDriver driver) {
    super(driver);
  }

  public void clickOnBlogTab() {
    wait.forElementVisible(blogTab);
    wait.forElementClickable(blogTab);
    blogTab.click();
    LOG.log("clickOnBlogTab", "Click on blog tab", LOG.Type.SUCCESS);
  }

  public BlogPageObject openBlogPage(int blogNumber) {
    String blogURL = blogPostList.get(blogNumber).getAttribute("href");
    getUrl(blogURL);
    LOG.logResult("openBlogPage",
                  "blog post " + blogURL + " opened",
                  true);
    return new BlogPageObject(driver);
  }

  public BlogPageObject openFirstPost() {
    for (int i = 0; i < blogPostList.size(); i++) {
      BlogPageObject blogPage = openBlogPage(i);
      String pageContent = blogPage.getAtricleTextRaw().toLowerCase();
      if (!(pageContent.contains("deleted") || pageContent.contains("redirected"))) {
        LOG.log("openFirstPost", "valid post found on " + i + " position", LOG.Type.SUCCESS);
        break;
      }
      LOG.logResult("openFirstPost", "deleted post found on " + i
                                     + " position, trying next one", true);
      driver.navigate().back();
    }
    return new BlogPageObject(driver);
  }

  public SpecialCreatePagePageObject clickOnCreateBlogPost() {
    wait.forElementVisible(createBlogPostButton);
    wait.forElementClickable(createBlogPostButton);
    scrollAndClick(createBlogPostButton);
    LOG.log("clickOnCreateBlogPost", "Click on create blog post button",
            true, driver);
    return new SpecialCreatePagePageObject(driver);
  }

  private void showAvatarControls() {
    setDisplayStyle(".avatar-controls", "block");
  }

  private void hideAvatarControls() {
    setDisplayStyle(".avatar-controls", "none");
  }

  public AvatarComponentObject clickEditAvatar() {
    showAvatarControls();
    avatarEditButton.click();
    hideAvatarControls();
    LOG.log("clickEditAvatar", "avatar edit button clicked", LOG.Type.SUCCESS);
    return new AvatarComponentObject(driver);
  }

  public String getAvatarUrl() {
    return avatarWrapper.findElement(image).getAttribute("src");
  }

  public void clickRemoveAvatar() {
    showAvatarControls();
    avatarRemoveButton.click();
    AlertHandler.acceptPopupWindow(driver, 20);
    hideAvatarControls();
    wait.forElementVisible(avatarWrapper);
    LOG.log("clickRemoveAvatar", "avatar remove button clicked", LOG.Type.SUCCESS);
  }

  public void verifyAvatar(String fileName) {
    wait.forElementVisible(By.cssSelector(avatarSelector.replace("%imageName%", fileName)));
	  LOG.log("verifyAvatar", "Desired avatar is visible on user profile page",
                  LOG.Type.SUCCESS);
  }

public void verifyProfilePage(String userName) {
	verifyURLcontains(URLsContent.USER_PROFILE.replace("%userName%", userName), 30);
	LOG.log("verifyProfilePage", userName + " user profile page verified", LOG.Type.SUCCESS);
}

}
