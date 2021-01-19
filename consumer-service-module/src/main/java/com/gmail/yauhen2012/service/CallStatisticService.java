package com.gmail.yauhen2012.service;

import java.util.Map;

public interface CallStatisticService {

    Boolean setStatistic(String ip);

    Map<String, Integer> getStatisticMap();
}
