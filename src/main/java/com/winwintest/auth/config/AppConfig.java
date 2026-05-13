package com.winwintest.auth.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties({JwtProperties.class, DataApiProperties.class})
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestClient dataApiRestClient(DataApiProperties props) {
        return RestClient.builder()
                .baseUrl(props.getBaseUrl().replaceAll("/$", ""))
                .defaultHeader("X-Internal-Token", props.getInternalToken())
                .build();
    }
}
