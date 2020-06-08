package com.example.demo;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {CTest.TestConfig.class})
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("c")
class CTest {

    @SpringBootConfiguration
    public static class TestConfig {

    }

    @Value("http://localhost:${wiremock.server.port}")
    private String wireMockUrl;

    @Test
    void test() {
        String routeForTest = "/Z";
        stubFor(WireMock.get(urlPathMatching(routeForTest)).willReturn(ok().withBody("test")));

        String response = new TestRestTemplate().getForEntity(wireMockUrl + routeForTest, String.class).getBody();

        assertThat(response).isEqualTo("test");
    }

}
