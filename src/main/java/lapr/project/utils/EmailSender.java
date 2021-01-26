package lapr.project.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final Logger LOGGER = Logger.getLogger(EmailSender.class.getName());

    private EmailSender() {
    }

    public static boolean sendEmail(String emailToFoward, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("farmacyservice.g21@gmail.com", "g21rumoAo20");
                    }
                });
        try {
            if (emailToFoward.equals("")) {
                throw new MessagingException();
            }

            String strBody = String.format("______________________________________________________________________________________\n" +
                    "%s\n\n______________________________________________________________________________________\n\n" +
                    "Thank you for choosing us.\nKing regards,\nPharmacy Service G21.", body);
            String strHtmlBody = htmlBody(strBody);
            if (subject.equals("")) {
                throw new MessagingException();
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("FarmacyService"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToFoward));
            message.setSubject(subject);
            message.setContent(strHtmlBody, Constants.EMAIL_BODY_TYPE);
            //Transport.send(message);
            LOGGER.log(Level.INFO, "Email Sent!");
            return true;
        } catch (MessagingException e) {
            LOGGER.log(Level.WARNING, "Error sending the email.");
        }
        return false;
    }

    private static String htmlBody(String body) {
        return body.replace("\n", "<br />");
    }
}
