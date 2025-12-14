package com.AureloBank.Aurelo_Bank_Service.WebClientConfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${card.network.base-url}")
    private String cardNetworkBaseUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient cardNetworkWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(cardNetworkBaseUrl)
                .build();
    }
}
