package com.dineup.rest.element.converter;

import com.dineup.rest.ElementContext;
import com.dineup.dom.Profile;
import com.dineup.rest.ElementConfig;
import com.dineup.rest.element.ProfileElement;

public class ProfileElementConverter extends BaseElementConverter<Profile, ProfileElement> {

    public ProfileElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected ProfileElement safeConvert(Profile profile) {
        return new ProfileElement(elementContext, elementConfig, profile);
    }
    
}
