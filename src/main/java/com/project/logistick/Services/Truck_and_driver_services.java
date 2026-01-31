package com.project.logistick.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.AvailableTruckDriverDTO;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Repositories.Order_Repo;
import com.project.logistick.Repositories.Truck_Repo;

@Service
public class Truck_and_driver_services {
    
    @Autowired
    private Truck_Repo truckRepo;
    
    @Autowired
    private Order_Repo orderRepo;
    
    // ✅ IMPROVED: Get available trucks with capacity tracking
    public List<AvailableTruckDriverDTO> getAvailableTrucksAndDrivers() {
        List<Truck> allTrucks = truckRepo.findAll();
        
        return allTrucks.stream()
                .filter(t -> t.getDriver() != null) // Only trucks with drivers
                .map(truck -> {
                    AvailableTruckDriverDTO dto = new AvailableTruckDriverDTO();
                    
                    // Basic truck info
                    dto.setTruckId(truck.getId());
                    dto.setTruckNumber(truck.getNumber());
                    
                    // Driver info
                    if (truck.getDriver() != null) {
                        dto.setDriverId(truck.getDriver().getId());
                        dto.setDriverName(truck.getDriver().getName());
                    }
                    
                    // Carrier info
                    if (truck.getCarrier() != null) {
                        dto.setCarrierId(truck.getCarrier().getId());
                        dto.setCarrierName(truck.getCarrier().getName());
                    }
                    
                    // ✅ CAPACITY CALCULATION
                    // Count non-delivered orders assigned to this truck
                    int capacityUsed = orderRepo.countByTruckIdAndStatusNot(
                        truck.getId(), 
                        "Delivered"
                    );
                    
                    dto.setCapacityUsed(capacityUsed);
                    dto.setMaxCapacity(20);
                    
                    return dto;
                })
                .collect(Collectors.toList());
    }
}