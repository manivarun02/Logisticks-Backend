package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Entity
public class Loading {
	@Id
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=150)
	private int id;
	private String date;
	private String time;
	@ManyToOne
	private Address adress;
	public Loading(int id,String date,String time, Address adress) {
		super();
		this.id=id;
		this.date = date;
		this.time = time;
		this.adress = adress;
	}
	public Loading() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Address getAdress() {
		return adress;
	}
	public void setAdress(Address adress) {
		this.adress = adress;
	}
	
	
	

}
