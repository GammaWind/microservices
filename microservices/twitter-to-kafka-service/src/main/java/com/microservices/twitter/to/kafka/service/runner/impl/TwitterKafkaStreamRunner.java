package com.microservices.twitter.to.kafka.service.runner.impl;


import com.microservices.config.TwitterToKafkaServiceConfigData;
import com.microservices.twitter.to.kafka.service.listener.TwitterKafkaServiceListener;
import com.microservices.twitter.to.kafka.service.runner.StreamRunner;
import org.springframework.stereotype.Component;
import twitter4j.*;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
public class TwitterKafkaStreamRunner implements StreamRunner {

    private static final Logger LOG = Logger.getLogger(TwitterKafkaStreamRunner.class);

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final TwitterKafkaServiceListener twitterKafkaServiceListener;
    private TwitterStream twitterStream;

    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData configData, TwitterKafkaServiceListener statusListener) {
        this.twitterToKafkaServiceConfigData = configData;
        this.twitterKafkaServiceListener = statusListener;
    }


    @Override
    public void start() throws TwitterException{

        twitterStream = TwitterStreamFactory.getSingleton();
        twitterStream.addListener(twitterKafkaServiceListener);
        addFilter();


    }
    @PreDestroy
    public void shutDown(){
        if(twitterStream != null){
            LOG.info("stream shutting down");
            twitterStream.shutdown();
        }

    }


    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("started filtering twitter stream for keywords {}" , Arrays.toString(keywords));
    }
}
