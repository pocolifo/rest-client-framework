package com.pocolifo.restclientframework.processor;

import com.pocolifo.restclientframework.request.AbstractRequest;
import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.response.NonJsonResponse;

import java.util.Map;

public class EndpointProcessor {
    public static String getRequestEndpoint(Class<?> c) {
        return c.getAnnotation(Endpoint.class).value();
    }

    public static String getEndpoint(AbstractRequest<?> request) {
        String endpoint = getRequestEndpoint(request.getClass());

        for (Map.Entry<String, String> entry : AnnotationProcessor.getRequestParameters(request).entrySet()) {
            endpoint = endpoint.replaceAll("%" + entry.getKey() + "%", entry.getValue());
        }

        return endpoint;
    }

    public static boolean shouldFormatJson(AbstractRequest<?> request) {
        return !request.getClass().isAnnotationPresent(NonJsonResponse.class);
    }
}
