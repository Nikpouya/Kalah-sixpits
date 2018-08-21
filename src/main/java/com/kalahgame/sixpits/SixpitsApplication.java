package com.kalahgame.sixpits;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SixpitsApplication extends SpringBootServletInitializer  {

    private static final Class<SixpitsApplication> applicationClass = SixpitsApplication.class;
    private final static Logger logger = Logger.getLogger(SixpitsApplication.class);
    public static void main(String[] args)
    {
        SpringApplication.run(applicationClass, args);
        logger.info("Application is running.");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
}
