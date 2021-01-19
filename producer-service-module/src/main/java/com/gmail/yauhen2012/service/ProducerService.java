package com.gmail.yauhen2012.service;

import java.util.List;

public interface ProducerService {

    List<String> getResponseFromConsumer(String callTimes, String ip);
}
