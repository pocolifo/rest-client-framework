package com.pocolifo.restclientframework.http.strategy.standard;

import com.pocolifo.restclientframework.http.HttpMethod;

import java.net.URL;

public class StandardHttpHeadRequestStrategy extends AbstractStandardHttpRequestStrategy {

    public StandardHttpHeadRequestStrategy(URL url) {
        super(url);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.HEAD;
    }
}
