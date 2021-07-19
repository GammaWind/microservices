package com.microservices.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import java.util.List;
@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {
    private List<String> twitterKeywords;
    private  String welcomeMessage;
    private List<String> anotherMessage;


    }


