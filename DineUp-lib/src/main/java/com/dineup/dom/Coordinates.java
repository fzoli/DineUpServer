package com.dineup.dom;

public class Coordinates {

    public static Double getDistanceInMeter(Coordinate coordinateA, Coordinate coordinateB) {
        if (coordinateA == null || coordinateB == null) {
            return null;
        }
        double pk = (double) (180/3.14169);
        double a1 = coordinateA.getLatitude() / pk;
        double a2 = coordinateA.getLongitude() / pk;
        double b1 = coordinateB.getLatitude() / pk;
        double b2 = coordinateB.getLongitude() / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000 * tt;
    }
    
    private Coordinates() {
    }
    
}
