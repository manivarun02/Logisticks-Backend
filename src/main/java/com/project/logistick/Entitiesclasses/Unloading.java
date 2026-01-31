package com.project.logistick.Entitiesclasses;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "unloading")
public class Unloading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "unloading_date", nullable = false)
    private LocalDate unloadingDate;

    @Column(name = "unloading_time", nullable = false)
    private LocalTime unloadingTime;

    private String street;
    private String city;
    private String pincode;
    private String state;

    public Unloading() {
        this.unloadingDate = LocalDate.now();
        this.unloadingTime = LocalTime.now();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getUnloadingDate() {
		return unloadingDate;
	}

	public void setUnloadingDate(LocalDate unloadingDate) {
		this.unloadingDate = unloadingDate;
	}

	public LocalTime getUnloadingTime() {
		return unloadingTime;
	}

	public void setUnloadingTime(LocalTime unloadingTime) {
		this.unloadingTime = unloadingTime;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

    // getters & setters
    
}
