package com.dineup.servlet;

import com.dineup.response.ResponseMessage;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.servlet.http.HttpServletResponse;

public class JsonMessageStreamer {
    
    public static void writeJson(Gson gson, HttpServletResponse response, ResponseMessage<?> message) throws IOException {
        JsonWriter writer = null;
        try {
            response.setContentType(ContentTypes.JSON);
            writer = new JsonWriter(new OutputStreamWriter(response.getOutputStream(), ContentTypes.ENCODING));
            gson.toJson(message, ResponseMessage.class, writer);
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (Exception ex) {
                    ;
                }
            }
        }
    }

    private JsonMessageStreamer() {
    }
    
}
