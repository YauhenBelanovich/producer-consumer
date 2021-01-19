package com.gmail.yauhen2012.springbootmodule.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.lang.management.ManagementFactory;
import java.util.Map;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.gmail.yauhen2012.service.CallStatisticService;
import com.gmail.yauhen2012.service.StatisticMBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerAPIController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final CallStatisticService callStatisticService;

    public ConsumerAPIController(CallStatisticService callStatisticService) {this.callStatisticService = callStatisticService;}

    @PostMapping
    public String addToStatistic(@RequestBody String ip) throws IOException {
        logger.debug("POST API call consumer method");
        logger.info(ip);
        callStatisticService.setStatistic(ip);
        return "Call successfully";
    }

    @GetMapping
    public Map<String, Integer> getStatistic() throws IOException, MalformedObjectNameException { ;

        logger.debug("GET API statistic method");

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://127.0.0.1:9000/jmxrmi");
        JMXConnector connector = JMXConnectorFactory.connect(url, null);
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        ObjectName objectName = new ObjectName("com.gmail.yauhen2012.service.jmx:type=StatisticMBeanImpl");
        StatisticMBean proxy = JMX.newMBeanProxy(connection, objectName, StatisticMBean.class, true);
        return proxy.getStatisticMap();

    }

}
