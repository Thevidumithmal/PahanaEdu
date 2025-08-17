package com.pahanaedu.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {
    private static final String FROM_EMAIL = "pahanaedulk@gmail.com\n";
    private static final String PASSWORD = "dadb yaoa hjoe vkkb";

    public static void sendEmailWithAttachment(String toEmail, String subject, String messageText, byte[] attachmentData, String fileName) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(messageText);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.setFileName(fileName);
        attachmentPart.setContent(attachmentData, "application/pdf");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);
        Transport.send(message);
    }
}
