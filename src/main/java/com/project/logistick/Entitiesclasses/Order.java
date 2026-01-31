package com.project.logistick.Entitiesclasses;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    
    // ✅ CARGO FIELD - CRITICAL FOR LINKING ORDER TO CARGO
    private Integer cargoId;
    
    // Address fields
    private Integer loadingId;
    private Integer unloadingId;
    
    // Assignment fields
    private Integer truckId;
    private Integer driverId;
    private Integer carrierId;
    
    // Weight and distance
    private Integer totalWeightKg;
    private Double distanceKm;
    
    // Pricing
    private Double cost;
    
    // Status and date
    private String status;
    private LocalDate orderDate;
    
    // Payment fields
    private String paymentMethod;
    private String paymentStatus;
    private Double paymentAmount;
    private String paymentReference;
    
    // ================= CONSTRUCTORS =================
    
    public Order() {}
    
    public Order(Long userId, Integer cargoId, Integer loadingId, Integer unloadingId, 
                 Integer totalWeightKg, Double distanceKm, Double cost, String status) {
        this.userId = userId;
        this.cargoId = cargoId;
        this.loadingId = loadingId;
        this.unloadingId = unloadingId;
        this.totalWeightKg = totalWeightKg;
        this.distanceKm = distanceKm;
        this.cost = cost;
        this.status = status;
        this.orderDate = LocalDate.now();
    }
    
    // ================= GETTERS & SETTERS =================
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    // ✅ CARGO ID GETTER & SETTER
    public Integer getCargoId() {
        return cargoId;
    }
    
    public void setCargoId(Integer cargoId) {
        this.cargoId = cargoId;
    }
    
    public Integer getLoadingId() {
        return loadingId;
    }
    
    public void setLoadingId(Integer loadingId) {
        this.loadingId = loadingId;
    }
    
    public Integer getUnloadingId() {
        return unloadingId;
    }
    
    public void setUnloadingId(Integer unloadingId) {
        this.unloadingId = unloadingId;
    }
    
    public Integer getTruckId() {
        return truckId;
    }
    
    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }
    
    public Integer getDriverId() {
        return driverId;
    }
    
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }
    
    public Integer getCarrierId() {
        return carrierId;
    }
    
    public void setCarrierId(Integer carrierId) {
        this.carrierId = carrierId;
    }
    
    public Integer getTotalWeightKg() {
        return totalWeightKg;
    }
    
    public void setTotalWeightKg(Integer totalWeightKg) {
        this.totalWeightKg = totalWeightKg;
    }
    
    public Double getDistanceKm() {
        return distanceKm;
    }
    
    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
    
    public Double getCost() {
        return cost;
    }
    
    public void setCost(Double cost) {
        this.cost = cost;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public Double getPaymentAmount() {
        return paymentAmount;
    }
    
    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
    
    public String getPaymentReference() {
        return paymentReference;
    }
    
    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
    
    // ================= OPTIONAL: toString() FOR DEBUGGING =================
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", cargoId=" + cargoId +
                ", loadingId=" + loadingId +
                ", unloadingId=" + unloadingId +
                ", truckId=" + truckId +
                ", driverId=" + driverId +
                ", carrierId=" + carrierId +
                ", totalWeightKg=" + totalWeightKg +
                ", distanceKm=" + distanceKm +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}