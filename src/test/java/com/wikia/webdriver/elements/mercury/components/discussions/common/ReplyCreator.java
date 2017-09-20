package com.wikia.webdriver.elements.mercury.components.discussions.common;

public interface ReplyCreator {

  boolean isPresent();

  ReplyCreator click();

  boolean isModalDialogVisible();

  ReplyCreator clickOkButtonInSignInDialog();

  ReplyCreator clickSignInButtonInSignInDialog();

  ReplyCreator clickGuidelinesReadButton();

  ReplyCreator clearText();

  ReplyCreator add(final String text);

  boolean isSubmitButtonActive();

  ReplyCreator clickSubmitButton();
}
