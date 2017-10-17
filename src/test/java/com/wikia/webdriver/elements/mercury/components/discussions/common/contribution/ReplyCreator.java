package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;

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
