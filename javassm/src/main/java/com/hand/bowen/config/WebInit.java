package com.hand.bowen.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @description:
 * @author: bowen.wei@hand-china.com
 * @version: 1.0
 * @date: 2019-09-2 10:15
 */
public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMVCConfig.class);
        ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("springmvc", new DispatcherServlet(context));
        servletRegistration.addMapping("/");
        servletRegistration.setLoadOnStartup(1);
    }
}
