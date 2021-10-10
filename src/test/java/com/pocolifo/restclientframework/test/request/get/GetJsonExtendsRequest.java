package com.pocolifo.restclientframework.test.request.get;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;
import com.pocolifo.restclientframework.test.response.get.GetJsonExtendsResponseResponse;

import java.util.List;

@Endpoint("https://jsonplaceholder.typicode.com/posts")
public class GetJsonExtendsRequest extends GetRequest<List<GetJsonExtendsResponseResponse>> {}
