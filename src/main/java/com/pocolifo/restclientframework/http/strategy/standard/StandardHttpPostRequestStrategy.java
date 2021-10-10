package com.pocolifo.restclientframework.http.strategy.standard;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.HttpMethod;
import com.pocolifo.restclientframework.http.HttpUtility;
import com.pocolifo.restclientframework.http.strategy.AbstractHttpRequestStrategy;
import com.pocolifo.restclientframework.request.methods.post.input.IPostRequestInput;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class StandardHttpPostRequestStrategy extends AbstractHttpRequestStrategy {
    private final IPostRequestInput builder;
    private HttpURLConnection connection;

    public StandardHttpPostRequestStrategy(URL url, IPostRequestInput builder) {
        super(url);

        this.builder = builder;
    }

    @Override
    public void request(RestClientFramework framework) throws IOException {
        this.connection = HttpUtility.getConnection(framework, this.url, this.getMethod());

        for (Map.Entry<String, String> e : this.getRequestHeaders().entrySet()) {
            this.connection.addRequestProperty(e.getKey(), e.getValue());
        }

        this.connection.setDoOutput(true);
        this.connection.setRequestProperty("Content-Type", this.builder.getContentType());

        try (OutputStream out = this.connection.getOutputStream()) {
            out.write(this.builder.getContents());
        }

        this.connection.disconnect();

        this.setInputStream(this.connection.getInputStream());
        this.setHeaders(this.connection.getHeaderFields());
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.POST;
    }
}
