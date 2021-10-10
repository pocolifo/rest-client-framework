package com.pocolifo.restclientframework.test.test.get;

import com.pocolifo.restclientframework.test.instances.TestClient;
import com.pocolifo.restclientframework.test.request.get.GetHeaderRequest;
import com.pocolifo.restclientframework.test.response.get.GetHeaderResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetHeaderTest {
    @Test
    public void main() throws Exception {
        GetHeaderRequest request = new GetHeaderRequest();
        GetHeaderResponse response = request.getResponse(TestClient.INSTANCE);

        Assertions.assertNotNull(response.rateLimitRemaining);
    }
}
