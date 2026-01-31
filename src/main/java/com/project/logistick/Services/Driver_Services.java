package com.project.logistick.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Carrier;
import com.project.logistick.Entitiesclasses.Driver_Class;
import com.project.logistick.Entitiesclasses.Truck;
import com.project.logistick.Exceptions.DriverNotFound;
import com.project.logistick.Repositories.Carrier_Repo;
import com.project.logistick.Repositories.Driver_Repo;
import com.project.logistick.Repositories.Truck_Repo;

@Service
public class Driver_Services {

    @Autowired
    private Driver_Repo drrepo;

    @Autowired
    private Carrier_Repo crrepo;

    @Autowired
    private Truck_Repo trepo;

    // ✅ SAVE DRIVER
    public ResponseEntity<ResponceStucture<Driver_Class>> saveDriver(Driver_Class d) {

        Driver_Class saved = drrepo.save(d);

        ResponceStucture<Driver_Class> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("Driver saved successfully");
        rs.setData(saved);

        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    // backward compatibility
    public ResponseEntity<ResponceStucture<Driver_Class>> saveDetails(Driver_Class d) {
        return saveDriver(d);
    }

    // ✅ LIST ALL DRIVERS (FIXES CONTROLLER ERROR)
    public List<Driver_Class> getAllDrivers() {
        return drrepo.findAll();
    }

    // ✅ FIND DRIVER
    public ResponseEntity<ResponceStucture<Driver_Class>> findDriver(int id) {

        Driver_Class driver = drrepo.findById(id)
                .orElseThrow(DriverNotFound::new);

        ResponceStucture<Driver_Class> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Driver found");
        rs.setData(driver);

        return ResponseEntity.ok(rs);
    }

    // ✅ DELETE DRIVER
    public ResponseEntity<ResponceStucture<String>> deleteDriver(int id) {

        Driver_Class driver = drrepo.findById(id)
                .orElseThrow(DriverNotFound::new);

        drrepo.delete(driver);

        ResponceStucture<String> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Driver deleted successfully");
        rs.setData("Deleted");

        return ResponseEntity.ok(rs);
    }

    // ✅ ASSIGN TRUCK & CARRIER
    public ResponseEntity<ResponceStucture<Driver_Class>> assignTruckToDriver(
            int driverId, int truckId, int carrierId) {

        Driver_Class driver = drrepo.findById(driverId)
                .orElseThrow(DriverNotFound::new);

        Truck truck = trepo.findById(truckId)
                .orElseThrow(() -> new RuntimeException("Truck not found"));

        Carrier carrier = crrepo.findById(carrierId)
                .orElseThrow(() -> new RuntimeException("Carrier not found"));

        driver.setTruck(truck);
        driver.setCarrier(carrier);

        Driver_Class updated = drrepo.save(driver);

        ResponceStucture<Driver_Class> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Truck & Carrier assigned to driver");
        rs.setData(updated);

        return ResponseEntity.ok(rs);
    }
}
