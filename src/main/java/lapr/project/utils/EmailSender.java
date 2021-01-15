/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    public static boolean emailSender(String emailToFoward, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String strHtmlBody = htmlBody(body);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("farmacyservice.g21@gmail.com", "g21rumoAo20");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("FarmacyService"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToFoward));
            message.setSubject(subject);
            message.setContent(strHtmlBody, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("Email Sent!");
            return true;
        } catch (MessagingException e) {
            System.out.println("Error sending the email.");
        }
        return false;
    }


    private static String htmlBody(String body) {
        if(body.equals(null) || body.equals(""))
            return "";
        return body.replace("\n", "<br />");
    }
}
