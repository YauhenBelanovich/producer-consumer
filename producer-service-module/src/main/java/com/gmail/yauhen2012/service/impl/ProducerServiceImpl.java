package com.gmail.yauhen2012.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;

import com.gmail.yauhen2012.service.ProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public List<String> getResponseFromConsumer(String callTimes, HttpServletRequest request) {
        String url = getUrlProperty();
        if (url != null) {
            RestTemplate restTemplate = new RestTemplate();
            List<String> resultList = new ArrayList<>();

            int intCallTimes = Integer.parseInt(callTimes);
            String ip = request.getRemoteAddr();

            for (int i = 0; i < intCallTimes; i++) {
                resultList.add((restTemplate.postForEntity(url, ip, String.class)).getBody());
            }
            return resultList;
        }
        return Collections.emptyList();
    }

    private String getUrlProperty() {
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            Properties prop = new Properties();
            if (inputStream == null) {
                logger.error("Sorry, unable to find application.properties");
            }
            prop.load(inputStream);
            return prop.getProperty("consumer.url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.error("Cannot find bot property - " + "consumer.url");
        return null;
    }

}
