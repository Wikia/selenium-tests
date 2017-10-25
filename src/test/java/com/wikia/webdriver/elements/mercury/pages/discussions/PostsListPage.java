package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.google.common.base.Predicate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.*;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoriesFieldset;
import com.wikia.webdriver.elements.mercury.components.discussions.common.contribution.ContributionEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.PostCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.PostCreatorMobile;
import java.net.URL;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

public class PostsListPage extends PageWithPosts {

  public static final String PATH = "/d/f";

  @Getter
  @FindBy(css = ".discussion-inline-editor-textarea-wrapper")
  private WebElement newPostArea;

  @Getter(lazy = true)
  private final PostCreatorDesktop postsCreatorDesktop = new PostCreatorDesktop();

  @Getter(lazy = true)
  private final SignInToFollowModalDialog signInDialog = new SignInToFollowModalDialog();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  @Getter(lazy = true)
  private final CategoriesFieldset categories = new CategoriesFieldset();

  @Override
  public PostsListPage open() {
    driver.get(urlBuilder.getUrlForWiki() + PATH);
    waitForPageReload();
    return this;
  }

  public PostCreatorMobile getPostsCreatorMobile() {
    return getDiscussionsHeader().getPostsCreatorMobile();
  }

  public void waitForPageReloadWith(final String categoryName) {
    waitForPageReload();

    changeImplicitWait(0, TimeUnit.SECONDS);
    try {
      new FluentWait<>(getPost())
          .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
          .until((Predicate<Post>) p -> p.getPosts().stream()
              .allMatch(postEntity -> postEntity.findCategory().endsWith(categoryName)));
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public ContributionEditor startPostCreation() {
    return startPostCreationWith(TextGenerator.defaultText());
  }

  public ContributionEditor startPostCreationWith(String description) {
    expandPostCreator()
        .addTitleWith(TextGenerator.defaultText())
        .addTextWith(description)
        .clickAddCategoryButton()
        .selectFirstCategory();
    return getPostsCreatorDesktop();
  }

  public ContributionEditor expandPostCreator() {
    waitAndClick(newPostArea);
    return getPostsCreatorDesktop().closeGuidelinesMessage();
  }

  public ContributionEditor startPostCreationWithLink(URL link) {
    return startPostCreationWith(String.format(" %s ", link.toString()));
  }

  public ContributionEditor startReplyCreationWith(String description) {
    expandPostCreator().addTextWith(description);
    return getPostsCreatorDesktop();
  }

  public ContributionEditor startReplyCreationWithLink(URL link) {
    return startReplyCreationWith(String.format(" %s ", link.toString()));
  }

}
