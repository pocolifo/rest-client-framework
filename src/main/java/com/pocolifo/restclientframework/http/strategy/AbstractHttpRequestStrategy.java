package com.pocolifo.restclientframework.http.strategy;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.HttpMethod;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractHttpRequestStrategy {

    private InputStream stream;
    protected final URL url;

    protected Map<String, String> requestHeaders = new HashMap<>();
    protected Map<String, List<String>> headers; // TODO: Pretty sure this is RESPONSE headers, not sure, rename

    public AbstractHttpRequestStrategy(URL url) {
        this.url = url;
    }

    protected void setInputStream(InputStream inputStream) {
        this.stream = inputStream;
    }

    public InputStream getResponseInputStream() {
        return this.stream;
    }

    public abstract void request(RestClientFramework framework) throws IOException;

    public abstract HttpMethod getMethod();

    protected void setHeaders(Map<String, List<String>> headerFields) {
        this.headers = headerFields;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public void addRequestHeader(String key, String value) {
        this.requestHeaders.put(key, value);
    }

    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }
}
