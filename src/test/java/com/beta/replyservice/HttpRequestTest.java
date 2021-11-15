package com.beta.replyservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void baseShouldReplyMessageEmptyResponse() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/reply",
                String.class)).contains("Message is empty");
    }

    @Test
    public void baseShouldReplyMessageEmptyResponseV2() throws Exception {
        String message = "message";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "v2/reply",
                String.class)).contains("Message is empty");
    }

    @Test
    public void messageShouldConvertToJSONObject() throws Exception {
        String message = "message";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/reply/" + message,
                String.class)).contains(message);
    }

    @Test
    public void messageShouldConvertToJSONObjectAndReverse() throws Exception {
        String rule = "1";
        String string = "message";
        String expected = "egassem";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v2/reply/" + rule + "-" + string,
                String.class)).contains(expected);
    }

    @Test
    public void messageShouldConvertToJSONObjectAndHash() throws Exception {
        String rule = "2";
        String string = "message";
        String expected = "78e73127d8fd5ed64234b7c9a63b3";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v2/reply/" + rule + "-" + string,
                String.class)).contains(expected);
    }

    @Test
    public void ruleShouldNotContainUnexpectedLetters() throws Exception {
        String rule = "3";
        String string = "message";
        String expected = "InvalidInput";
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/v2/reply/" + rule + "-" + string,
                String.class)).contains("Invalid input");
    }
}
