package com.minhphuc.event;

import com.minhphuc.web.dto.UserDto;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

// LEARN MORE: https://www.baeldung.com/spring-events, https://howtodoinjava.com/spring-core/how-to-publish-and-listen-application-events-in-spring/
// This class is just a placeholder to store the event data

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserDto user;


    public OnRegistrationCompleteEvent(final String appUrl, final Locale locale, final UserDto user){
        super(user);

        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
