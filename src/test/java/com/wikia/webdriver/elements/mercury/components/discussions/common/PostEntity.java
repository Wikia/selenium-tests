package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.annotation.Nullable;

public class PostEntity {

  private final WebElement webElement;

  PostEntity(WebElement webElement) {
    this.webElement = webElement;
  }

  public boolean hasTopNote() {
    return null != findTopNoteElement();
  }

  private WebElement findTopNoteElement() {
    return webElement.findElement(By.className("top-note"));
  }

  public boolean isReported() {
    return hasClass("is-reported");
  }

  private boolean hasClass(final String className) {
    return webElement.getAttribute("class").contains(className);
  }

  public boolean isDeleted() {
    return hasClass("is-deleted");
  }

  public TopNote findTopNote() {
    return new TopNote(findTopNoteElement());
  }

  public String findId() {
    final String idAttribute = webElement.findElement(By.className("discussion-more-options")).getAttribute("id");
    return StringUtils.substringAfterLast(idAttribute, "-");
  }

  public String findTimestamp() {
    return webElement.findElement(By.className("timestamp")).getText();
  }

  public String findTitle() {
    return webElement.findElement(By.className("post-title")).getText();
  }

  public String findDescription() {
    return isOnPostDetailsPage()
        ? createDescriptionOnPostDetailsPage()
        : findDescriptionElement().getText();
  }

  private boolean isOnPostDetailsPage() {
    return Iterables.all(webElement.findElements(By.tagName("a")), new Predicate<WebElement>() {
      @Override
      public boolean apply(@Nullable WebElement e) {
        return !e.getAttribute("class").contains("post-details-link");
      }
    });
  }

  private String createDescriptionOnPostDetailsPage() {
    final String content = webElement.findElement(By.className("discussion-content")).getText();
    return StringUtils.remove(content, findTitle());
  }

  public String findCategory() {
    return webElement.findElement(By.className("post-category-name")).getText();
  }

  public String findLinkToPostDetails() {
    return isOnPostDetailsPage() ? StringUtils.EMPTY : findDescriptionElement().getAttribute("href");
  }

  private WebElement findDescriptionElement() {
    return webElement.findElement(By.className("post-details-link"));
  }

  public String findAuthorId() {
    clickMoreOptions();

    String hrefAttribute = webElement.findElement(By.cssSelector("a[href^='/d/u']")).getAttribute("href");
    final String authorId = StringUtils.substringAfterLast(hrefAttribute, "/");

    clickMoreOptions();

    return authorId;
  }

  public void click() {
    if (!isOnPostDetailsPage()) {
      findDescriptionElement().click();
    }
  }

  public MoreOptionsPopOver clickMoreOptions() {
    webElement.findElement(By.className("discussion-more-options")).click();
    return new MoreOptionsPopOver(webElement);
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

  @lombok.Data
  @Builder
  public static class Data {

    private final String id;

    private final String authorId;

    private final String category;

    private final String title;

    private final String description;
  }
}
