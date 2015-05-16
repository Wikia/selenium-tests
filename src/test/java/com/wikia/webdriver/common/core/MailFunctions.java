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

import org.openqa.selenium.WebDriverException;

import com.wikia.webdriver.common.logging.PageObjectLogging;

/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class MailFunctions {

  private MailFunctions() {

  }

  public static String getFirstEmailContent(String userName, String password, String subject) {
    try {
      // establishing connections
      Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");
      Session session = Session.getDefaultInstance(props, null);
      Store store = session.getStore("imaps");
      store.connect("imap.googlemail.com", userName, password);
      // getInbox
      Folder inbox = store.getFolder("Inbox");
      inbox.open(Folder.READ_ONLY);
      Message messages[] = null;

      int counter = 0;
      boolean forgottenPasswordMessageFound = false;
      Message magicMessage = null;
      while (!forgottenPasswordMessageFound) {
        messages= inbox.getMessages();

        System.out.println("Waiting for message ... \r");
        Thread.sleep(2000);
        for (Message message : messages) {
          if (message.getSubject().contains(subject)) {
            forgottenPasswordMessageFound = true;
            magicMessage = message;
          }
          if (counter > 300) {
            throw new WebDriverException("Mail timeout exceeded");
          }
          counter += 1;
        }
      }

      System.out.println("Mail arrived... \r");

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
      PageObjectLogging.log("getFirstEmailContent", e.getMessage(), false);
      return e.getMessage();
    } catch (MessagingException e) {
      PageObjectLogging.log("getFirstEmailContent", e.getMessage(), false);
      return e.getMessage();
    } catch (InterruptedException e) {
      PageObjectLogging.log("getFirstEmailContent", e.getMessage(), false);
      return e.getMessage();
    } catch (IOException e) {
      PageObjectLogging.log("getFirstEmailContent", e.getMessage(), false);
      return e.getMessage();
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
        System.out.println("There is no messages in inbox");
      }
      store.close();
    } catch (NoSuchProviderException e) {
      System.out.println("problems : " + e.getMessage());
    } catch (MessagingException e) {
      System.out.println("problems : " + e.getMessage());
    }
  }

  public static String getActivationLinkFromEmailContent(String mailContent) {
    // mail content contain '=' chars, which has to be removed
    String content = mailContent.replace("=", "");
    Pattern p = Pattern.compile("Special:WikiaConfirmEmail/*\\w{3,}<"); // getting activation URL
                                                                        // from mail content
    Matcher m = p.matcher(content);
    if (m.find()) {
      return m.group(0).substring(0, m.group(0).length() - 1);
      // m.group(0) returns first match for the regexp
      // last character is '<' so has to be removed
    } else {
      throw new RuntimeException("There was no match in the following content: \n" + content);
    }
  }

  public static String getPasswordFromEmailContent(String mailContent) {
    String content = mailContent.replace("\"", "\n");
    String[] lines = content.split("\n");
    return lines[1];
  }
}
