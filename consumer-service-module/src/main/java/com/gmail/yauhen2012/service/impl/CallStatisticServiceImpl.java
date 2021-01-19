package com.gmail.yauhen2012.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class CallStatisticServiceImpl implements com.gmail.yauhen2012.service.CallStatisticService {

    private Map<String, Integer> statisticMap = new HashMap<>();

    @Override
    public void setStatistic(String ip) {
        if (statisticMap.containsKey(ip)) {
            Integer value = statisticMap.get(ip);
            statisticMap.put(ip, value + 1);
        } else {
            statisticMap.put(ip, 1);
        }
    }

    @Override
    public Map<String, Integer> getStatisticMap() {
        return statisticMap;
    }

}
