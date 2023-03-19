package com.pocolifo.restclientframework;

import com.pocolifo.restclientframework.http.strategy.BaseRequestStrategies;
import com.pocolifo.restclientframework.http.strategy.IBaseRequestStrategies;

import java.net.Proxy;

public class RestClientFramework {
    public static final String FRAMEWORK_VERSION = "1.3.2";
    public static final int FRAMEWORK_VERSION_ID = 132;

    public IBaseRequestStrategies strategies;
    public Proxy proxy;

    public RestClientFramework(Proxy proxy, IBaseRequestStrategies strategies) {
        this.proxy = proxy;
        this.strategies = strategies;
    }

    public RestClientFramework() {
        this.proxy = null;
        this.strategies = new BaseRequestStrategies();
    }
}
