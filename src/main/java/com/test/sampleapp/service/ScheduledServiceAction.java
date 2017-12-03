package com.test.sampleapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;



/**
 * Created by MRomeh
 */
@Service
public class ScheduledServiceAction {
    private static final Logger log = LoggerFactory.getLogger(ScheduledServiceAction.class);


    @Scheduled(initialDelayString = "${initialDelay}", fixedDelayString = "${fixedDelay}")
    public void cleanExpiredRecords() {
        log.debug("Starting the clean up job to clear the expired records");
        // do your scheduled action which is configured based into the above properties
    }

}
