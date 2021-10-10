package com.pocolifo.restclientframework.http.strategy.standard;

import com.pocolifo.restclientframework.http.HttpMethod;

import java.net.URL;

public class StandardHttpGetRequestStrategy extends AbstractStandardHttpRequestStrategy {

    public StandardHttpGetRequestStrategy(URL url) {
        super(url);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}

