package com.gmail.yauhen2012;

import com.gmail.yauhen2012.service.CallStatisticService;
import com.gmail.yauhen2012.service.impl.CallStatisticServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CallStatisticServiceTest {

    private CallStatisticService callStatisticService;

    private static final String TEST_IP = "0.0.0.0:123";
    private static final String TEST_DIFFERENT_IP = "0.0.0.0:124";

    @BeforeEach
    public void setup() {
        callStatisticService = new CallStatisticServiceImpl();
    }

    @Test
    public void setStatisticFromIp_returnMap() {

        callStatisticService.setStatistic(TEST_IP);
        callStatisticService.setStatistic(TEST_IP);
        Assertions.assertThat(callStatisticService.getStatisticMap().get(TEST_IP)).isEqualTo(2);
    }

    @Test
    public void setStatisticFromDifferentIP_returnMap() {

        callStatisticService.setStatistic(TEST_IP);
        callStatisticService.setStatistic(TEST_DIFFERENT_IP);
        Assertions.assertThat(callStatisticService.getStatisticMap().keySet().size()).isEqualTo(2);
    }

}
