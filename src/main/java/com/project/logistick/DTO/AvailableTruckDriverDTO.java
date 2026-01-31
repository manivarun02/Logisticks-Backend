package com.project.logistick.DTO;

public class AvailableTruckDriverDTO {
    private Long truckId;
    private String truckNumber;
    private Long driverId;
    private String driverName;
    private Long carrierId;
    private String carrierName;
    private Integer capacityUsed;  // ✅ NEW: Track capacity
    private Integer maxCapacity;   // ✅ NEW: Max capacity (20)
    
    // ✅ NO-ARG CONSTRUCTOR (VERY IMPORTANT)
    public AvailableTruckDriverDTO() {
        this.maxCapacity = 20; // Default max capacity
    }
    
    // ✅ FULL CONSTRUCTOR
    public AvailableTruckDriverDTO(
            Long truckId,
            String truckNumber,
            Long driverId,
            String driverName,
            Long carrierId,
            String carrierName,
            Integer capacityUsed,
            Integer maxCapacity
    ) {
        this.truckId = truckId;
        this.truckNumber = truckNumber;
        this.driverId = driverId;
        this.driverName = driverName;
        this.carrierId = carrierId;
        this.carrierName = carrierName;
        this.capacityUsed = capacityUsed;
        this.maxCapacity = maxCapacity != null ? maxCapacity : 20;
    }
    
    // ✅ GETTERS & SETTERS
    public Long getTruckId() {
        return truckId;
    }
    
    public void setTruckId(long i) {
        this.truckId = i;
    }
    
    public String getTruckNumber() {
        return truckNumber;
    }
    
    public void setTruckNumber(String truckNumber) {
        this.truckNumber = truckNumber;
    }
    
    public Long getDriverId() {
        return driverId;
    }
    
    public void setDriverId(long i) {
        this.driverId = i;
    }
    
    public String getDriverName() {
        return driverName;
    }
    
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    public Long getCarrierId() {
        return carrierId;
    }
    
    public void setCarrierId(long i) {
        this.carrierId = i;
    }
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    public Integer getCapacityUsed() {
        return capacityUsed != null ? capacityUsed : 0;
    }
    
    public void setCapacityUsed(Integer capacityUsed) {
        this.capacityUsed = capacityUsed;
    }
    
    public Integer getMaxCapacity() {
        return maxCapacity != null ? maxCapacity : 20;
    }
    
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    // ✅ HELPER METHOD: Check if truck is full
    public boolean isFull() {
        return getCapacityUsed() >= getMaxCapacity();
    }
}