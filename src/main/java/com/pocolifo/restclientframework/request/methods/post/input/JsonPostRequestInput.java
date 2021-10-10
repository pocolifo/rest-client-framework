package com.pocolifo.restclientframework.request.methods.post.input;

import java.nio.charset.Charset;

public class JsonPostRequestInput implements IPostRequestInput {

    private final Charset charset;
    private final byte[] bytes;

    public JsonPostRequestInput(Charset charset, String json) {
        this.charset = charset;
        this.bytes = json.getBytes(charset);
    }

    @Override
    public byte[] getContents() {
        return this.bytes;
    }

    @Override
    public String getContentType() {
        return "application/json; charset=" + this.getCharset().name();
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }
}
