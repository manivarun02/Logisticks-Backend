package com.project.logistick.DTO;

public class OrderDto {
    
    // Existing fields
    private Long userId;
    private Integer loadingId;
    private Integer unloadingId;
    private Double totalWeightKg;
    private Double distanceKm;
    private Double cost;
    private String paymentMethod;
    
    // ✅ NEW CARGO FIELDS
    private String cargoName;
    private String cargoDescription;
    private Integer cargoCount;
    
    // Constructors
    public OrderDto() {}
    
    // ========== EXISTING GETTERS & SETTERS ==========
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public Double getTotalWeightKg() {
        return totalWeightKg;
    }
    
    public void setTotalWeightKg(Double totalWeightKg) {
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
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    // ========== ✅ NEW CARGO GETTERS & SETTERS ==========
    
    public String getCargoName() {
        return cargoName;
    }
    
    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }
    
    public String getCargoDescription() {
        return cargoDescription;
    }
    
    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }
    
    public Integer getCargoCount() {
        return cargoCount;
    }
    
    public void setCargoCount(Integer cargoCount) {
        this.cargoCount = cargoCount;
    }
    
    // ========== OPTIONAL: toString() FOR DEBUGGING ==========
    
    @Override
    public String toString() {
        return "OrderDto{" +
                "userId=" + userId +
                ", loadingId=" + loadingId +
                ", unloadingId=" + unloadingId +
                ", totalWeightKg=" + totalWeightKg +
                ", distanceKm=" + distanceKm +
                ", cost=" + cost +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", cargoName='" + cargoName + '\'' +
                ", cargoDescription='" + cargoDescription + '\'' +
                ", cargoCount=" + cargoCount +
                '}';
    }
}