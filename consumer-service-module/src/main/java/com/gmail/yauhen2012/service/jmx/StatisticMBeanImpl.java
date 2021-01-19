package com.gmail.yauhen2012.service.jmx;

import java.util.Map;

import com.gmail.yauhen2012.service.CallStatisticService;
import com.gmail.yauhen2012.service.impl.CallStatisticServiceImpl;
import com.gmail.yauhen2012.service.StatisticMBean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service("StatisticMBean")
@ManagedResource(description = "Application statistic bean", objectName = "com.gmail.yauhen2012.service.jmx:type=StatisticMBeanImpl")
public class StatisticMBeanImpl implements StatisticMBean {

    private final CallStatisticService callStatisticService;

    public StatisticMBeanImpl(CallStatisticService callStatisticService) {this.callStatisticService = callStatisticService;}


    @Override
    @ManagedAttribute(description = "Get statistic map")
    public Map<String, Integer> getStatisticMap() {
        return callStatisticService.getStatisticMap();
    }

}
