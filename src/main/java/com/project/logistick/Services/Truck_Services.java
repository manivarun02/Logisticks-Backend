package com.project.logistick.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Driver_Class;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Exceptions.TruckNotFound;
import com.project.logistick.Repositories.Carrier_Repo;
import com.project.logistick.Repositories.Driver_Repo;
import com.project.logistick.Repositories.Truck_Repo;
import com.project.logistick.Repositories.Order_Repo; // ADD THIS

@Service
public class Truck_Services {

    @Autowired private Truck_Repo truckRepo;
    @Autowired private Carrier_Repo carrierRepo;
    @Autowired private Driver_Repo driverRepo;
    @Autowired private Order_Repo orderRepo; // ADD THIS

    public ResponseEntity<ResponceStucture<Truck>> saveTruck(Truck truck) {
        Truck saved = truckRepo.save(truck);
        ResponceStucture<Truck> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Truck saved successfully");
        rs.setData(saved);
        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponceStucture<Truck>> saveDetails(Truck truck) {
        return saveTruck(truck);
    }

    // ✅ List all trucks
    public List<Truck> getAllTrucks() {
        return truckRepo.findAll();
    }

    // ✅ NEW: Get truck order counts (for 20-order limit)
    public Map<Integer, Integer> getTruckOrderCounts() {
        List<Truck> trucks = truckRepo.findAll();
        Map<Integer, Integer> orderCounts = new HashMap<>();
        
        for (Truck truck : trucks) {
            // Count orders assigned to this truck
            long count = orderRepo.countByTruckId(truck.getId());
            orderCounts.put(truck.getId(), (int) count);
        }
        return orderCounts;
    }

    public ResponseEntity<ResponceStucture<String>> deleteTruck(int id) {
        Truck truck = truckRepo.findById(id).orElseThrow(TruckNotFound::new);
        truckRepo.delete(truck);
        ResponceStucture<String> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Truck deleted");
        rs.setData("Deleted");
        return ResponseEntity.ok(rs);
    }

    public ResponseEntity<ResponceStucture<Truck>> updateTruckByNumber(String truckNumber, Integer carrierId, Integer driverId) {
        Truck truck = truckRepo.findByNumber(truckNumber);
        if (truck == null) throw new TruckNotFound();

        if (carrierId != null) {
            Carrier carrier = carrierRepo.findById(carrierId)
                    .orElseThrow(() -> new RuntimeException("Carrier not found"));
            truck.setCarrier(carrier);
        }
        if (driverId != null) {
            Driver_Class driver = driverRepo.findById(driverId)
                    .orElseThrow(() -> new RuntimeException("Driver not found"));
            truck.setDriver(driver);
        }
        Truck updated = truckRepo.save(truck);
        ResponceStucture<Truck> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Truck updated");
        rs.setData(updated);
        return ResponseEntity.ok(rs);
    }
}
