package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
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
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=20)
	private int id;
	@Size(min=2,max=20)
	@NotNull
	private String name;
	@Size(min=10,max=10)
	@NotNull
	private String number;
	@Positive
	@NotNull
	@Min(value=50)
	@Max(value=50000)
	private int capacity;
	private String status="Available";
	@OneToOne
	private Carrier carrier;

	public Truck( int id,String name, String number, int capacity, Carrier carrier) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.capacity = capacity;
		this.carrier = carrier;
	}

	public Truck() {
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

}
