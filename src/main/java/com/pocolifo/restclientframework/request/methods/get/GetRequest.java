package com.pocolifo.restclientframework.request.methods.get;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.strategy.AbstractHttpRequestStrategy;
import com.pocolifo.restclientframework.request.AbstractRequest;
import com.pocolifo.restclientframework.response.Response;

import java.util.Map;

public class GetRequest<T> extends AbstractRequest<T> {
    @Override
    protected AbstractRequest<T> doRequest(RestClientFramework framework, Map<String, String> extraHeaders) throws Exception {
        AbstractHttpRequestStrategy strategy = framework.strategies.GET(this.getEndpoint());

        for (Map.Entry<String, String> e : extraHeaders.entrySet()) {
            strategy.addRequestHeader(e.getKey(), e.getValue());
        }

        strategy.request(framework);

        this.response = new Response();
        this.response.setStream(strategy.getResponseInputStream());
        this.response.setHeaders(strategy.getHeaders());

        return this;
    }

    @Override
    public String formatResponseString(Response response) {
        return response.getString();
    }
}
