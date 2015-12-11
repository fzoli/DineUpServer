package com.dineup.rest;

import com.dineup.service.rest.ProfileKeys;
import com.dineup.service.rest.RequestPath;
import com.dineup.dom.Profile;
import com.dineup.ejb.profile.ProfileManager;
import com.dineup.ejb.profile.ProfileManagerFactory;
import java.net.URI;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path(RequestPath.PATH_GOOGLE_PROFILE_PHOTO)
public class GoogleProfilePhotoResource extends BaseResource {
    
    @Inject
    private ProfileManagerFactory profileManagerFactory;
    
    @QueryParam(ProfileKeys.USER_ID)
    private String userId;
    
    @GET
    public Response getResponseByGet() {
        ProfileManager profileManager = profileManagerFactory.createManager(createElementConfig().createProfileDescriptor(Profile.Type.GOOGLE_PLUS));
        try {
            String url = profileManager.resolveProfilePhotoUrl(userId);
            return Response.temporaryRedirect(URI.create(url)).build();
        }
        catch (Exception ex) {
            return Response.serverError().build();
        }
    }
    
}
