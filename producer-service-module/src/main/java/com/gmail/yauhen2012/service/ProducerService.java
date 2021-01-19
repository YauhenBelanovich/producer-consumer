package com.gmail.yauhen2012.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface ProducerService {

    List<String> getResponseFromConsumer(String callTimes, HttpServletRequest request);
}
