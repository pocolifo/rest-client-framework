package com.pocolifo.restclientframework.test.response.get;

import com.pocolifo.restclientframework.response.ResponseHeader;

public class GetHeaderExtendsResponseResponse extends GetHeaderResponse {
    @ResponseHeader("x-ratelimit-limit")
    public String rateLimitLimit;
}
