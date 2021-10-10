package com.pocolifo.restclientframework.test.test.get;

import com.pocolifo.restclientframework.test.instances.TestClient;
import com.pocolifo.restclientframework.test.request.get.GetJsonRequest;
import com.pocolifo.restclientframework.test.response.get.GetJsonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetJsonTest {
    @Test
    public void main() throws Exception {
        GetJsonRequest json = new GetJsonRequest();
        GetJsonResponse response = json.getResponse(TestClient.INSTANCE).get(0);

        Assertions.assertNotNull(response.title);
    }
}
