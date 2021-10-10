package com.pocolifo.restclientframework.test.response.get;

import com.pocolifo.restclientframework.response.ResponseHeader;

public class GetHeaderResponse {
    @ResponseHeader("x-ratelimit-remaining")
    public String rateLimitRemaining;
}
