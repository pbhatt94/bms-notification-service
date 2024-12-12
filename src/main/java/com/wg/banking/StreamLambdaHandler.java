package com.wg.banking;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.exceptions.InvalidRequestEventException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wg.banking.util.EventAdapter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;

@Log4j2
public class StreamLambdaHandler implements RequestStreamHandler {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
//        try {
//            handler.proxyStream(inputStream, outputStream, context);
//        } catch (Exception e) {
//            log.error("Not an API Gateway request {}", e.getMessage());
//            AwsProxyRequest dummyRequest = EventAdapter.createDummyApiGatewayRequest();
//
//            String dummyRequestJson = convertToJson(dummyRequest);
//            InputStream dummyInputStream = new ByteArrayInputStream(dummyRequestJson.getBytes());
//
//            handler.proxyStream(dummyInputStream, outputStream, context);
//        }
        AwsProxyRequest dummyRequest = EventAdapter.createDummyApiGatewayRequest();

        String dummyRequestJson = convertToJson(dummyRequest);
        InputStream dummyInputStream = new ByteArrayInputStream(dummyRequestJson.getBytes());

        handler.proxyStream(dummyInputStream, outputStream, context);
    }

    private String convertToJson(AwsProxyRequest dummyRequest) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(dummyRequest);
        } catch (IOException e) {
            log.error("Error converting dummy request to JSON", e);
            return "{}";
        }
    }
}