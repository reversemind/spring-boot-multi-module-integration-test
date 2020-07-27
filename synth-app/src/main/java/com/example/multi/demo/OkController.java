package com.example.multi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@RestController
public class OkController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RemoteServiceConfiguration remoteServiceConfiguration;

    /**
     * Health check
     *
     * @return - "OK"
     */
    @RequestMapping("/")
    public String ok() {
        return "OK";
    }

    /**
     * Call remote service
     *
     * @return - "OK
     */
    @RequestMapping("/mock")
    public String mock() {
        log.debug("Going to call remote service at host:{} and port:{}", remoteServiceConfiguration.getHost(), remoteServiceConfiguration.getPort());

        String remoteServiceEndPoint = "http://" + remoteServiceConfiguration.getHost() + ":" + remoteServiceConfiguration.getPort() + "/mock";
        log.debug("url:'{}'", remoteServiceEndPoint);

        ResponseEntity<String> response
                = restTemplate.getForEntity(remoteServiceEndPoint, String.class);

        if (response.getStatusCode().equals(HttpStatus.OK) && Objects.requireNonNull(response.getBody()).equals("MOCK")) {
            return "OK";
        }

        log.error("Unable to call remote service at host:{} and port:{}", remoteServiceConfiguration.getHost(), remoteServiceConfiguration.getPort());
        throw new RuntimeException("Unable to call a remote service");
    }

}
