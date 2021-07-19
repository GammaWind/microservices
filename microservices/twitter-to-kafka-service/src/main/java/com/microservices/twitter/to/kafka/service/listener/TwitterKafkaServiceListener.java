package com.microservices.twitter.to.kafka.service.listener;

import org.springframework.stereotype.Component;
import twitter4j.Logger;
import twitter4j.Status;
import twitter4j.StatusAdapter;

@Component
public class TwitterKafkaServiceListener extends StatusAdapter {

    private static final Logger LOG = Logger.getLogger(TwitterKafkaServiceListener.class);


    @Override
    public void onStatus(Status status){
        LOG.info("twitter status with text {}",status.getText());

    }
}
