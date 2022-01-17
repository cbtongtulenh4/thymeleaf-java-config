package com.minhphuc.utils;

import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;
import java.util.Properties;
import java.util.ResourceBundle;

//@PropertySource("classpath:")
public class EmailUtil {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    private final static String HOST = resourceBundle.getString("spring.mail.host");
    private final static String PORT = resourceBundle.getString("spring.mail.port");
    private final static String USERNAME = resourceBundle.getString("spring.mail.username");
    private final static String PASSWORD = resourceBundle.getString("spring.mail.password");
    private final static String PROTOCOL = resourceBundle.getString("spring.mail.protocol");
    private final static String TRANSPORT_PROTOCOL = resourceBundle.getString("spring.mail.properties.mail.transport.protocol");
    private final static String SMTPS_AUTH = resourceBundle.getString("spring.mail.properties.mail.smtps.auth");
 //   private final static String SMTPS_STARTTLS_ENABLE = resourceBundle.getString("spring.mail.properties.mail.smtps.starttls.enable");
    private final static String SMTPS_TIMEOUT = resourceBundle.getString("spring.mail.properties.mail.smtps.timeout");
    private final static String SMTP_SOCKET_FACTORY_PORT = resourceBundle.getString("spring.mail.properties.mail.smtp.socketFactory.port");
    private final static String SMTP_SOCKET_FACTORY_CLASS = resourceBundle.getString("spring.mail.properties.mail.smtp.socketFactory.class");



    public static JavaMailSenderImpl constructMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(HOST);
        mailSender.setPort(Integer.parseInt(PORT));
        mailSender.setProtocol(PROTOCOL);

//        Session session = Session.getInstance(prop,
//            new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });

        mailSender.setUsername(USERNAME);
        mailSender.setPassword(PASSWORD);
        mailSender.setJavaMailProperties(constructMailProperties());
        return mailSender;
    }

    private static Properties constructMailProperties(){
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.transport.protocol", TRANSPORT_PROTOCOL);
        javaMailProperties.setProperty("mail.smtps.auth", SMTPS_AUTH);
        javaMailProperties.setProperty("mail.smtp.socketFactory.port", SMTP_SOCKET_FACTORY_PORT);
        javaMailProperties.setProperty("mail.smtp.socketFactory.class", SMTP_SOCKET_FACTORY_CLASS);
        javaMailProperties.setProperty("mail.smtps.timeout", SMTPS_TIMEOUT);
        return javaMailProperties;
    }


}
