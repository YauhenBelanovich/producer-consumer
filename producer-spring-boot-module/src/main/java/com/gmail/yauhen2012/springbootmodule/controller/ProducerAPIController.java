package com.gmail.yauhen2012.springbootmodule.controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gmail.yauhen2012.service.ProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerAPIController {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final ProducerService producerService;

    public ProducerAPIController(ProducerService producerService) {this.producerService = producerService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> callConsumer(@RequestParam String callTimes, HttpServletRequest request) throws IOException {

        logger.debug("POST API call consumer method");
        String ip = request.getRemoteAddr();
        return producerService.getResponseFromConsumer(callTimes, ip);
    }

}
