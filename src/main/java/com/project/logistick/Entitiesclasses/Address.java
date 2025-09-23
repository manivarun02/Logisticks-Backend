package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Size(min=2,max=20)
	@NotNull
	private String street;
	@Size(min=2,max=20)
	@NotNull
	private String city;
	@Min(value=5)
	@Max(value=8)
	@NotNull
	private int pincode;
	@Size(min=2,max=20)
	@NotNull
	private String state;
	public Address( String street, String city, int pincode, String state) {
		super();
//		this.id = id;
		this.street = street;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
	}
	public Address() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", pincode=" + pincode + ", state="
				+ state + "]";
	}
	
	
	

}
