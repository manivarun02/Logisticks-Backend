package com.project.logistick.Entitiesclasses;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name = "loading")
public class Loading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "loading_date", nullable = false)
    private LocalDate loadingDate;

    @Column(name = "loading_time", nullable = false)
    private LocalTime loadingTime;

    private String street;
    private String city;
    private String pincode;
    private String state;

    public Loading() {
        this.loadingDate = LocalDate.now();
        this.loadingTime = LocalTime.now();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(LocalDate loadingDate) {
		this.loadingDate = loadingDate;
	}

	public LocalTime getLoadingTime() {
		return loadingTime;
	}

	public void setLoadingTime(LocalTime loadingTime) {
		this.loadingTime = loadingTime;
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
