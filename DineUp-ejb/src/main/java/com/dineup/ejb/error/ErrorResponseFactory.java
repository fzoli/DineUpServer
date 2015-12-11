package com.dineup.ejb.error;

import com.dineup.service.rest.HeaderKeys;
import com.dineup.util.Strings;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;

@Singleton
@LocalBean
public class ErrorResponseFactory implements HeaderKeys {
    
    private static final String ENCODING = "utf-8";
    
    @EJB
    private ErrorDetailResolver errorDetailResolver;
    
    public Response createResponse(Exception ex, String languageCode) {
        try {
            ErrorDetail detail = errorDetailResolver.resolveError(ex, languageCode);
            Response.ResponseBuilder builder = Response.status(detail.getStatus());
            builder.header(ERROR, detail.getKey().name());
            if (detail.getMessage() != null) {
                builder.header(ERROR_MESSAGE, URLEncoder.encode(detail.getMessage().getMessage(), ENCODING));
                builder.header(ERROR_DESCRIPTION, URLEncoder.encode(detail.getMessage().getDescription(), ENCODING));
            }
            if (detail.getLocalizedMessage() != null) {
                builder.header(LOCALIZED_ERROR_MESSAGE, URLEncoder.encode(detail.getLocalizedMessage().getMessage(), ENCODING));
                builder.header(LOCALIZED_ERROR_DESCRIPTION, URLEncoder.encode(detail.getLocalizedMessage().getDescription(), ENCODING));
            }
            if (!Strings.isEmptyText(detail.getNote())) {
                builder.header(ERROR_NOTE, detail.getNote());
            }
            return builder.build();
        }
        catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
}
