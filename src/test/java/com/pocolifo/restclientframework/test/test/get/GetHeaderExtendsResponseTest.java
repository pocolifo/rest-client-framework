package com.pocolifo.restclientframework.test.test.get;

import com.pocolifo.restclientframework.test.instances.TestClient;
import com.pocolifo.restclientframework.test.request.get.GetHeaderExtendsRequest;
import com.pocolifo.restclientframework.test.response.get.GetHeaderExtendsResponseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetHeaderExtendsResponseTest {
    @Test
    public void main() throws Exception {
        GetHeaderExtendsRequest request = new GetHeaderExtendsRequest();
        GetHeaderExtendsResponseResponse response = request.getResponse(TestClient.INSTANCE);

        Assertions.assertNotNull(response.rateLimitRemaining, response.rateLimitLimit);
    }
}
