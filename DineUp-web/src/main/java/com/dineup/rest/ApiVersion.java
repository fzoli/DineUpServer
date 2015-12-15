package com.dineup.rest;

public interface ApiVersion {
    String KEY = "apiVersion";
    String PATTERN = "{apiVersion: v[0-9]+.[0-9]+}";
    String ROOT = "/" + PATTERN;
}
