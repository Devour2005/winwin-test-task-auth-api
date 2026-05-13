package com.winwintest.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "data-api")
public class DataApiProperties {

    private String baseUrl = "http://localhost:8081";
    private String internalToken = "";

}
