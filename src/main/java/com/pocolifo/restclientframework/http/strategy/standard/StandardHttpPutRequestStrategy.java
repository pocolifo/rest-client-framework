package com.pocolifo.restclientframework.http.strategy.standard;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.HttpMethod;
import com.pocolifo.restclientframework.http.HttpUtility;
import com.pocolifo.restclientframework.http.strategy.AbstractHttpRequestStrategy;
import com.pocolifo.restclientframework.request.methods.put.IPutRequestInput;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class StandardHttpPutRequestStrategy extends AbstractHttpRequestStrategy {
    private final byte[] bytes;
    private final String contentType;

    private HttpURLConnection connection;

    public StandardHttpPutRequestStrategy(URL url, IPutRequestInput input) {
        super(url);

        this.contentType = input.getContentType();
        this.bytes = input.getBytes();
    }

    @Override
    public void request(RestClientFramework framework) throws IOException {
        this.connection = HttpUtility.getConnection(framework, this.url, this.getMethod());

        for (Map.Entry<String, String> e : this.getRequestHeaders().entrySet()) {
            this.connection.addRequestProperty(e.getKey(), e.getValue());
        }

        this.connection.setDoOutput(true);
        this.connection.setRequestProperty("Content-Type", this.contentType);

        try (OutputStream out = this.connection.getOutputStream()) {
            out.write(this.bytes);
        }

        this.connection.disconnect();

        this.setInputStream(this.connection.getInputStream());
        this.setHeaders(this.connection.getHeaderFields());
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.PUT;
    }
}
