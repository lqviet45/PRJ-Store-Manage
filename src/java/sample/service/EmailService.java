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
import sample.user.UserDTO;

public class EmailService {

    private final String formSend = "<body style=\"text-align: center;\">\n"
            + "    <p>This is your verify email, please click the button to verify</p>\n"
            + "    <p>The verify link will exprime in <strong>15'</strong></p>\n"
            + "    <a href=\"http://localhost:8080/PRJ301_T2S2_JSP_JSTL/MainController?action=verify&key=[userkey]&email=[useremail]\" \n"
            + "    style=\"display: inline-block; background-color: aqua; width: 100px; text-align: center; text-decoration: none;\n"
            + "    color: black; font-size: 20px; padding: 10px; border-radius: 10px\n"
            + "    \"   \n"
            + "    >Verify</a>\n"
            + "    <p>Thank you</p>\n"
            + "    \n"
            + "\n"
            + "</body>";

    public boolean sendMail(String mailTo, String token) {
        Properties popr = new Properties();
        popr.put("mail.smtp.host", "smtp.gmail.com");
        popr.put("mail.smtp.port", "587");
        popr.put("mail.smtp.auth", "true");
        popr.put("mail.smtp.starttls.enable", "true");

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
            String mailSend = formSend.replace("[userkey]", token);
            mailSend = mailSend.replace("[useremail]", mailTo);
            message.setSubject("Your Email Verify");
            message.setContent(mailSend, "text/html");
            Transport.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
