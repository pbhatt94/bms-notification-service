package com.wg.banking.util;

import com.amazonaws.serverless.proxy.model.AwsProxyRequest;

import java.util.Collections;
import java.util.Map;

public class EventAdapter {

    public static AwsProxyRequest createDummyApiGatewayRequest() {
        AwsProxyRequest request = new AwsProxyRequest();

        request.setPathParameters(Collections.emptyMap());
        request.setQueryStringParameters(Collections.emptyMap());

        request.setHttpMethod("GET");
        request.setPath("/ping");

        return request;
    }
}