package com.pocolifo.restclientframework.request.methods.post.input;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public interface IPostRequestInput {
    byte[] getContents();

    String getContentType();

    default Charset getCharset() {
        return StandardCharsets.UTF_8;
    }
}
