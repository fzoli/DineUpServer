package com.dineup.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringConcatTest {
    
    public StringConcatTest() {
    }

    @Test
    public void concatNonEmptyTexts() {
        assertEquals("A/B", Strings.concat("/", "A", "B"));
        assertEquals("A/B", Strings.concat("/", "A", "/B"));
        assertEquals("A/B", Strings.concat("/", "A/", "B"));
        assertEquals("A/B", Strings.concat("/", "A/", "/B"));
    }
    
    @Test
    public void concatTexts() {
        assertEquals("A/C", Strings.concat("/", "A", "", "C"));
        assertEquals("A/C", Strings.concat("/", "A", "", "/C"));
        assertEquals("A/C", Strings.concat("/", "A/", "", "C"));
        assertEquals("A/C", Strings.concat("/", "A/", "", "/C"));
    }
    
    @Test
    public void concatTexts2() {
        assertEquals("A", Strings.concat("/", "A", ""));
        assertEquals("A", Strings.concat("/", "A/", ""));
    }
    
}
