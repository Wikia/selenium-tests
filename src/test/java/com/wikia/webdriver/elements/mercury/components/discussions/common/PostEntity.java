package com.wikia.webdriver.elements.mercury.components.discussions.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostEntity {

  private static final String CLASS_ATTRIBUTE = "class";

  private static final String POST_EDITED_BY_CLASS_NAME = "post-edited-by-row";

  @Getter
  @FindBy(css = ".follow-area.is-active")
  private WebElement followingActiveState;

  @Getter(AccessLevel.PACKAGE)
  private final WebElement post;

  public PostEntity(WebElement post) {
    this.post = post;
  }

  public boolean hasOpenGraphAtContentEnd() {
    return null != post.findElement(By.cssSelector(".discussion-content + .og-container"));
  }

  public boolean hasTopNote() {
    return null != findTopNoteElement();
  }

  private WebElement findTopNoteElement() {
    return post.findElement(By.className("top-note"));
  }

  public boolean isLocked() {
    return hasClass("is-locked");
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
    final String idAttribute =
        post.findElement(By.className("discussion-more-options")).getAttribute("id");
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
    return isOnPostDetailsPage()
           ? createDescriptionOnPostDetailsPage()
           : findDescriptionElement().getText();
  }

  private boolean isOnPostDetailsPage() {
    return post.findElements(By.tagName("a")).stream()
        .noneMatch(e -> e.getAttribute(CLASS_ATTRIBUTE).contains("post-details-link"));
  }

  private String createDescriptionOnPostDetailsPage() {
    final String content = post.findElement(By.className("discussion-content")).getText();
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
    return post.findElement(By.cssSelector(".post-details-link.clamp"));
  }

  public String findAuthorId() {
    clickMoreOptions();

    String hrefAttribute = post.findElement(By.cssSelector("a[href^='/d/u']")).getAttribute("href");
    final String authorId = StringUtils.substringAfterLast(hrefAttribute, "/");

    clickMoreOptions();

    return authorId;
  }

  public boolean hasEditedBySection() {
    return post.findElement(By.cssSelector(".discussion-content + div"))
        .getAttribute(CLASS_ATTRIBUTE)
        .contains(POST_EDITED_BY_CLASS_NAME);
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
    post.findElement(By.className("discussion-more-options")).click();
    return MoreOptionsPopOver.fromPostEntity(this);
  }

  public void clickFollow() {
    findFollowArea().click();
  }

  private WebElement findFollowArea() {
    return post.findElement(By.className("follow-area"));
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
