package com.microservices.twitter.to.kafka.service;




import com.microservices.twitter.to.kafka.service.init.StreamInitializer;
import com.microservices.twitter.to.kafka.service.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;





@SpringBootApplication
@ComponentScan(basePackages = "com.microservices")

public class TwitterToKafkaService implements CommandLineRunner {

    private  static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaService.class);

    private final StreamRunner streamRunner;
    private final StreamInitializer streamInitializer;

    public TwitterToKafkaService(StreamRunner runner, StreamInitializer streamInitializer) {

        this.streamRunner = runner;
        this.streamInitializer = streamInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaService.class, args);

    }


    @Override
    public void run(String... args) throws Exception {
        LOG.info("app started...");
        streamInitializer.init();
        streamRunner.start();

    }
}
