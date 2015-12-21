package com.dineup.api.dom;

import java.util.Date;

public final class TimeInterval {
    
    private final Date startTime;
    private final Date endTime;

    public TimeInterval(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    
}
