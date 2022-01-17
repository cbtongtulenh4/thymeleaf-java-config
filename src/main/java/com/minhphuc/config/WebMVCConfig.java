package com.minhphuc.config;

import com.minhphuc.event.listener.RegistrationListener;
import com.minhphuc.utils.EmailUtil;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import nz.net.ultraq.thymeleaf.layoutdialect.decorators.strategies.GroupingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

// this annotation indicates that a class declares one or more @bean methods
// and may be processed by the Spring container to generate bean definitions
@Configuration
// this annotation enable annotation driven Spring MVC configuration
@EnableWebMvc
// this enables the automatic discovery of beans in the given base package
@ComponentScan(basePackages = "com.minhphuc")

@PropertySource("classpath:application.properties")
public class WebMVCConfig implements WebMvcConfigurer, ApplicationContextAware {

    @Autowired
    private Environment evr;

    @Autowired
    private MessageSource messageSource;

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    // indicate append view for controller
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login");
        registry.addViewController("/home.html");
    }

    @Bean
    @Description("set ViewResolver for html page")
    // indicate page form to display to screen UI
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        // build a custom ArrayUtil, use to get String[]
//        resolver.setViewNames(ArrayUtil.array("*.html"));
        return resolver;
    }

    private ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect(new GroupingStrategy()));
        engine.setTemplateResolver(templateResolver);
        engine.setTemplateEngineMessageSource(messageSource());
        return engine;
    }

    // resource resolution
    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        // storage location of files UI
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        //turn off stored data from requests UI
        resolver.setCacheable(false);
        // The template's setup type is HTML instead of using setSuffix(".html") method
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

    private ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("message_en");
        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/template/**")
                .addResourceLocations("/template/");
        registry.addResourceHandler("/resource/**")
                .addResourceLocations("/", "/resource/");
    }


//    @Bean
//    public LocaleResolver localeResolver() {
//        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return cookieLocaleResolver;
//    }

    @Bean
    public JavaMailSender mailSender(){
        return EmailUtil.constructMailSender();
    }

    @Bean
    // Create this bean in container, to ApplicationEventPublisher call
    public RegistrationListener registrationListener(){
        return new RegistrationListener();
    }

//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//        validator.setValidationMessageSource(messageSource);
//        return validator;
//    }


}
/*
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
    //start Thymeleaf specific configuration
//    @Bean(name ="templateResolver")
//    public SpringResourceTemplateResolver getTemplateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/views/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("XHTML");
//        return templateResolver;
//    }

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }


    private ITemplateResolver getTemplateResolver(){
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        // storage location of files UI
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        //turn off stored data from requests UI
        resolver.setCacheable(false);
        // The template's setup type is HTML instead of using setSuffix(".html") method
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }

//    @Bean(name ="templateEngine")
//    public SpringTemplateEngine getTemplateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(getTemplateResolver());
//        return templateEngine;
//    }

    private ISpringTemplateEngine getTemplateEngine(ITemplateResolver templateResolver){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect(new GroupingStrategy()));
        engine.setTemplateResolver(templateResolver);
//        engine.setTemplateEngineMessageSource(messageSource());
        return engine;
    }

//    @Bean(name="viewResolver")
//    public ThymeleafViewResolver getViewResolver(){
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(getTemplateEngine(getTemplateResolver()));
//        return viewResolver;
//    }

    @Bean
    @Description("set ViewResolver for html page")
    // indicate page form to display to screen UI
    public ViewResolver htmlViewResolver(){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(getTemplateEngine(getTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        // build a custom ArrayUtil, use to get String[]
        resolver.setViewNames(new String[]{"*.html"});
        return resolver;
    }


    //end Thymeleaf specific configuration
    @Bean(name ="messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/i18/blogmsg");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
*/