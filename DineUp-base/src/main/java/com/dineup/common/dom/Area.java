package com.dineup.common.dom;

public final class Area {
    
    private final Coordinate coordinate;
    private final double radius;

    public Area(Coordinate coordinate, double radius) {
        this.coordinate = coordinate;
        this.radius = radius;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getRadius() {
        return radius;
    }
    
    public boolean isInArea(Coordinate coordinate) {
        Double distance = Coordinates.getDistanceInMeter(this.coordinate, coordinate);
        if (distance == null) {
            return true;
        }
        return distance <= radius;
    }
    
}
