package com.gmail.yauhen2012.service;

import java.util.Map;

public interface CallStatisticService {

    void setStatistic(String ip);

    Map<String, Integer> getStatisticMap();
}
