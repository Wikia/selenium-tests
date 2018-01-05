package com.wikia.webdriver.common.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.logging.PageObjectLogging;

public class EmailUtils {

  private static final String EMAIL_LINK_PATTERN = ".*<a[^>]*href=\"(?<url>[^\"]+?)\"[^>]+>%s</a>.*";
  private static final String PASSWORD_RESET_LINK = String.format(EMAIL_LINK_PATTERN, "SET NEW PASSWORD");
  private static final String CONFIRM_EMAIL_LINK = String.format(EMAIL_LINK_PATTERN, "Confirm Now");

  // Pattern.DOTALL flag forces dot in regex to also match line terminators
  private static Pattern getPatternForString(String value) {
    return Pattern.compile(value, Pattern.DOTALL);
  }

  private EmailUtils() {
  }

  public static String getFirstEmailContent(String userName, String password, String subject) {
    try {
      PageObjectLogging.logInfo("Checking emails for " + userName + " that contain '" + subject + "'");

      // establishing connections
      Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");
      Session session = Session.getDefaultInstance(props, null);
      Store store = session.getStore("imaps");
      store.connect("imap.googlemail.com", userName, password);
      // getInbox
      Folder inbox = store.getFolder("Inbox");
      inbox.open(Folder.READ_ONLY);
      Message[] messages = null;

      boolean forgottenPasswordMessageFound = false;
      Message magicMessage = null;
      
      for (int i = 0; !forgottenPasswordMessageFound; i++) {
        messages = inbox.getMessages();

        PageObjectLogging.log("Mail", "Waiting for the message", true);
        Thread.sleep(2000);
        
        for (Message message : messages) {
          if (message.getSubject().contains(subject)) {
            forgottenPasswordMessageFound = true;
            magicMessage = message;
          }
        }
        
        if (i > 15) {
          throw new WebDriverException("Mail timeout exceeded");
        }
      }

      PageObjectLogging.log("Mail", "Mail arrived", true);

      Message m = magicMessage;
      String line;
      StringBuilder builder = new StringBuilder();
      InputStreamReader in = new InputStreamReader(m.getInputStream());
      BufferedReader reader = new BufferedReader(in);
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }
      store.close();

      return builder.toString();
    } catch (NoSuchProviderException e) {
      PageObjectLogging.log("getFirstEmailContent", e, false);
      throw new WebDriverException();
    } catch (MessagingException | IOException | InterruptedException e) {
      PageObjectLogging.log("getFirstEmailContent", e, false);
      throw new WebDriverException();
    }
  }

  public static void deleteAllEmails(String userName, String password) {
    try {
      // establishing connections
      Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");
      Session session = Session.getDefaultInstance(props, null);
      session.setDebug(false);
      Store store = session.getStore("imaps");
      store.connect("imap.googlemail.com", userName, password);
      // getInbox
      Folder inbox = store.getFolder("Inbox");
      inbox.open(Folder.READ_WRITE);
      Message messages[] = inbox.getMessages();
      
      if (messages.length != 0) {
        for (int i = 0; i < messages.length; i++) {
          messages[i].setFlag(Flags.Flag.DELETED, true);
        }
      } else {
        PageObjectLogging.log("Mail", "There are no messages in inbox", true);
      }
      
      store.close();
    } catch (NoSuchProviderException e) {
      PageObjectLogging.log("Mail", e, false);
    } catch (MessagingException e) {
      PageObjectLogging.log("Mail", e, false);
    }
  }

  public static String getActivationLinkFromEmailContent(String mailContent) {
    // mail content contain '=' chars, which has to be removed
    String content = mailContent.replace("=", "");
    // mail content contain 'upn3D' chars, which has to be changed to 'upn='
    content = content.replace("upn3D", "upn=");
    Pattern p = Pattern.compile("button\" href3D\"http[\\s\\S]*?(?=\")"); // getting activation URL
    // from mail content
    Matcher m = p.matcher(content);
    
    if (m.find()) {
      return m.group(0).replace("button\" href3D\"", "");
      // m.group(0) returns first match for the regexp
    } else {
      throw new WebDriverException("There was no match in the following content: \n" + content);
    }
  }

  public static String getPasswordResetLinkFromEmailContent(String mailContent) {
    return getLinkWith(mailContent, PASSWORD_RESET_LINK);
  }

  public static String getConfirmationLinkFromEmailContent(String mailContent) {
    return getLinkWith(mailContent, CONFIRM_EMAIL_LINK);
  }

  private static String getLinkWith(String mailContent, String linkValue) {
        /*
      remove "=" character except when followed by "3D" hex sequence
      and replace "=3D" sequence with a single "=" character
      because of RFC-2045 line breaks and encoding in IMAP
      see: https://tools.ietf.org/html/rfc2045#section-6.7
    */
    String formattedContent = mailContent.replaceAll("=(?!3D)", "").replaceAll("=3D", "=");
    Matcher m = getPatternForString(linkValue).matcher(formattedContent);
    if (m.find()) {
      return m.group("url");
    } else {
      throw new WebDriverException("There was no match in the following content: \n" + formattedContent);
    }
  }

  /**
   * Method generates a new "fake" email, by adding number of "+" characters before "@" symbol,
   * this way, you can trick system that you changed email, but in fact,
   * message will arrive to the same address
   * @param emailAddress
   * @return
   */
  public static String getEmail(String emailAddress) {
    int specialsToAdd = 1 + StringUtils.countMatches(emailAddress, "+") % 3;
    
    return emailAddress.replace("+", "").replace("@",
        new String(new char[specialsToAdd]).replace("\0", "+") + "@");
  }

}
