package com.dineup.response;

public class ResponseMessage<Response> {

    private final Result result;
    private final Response response;
    private final Throwable error;

    ResponseMessage(Response response) {
        this.result = Result.OK;
        this.response = response;
        this.error = null;
    }

    ResponseMessage(Error error) {
        this.error = error.getThrowable();
        this.result = error.getResult();
        this.response = null;
    }

    public Result getResult() {
        return result;
    }

    public Response getResponse() {
        return response;
    }

    public Throwable getError() {
        return error;
    }

}
