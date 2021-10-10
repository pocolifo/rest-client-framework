package com.pocolifo.restclientframework.test.test.get;

import com.pocolifo.restclientframework.test.instances.TestClient;
import com.pocolifo.restclientframework.test.request.get.GetJsonExtendsRequest;
import com.pocolifo.restclientframework.test.response.get.GetJsonExtendsResponseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetJsonExtendsResponseTest {
    @Test
    public void main() throws Exception {
        GetJsonExtendsRequest request = new GetJsonExtendsRequest();
        GetJsonExtendsResponseResponse response = request.getResponse(TestClient.INSTANCE).get(0);

        Assertions.assertNotNull(response.id);
    }
}
