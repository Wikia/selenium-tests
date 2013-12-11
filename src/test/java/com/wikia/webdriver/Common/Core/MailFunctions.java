package com.wikia.webdriver.Common.Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 *
 * @author Karol 'kkarolk' Kujawiak
 *
 */
public class MailFunctions {

	public static String getFirstMailContent(String userName, String password) {
		try {
			//establishing connections
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.googlemail.com", userName, password);
			//getInbox
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_ONLY);
			Message messages[] = inbox.getMessages();

			int counter = 0;
			while (messages.length == 0) {
				Thread.sleep(150);
				messages = inbox.getMessages();
				counter+=1;
				if ((counter % 10) == 0) {
					System.out.println("Checking mail... \r");
				}
				if (counter >2500) {
					break;
				}
			}
			System.out.println("Mail arrived... \r");
			if (messages.length != 0) {
				Message m = messages[0];
				String line;
				StringBuffer buffer = new StringBuffer();
				InputStreamReader in = new InputStreamReader(m.getInputStream());
				BufferedReader reader = new BufferedReader(in);
				while((line = reader.readLine()) != null) {
					buffer.append(line);
				}
				store.close();
				return buffer.toString();
			} else {
				store.close();
				return "no messages";
			}
		}
		catch (NoSuchProviderException e) {
			System.out.println("problems : " + e.getMessage());
			return e.getMessage();
		}
		catch (MessagingException e) {
			System.out.println("problems : " + e.getMessage());
			return e.getMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public static void deleteAllMails(String userName, String password) {
		try {
			//establishing connections
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(false);
			Store store = session.getStore("imaps");
			store.connect("imap.googlemail.com", userName, password);
			//getInbox
			Folder inbox = store.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			Message messages[] = inbox.getMessages();
			if (messages.length != 0) {
				for (int i=0; i< messages.length; i++) {
					messages[i].setFlag(Flags.Flag.DELETED, true);
				}
			} else {
				System.out.println("There is no messages in inbox");
			}
			store.close();
		}
		catch (NoSuchProviderException e) {
			System.out.println("problems : " + e.getMessage());
		}
		catch (MessagingException e) {
			System.out.println("problems : " + e.getMessage());
		}
	}

	public static String getPasswordFromMailContent(String content) {
		content = content.replace("\"","\n");
		String [] lines = content.split("\n");
		return lines[1];
	}

	public static String getActivationLinkFromMailContent(String content) {
		content = content.replace("=0D","\n" );
		String [] lines = content.split("\n");
		return lines[4];
	}
}
