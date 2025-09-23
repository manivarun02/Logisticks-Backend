package com.project.logistick.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class OrderDto {
	private String orderdate;
	@Positive
	@Min(value=1)
	@Max(value=100)
	@NotNull
	private int cargoid;
	@Size(min=2,max=15)
	@NotNull
	private String cargoname;
	@Size(min=5,max=1000000)
	@NotNull
	private String cargodiscription;
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=100)
	private int cargowieght;
	@Positive
	@NotNull
	@Min(value=1)
	@Max(value=100)
	private int carcount;
	@Positive
	@NotNull
	private int loadid;
	@Positive
	@NotNull
	private int unloadid;
	public OrderDto(String orderdate, int cargoid, String cargoname, String cargodiscription,
			int cargowieght, int carcount, int loadid, int unloadid) {
		super();

		this.orderdate = orderdate;
		this.cargoid = cargoid;
		this.cargoname = cargoname;
		this.cargodiscription = cargodiscription;
		this.cargowieght = cargowieght;
		this.carcount = carcount;
		this.loadid = loadid;
		this.unloadid = unloadid;
	}
	public OrderDto() {
		super();
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public int getCargoid() {
		return cargoid;
	}
	public void setCargoid(int cargoid) {
		this.cargoid = cargoid;
	}
	public String getCargoname() {
		return cargoname;
	}
	public void setCargoname(String cargoname) {
		this.cargoname = cargoname;
	}
	public String getCargodiscription() {
		return cargodiscription;
	}
	public void setCargodiscription(String cargodiscription) {
		this.cargodiscription = cargodiscription;
	}
	public int getCargowieght() {
		return cargowieght;
	}
	public void setCargowieght(int cargowieght) {
		this.cargowieght = cargowieght;
	}
	public int getCarcount() {
		return carcount;
	}
	public void setCarcount(int carcount) {
		this.carcount = carcount;
	}
	public int getLoadid() {
		return loadid;
	}
	public void setLoadid(int loadid) {
		this.loadid = loadid;
	}
	public int getUnloadid() {
		return unloadid;
	}
	public void setUnloadid(int unloadid) {
		this.unloadid = unloadid;
	}
	

}
