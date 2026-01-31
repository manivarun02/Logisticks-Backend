package com.project.logistick.Controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.logistick.DTO.*;
import com.project.logistick.Entitiesclasses.*;
import com.project.logistick.Repositories.Order_Repo;
import com.project.logistick.Services.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5173" })
public class User_Controller {

    @Autowired private UsersService userService;
    @Autowired private Address_Services addressService;
    @Autowired private Cargo_Services cargoService;
    @Autowired private Order_Services orderService;
    @Autowired private Order_Repo orderRepo;

    // ✅ NEW (SAFE): reuse existing services
    @Autowired private Loading_Services loadingService;
    @Autowired private Unloading_Services unloadingService;

    // ================= USER REGISTER & LOGIN =================

    @PostMapping("/register")
    public ResponseEntity<ResponceStucture<Users>> registerUser(
            @RequestBody @Valid UserRegisterRequest request) {

        Users user = new Users();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setMobile(request.getMobile());
        user.setLocation(request.getLocation());
        user.setUsageType(request.getUsageType());

        Users saved = userService.registerUser(user);

        ResponceStucture<Users> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.CREATED.value());
        rs.setMessage("User registered successfully");
        rs.setData(saved);

        return new ResponseEntity<>(rs, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponceStucture<Users>> login(
            @RequestBody UserLoginRequest request) {

        Users user = userService.loginUser(request.getEmail(), request.getPassword());
        ResponceStucture<Users> rs = new ResponceStucture<>();

        if (user != null) {
            rs.setCode(HttpStatus.OK.value());
            rs.setMessage("Login successful");
            rs.setData(user);
            return ResponseEntity.ok(rs);
        }

        rs.setCode(HttpStatus.UNAUTHORIZED.value());
        rs.setMessage("Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(rs);
    }

    // ================= ADDRESS & CARGO =================

    @PostMapping("/address")
    public ResponseEntity<ResponceStucture<Address>> saveAddress(
            @RequestBody @Valid Address address) {
        return addressService.saveadress(address);
    }

    @PostMapping("/cargo")
    public ResponseEntity<ResponceStucture<Cargo>> saveCargo(
            @RequestBody @Valid Cargo cargo) {
        return cargoService.saveCargo(cargo);
    }

    @GetMapping("/cargo/{id}")
    public ResponseEntity<ResponceStucture<Cargo>> findCargo(
            @PathVariable Integer id) {
        return cargoService.findCargo(id);
    }

    @DeleteMapping("/cargo/{id}")
    public ResponseEntity<ResponceStucture<String>> deleteCargo(
            @PathVariable Integer id) {
        return cargoService.deleteCargo(id);
    }

    // ================= ✅ NEW: USER LOADING / UNLOADING =================

    @PostMapping("/loading")
    public ResponseEntity<?> saveLoading(@RequestBody @Valid Loading loading) {
        // internally same logic as admin
        return loadingService.saveLoading(loading);
    }

    @PostMapping("/unloading")
    public ResponseEntity<?> saveUnloading(@RequestBody @Valid Unloading unloading) {
        // internally same logic as admin
        return unloadingService.saveUnloading(unloading);
    }

    // ================= ORDERING =================

    @PostMapping("/orders/price-preview")
    public ResponseEntity<ResponceStucture<PricePreviewResponse>> pricePreview(
            @RequestBody OrderDto dto) {
        return orderService.pricePreview(dto);
    }

    @PostMapping("/orders")
    public ResponseEntity<ResponceStucture<Order>> placeOrder(
            @RequestBody OrderDto dto) {
        return orderService.orderPlacing(dto);
    }

    @GetMapping("/orders/track/{orderId}")
    public ResponseEntity<ResponceStucture<Order>> trackOrder(
            @PathVariable Long orderId) {
        return orderService.tracingOrder(orderId);
    }

    @PutMapping("/orders/cancel/{id}")
    public ResponseEntity<ResponceStucture<Order>> cancelOrder(
            @PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<ResponceStucture<List<Order>>> getOrdersForUser(
            @PathVariable Long userId) {

        List<Order> orders = orderService.getOrdersByUser(userId);
        ResponceStucture<List<Order>> rs = new ResponceStucture<>();
        rs.setCode(HttpStatus.OK.value());
        rs.setMessage("Orders retrieved");
        rs.setData(orders);

        return ResponseEntity.ok(rs);
    }

    // ================= PAYMENT =================

    @PostMapping("/payment/initiate")
    public ResponseEntity<?> initiatePayment(@RequestBody PaymentRequest req) {
        try {
            Order order = orderRepo.findById(Long.valueOf(req.getOrderId()))
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            double amount = (req.getAmount() != null) ? req.getAmount() : order.getCost();
            String method = (req.getMethod() != null) ? req.getMethod().toUpperCase() : "COD";

            order.setPaymentMethod(method);
            order.setPaymentStatus("PENDING");
            order.setPaymentAmount(amount);
            orderRepo.save(order);

            return ResponseEntity.ok(Map.of(
                    "orderId", order.getId(),
                    "method", method,
                    "amount", amount,
                    "status", "PENDING"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/payment/confirm")
    public ResponseEntity<?> confirmPayment(
            @RequestParam Long orderId,
            @RequestParam String reference) {

        try {
            Order order = orderRepo.findById(orderId)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            order.setPaymentStatus("PAID");
            order.setPaymentReference(reference);
            orderRepo.save(order);

            return ResponseEntity.ok(Map.of(
                    "orderId", orderId,
                    "status", "PAID",
                    "reference", reference
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
