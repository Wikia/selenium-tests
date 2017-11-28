package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.google.common.base.Function;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoreOptionsPopOver {

  private final PostEntity postEntity;

  private final WebElement post;

  public boolean hasLockPostOption() {
    return hasOption("#wds-icons-lock-small");
  }

  public boolean hasReportPostOption() {
    return hasOption("#wds-icons-alert-small");
  }

  private boolean hasOption(final String href) {
    return post.findElements(By.cssSelector(".more-options-pop-over use")).stream()
        .map(element -> element.getAttribute("xlink:href"))
        .anyMatch(attribute -> attribute.equals(href));
  }

  public MoreOptionsPopOver clickLockPostOption() {
    // should be changed to post.findElement(By.className("<class name>")).click(); after SOC-3791
    clickReportLinkOption("Lock Post");
    waitFor(PostEntity::isLocked);
    return this;
  }

  private void clickReportLinkOption(String linkText) {
    List<WebElement> elements = post.findElements(By.className("report-link"));
    if (elements.size() > 1) {
      WebElement element = elements.get(1);
      if (linkText.equals(element.getText())) {
        elements.get(1).click();
      }
    }
  }

  private void waitFor(final Function<PostEntity, Boolean> predicate) {
    new FluentWait<>(postEntity)
        .withTimeout(DiscussionsConstants.TIMEOUT, TimeUnit.SECONDS)
        .until(predicate);
  }

  public ReportDialog clickReportPostOption() {
    post.findElement(By.className("report-link")).click();
    return new ReportDialog();
  }

  public ShareDialog clickSharePostOption() {
    post.findElement(By.className("share-link")).click();
    return new ShareDialog(post.findElement(By.cssSelector(".discussion-share-dialog .modal-dialog")));
  }

  public MoreOptionsPopOver clickUnlockPostOption() {
    // should be changed to post.findElement(By.className("<class name>")).click(); after SOC-3791
    clickReportLinkOption("Unlock Post");
    waitFor(PostEntity::isLocked);
    return this;
  }

  public UserPostsPage clickViewAllPostsByOption() {
    post.findElement(By.cssSelector("a[href^='/d/u/']")).click();
    return new UserPostsPage();
  }

  static MoreOptionsPopOver fromPostEntity(final PostEntity postEntity) {
    return new MoreOptionsPopOver(postEntity, postEntity.getPost());
  }
}
