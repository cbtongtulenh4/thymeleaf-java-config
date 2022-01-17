package com.minhphuc.web.controller;

import com.minhphuc.event.OnRegistrationCompleteEvent;
import com.minhphuc.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegistrationController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserDto userTemp;

    // LEARN MORE @ModelAttribute: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registrationUserAccount(
            @ModelAttribute("user") @Valid final UserDto userDto,
            final BindingResult result,
            final HttpServletRequest request)
    {
        LOGGER.debug("Registering user account with information: {}", userDto);

        if (result.hasErrors()) {
            return new ModelAndView("redirect:/login?status=0");
        }
        userTemp = userDto;
        try
        {
            final Locale locale = request.getLocale();
            final String appUrl = "http://" + request.getServerName() +
                    ":" + request.getServerPort() + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appUrl, locale, userDto));
        }catch (Exception ex){
            LOGGER.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("redirect:/login?status=1");
    }

}
