package com.pocolifo.restclientframework.test.request.get;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;
import com.pocolifo.restclientframework.response.NonJsonResponse;
import com.pocolifo.restclientframework.test.response.get.GetHeaderExtendsResponseResponse;

@NonJsonResponse
@Endpoint("https://jsonplaceholder.typicode.com/todos")
public class GetHeaderExtendsRequest extends GetRequest<GetHeaderExtendsResponseResponse> { }