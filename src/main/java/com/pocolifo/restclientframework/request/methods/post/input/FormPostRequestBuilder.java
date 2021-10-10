package com.pocolifo.restclientframework.request.methods.post.input;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class FormPostRequestBuilder implements IPostRequestInput {

    private final Map<String, String> args = new HashMap<>();
    private byte[] bytes;

    private final Charset charset;

    public FormPostRequestBuilder(Charset charset) {
        this.charset = charset;
    }

    public FormPostRequestBuilder addField(String key, String value) {
        this.args.put(key, value);
        return this;
    }

    public FormPostRequestBuilder build() {
        StringBuilder str = new StringBuilder();

        for (Map.Entry<String, String> entry : this.args.entrySet()) {
            try {
                str.append(URLEncoder.encode(entry.getKey(), this.charset.name())).append("=").append(URLEncoder.encode(entry.getValue(), this.charset.name())).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String s = str.toString();
        this.bytes = s.substring(0, s.length() - 1).getBytes(this.charset);

        return this;
    }

    @Override
    public byte[] getContents() {
        return this.bytes;
    }

    @Override
    public String getContentType() {
        return "application/x-www-form-urlencoded; charset=" + this.getCharset().name();
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }
}
