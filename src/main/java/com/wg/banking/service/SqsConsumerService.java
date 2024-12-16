package com.wg.banking.service;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import org.springframework.stereotype.Service;

@Service
public class SqsConsumerService {

    public void processMessage(SQSEvent event) {
        for (SQSEvent.SQSMessage message : event.getRecords()) {
            String body = message.getBody();
            System.out.println("Received message: " + body);
        }
    }
}
