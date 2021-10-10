# Pocolifo's REST Client Framework
Java framework for making REST clients

---

# Here's how easy it is

Here's an example for a GET request in the context of a service status request:
```java
package my.awesome.api.client.request;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;

@Endpoint("https://api.awesome.com")
public class ServiceStatusRequest extends GetRequest<ServiceStatusResponse> {}
```

You would then have a `ServiceStatusResponse` class:
```java
package my.awesome.api.client.response;

import com.google.gson.annotations.SerializedName;
import com.pocolifo.restclientframework.response.ResponseHeader;

public class ServiceStatusResponse {
    
    @SerializedName("version")
    public String version;
    
    @ResponseHeader("server")
    public String apiServerHeader;
    
}
```

A GET request, but with some parameters:
```java
package my.awesome.api.client.request;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;
import com.pocolifo.restclientframework.request.RequestParameter;

@Endpoint("https://api.awesome.com/items/%itemId%")
public class GetItemRequest extends GetRequest<ItemResponse> {
    
    @RequestParameter
    public String itemId;
    
}
```

Then the response template:
```java
package my.awesome.api.client.response;

import com.google.gson.annotations.SerializedName;

public class ItemResponse {
    
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
}
```

---

You can also add headers to the request you make. Note that these variables _must_ be of the `String` type.
```java
package my.awesome.api.client.request;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.response.ResponseHeader;

@Endpoint("https://api.awesome.com/items/put")
public class PutNewItemRequest {
    
    @ResponseHeader("X-Authentication-Method")
    public String authMethod;

    @ResponseHeader("X-Authentication-Key")
    public String authKey;

}

```