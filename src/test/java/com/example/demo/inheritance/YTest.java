package com.example.demo.inheritance;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("b")
class YTest extends WireMockTestBase {

    @Test
    void test() {
        testSomething();
    }

}
