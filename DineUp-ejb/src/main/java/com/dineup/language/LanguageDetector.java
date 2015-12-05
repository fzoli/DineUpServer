package com.dineup.language;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import java.net.URL;

public final class LanguageDetector {

    private static final LanguageDetector instance = new LanguageDetector();

    public static LanguageDetector getInstance() {
        return instance;
    }
    
    public String getLanguageCode(String text) throws Exception {
        initOnce();
        Detector detector = DetectorFactory.create();
        detector.append(text);
        return detector.detect();
    }
    
    private boolean initialized = false;
    private synchronized void initOnce() throws Exception {
        if (initialized) {
            return;
        }
        URL profilesUrl = LanguageDetector.class.getClassLoader().getResource("language-detector-profiles");
        DetectorFactory.loadProfile(profilesUrl.getPath());
        initialized = true;
    }
    
    private LanguageDetector() {
    }
    
}
