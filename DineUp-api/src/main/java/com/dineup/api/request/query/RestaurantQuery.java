package com.dineup.api.request.query;

import com.dineup.api.dom.Coordinate;

/**
 * Restaurant query.
 * <pre><code>
 * RestaurantQuery queryBase = RestaurantQuery.newQuery()
 *     .build();
 * 
 * RestaurantQuery queryCoordinateProvidedNotFiltered = RestaurantQuery.newQuery()
 *     .coordinate(new Coordinate(1, 1))
 *     .build();
 * 
 * RestaurantQuery queryWithMaxDistanceFilter = RestaurantQuery.newQuery()
 *     .coordinate(new Coordinate(1, 1))
 *     .maxDistanceInMeters(10)
 *     .build();
 * </code></pre>
 */
public class RestaurantQuery {

    public static RestaurantQueryBuilder newQuery() {
        return new RestaurantQueryBuilder();
    }
    
    public static class RestaurantQueryBuilder {
        
        private Coordinate coordinate;
        private Double maxDistanceInMeters;

        private RestaurantQueryBuilder() {
        }
        
        public CoordinateOpportunities coordinate(Coordinate coordinate) {
            if (coordinate == null) {
                throw new IllegalArgumentException("coordinate can not be null");
            }
            this.coordinate = coordinate;
            return new CoordinateOpportunities(this);
        }

        public RestaurantQuery build() {
            return new RestaurantQuery(this);
        }
        
    }
    
    public static class CoordinateOpportunities {

        private final RestaurantQueryBuilder queryBuilder;

        public CoordinateOpportunities(RestaurantQueryBuilder queryBuilder) {
            this.queryBuilder = queryBuilder;
        }

        public CoordinateOpportunities maxDistanceInMeters(double maxDistanceInMeters) {
            if (maxDistanceInMeters <= 0) {
                throw new IllegalArgumentException("maxDistanceInMeters must be positive number");
            }
            queryBuilder.maxDistanceInMeters = maxDistanceInMeters;
            return this;
        }

        public RestaurantQuery build() {
            return queryBuilder.build();
        }

    }
    
    private final Coordinate coordinate;
    private final Double maxDistanceInMeters;
    
    private RestaurantQuery(RestaurantQueryBuilder builder) {
        this.coordinate = builder.coordinate;
        this.maxDistanceInMeters = builder.maxDistanceInMeters;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Double getMaxDistanceInMeters() {
        return maxDistanceInMeters;
    }

}
