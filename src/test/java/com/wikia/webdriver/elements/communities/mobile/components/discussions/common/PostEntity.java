package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

import com.wikia.webdriver.common.logging.Log;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostEntity {

  private static final String CLASS_ATTRIBUTE = "class";

  private static final String POST_EDITED_BY_CLASS_NAME = "post-edited-by-row";
  @Getter(AccessLevel.PACKAGE)
  private final WebElement post;
  @Getter
  @FindBy(css = ".follow-area.is-active")
  private WebElement followingActiveState;

  public PostEntity(WebElement post) {
    this.post = post;
  }

  public boolean hasOpenGraph() {
    return !post.findElements(By.cssSelector(".og-container")).isEmpty();
  }

  public boolean hasTopNote() {
    return null != findTopNoteElement();
  }

  public boolean hasImage() {
    return !post.findElements(By.cssSelector(".post-image-inner-image")).isEmpty();
  }

  public boolean hasPoll() {
    return !post.findElements(By.cssSelector(".poll-wrapper")).isEmpty();
  }

  private WebElement findTopNoteElement() {
    return post.findElement(By.className("top-note"));
  }

  public boolean isLocked() {
    return hasClass("is-locked");
  }

  public boolean isNotLocked() {
    return !isLocked();
  }

  public boolean isReported() {
    return hasClass("is-reported");
  }

  private boolean hasClass(final String className) {
    return post.getAttribute(CLASS_ATTRIBUTE).contains(className);
  }

  public boolean isDeleted() {
    return hasClass("is-deleted");
  }

  public TopNote findTopNote() {
    return new TopNote(findTopNoteElement());
  }

  public String findId() {
    // The fact that whole remote data creation depends on ID from CSS element's attribute value is unbelievable.
    // I'm just here editing selector. Do not blame me for that hell.
    final String idAttribute = post.findElement(By.className("post-card__header")).getAttribute("id");
    return StringUtils.substringAfterLast(idAttribute, "-");
  }

  public String findTimestamp() {
    return post.findElement(By.className("timestamp")).getText();
  }

  public String findTitle() {
    List<WebElement> elements = post.findElements(By.className("post-title"));
    return elements.isEmpty() ? "" : elements.get(0).getText();
  }

  public String findDescription() {
    return isOnPostDetailsPage() ? createDescriptionOnPostDetailsPage()
                                 : findDescriptionElement().getText();
  }

  private boolean isOnPostDetailsPage() {
    return post.findElements(By.tagName("a"))
        .stream()
        .noneMatch(e -> e.getAttribute(CLASS_ATTRIBUTE).contains("post-details-link"));
  }

  private String createDescriptionOnPostDetailsPage() {
    final String content = post.findElement(By.className("post-content")).getText();
    return StringUtils.remove(content, findTitle());
  }

  public String findCategory() {
    return post.findElement(By.className("post-category-name")).getText();
  }

  public String findLinkToPostDetails() {
    return isOnPostDetailsPage() ? StringUtils.EMPTY
                                 : findDescriptionElement().getAttribute("href");
  }

  private WebElement findDescriptionElement() {
    return post.findElement(By.cssSelector(".post-content"));
  }

  public String findAuthorId() {
    clickMoreOptions();

    String hrefAttribute = post.findElement(By.cssSelector("a[href^='/d/u']")).getAttribute("href");
    final String authorId = StringUtils.substringAfterLast(hrefAttribute, "/");

    clickMoreOptions();

    return authorId;
  }

  public boolean hasEditedBySection() {
    try {
      return post.findElement(By.cssSelector(".post-edited-by-row"))
          .getAttribute(CLASS_ATTRIBUTE)
          .contains(POST_EDITED_BY_CLASS_NAME);
    } catch (NoSuchElementException e) {
      Log.log("Element not found", "Edited by section not found", true);
      return false;
    }
  }

  public String getEditedBySectionText() {
    return post.findElement(By.className(POST_EDITED_BY_CLASS_NAME)).getText();
  }

  public void click() {
    if (!isOnPostDetailsPage()) {
      findDescriptionElement().click();
    }
  }

  public MoreOptionsPopOver clickMoreOptions() {
    post.findElement(By.className("options-menu")).click();
    return MoreOptionsPopOver.fromPostEntity(this);
  }

  public PostEntity clickFollow() {
    findFollowArea().click();
    sleepForTwoSeconds();
    return this;
  }

  @SneakyThrows(InterruptedException.class)
  private void sleepForTwoSeconds() {
    TimeUnit.SECONDS.sleep(2);
  }

  private WebElement findFollowArea() {
    return post.findElement(By.className("post-actions__follow"));
  }

  public boolean isFollowed() {
    return findFollowArea().getAttribute("class").contains("is-active");
  }

  public WebElement getWebElement() {
    return post;
  }

  public Data toData() {
    return Data.builder()
        .id(findId())
        .authorId(findAuthorId())
        .category(findCategory())
        .title(findTitle())
        .description(findDescription())
        .build();
  }

  @Builder
  @lombok.Data
  public static class Data {

    private final String id;
    private final String authorId;
    private final String category;
    private final String title;
    private final String description;
    private final String firstPostId;
  }
}
