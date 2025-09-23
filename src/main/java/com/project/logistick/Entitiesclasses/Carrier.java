package com.project.logistick.Entitiesclasses;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Carrier {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
    @Size(min=2,max=15)
	@NotNull
	private String name;
	@Email
	@NotNull
	private String email;
	@Min(value=6000000000l)
	@Max(value=9999999999l)
	@NotNull
	private long contact;

	public Carrier( String name, String email, long contact) {
		super();
//		this.id = id;
		this.name = name;
		this.email = email;
		this.contact = contact;
	}

	public Carrier() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

}
