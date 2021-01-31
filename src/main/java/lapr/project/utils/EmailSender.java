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

/**
 * Email Sender.
 *
 * Group: Team Lisa [G-021]
 * ______________________________________________________
 * @author António Barbosa <1190404@isep.ipp.pt>
 * @author Ernesto Rodrigues <1190560@isep.ipp.pt>
 * @author Jessica Alves <1190682@isep.ipp.pt>
 * @author Pedro Santos <1190967@isep.ipp.pt>
 * @author Rodrigo Costa <1191014@isep.ipp.pt>
 * @author Tiago Costa <1191460@isep.ipp.pt>
 */

public class EmailSender {

    /**
     * Logger which is used to generate warnings or information, with
     * a custom message.
     */
    private static final Logger LOGGER = Logger.getLogger(EmailSender.class.getName());

    /**
     * An empty constructor of Email Sender.
     */
    private EmailSender() {
    }

    /**
     * The Method sends a email to an eletronic mail passed by parameter.
     * @param emailToFoward Eletronic Mail.
     * @param subject Email's Subject.
     * @param body Email's Body.
     * @return true if the Email is sent. False if otherwise.
     */
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
                        return new PasswordAuthentication("g21.teamlisa@gmail.com", "g21rumoAo20");
                    }
                });
        try {
            if(body.isEmpty()){throw new MessagingException();}


            String strBody = String.format("______________________________________________________________________________________\n" +
                    "%s\n\n______________________________________________________________________________________\n\n" +
                    "Thank you for choosing us.\nKing regards,\nPharmacy Service G21.",body);
            String strHtmlBody = htmlBody(strBody);
            if(emailToFoward.equals("") || subject.equals("")) {
                throw new MessagingException();
            }
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("FarmacyService"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToFoward));
            message.setSubject(subject);
            message.setContent(strHtmlBody, Constants.EMAIL_BODY_TYPE);
            Transport.send(message);
            LOGGER.log(Level.INFO, "Email Sent!");
            return true;
        } catch (MessagingException e) {
            LOGGER.log(Level.WARNING, "Error sending the email.");
        }
        return false;
    }

    /**
     * HTML Body.
     * @param body Body.
     * @return HTML Body.
     */
    public static String htmlBody(String body) {
        return body.replace("\n", "<br />");
    }
}