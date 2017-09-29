package com.wikia.webdriver.elements.mercury.components.discussions.common;

import java.net.URL;

public interface ReplyCreator {

  boolean isPresent();

  ReplyCreator click();

  boolean isModalDialogVisible();

  ReplyCreator clickOkButtonInSignInDialog();

  ReplyCreator clickSignInButtonInSignInDialog();

  ReplyCreator clickGuidelinesReadButton();

  ReplyCreator clearText();

  ReplyCreator add(final String text);

  ReplyCreator addWithLink(final URL url);

  boolean isSubmitButtonActive();

  ReplyCreator clickSubmitButton();
}
