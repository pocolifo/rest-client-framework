package com.pocolifo.restclientframework.test.instances;

import com.pocolifo.restclientframework.RestClientFramework;
import com.pocolifo.restclientframework.http.strategy.BaseRequestStrategies;

import java.io.IOException;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestClient extends RestClientFramework {

    public static final TestClient INSTANCE = new TestClient(null);
    public static Path TEMPORARY_DIR = null;

    public TestClient(Proxy proxy) {
        super(proxy, new BaseRequestStrategies());

        try {
            TEMPORARY_DIR = Files.createTempDirectory("rest-client-framework-test_");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
