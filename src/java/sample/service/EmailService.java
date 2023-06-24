package sample.service;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sample.security.EmailConstants;

public class EmailService {

    public boolean sendMail(String mailTo) {
        Properties popr = new Properties();
        popr.put("mail.smtp.host", "smtp.gmail.com");
        popr.put("mail.smtp.port", "587");
        popr.put("mail.smtp.auth", "true");
        popr.put("mail.smtp.starttls", "true");

        Session session = Session.getInstance(popr, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConstants.FROM, EmailConstants.PASS_WORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailConstants.FROM));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject("Your Email Verify");
            message.setContent("test", "text/html");
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}
