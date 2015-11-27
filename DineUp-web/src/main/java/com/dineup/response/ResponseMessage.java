package com.dineup.response;

public class ResponseMessage<Response> {

    private final Result result;
    private final Response response;

    ResponseMessage(Response response) {
        this.result = Result.OK;
        this.response = response;
    }

    ResponseMessage(Result result) {
        this.result = result;
        this.response = null;
    }

    public Result getResult() {
        return result;
    }

    public Response getResponse() {
        return response;
    }

}
