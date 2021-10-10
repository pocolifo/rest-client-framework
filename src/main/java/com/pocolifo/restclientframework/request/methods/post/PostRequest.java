package com.pocolifo.restclientframework.request.methods.post;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.strategy.AbstractHttpRequestStrategy;
import com.pocolifo.restclientframework.request.AbstractRequest;
import com.pocolifo.restclientframework.request.methods.post.input.IPostRequestInput;
import com.pocolifo.restclientframework.response.Response;

import java.util.Map;

public abstract class PostRequest<T> extends AbstractRequest<T> {
    @Override
    protected AbstractRequest<?> doRequest(RestClientFramework framework, Map<String, String> extraHeaders) throws Exception {
        AbstractHttpRequestStrategy strategy = framework.strategies.POST(this.getEndpoint(), this.getContent());

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

    protected abstract IPostRequestInput getContent();
}
