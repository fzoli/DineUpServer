package com.dineup.api;

public interface Service {
    public int getClientProtocolVersion();
    public int getServerProtocolVersion();
    public boolean isUpToDate();
}
