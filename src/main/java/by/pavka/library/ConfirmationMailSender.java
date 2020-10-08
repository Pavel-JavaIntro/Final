package by.pavka.library;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ConfirmationMailSender {
  public static void main(String[] args) {
    final String mailSender = "pavellibrarytest@gmail.com";
    final String password = "pavellibrarypass";
    String mailReceiver = "pavellibrarytest@gmail.com";


    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSender, password);
              }
            });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(mailSender));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(mailReceiver));
      message.setSubject("Testing Subject");
      message.setText("Dear Mail Crawler,"
              + "\n\n No spam to my email, please!");

      Transport.send(message);

      System.out.println("Done");

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

}

