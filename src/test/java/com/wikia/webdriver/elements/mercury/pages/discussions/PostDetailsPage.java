package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Post;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Reply;
import com.wikia.webdriver.elements.mercury.components.discussions.desktop.ReplyCreatorDesktop;
import com.wikia.webdriver.elements.mercury.components.discussions.mobile.ReplyCreatorMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;


public class PostDetailsPage extends WikiBasePageObject implements PageWithPosts {

  @Getter(lazy = true)
  private final Post post = new Post();

  @Getter(lazy = true)
  private final Reply reply = new Reply();

  @Getter(lazy = true)
  private final ReplyCreatorDesktop replyCreatorDesktop = new ReplyCreatorDesktop();

  @Getter(lazy = true)
  private final ReplyCreatorMobile replyCreatorMobile = new ReplyCreatorMobile();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final Pattern PAGE_PATTERN = Pattern.compile("/d/p/\\d+$");

  private static final String PATH = "/d/p/%s";

  private static final String DEFAULT_POST_ID = "2741364719297234368";

  private static final String EMPTY_POST_ID = "4809883";

  public PostDetailsPage open(String postId) {
    getUrl(urlBuilder.getUrlForWiki() + String.format(PATH, postId));
    return this;
  }

  public PostDetailsPage openDefaultPost() {
    return open(DEFAULT_POST_ID);
  }

  public PostDetailsPage openEmptyPost() {
    return open(EMPTY_POST_ID);
  }

  public static boolean is(String url) {
    return PAGE_PATTERN.matcher(url).find();
  }

  public static String extractPostIdFrom(String url) {
    return PostDetailsPage.is(url) ? StringUtils.substringAfterLast(url, "/") : StringUtils.EMPTY;
  }
}
