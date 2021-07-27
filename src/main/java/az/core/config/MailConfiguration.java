package az.core.config;

import az.core.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Value("${mail.username}")
    String username;

    @Value("${mail.to}")
    String to;

    @Value("${mail.password}")
    String password;

    @Value("${mail.host}")
    String host;

    @Value("${mail.port}")
    String port;


    public void sendMail(UserDto userDto) {
        try {
            Properties properties = System.getProperties();
            properties.setProperty("mail.smtp.host", host);
            properties.setProperty("mail.smtp.port", port);
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.socketFactory.port", port);
            properties.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail from Java Program");
            message.setText("User id:" + userDto.getId() + "User name" + userDto.getName());
            Transport.send(message);
            System.out.println("Email Sent successfully....");
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
}
