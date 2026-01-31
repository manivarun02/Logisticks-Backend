package com.project.logistick.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.logistick.DTO.OrderDto;
import com.project.logistick.DTO.PricePreviewResponse;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Cargo;
import com.project.logistick.Entitiesclasses.Order;
import com.project.logistick.Repositories.Cargo_Repo;
import com.project.logistick.Repositories.Order_Repo;

@Service
public class Order_Services {

    @Autowired
    private Order_Repo orderRepo;
    
    @Autowired
    private Cargo_Repo cargoRepo;

    // ================= PRICE PREVIEW =================
    public ResponseEntity<ResponceStucture<PricePreviewResponse>> pricePreview(OrderDto dto) {

        ResponceStucture<PricePreviewResponse> rs = new ResponceStucture<>();

        if (dto.getTotalWeightKg() == null || dto.getTotalWeightKg() <= 0) {
            rs.setCode(HttpStatus.BAD_REQUEST.value());
            rs.setMessage("Total weight is required");
            rs.setData(null);
            return ResponseEntity.badRequest().body(rs);
        }

        double distanceKm = 150.0;
        double cost = (dto.getTotalWeightKg() * 10) + (distanceKm * 10);

        PricePreviewResponse response = new PricePreviewResponse();
        response.setDistanceKm(distanceKm);
        response.setCost(cost);

        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Price calculated successfully");
        rs.setData(response);

        return ResponseEntity.ok(rs);
    }

    // ================= PLACE ORDER (UPDATED WITH CARGO CREATION) =================
    public ResponseEntity<ResponceStucture<Order>> orderPlacing(OrderDto dto) {

        ResponceStucture<Order> rs = new ResponceStucture<>();

        try {
            // ✅ STEP 1: VALIDATE CARGO DETAILS
            if (dto.getCargoName() == null || dto.getCargoName().trim().isEmpty()) {
                rs.setCode(HttpStatus.BAD_REQUEST.value());
                rs.setMessage("Cargo name is required");
                rs.setData(null);
                return ResponseEntity.badRequest().body(rs);
            }

            if (dto.getCargoDescription() == null || dto.getCargoDescription().trim().isEmpty()) {
                rs.setCode(HttpStatus.BAD_REQUEST.value());
                rs.setMessage("Cargo description is required");
                rs.setData(null);
                return ResponseEntity.badRequest().body(rs);
            }

            if (dto.getTotalWeightKg() == null || dto.getTotalWeightKg() <= 0) {
                rs.setCode(HttpStatus.BAD_REQUEST.value());
                rs.setMessage("Valid cargo weight is required");
                rs.setData(null);
                return ResponseEntity.badRequest().body(rs);
            }

            // ✅ STEP 2: CREATE AND SAVE CARGO FIRST
            Cargo cargo = new Cargo();
            cargo.setName(dto.getCargoName().trim());
            cargo.setDescription(dto.getCargoDescription().trim());
            cargo.setWeight(dto.getTotalWeightKg().intValue()); // Convert Double to int
            cargo.setCount(dto.getCargoCount() != null ? dto.getCargoCount() : 1);

            Cargo savedCargo = cargoRepo.save(cargo);
            System.out.println("✅ Cargo saved with ID: " + savedCargo.getId());

            // ✅ STEP 3: CREATE ORDER WITH CARGO ID
            Order order = new Order();
            order.setUserId(dto.getUserId());
            order.setCargoId(savedCargo.getId());
            order.setLoadingId(dto.getLoadingId());
            order.setUnloadingId(dto.getUnloadingId());
            order.setTotalWeightKg(dto.getTotalWeightKg() != null ? dto.getTotalWeightKg().intValue() : 0); // Convert Double to int

            double distanceKm = 150.0;
            order.setDistanceKm(distanceKm);

            double cost = (dto.getTotalWeightKg() * 10) + (distanceKm * 10);
            order.setCost(cost);

            order.setPaymentMethod(dto.getPaymentMethod() != null ? dto.getPaymentMethod() : "COD");
            order.setPaymentStatus("PENDING");
            order.setPaymentAmount(cost);

            order.setStatus("PLACED");
            order.setOrderDate(LocalDate.now());

            order.setTruckId(null);
            order.setDriverId(null);
            order.setCarrierId(null);

            Order savedOrder = orderRepo.save(order);
            System.out.println("✅ Order saved with ID: " + savedOrder.getId() + ", Cargo ID: " + savedOrder.getCargoId());

            rs.setCode(HttpStatus.CREATED.value());
            rs.setMessage("Order placed successfully with cargo details");
            rs.setData(savedOrder);

            return new ResponseEntity<>(rs, HttpStatus.CREATED);

        } catch (Exception e) {
            System.err.println("❌ Error placing order: " + e.getMessage());
            e.printStackTrace();
            
            rs.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            rs.setMessage("Failed to place order: " + e.getMessage());
            rs.setData(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(rs);
        }
    }

    // ================= GET ALL ORDERS =================
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    // ================= GET USER ORDERS =================
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    // ================= TRACK ORDER =================
    public ResponseEntity<ResponceStucture<Order>> tracingOrder(Long orderId) {

        Optional<Order> opt = orderRepo.findById(orderId);
        ResponceStucture<Order> rs = new ResponceStucture<>();

        if (opt.isPresent()) {
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Order found");
            rs.setData(opt.get());
            return ResponseEntity.ok(rs);
        }

        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Order not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    // ================= CANCEL ORDER =================
    public ResponseEntity<ResponceStucture<Order>> cancelOrder(Long id) {

        Optional<Order> opt = orderRepo.findById(id);
        ResponceStucture<Order> rs = new ResponceStucture<>();

        if (opt.isPresent()) {
            Order order = opt.get();
            order.setStatus("CANCELLED");
            orderRepo.save(order);

            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Order cancelled successfully");
            rs.setData(order);
            return ResponseEntity.ok(rs);
        }

        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Order not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    // ================= ADMIN: ASSIGN TRUCK ONLY =================
    public ResponseEntity<ResponceStucture<Order>> assignOrderToTruck(Long orderId, Integer truckId) {

        Optional<Order> opt = orderRepo.findById(orderId);
        ResponceStucture<Order> rs = new ResponceStucture<>();

        if (opt.isPresent()) {
            Order order = opt.get();
            order.setTruckId(truckId);
            order.setStatus("ASSIGNED");
            orderRepo.save(order);

            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Truck assigned successfully");
            rs.setData(order);
            return ResponseEntity.ok(rs);
        }

        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Order not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    // ✅ FULL ASSIGNMENT (TRUCK + DRIVER + CARRIER)
    public ResponseEntity<ResponceStucture<Order>> assignFullOrder(Long orderId, Integer truckId, Integer driverId, Integer carrierId) {

        Optional<Order> opt = orderRepo.findById(orderId);
        ResponceStucture<Order> rs = new ResponceStucture<>();

        if (opt.isPresent()) {
            Order order = opt.get();
            
            System.out.println("🔄 Assigning order #" + orderId);
            System.out.println("   Truck ID: " + truckId);
            System.out.println("   Driver ID: " + driverId);
            System.out.println("   Carrier ID: " + carrierId);
            
            if (truckId != null) {
                order.setTruckId(truckId);
                System.out.println("   ✅ Truck assigned");
            }
            if (driverId != null) {
                order.setDriverId(driverId);
                System.out.println("   ✅ Driver assigned");
            }
            if (carrierId != null) {
                order.setCarrierId(carrierId);
                System.out.println("   ✅ Carrier assigned");
            }
            
            if (truckId != null && driverId != null && carrierId != null) {
                order.setStatus("FULLY_ASSIGNED");
                System.out.println("   ✅ Status: FULLY_ASSIGNED");
            } else if (truckId != null) {
                order.setStatus("ASSIGNED");
                System.out.println("   ✅ Status: ASSIGNED");
            }
            
            Order savedOrder = orderRepo.save(order);
            System.out.println("✅ Order #" + orderId + " saved successfully");

            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Assignment successful");
            rs.setData(savedOrder);
            return ResponseEntity.ok(rs);
        }

        System.err.println("❌ Order #" + orderId + " not found");
        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Order not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }

    // ================= ADMIN: UPDATE STATUS =================
    public ResponseEntity<ResponceStucture<Order>> updateLoadingUnloadingDate(Long orderId) {

        Optional<Order> opt = orderRepo.findById(orderId);
        ResponceStucture<Order> rs = new ResponceStucture<>();

        if (opt.isPresent()) {
            Order order = opt.get();
            order.setStatus("IN_TRANSIT");
            orderRepo.save(order);

            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Order moved to transit");
            rs.setData(order);
            return ResponseEntity.ok(rs);
        }

        rs.setCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage("Order not found");
        rs.setData(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }
}