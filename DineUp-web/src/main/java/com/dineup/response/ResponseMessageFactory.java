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
        catch (Error error) {
            return new ResponseMessage(resolveError(error));
        }
    }

    private Result resolveException(Exception exception) {
        for (ErrorResolver errorResolver : errorResolvers) {
            if (errorResolver != null) {
                Result result = errorResolver.resolveError(exception);
                if (result != null) {
                    return result;
                }
            }
        }
        return Result.GENERAL_ERROR;
    }

    private Result resolveError(Error error) {
        return Result.FATAL_ERROR;
    }

}
