package com.pocolifo.restclientframework.response;

import com.pocolifo.restclientframework.http.HttpUtility;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    private InputStream stream;
    private Map<String, List<String>> headers;

    public byte[] getBytes() {
        return HttpUtility.readInputStreamToBytes(this.getStream(), true);
    }

    public String getString() {
        return HttpUtility.readInputStreamToString(this.getStream(), true);
    }

    public InputStream getStream() {
        return this.stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        Map<String, List<String>> formatted = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (entry.getKey() != null) formatted.put(entry.getKey().toLowerCase(), entry.getValue());
        }

        this.headers = formatted;
    }

    public Map<String, List<String>> getHeaders() {
        return this.headers;
    }
}
