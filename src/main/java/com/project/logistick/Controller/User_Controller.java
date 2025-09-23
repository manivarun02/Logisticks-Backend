package com.project.logistick.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.logistick.DTO.OrderDto;
import com.project.logistick.DTO.ResponceStucture;
import com.project.logistick.Entitiesclasses.Cargo;
import com.project.logistick.Entitiesclasses.Order;
import com.project.logistick.Services.Cargo_Services;
import com.project.logistick.Services.Order_Services;

import jakarta.validation.Valid;

@RestController
//@RequestMapping("/user")
public class User_Controller {
	
//	//cargo crud operations
	@Autowired
	private Cargo_Services cargosr;
	
	@PostMapping("/savingcargodetails")
	public ResponseEntity<ResponceStucture<Cargo>> cargoDetails(@RequestBody @Valid Cargo cargo)
	{
		return cargosr.saveCargo(cargo);
	}
	
	@GetMapping("/cargofind/{id}")
	public ResponseEntity<ResponceStucture<Cargo>> cargoTrack(@PathVariable int id)
	{
		return cargosr.trackCargo(id);
	}
	
	@DeleteMapping("/deletecargo/{id}")
	public ResponseEntity<ResponceStucture<Cargo>> deleteCargo(@PathVariable int id)
	{
		return cargosr.removeCargo(id);
	}
	
	
	
	//order details with saving  tracing and canceling
	@Autowired
	private Order_Services orderservice;
	//saving
	@PostMapping("/Placingorder")
	public void orderDetails(@RequestBody OrderDto order)
	{
		orderservice.orderPlacing(order);
	}
	
	@GetMapping("/TrackingOrder/{id}")
	public ResponseEntity<ResponceStucture<Order>> trackingOrder(@PathVariable int id)
	{
		return orderservice.tracingOrder(id);
	}
	@PutMapping("cancleorder/{id}")
	public ResponseEntity<ResponceStucture<Order>> cancleOrder(@PathVariable int id)
	{
		return orderservice.cancleOrder(id);
	}

}
