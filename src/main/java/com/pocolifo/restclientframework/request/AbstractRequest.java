package com.pocolifo.restclientframework.request;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.processor.AnnotationProcessor;
import com.pocolifo.restclientframework.processor.EndpointProcessor;
import com.pocolifo.restclientframework.processor.ResponseProcessor;
import com.pocolifo.restclientframework.response.BinaryResponse;
import com.pocolifo.restclientframework.response.Response;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

// T = Response class
public abstract class AbstractRequest<T> {
    protected AbstractRequest<?> request;
    protected Response response; // Important that this is null, it is set in subclass

    protected URL getEndpoint() throws MalformedURLException {
        return new URL(EndpointProcessor.getEndpoint(this));
    }

    private boolean noActionTaken() {
        return this.request == null && this.response == null;
    }

    protected void processRequest(RestClientFramework framework) throws Exception {
        if (this.noActionTaken()) {
            this.request = this.doRequest(framework, AnnotationProcessor.getAdditionalHeaders(this));
        }
    }

    @SuppressWarnings("unchecked")
    public T getResponse(RestClientFramework framework) throws Exception {
        this.processRequest(framework);

        // Do not format if BinaryResponse is present
        if (this.getClass().isAnnotationPresent(BinaryResponse.class)) {
            return (T) this.response.getBytes();
        } else {
            return ResponseProcessor.getClassInstance(this.getType(), this, this.response);
        }
    }

    public Type getType() {
        return ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract AbstractRequest<?> doRequest(RestClientFramework framework, Map<String, String> extraHeaders) throws Exception;
    public abstract String formatResponseString(Response response);
}
