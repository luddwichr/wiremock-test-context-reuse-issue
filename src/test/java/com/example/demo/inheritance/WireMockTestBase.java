package com.example.demo.inheritance;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {WireMockTestBase.TestConfig.class})
@AutoConfigureWireMock(port = 0)
public abstract class WireMockTestBase {

    @SpringBootConfiguration
    public static class TestConfig {

    }

    @Value("http://localhost:${wiremock.server.port}")
    private String wireMockUrl;

    protected void testSomething() {
        String routeForTest = "/" + getClass().getSimpleName();
        stubFor(WireMock.get(urlPathMatching(routeForTest)).willReturn(ok().withBody("test")));

        String response = new TestRestTemplate().getForEntity(wireMockUrl + routeForTest, String.class).getBody();

        assertThat(response).isEqualTo("test");
    }

}
