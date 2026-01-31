package com.project.logistick.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.logistick.DTO.AdminLoginRequest;
import com.project.logistick.DTO.AvailableTruckDriverDTO;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.*;
import com.project.logistick.Repositories.UsersRepository;
import com.project.logistick.Services.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class Admin_Controller {

    @Autowired private Truck_Services trservice;
    @Autowired private Carrier_Services crservices;
    @Autowired private Address_Services adservices;
    @Autowired private Order_Services orderservice;
    @Autowired private Loading_Services loadservice;
    @Autowired private Unloading_Services unloaddetails;
    @Autowired private Truck_and_driver_services truckDriverService;
    @Autowired private UsersRepository usersRepository;
    @Autowired private AdminService adminService;
    @Autowired private Driver_Services driverService;

    // ==================== ADMIN AUTH ====================
    @PostMapping("/register")
    public ResponseEntity<ResponceStucture<Admin>> registerAdmin(@RequestBody @Valid Admin admin) {
        Admin saved = adminService.registerAdmin(admin);
        ResponceStucture<Admin> rs = new ResponceStucture<>();
        rs.setCode(201); rs.setMessage("Admin registered successfully"); rs.setData(saved);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponceStucture<Admin>> loginAdmin(@RequestBody AdminLoginRequest request) {
        Admin admin = adminService.loginAdmin(request.getEmpId(), request.getPassword());
        ResponceStucture<Admin> rs = new ResponceStucture<>();
        if (admin != null) {
            rs.setCode(200); rs.setMessage("Admin login successful"); rs.setData(admin);
            return ResponseEntity.ok(rs);
        }
        rs.setCode(401); rs.setMessage("Invalid admin credentials"); rs.setData(null);
        return ResponseEntity.status(401).body(rs);
    }

    // ==================== DRIVER MANAGEMENT ====================
    @PostMapping("/savedriverdetails") public ResponseEntity<?> saveDriver(@RequestBody @Valid Driver_Class driver) { return driverService.saveDriver(driver); }
    @GetMapping("/finddriver/{id}") public ResponseEntity<?> findDriver(@PathVariable Integer id) { return driverService.findDriver(id); }
    @DeleteMapping("/deletedriver/{id}") public ResponseEntity<?> deleteDriver(@PathVariable Integer id) { return driverService.deleteDriver(id); }
    @PutMapping("/updatedriverassignment/{driverId}") public ResponseEntity<?> assignTruckToDriver(@PathVariable Integer driverId, @RequestParam Integer truckId, @RequestParam Integer carrierId) { return driverService.assignTruckToDriver(driverId, truckId, carrierId); }

    // ✅ FIXED: Keep your existing endpoint but add Response wrapper
    @GetMapping("/alldrivers")
    public ResponseEntity<ResponceStucture<List<Driver_Class>>> getAllDrivers() {
        List<Driver_Class> drivers = driverService.getAllDrivers();
        ResponceStucture<List<Driver_Class>> rs = new ResponceStucture<>();
        rs.setCode(200); rs.setMessage("Drivers fetched"); rs.setData(drivers);
        return ResponseEntity.ok(rs);
    }

    // ==================== TRUCK MANAGEMENT ====================
    @PostMapping("/savetruck") public ResponseEntity<?> saveTruck(@RequestBody @Valid Truck truck) { return trservice.saveTruck(truck); }
    @DeleteMapping("/deletetruck/{id}") public ResponseEntity<?> deleteTruck(@PathVariable Integer id) { return trservice.deleteTruck(id); }
    @PutMapping("/updatetruck/{number}") public ResponseEntity<?> updateTruckByNumber(@PathVariable String number, @RequestParam Integer carrierId, @RequestParam Integer driverId) { return trservice.updateTruckByNumber(number, carrierId, driverId); }

    // ✅ FIXED: Keep your existing endpoint + NEW order counts
    @GetMapping("/alltrucks")
    public ResponseEntity<ResponceStucture<List<Truck>>> getAllTrucks() {
        List<Truck> trucks = trservice.getAllTrucks();
        ResponceStucture<List<Truck>> rs = new ResponceStucture<>();
        rs.setCode(200); rs.setMessage("Trucks fetched"); rs.setData(trucks);
        return ResponseEntity.ok(rs);
    }

    // ✅ NEW: Truck Order Counts (CRITICAL for 20-order limit)
    @GetMapping("/trucks/order-counts")
    public ResponseEntity<ResponceStucture<Map<Integer, Integer>>> getTruckOrderCounts() {
        Map<Integer, Integer> orderCounts = trservice.getTruckOrderCounts();
        ResponceStucture<Map<Integer, Integer>> rs = new ResponceStucture<>();
        rs.setCode(200); rs.setMessage("Truck order counts"); rs.setData(orderCounts);
        return ResponseEntity.ok(rs);
    }

    // ==================== CARRIER MANAGEMENT ====================
    @PostMapping("/savecarrier") public ResponseEntity<?> saveCarrier(@RequestBody @Valid Carrier carrier) { return crservices.saveCarrier(carrier); }
    @GetMapping("/findcarrier/{id}") public ResponseEntity<?> findCarrier(@PathVariable Integer id) { return crservices.findCarrier(id); }
    @DeleteMapping("/deletecarrier/{id}") public ResponseEntity<?> deleteCarrier(@PathVariable Integer id) { return crservices.deleteCarrier(id); }

    // ✅ FIXED: Keep your existing endpoint
    @GetMapping("/allcarriers")
    public ResponseEntity<ResponceStucture<List<Carrier>>> getAllCarriers() {
        List<Carrier> carriers = crservices.getAllCarriers();
        ResponceStucture<List<Carrier>> rs = new ResponceStucture<>();
        rs.setCode(200); rs.setMessage("Carriers fetched"); rs.setData(carriers);
        return ResponseEntity.ok(rs);
    }

    // ==================== REST OF YOUR ENDPOINTS (UNCHANGED) ====================
    @GetMapping("/findaddress/{id}") public ResponseEntity<?> findAddress(@PathVariable Integer id) { return adservices.findAddress(id); }
    @DeleteMapping("/deleteaddress/{id}") public ResponseEntity<?> deleteAddress(@PathVariable Integer id) { return adservices.deleteAddress(id); }

    @PostMapping("/saveloading") public ResponseEntity<?> saveLoading(@RequestBody @Valid Loading loading) { return loadservice.saveLoading(loading); }
    @GetMapping("/findloading/{id}") public ResponseEntity<?> findLoading(@PathVariable Integer id) { return loadservice.findLoading(id); }
    @DeleteMapping("/deleteloading/{id}") public ResponseEntity<?> deleteLoading(@PathVariable Integer id) { return loadservice.deleteLoading(id); }

    @PostMapping("/saveunloading") public ResponseEntity<?> saveUnloading(@RequestBody @Valid Unloading unloading) { return unloaddetails.saveUnloading(unloading); }
    @GetMapping("/findunloading/{id}") public ResponseEntity<?> findUnloading(@PathVariable Integer id) { return unloaddetails.findUnloading(id); }
    @DeleteMapping("/cancelunloading/{id}") public ResponseEntity<?> deleteUnloading(@PathVariable Integer id) { return unloaddetails.deleteUnloading(id); }

    @GetMapping("/allorders") public List<Order> getAllOrders() { return orderservice.getAllOrders(); }
    @GetMapping("/userorders/{userId}") public List<Order> getOrdersForUser(@PathVariable Long userId) { return orderservice.getOrdersByUser(userId); }
    @PutMapping("/updateorderassigncarrie/{orderId}/bytruckid/{truckId}") public ResponseEntity<?> assignOrderToTruck(@PathVariable Long orderId, @PathVariable Integer truckId) { return orderservice.assignOrderToTruck(orderId, truckId); }
    @PutMapping("/updateloadingunloadingdatebyorder/{orderId}") public ResponseEntity<?> updateLoadingUnloadingDate(@PathVariable Long orderId) { return orderservice.updateLoadingUnloadingDate(orderId); }

    @GetMapping("/available-trucks-drivers") public List<AvailableTruckDriverDTO> getAvailableTrucksAndDrivers() { return truckDriverService.getAvailableTrucksAndDrivers(); }
 // ADD THIS to Admin_Controller.java
    @PutMapping("/assign-full/{orderId}")
    public ResponseEntity<?> assignFullOrder(
        @PathVariable Long orderId, 
        @RequestParam(required = false) Integer truckId,
        @RequestParam(required = false) Integer driverId,
        @RequestParam(required = false) Integer carrierId
    ) { 
        return orderservice.assignFullOrder(orderId, truckId, driverId, carrierId); 
    }

    @GetMapping("/allusers") public List<Users> getAllUsers() { return usersRepository.findAll(); }
    
 // ✅ ADD THESE TO Admin_Controller.java

    @GetMapping("/allloadings")
    public ResponseEntity<ResponceStucture<List<Loading>>> getAllLoadings() {
        List<Loading> loadings = loadservice.getAllLoadings();
        ResponceStucture<List<Loading>> rs = new ResponceStucture<>();
        rs.setCode(200);
        rs.setMessage("Loadings fetched");
        rs.setData(loadings);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/allunloadings")
    public ResponseEntity<ResponceStucture<List<Unloading>>> getAllUnloadings() {
        List<Unloading> unloadings = unloaddetails.getAllUnloadings();
        ResponceStucture<List<Unloading>> rs = new ResponceStucture<>();
        rs.setCode(200);
        rs.setMessage("Unloadings fetched");
        rs.setData(unloadings);
        return ResponseEntity.ok(rs);
    }
}
