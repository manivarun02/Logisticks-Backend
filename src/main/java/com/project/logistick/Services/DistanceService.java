package com.project.logistick.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    private static final double EARTH_RADIUS_KM = 6371.0;
    
    private final GeoCodingService geoCodingService;
    
    @Autowired
    public DistanceService(GeoCodingService geoCodingService) {
        this.geoCodingService = geoCodingService;
    }
    
    /**
     * Calculate distance between two addresses
     * @param pickup Full address string (e.g., "Street, City, Pincode, State")
     * @param dropoff Full address string
     * @return Distance in kilometers
     * @throws IllegalArgumentException if geocoding fails
     */
    public double calculateDistanceKm(String pickup, String dropoff) {
        if (pickup == null || pickup.trim().isEmpty()) {
            throw new IllegalArgumentException("Pickup address cannot be empty");
        }
        if (dropoff == null || dropoff.trim().isEmpty()) {
            throw new IllegalArgumentException("Dropoff address cannot be empty");
        }
        
        double[] pickupCoords = geoCodingService.getLatLng(pickup);
        double[] dropoffCoords = geoCodingService.getLatLng(dropoff);
        
        if (pickupCoords == null || pickupCoords.length != 2) {
            throw new IllegalArgumentException("Failed to geocode pickup address: " + pickup);
        }
        if (dropoffCoords == null || dropoffCoords.length != 2) {
            throw new IllegalArgumentException("Failed to geocode dropoff address: " + dropoff);
        }
        
        return getDistanceKm(pickupCoords[0], pickupCoords[1], dropoffCoords[0], dropoffCoords[1]);
    }
    
    /**
     * Calculate distance using Haversine formula
     * @param lat1 Latitude of point 1
     * @param lon1 Longitude of point 1
     * @param lat2 Latitude of point 2
     * @param lon2 Longitude of point 2
     * @return Distance in kilometers
     */
    public double getDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        // Validate coordinates
        if (!isValidLatitude(lat1) || !isValidLatitude(lat2)) {
            throw new IllegalArgumentException("Invalid latitude values");
        }
        if (!isValidLongitude(lon1) || !isValidLongitude(lon2)) {
            throw new IllegalArgumentException("Invalid longitude values");
        }
        
        // Haversine formula
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS_KM * c;
    }
    
    /**
     * Validate latitude is between -90 and 90
     */
    private boolean isValidLatitude(double lat) {
        return lat >= -90.0 && lat <= 90.0;
    }
    
    /**
     * Validate longitude is between -180 and 180
     */
    private boolean isValidLongitude(double lon) {
        return lon >= -180.0 && lon <= 180.0;
    }
}