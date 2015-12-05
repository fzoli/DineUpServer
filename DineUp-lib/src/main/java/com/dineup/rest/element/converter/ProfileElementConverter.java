package com.dineup.rest.element.converter;

import com.dineup.dom.Profile;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.ProfileElement;

public class ProfileElementConverter extends BaseElementConverter<Profile, ProfileElement> {

    public ProfileElementConverter(ElementConfig elementConfig) {
        super(elementConfig);
    }

    @Override
    protected ProfileElement safeConvert(Profile profile) {
        return new ProfileElement(elementConfig, profile);
    }
    
}
