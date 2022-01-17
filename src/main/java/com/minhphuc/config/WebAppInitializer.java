package com.minhphuc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Way1:

// class extend, that is automatically used to configure DispatcherServlet
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // this method is used to defined any other application context that is loaded ( # WebConfig )
    // Ex: WebSecurityConfig.class
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebSecurityConfig.class};
    }

    // this method is used to load Spring application context
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebMVCConfig.class};
    }

    // this method defines the URL pattern served by the DispatcherServlet
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}

// Way 2:

//
//public class WebAppInitializer implements WebApplicationInitializer {
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebConfig.class);
//        ctx.setServletContext(servletContext);
//        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        dynamic.addMapping("/");
//        dynamic.setLoadOnStartup(1);
//    }
//}
