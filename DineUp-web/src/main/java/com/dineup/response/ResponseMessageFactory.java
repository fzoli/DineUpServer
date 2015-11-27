package com.dineup.response;

public final class ResponseMessageFactory {

    private final ErrorResolver[] errorResolvers;

    public ResponseMessageFactory(ErrorResolver ... errorResolvers) {
        this.errorResolvers = errorResolvers;
    }

    public <Response> ResponseMessage<Response> createMessage(ResponseGenerator<Response> responseGenerator) {
        try {
            Response response = responseGenerator.generateResponse();
            return new ResponseMessage(response);
        }
        catch (Exception exception) {
            return new ResponseMessage(resolveException(exception));
        }
        catch (Throwable error) {
            return new ResponseMessage(resolveError(error));
        }
    }

    private Error resolveException(Exception exception) {
        for (ErrorResolver errorResolver : errorResolvers) {
            if (errorResolver != null) {
                Error error = errorResolver.resolveError(exception);
                if (error != null) {
                    return error;
                }
            }
        }
        return new Error(Result.GENERAL_ERROR, exception);
    }

    private Error resolveError(Throwable error) {
        return new Error(Result.FATAL_ERROR, error);
    }

}
