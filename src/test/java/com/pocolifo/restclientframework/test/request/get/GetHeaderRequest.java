package com.pocolifo.restclientframework.test.request.get;

import com.pocolifo.restclientframework.request.Endpoint;
import com.pocolifo.restclientframework.request.methods.get.GetRequest;
import com.pocolifo.restclientframework.response.NonJsonResponse;
import com.pocolifo.restclientframework.test.response.get.GetHeaderResponse;

@NonJsonResponse
@Endpoint("https://jsonplaceholder.typicode.com/albums")
public class GetHeaderRequest extends GetRequest<GetHeaderResponse> { }
