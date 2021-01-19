package com.gmail.yauhen2012.springbootmodule;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConsumerAPITest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void postStatistic_return201() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>("ip", httpHeaders);
        ResponseEntity<String> entity = template
                .exchange("/consumer", HttpMethod.POST, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    }

}
