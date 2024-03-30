package com.shazahmed.notificationservice.utils;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailUtils {

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     * @param body
     */
    public void sendEmail(Session session, String toEmail, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(
                    new InternetAddress
                    (
                            "project.notificationservice@gmail.com",
                            "NotificationService"
                    )
            );

            msg.setReplyTo
                    (
                            InternetAddress.parse
                                    (
                                            "project.notificationservice@gmail.com",
                                            false
                                    )
                    );

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("E-Mail Sent Successfully !!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
