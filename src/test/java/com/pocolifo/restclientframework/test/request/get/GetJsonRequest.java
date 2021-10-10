package com.pocolifo.restclientframework.test.request.get;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;
import com.pocolifo.restclientframework.test.response.get.GetJsonResponse;

import java.util.List;

@Endpoint("https://jsonplaceholder.typicode.com/todos")
public class GetJsonRequest extends GetRequest<List<GetJsonResponse>> {}
