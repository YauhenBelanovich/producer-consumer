package com.gmail.yauhen2012.springbootmodule;

import com.gmail.yauhen2012.springbootmodule.config.TestAPIConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static com.gmail.yauhen2012.springbootmodule.constant.TestAdminConstant.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestAPIConfig.class)
@ActiveProfiles("test")
public class CityInfoRESTTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void postWithoutParameters_return400() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<>("", httpHeaders);
        ResponseEntity<String> entity = template.withBasicAuth(TEST_ADMIN, TEST_PASSWORD)
                .exchange("/producer", HttpMethod.POST, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }

}
