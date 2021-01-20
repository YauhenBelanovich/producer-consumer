package com.gmail.yauhen2012.service.impl;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gmail.yauhen2012.service.ProducerService;
import com.gmail.yauhen2012.service.util.ReadPropertyFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducerServiceImpl implements ProducerService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ReadPropertyFile readPropertyFile;

    public ProducerServiceImpl(ReadPropertyFile readPropertyFile) {this.readPropertyFile = readPropertyFile;}

    @Override
    public List<String> getResponseFromConsumer(String callTimes, String ip) {
        String url = readPropertyFile.getConsumerUrlFromPropertyFile();
        if (url != null) {
            RestTemplate restTemplate = new RestTemplate();
            List<String> resultList = new ArrayList<>();

            int intCallTimes = Integer.parseInt(callTimes);

            for (int i = 0; i < intCallTimes; i++) {
                resultList.add((restTemplate.postForEntity(url, ip, String.class)).getBody());
            }
            return resultList;
        }
        logger.error("URL not found");
        return Collections.emptyList();
    }

}
