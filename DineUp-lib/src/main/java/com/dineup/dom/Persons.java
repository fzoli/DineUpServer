package com.dineup.dom;

import com.dineup.util.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persons {

    private static final int NUMBER_OF_NAME_FIELDS = 3;
    
    private static final FullNameProvider DEFAULT_FULL_NAME_PROVIDER = new EnglishFullNameProvider();
    
    private static final Map<String, FullNameProvider> FULL_NAME_PROVIDERS = new HashMap<String, FullNameProvider>() {{
        put("hu", new HungarianFullNameProvider());
    }};
    
    public static String getFullName(Person.Name name, String languageCode) {
        if (name == null) {
            return null;
        }
        List<String> fields = new ArrayList<>(NUMBER_OF_NAME_FIELDS);
        getFullNameProvider(languageCode).putFullNameFields(fields, name);
        return Strings.concat(fields, " ");
    }
    
    private static FullNameProvider getFullNameProvider(String languageCode) {
        if (languageCode == null) {
            return DEFAULT_FULL_NAME_PROVIDER;
        }
        FullNameProvider fullNameProvider = FULL_NAME_PROVIDERS.get(languageCode.toLowerCase());
        if (fullNameProvider == null) {
            return DEFAULT_FULL_NAME_PROVIDER;
        }
        return fullNameProvider;
    }
    
    private static interface FullNameProvider {
        public void putFullNameFields(List<String> fields, Person.Name name);
    }
    
    private static class EnglishFullNameProvider implements FullNameProvider {
        @Override
        public void putFullNameFields(List<String> fields, Person.Name name) {
            fields.add(name.getFirstName());
            fields.add(name.getMiddleName());
            fields.add(name.getLastName());
        }
    }
    
    private static class HungarianFullNameProvider implements FullNameProvider {
        @Override
        public void putFullNameFields(List<String> fields, Person.Name name) {
            fields.add(name.getLastName());
            fields.add(name.getMiddleName());
            fields.add(name.getFirstName());
        }
    }
    
    private Persons() {
    }
    
}
