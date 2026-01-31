package com.project.logistick.Entitiesclasses;

import jakarta.annotation.Generated;
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
public class Driver_Class {
	@Id
	
	private int id;

	
	
	
	@Size(min=2,max=20)
	@NotNull
	private String name;
	@Positive
	@Min(value=6000000000L)
	@Max(value=9999999999L)
	@NotNull
	private long contact;
	
	@OneToOne
	private Truck truck;

	@OneToOne
	private Carrier carrier;

	public Driver_Class(int id,String name, long contact, Truck truck, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.truck = truck;
		this.carrier = carrier;
	}

	public Driver_Class() {
		super();
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

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Truck getTruck() {
		return truck;
	}

	public void setTruck(Truck truck) {
		this.truck = truck;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

}
