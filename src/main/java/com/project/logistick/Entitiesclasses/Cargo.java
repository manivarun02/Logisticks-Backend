package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Cargo {
	@Id
	@Positive
	@Min(value=1)
	@Max(value=100)
	@NotNull
	private int id;
	@Size(min=2,max=15)
	@NotNull
	private String name;
	@Size(min=5,max=1000000)
	@NotNull
	private String discription;
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=100)
	private int weight;
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=100)
	private int count;
	public Cargo(int id,String name, String discription, int  weight, int count) {
		super();
		this.id=id;
		this.name = name;
		this.discription = discription;
		this.weight = weight;
		this.count = count;
	}
	public Cargo() {
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
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
