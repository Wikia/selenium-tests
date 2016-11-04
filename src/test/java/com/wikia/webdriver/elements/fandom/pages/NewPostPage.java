package com.wikia.webdriver.elements.fandom.pages;

import lombok.Getter;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.fandom.FandomWPPage;
import com.wikia.webdriver.elements.fandom.components.TextEditor;

public class NewPostPage extends FandomWPPage<NewPostPage> {

  @Getter(lazy = true)
  private final TextEditor textEditor = new TextEditor();

  @FindBy(css = "input#title")
  private WebElement postTitileInput;

  @FindBy(css = "input#publish:not(.disabled)")
  private WebElement publishButton;

  @Override
  public NewPostPage open() {
    getUrl(SITE_URL + "wp-admin/post-new.php");

    return this;
  }

  public NewPostPage typeTitle(String title) {
    postTitileInput.sendKeys(title);

    return this;
  }

  public NewPostPage publish() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      PageObjectLogging.log("Wait for publish button to be active", e, true);
    }
    publishButton.click();
    wait.forUrlContains("/post.php");

    return this;
  }
}
