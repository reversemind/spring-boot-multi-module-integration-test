package com.example.multi.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Configuration
@NoArgsConstructor
public class RemoteServiceConfiguration implements Serializable {

    @Value("${remote.service.host:localhost}")
    private String host;

    @Value("${remote.service.port:9999}")
    private String port;

}
