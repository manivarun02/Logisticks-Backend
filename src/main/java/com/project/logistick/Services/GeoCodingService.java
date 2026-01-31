package com.project.logistick.Services;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

@Service
public class GeoCodingService {
    
    private static final Logger logger = LoggerFactory.getLogger(GeoCodingService.class);
    
    // FREE API - Get key from: https://locationiq.com/register (No credit card needed)
    // 5,000 requests/day FREE forever
    @Value("${locationiq.api.key:}")
    private String locationiqApiKey;
    
    private final RestTemplate restTemplate;
    
    public GeoCodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    /**
     * Get latitude and longitude for a given address
     * @param address Full address string
     * @return double array [latitude, longitude] or null if not found
     */
    public double[] getLatLng(String address) {
        if (address == null || address.trim().isEmpty()) {
            logger.error("Address is null or empty");
            return null;
        }
        
        // Try LocationIQ API first if key is configured
        if (locationiqApiKey != null && !locationiqApiKey.isEmpty() && !locationiqApiKey.equals("your-api-key-here")) {
            double[] coordinates = fetchFromLocationIQ(address);
            if (coordinates != null) {
                logger.info("Found coordinates via LocationIQ for: {}", address);
                return coordinates;
            }
        }
        
        // Fallback to mock data for testing
        logger.info("Using mock coordinates for: {}", address);
        return getMockCoordinates(address);
    }
    
    /**
     * Fetch coordinates from LocationIQ API (FREE - 5,000 requests/day)
     */
    private double[] fetchFromLocationIQ(String address) {
        try {
            String encodedAddress = UriUtils.encode(address, StandardCharsets.UTF_8);
            String url = String.format(
                "https://us1.locationiq.com/v1/search.php?key=%s&q=%s&format=json&limit=1",
                locationiqApiKey,
                encodedAddress
            );
            
            logger.debug("Querying LocationIQ API for: {}", address);
            
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);
            
            if (response != null && !response.isEmpty()) {
                Map<String, Object> location = response.get(0);
                double lat = Double.parseDouble(location.get("lat").toString());
                double lon = Double.parseDouble(location.get("lon").toString());
                
                logger.debug("Found coordinates - Lat: {}, Lon: {}", lat, lon);
                
                // Rate limiting: Wait 1 second between requests
                Thread.sleep(1000);
                
                return new double[]{lat, lon};
            }
            
            logger.warn("No results found from LocationIQ for: {}", address);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted during LocationIQ request", e);
        } catch (RestClientException e) {
            logger.error("LocationIQ API connection error: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("LocationIQ API error for address '{}': {}", address, e.getMessage());
        }
        return null;
    }
    
    /**
     * Mock coordinates for testing (when API key is not configured)
     * Covers major Indian cities and landmarks
     */
    private double[] getMockCoordinates(String address) {
        String lower = address.toLowerCase().trim();
        
        // === TELANGANA ===
        if (lower.contains("hyderabad") || lower.contains("500")) {
            logger.debug("Using mock coordinates for Hyderabad");
            return new double[]{17.3850, 78.4867};
        }
        if (lower.contains("charminar")) {
            logger.debug("Using mock coordinates for Charminar");
            return new double[]{17.3616, 78.4747};
        }
        if (lower.contains("kukatpally") || lower.contains("kphb")) {
            logger.debug("Using mock coordinates for KPHB/Kukatpally");
            return new double[]{17.4849, 78.3915};
        }
        if (lower.contains("hitech") || lower.contains("hitec")) {
            logger.debug("Using mock coordinates for Hitech City");
            return new double[]{17.4474, 78.3732};
        }
        if (lower.contains("gachibowli")) {
            logger.debug("Using mock coordinates for Gachibowli");
            return new double[]{17.4399, 78.3489};
        }
        if (lower.contains("secunderabad")) {
            logger.debug("Using mock coordinates for Secunderabad");
            return new double[]{17.4399, 78.4983};
        }
        if (lower.contains("uppal")) {
            logger.debug("Using mock coordinates for Uppal");
            return new double[]{17.4065, 78.5564};
        }
        if (lower.contains("warangal")) {
            logger.debug("Using mock coordinates for Warangal");
            return new double[]{17.9689, 79.5941};
        }
        
        // === KARNATAKA ===
        if (lower.contains("bengaluru") || lower.contains("bangalore") || lower.contains("560")) {
            logger.debug("Using mock coordinates for Bengaluru");
            return new double[]{12.9716, 77.5946};
        }
        if (lower.contains("whitefield")) {
            logger.debug("Using mock coordinates for Whitefield");
            return new double[]{12.9698, 77.7499};
        }
        if (lower.contains("vidhana soudha")) {
            logger.debug("Using mock coordinates for Vidhana Soudha");
            return new double[]{12.9796, 77.5909};
        }
        if (lower.contains("mysore") || lower.contains("mysuru")) {
            logger.debug("Using mock coordinates for Mysore");
            return new double[]{12.2958, 76.6394};
        }
        
        // === MAHARASHTRA ===
        if (lower.contains("mumbai") || lower.contains("400")) {
            logger.debug("Using mock coordinates for Mumbai");
            return new double[]{19.0760, 72.8777};
        }
        if (lower.contains("pune") || lower.contains("411")) {
            logger.debug("Using mock coordinates for Pune");
            return new double[]{18.5204, 73.8567};
        }
        if (lower.contains("nagpur")) {
            logger.debug("Using mock coordinates for Nagpur");
            return new double[]{21.1458, 79.0882};
        }
        
        // === DELHI NCR ===
        if (lower.contains("delhi") || lower.contains("110")) {
            logger.debug("Using mock coordinates for Delhi");
            return new double[]{28.7041, 77.1025};
        }
        if (lower.contains("noida")) {
            logger.debug("Using mock coordinates for Noida");
            return new double[]{28.5355, 77.3910};
        }
        if (lower.contains("gurgaon") || lower.contains("gurugram")) {
            logger.debug("Using mock coordinates for Gurugram");
            return new double[]{28.4595, 77.0266};
        }
        
        // === TAMIL NADU ===
        if (lower.contains("chennai") || lower.contains("600")) {
            logger.debug("Using mock coordinates for Chennai");
            return new double[]{13.0827, 80.2707};
        }
        if (lower.contains("coimbatore")) {
            logger.debug("Using mock coordinates for Coimbatore");
            return new double[]{11.0168, 76.9558};
        }
        
        // === WEST BENGAL ===
        if (lower.contains("kolkata") || lower.contains("700")) {
            logger.debug("Using mock coordinates for Kolkata");
            return new double[]{22.5726, 88.3639};
        }
        
        // === GUJARAT ===
        if (lower.contains("ahmedabad") || lower.contains("380")) {
            logger.debug("Using mock coordinates for Ahmedabad");
            return new double[]{23.0225, 72.5714};
        }
        if (lower.contains("surat") || lower.contains("395")) {
            logger.debug("Using mock coordinates for Surat");
            return new double[]{21.1702, 72.8311};
        }
        if (lower.contains("vadodara")) {
            logger.debug("Using mock coordinates for Vadodara");
            return new double[]{22.3072, 73.1812};
        }
        
        // === RAJASTHAN ===
        if (lower.contains("jaipur") || lower.contains("302")) {
            logger.debug("Using mock coordinates for Jaipur");
            return new double[]{26.9124, 75.7873};
        }
        if (lower.contains("udaipur")) {
            logger.debug("Using mock coordinates for Udaipur");
            return new double[]{24.5854, 73.7125};
        }
        
        // === OTHER MAJOR CITIES ===
        if (lower.contains("lucknow")) {
            logger.debug("Using mock coordinates for Lucknow");
            return new double[]{26.8467, 80.9462};
        }
        if (lower.contains("chandigarh")) {
            logger.debug("Using mock coordinates for Chandigarh");
            return new double[]{30.7333, 76.7794};
        }
        if (lower.contains("kochi") || lower.contains("cochin")) {
            logger.debug("Using mock coordinates for Kochi");
            return new double[]{9.9312, 76.2673};
        }
        if (lower.contains("trivandrum") || lower.contains("thiruvananthapuram")) {
            logger.debug("Using mock coordinates for Trivandrum");
            return new double[]{8.5241, 76.9366};
        }
        if (lower.contains("bhubaneswar")) {
            logger.debug("Using mock coordinates for Bhubaneswar");
            return new double[]{20.2961, 85.8245};
        }
        if (lower.contains("indore")) {
            logger.debug("Using mock coordinates for Indore");
            return new double[]{22.7196, 75.8577};
        }
        
        logger.warn("Could not find coordinates for address: {}", address);
        logger.warn("HINT: Add LocationIQ API key in application.properties or use recognized city names");
        return null;
    }
}