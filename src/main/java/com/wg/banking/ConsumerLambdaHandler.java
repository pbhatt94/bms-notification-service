package com.wg.banking;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.wg.banking.service.SqsConsumerService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerLambdaHandler implements RequestHandler<SQSEvent, String> {
    private final SqsConsumerService sqsConsumerService;

    public ConsumerLambdaHandler(SqsConsumerService sqsConsumerService) {
        this.sqsConsumerService = sqsConsumerService;
    }

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        System.out.println("EVENT: " + event);
        System.out.println("Context: " + context);
        sqsConsumerService.processMessage(event);
//        for (SQSEvent.SQSMessage message : event.getRecords()) {
//            String body = message.getBody();
//            System.out.println("Received message: " + body);
//        }
        return "Processed " + event.getRecords().size() + " messages.";
    }
}