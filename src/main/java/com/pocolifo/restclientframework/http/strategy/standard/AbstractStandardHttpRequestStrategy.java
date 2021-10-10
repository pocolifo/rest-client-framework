package com.pocolifo.restclientframework.http.strategy.standard;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.HttpUtility;
import com.pocolifo.restclientframework.http.strategy.AbstractHttpRequestStrategy;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public abstract class AbstractStandardHttpRequestStrategy extends AbstractHttpRequestStrategy {
    private HttpURLConnection connection;

    public AbstractStandardHttpRequestStrategy(URL url) {
        super(url);
    }

    @Override
    public void request(RestClientFramework framework) throws IOException {
        this.connection = HttpUtility.getConnection(framework, this.url, this.getMethod());

        for (Map.Entry<String, String> e : this.getRequestHeaders().entrySet()) {
            this.connection.addRequestProperty(e.getKey(), e.getValue());
        }

        this.connection.disconnect();

        this.setInputStream(this.connection.getInputStream());
        this.setHeaders(this.connection.getHeaderFields());
    }
}
