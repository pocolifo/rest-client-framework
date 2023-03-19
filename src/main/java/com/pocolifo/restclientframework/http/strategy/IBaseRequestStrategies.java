package com.pocolifo.restclientframework.http.strategy;

import com.pocolifo.restclientframework.request.methods.post.input.IPostRequestInput;
import com.pocolifo.restclientframework.request.methods.put.IPutRequestInput;

import java.net.URL;

public interface IBaseRequestStrategies {
    AbstractHttpRequestStrategy GET(URL url);

    AbstractHttpRequestStrategy POST(URL url, IPostRequestInput builder);

    AbstractHttpRequestStrategy HEAD(URL url);

    AbstractHttpRequestStrategy PUT(URL url, IPutRequestInput input);
}
