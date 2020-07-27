package com.example.multi.demo;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    @Test
    public void testSynthController() {

        // TODO parameterize it
        ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:9081/mock", String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody(), "OK");
    }

}
