package com.gmail.yauhen2012.springbootmodule.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ReadPropertyFile {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public String getHostProperty() {
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            Properties prop = new Properties();
            if (inputStream == null) {
                logger.error("Sorry, unable to find application.properties");
            }
            prop.load(inputStream);
            return prop.getProperty("jmx.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.error("Cannot find bot property - " + "jmx.url");
        return null;
    }

}
