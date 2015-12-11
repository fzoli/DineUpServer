package com.dineup.service.element.converter;

import com.dineup.service.ElementContext;
import com.dineup.dom.Profile;
import com.dineup.service.ElementConfig;
import com.dineup.service.element.ProfileElement;

public class ProfileElementConverter extends BaseElementConverter<Profile, ProfileElement> {

    public ProfileElementConverter(ElementContext elementContext, ElementConfig elementConfig) {
        super(elementContext, elementConfig);
    }

    @Override
    protected ProfileElement safeConvert(Profile profile) {
        return new ProfileElement(elementContext, elementConfig, profile);
    }
    
}
