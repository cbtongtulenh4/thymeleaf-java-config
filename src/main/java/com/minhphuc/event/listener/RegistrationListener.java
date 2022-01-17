package com.minhphuc.event.listener;

import com.minhphuc.event.OnRegistrationCompleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@PropertySource("classpath:application.properties")
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class.getName());

    @Autowired
    private MessageSource messages;

    @Autowired
    private Environment evr;

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        final String token = UUID.randomUUID().toString();
        try {
            SimpleMailMessage mail = constructEmailMessage(event, token);
            mailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error("Message creation exception: "+e.getMessage());
        }

    }


    private SimpleMailMessage constructEmailMessage(
            final OnRegistrationCompleteEvent event,
            final String token){
        LOGGER.info("OnRegistrationCompleteEvent confirmSocialPosting fired");

        // setting attribute of SimpleEmailMessage class
        final String recipientAddress = event.getUser().getEmail();
        final String subject = "Registration Confirm";
        final String confirmUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        final String message = messages.getMessage(
                "message.regSuccLink",
                null,
                "You registered successfully. To confirm your registration, please click on the below link.",
                event.getLocale()
        );
        // Can use MimeMessage: https://stackoverflow.com/questions/37630451/spring-mvc-custom-event-which-extends-applicationevent-called-twice

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmUrl);
        email.setFrom(evr.getProperty("support.email", "cbtongtulenh2001@gmail.com"));
        return email;
    }


//    public static void main(String[] args){
//        final String token = UUID.randomUUID().toString();
//        final String recipientAddress = "cbtongtulenh4@gmail.com";
//        final String subject = "Registration Confirm";
//        final String confirmUrl = "alo";
//        final String message = "hello";
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + "\r\n" + confirmUrl);
//        email.setFrom("cbtongtulenh2001@gmail.com");
//        EmailUtil.constructMailSender().send(email);
//    }

}
