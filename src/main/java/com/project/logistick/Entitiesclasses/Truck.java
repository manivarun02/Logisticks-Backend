package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 10, max = 10)
    private String number;

    @NotNull
    @Positive
    @Min(50)
    @Max(50000)
    private int capacity;

    // Frontend + services expect this exact value
    private String status = "Available";

    @OneToOne
    private Carrier carrier;

    @OneToOne
    private Driver_Class driver;

    public Truck() {
    }

    public Truck(int id, String name, String number, int capacity, Carrier carrier, Driver_Class driver) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.capacity = capacity;
        this.carrier = carrier;
        this.driver = driver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Driver_Class getDriver() {
        return driver;
    }

    public void setDriver(Driver_Class driver) {
        this.driver = driver;
    }
}
