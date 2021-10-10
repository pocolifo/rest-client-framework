package com.pocolifo.restclientframework.http.strategy;

import com.pocolifo.restclientframework.http.strategy.standard.StandardHttpGetRequestStrategy;
import com.pocolifo.restclientframework.http.strategy.standard.StandardHttpHeadRequestStrategy;
import com.pocolifo.restclientframework.http.strategy.standard.StandardHttpPostRequestStrategy;
import com.pocolifo.restclientframework.http.strategy.standard.StandardHttpPutRequestStrategy;
import com.pocolifo.restclientframework.request.methods.post.input.IPostRequestInput;
import com.pocolifo.restclientframework.request.methods.put.IPutRequestInput;

import java.net.URL;

public interface IBaseRequestStrategies {
    default AbstractHttpRequestStrategy GET(URL url) {
        return new StandardHttpGetRequestStrategy(url);
    }

    default AbstractHttpRequestStrategy POST(URL url, IPostRequestInput builder) { return new StandardHttpPostRequestStrategy(url, builder); }

    default AbstractHttpRequestStrategy HEAD(URL url) {
        return new StandardHttpHeadRequestStrategy(url);
    }

    default AbstractHttpRequestStrategy PUT(URL url, IPutRequestInput input) { return new StandardHttpPutRequestStrategy(url, input); }
}
