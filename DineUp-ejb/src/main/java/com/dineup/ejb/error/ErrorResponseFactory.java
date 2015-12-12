package com.dineup.ejb.error;

import com.dineup.service.rest.HeaderKeys;
import com.dineup.util.Strings;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringEscapeUtils;

@Singleton
@LocalBean
public class ErrorResponseFactory implements HeaderKeys {
    
    @EJB
    private ErrorDetailResolver errorDetailResolver;
    
    public Response createResponse(Exception ex, String languageCode) {
        ErrorDetail detail = errorDetailResolver.resolveError(ex, languageCode);
        Response.ResponseBuilder builder = Response.status(detail.getStatus());
        builder.header(ERROR, detail.getKey().name());
        if (detail.getMessage() != null) {
            builder.header(ERROR_MESSAGE, StringEscapeUtils.escapeJson(detail.getMessage().getMessage()));
            builder.header(ERROR_DESCRIPTION, StringEscapeUtils.escapeJson(detail.getMessage().getDescription()));
        }
        if (detail.getLocalizedMessage() != null) {
            builder.header(LOCALIZED_ERROR_MESSAGE, StringEscapeUtils.escapeJson(detail.getLocalizedMessage().getMessage()));
            builder.header(LOCALIZED_ERROR_DESCRIPTION, StringEscapeUtils.escapeJson(detail.getLocalizedMessage().getDescription()));
        }
        if (!Strings.isEmptyText(detail.getNote())) {
            builder.header(ERROR_NOTE, detail.getNote());
        }
        return builder.build();
    }
    
}
