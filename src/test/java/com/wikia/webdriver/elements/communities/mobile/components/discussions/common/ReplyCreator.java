package com.wikia.webdriver.elements.communities.mobile.components.discussions.common;

public interface ReplyCreator {

  boolean isPresent();

  ReplyCreator click();

  boolean isModalDialogVisible();

  ReplyCreator clickOkButtonInSignInDialog();

  ReplyCreator clickSignInButtonInSignInDialog();

  ReplyCreator clickGuidelinesReadButton();

  ReplyCreator clearText();

  ReplyCreator startReplyCreationWith(String text);

  ReplyCreator add(final String text);

  boolean isSubmitButtonActive();

  ReplyCreator clickSubmitButton();
}
