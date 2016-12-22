package com.wikia.webdriver.elements.mercury.components.discussions.common;

public interface ReplyCreator {

  boolean isPresent();

  ReplyCreator click();

  boolean isModalDialogVisible();

  ReplyCreator clickOkButtonInSignInDialog();

  ReplyCreator clickSignInButtonInSignInDialog();

  ReplyCreator clickGuidelinesReadButton();

  ReplyCreator add(final String text);

  ReplyCreator clickSubmitButton();
}
